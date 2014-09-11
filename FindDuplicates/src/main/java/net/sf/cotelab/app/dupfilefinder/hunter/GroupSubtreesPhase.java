/**
 * 
 */
package net.sf.cotelab.app.dupfilefinder.hunter;

import java.io.File;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.logging.Logger;

import net.sf.cotelab.app.dupfilefinder.beans.PhaseStats;
import net.sf.cotelab.util.collections.HashMultiMap;

/**
 * The "group subtrees" duplicate file finder phase partitions the duplicate
 * files' ancestors into groups of duplicate subtrees.
 * 
 * @author cote
 */
public class GroupSubtreesPhase extends Phase {
    /**
	 * A pair of <tt>File</tt> objects, suitable for use as a key in a
	 * <tt>HashMap</tt>.
	 * @author cote
	 */
	public class FilePair {
		protected File a;
		protected File b;
		
		public FilePair(File file1, File file2) {
			super();
			
			if (file1.compareTo(file2) < 0) {
				a = file1;
				b = file2;
			} else {
				a = file2;
				b = file1;
			}
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj) {
			boolean result = false;
			
			if ((obj != null) && (obj instanceof FilePair)) {
				FilePair o = (FilePair) obj;
				
				result = a.equals(o.a) && b.equals(o.b);
			}
			
			return result;
		}

		/**
		 * @return the a
		 */
		public File getA() {
			return a;
		}

		/**
		 * @return the b
		 */
		public File getB() {
			return b;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			return a.hashCode() + b.hashCode();
		}
	}
	
	public class ParentsOfDups {
		protected int count;
		protected FilePair pair;
		
		public ParentsOfDups(FilePair pair, int count) {
			super();
			
			this.pair = pair;
			this.count = count;
		}

		/**
		 * @return the count
		 */
		public int getCount() {
			return count;
		}

		/**
		 * @return the pair
		 */
		public FilePair getPair() {
			return pair;
		}

		/**
		 * @param count the count to set
		 */
		public void setCount(int count) {
			this.count = count;
		}
	}
	public static final double PROGRESS_WEIGHT = 17100;
    public static final double PROGRESS_WEIGHT_USING_CKSUM = 17539;
	
	@SuppressWarnings("unused")
	private static Logger log =
			Logger.getLogger(GroupSubtreesPhase.class.getName());

	protected HashMap<File, File> child2Parent = new HashMap<File, File>();

	protected HashMap<File, Collection<File>> file2DupFile =
			new HashMap<File, Collection<File>>();
	protected HashMultiMap<File, File> parent2Child =
			new HashMultiMap<File, File>();
	/**
	 * Construct a new object.
	 * 
	 * @param input standard input to the phase.
	 * @param output standard output from the phase.
	 * @param worker the <tt>SwingWorker</tt> in charge.
	 */
	public GroupSubtreesPhase(PhaseStats input, PhaseStats output,
			Future<Void> worker) {
		super(input, output, worker);
        
        progressWeight = PROGRESS_WEIGHT;
        progressWeightUsingCksum = PROGRESS_WEIGHT_USING_CKSUM;

        output.setMeaningfulProgress(false);
	}
	protected void copyInputGroupsToOutput() {
		List<Collection<File>> inputGroups = input.getGroups();
		
		for (Collection<File> inputGroup : inputGroups) {
			output.addGroup(inputGroup);
		}
	}

	protected int getChildCount(File a) {
		int cc = 0;
		
		if (a != null) {
			String[] kids = a.list();
			
			if (kids != null) {
				cc = kids.length;
			}
		}
		
		return cc;
	}
	
	protected boolean identifySubtreesPass(int pass) {
		HashMap<FilePair, ParentsOfDups> podColl =
				new HashMap<FilePair, ParentsOfDups>();
		HashMultiMap<File, File> equivalenceMap =
				new HashMultiMap<File, File>();
		boolean anyChange = true;
		boolean anyNewGroups = false;
		
		
		// identify "parents of duplicates" relationships
		output.setProgressMessage("Group subtrees pass " + pass +
				": identifying parents of duplicates");
		for (File a : parent2Child.keySet()) {
			if (!child2Parent.containsKey(a)) { // don't rescan known dups
				for (File c : parent2Child.get(a)) {
					for (File d : file2DupFile.get(c)) {
						File b = child2Parent.get(d);
						
						if (!child2Parent.containsKey(b)) { // don't rescan dups
							FilePair pair = new FilePair(a, b);
							ParentsOfDups pod = podColl.get(pair);
							
							if (pod == null) {
								pod = new ParentsOfDups(pair, 1);
								podColl.put(pair, pod);
							} else {
								pod.setCount(pod.getCount() + 1);
							}
						}
					}
				}
			}

			output.setPhaseExitStamp(new Date());

	        if (isCancelled()) {
	            return false;
	        }
		}
		
		// identify initial equivalence groups
		// x and y share a group if they have as many "parents of duplicates"
		// relationships as they have children
		output.setProgressMessage("Group subtrees pass " + pass +
				": identifying equivalence pairs");
		for (ParentsOfDups pod : podColl.values()) {
			FilePair fp = pod.getPair();
			File a = fp.getA();
			File b = fp.getB();
			// pod.getCount reflects updates from both subdirs
			int podCount = pod.getCount() / 2;
			int aCount = parent2Child.get(a).size();
			int bCount = parent2Child.get(b).size();
			
			if ((podCount == aCount) && (podCount == bCount)) {
				int aChildCount = getChildCount(a);
				int bChildCount = getChildCount(b);
				
				if ((podCount == aChildCount) && (podCount == bChildCount)) {
					equivalenceMap.put(a, b);
				}
			}

			output.setPhaseExitStamp(new Date());

	        if (isCancelled()) {
	            return false;
	        }
		}
		
		// anneal the groups
		// if x is a dup of y and y is a dup of z then x is a dup of z
		output.setProgressMessage("Group subtrees pass " + pass +
				": annealing into equivalence sets");
		while (anyChange) {
			anyChange = false;
			FilePair newPair = null;
			FilePair oldPair = null;
			
			for (File a : equivalenceMap.keySet()) {
				for (File b : equivalenceMap.get(a)) {
					Collection<File> bMatches = equivalenceMap.get(b);
					
					if (bMatches != null) {
						File c = bMatches.iterator().next();
						
						newPair = new FilePair(a, c);
						oldPair = new FilePair(b, c);
						break;
					}
				}
				
				if (newPair != null) {
					break;
				}
			}
			
			if (newPair != null) {
				equivalenceMap.put(newPair.getA(), newPair.getB());
				equivalenceMap.remove(oldPair.getA(), oldPair.getB());
				anyChange = true;
			}

			output.setPhaseExitStamp(new Date());

	        if (isCancelled()) {
	            return false;
	        }
		}
		
		// publish any newly-found equivalence groups
		output.setProgressMessage("Group subtrees pass " + pass +
				": publishing " + equivalenceMap.entrySet().size() +
				" newly-found equivalence groups");
		child2Parent.clear();
		file2DupFile.clear();
		parent2Child.clear();
		for (File a : equivalenceMap.keySet()) {
			if (!file2DupFile.containsKey(a)) {
				LinkedList<File> group = new LinkedList<File>();
				
				group.add(a);
				group.addAll(equivalenceMap.get(a));
				
				output.addGroup(group);
				updateSimpleRelations(group);
				anyNewGroups = true;
			}

			output.setPhaseExitStamp(new Date());

	        if (isCancelled()) {
	            return false;
	        }
		}
		
		return anyNewGroups;
	}

	// collect ancestor and duplicate relationships
	protected void initializeSimpleRelations() {
		output.setProgressMessage(
				"Group subtrees: initializing simple relationships");
		for (Collection<File> dupGroup : output.getGroups()) {
			updateSimpleRelations(dupGroup);

			output.setPhaseExitStamp(new Date());

	        if (isCancelled()) {
	            break;
	        }
		}
	}
	
	/* (non-Javadoc)
	 * @see net.cote.app.dupfilefinder.hunter.Phase#runPhase()
	 */
	@Override
	protected void runPhase() {
		int pass = 1;
		boolean anyChange = false;
		int groupCount;
		
		copyInputGroupsToOutput();

        if (isCancelled()) {
            return;
        }
		
		initializeSimpleRelations();

        if (isCancelled()) {
            return;
        }
        
        groupCount = file2DupFile.keySet().size();
		
		do {
			int oldGroupCount;
			
			anyChange = false;
			
			identifySubtreesPass(pass);
			
			oldGroupCount = groupCount;
	        groupCount = file2DupFile.keySet().size();

            if (isCancelled()) {
                break;
            }
	        
	        anyChange = (oldGroupCount != groupCount);

			output.setPhaseExitStamp(new Date());

			++pass;
		} while (anyChange);

		output.setPhaseExitStamp(new Date());
	}

	protected void updateSimpleRelations(Collection<File> dupGroup) {
		for (File dup : dupGroup) {
			File parent = dup.getParentFile();
			
			if (parent != null) {
				child2Parent.put(dup, parent);
				parent2Child.put(parent, dup);
			}
			
			file2DupFile.put(dup, dupGroup);
		}
	}

}

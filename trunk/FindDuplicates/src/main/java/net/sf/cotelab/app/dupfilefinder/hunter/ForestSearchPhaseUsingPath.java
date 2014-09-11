/**
 * $Id: ForestSearchPhase.java,v 1.2 2008/04/19 01:16:59 acote Exp $
 * $Log: ForestSearchPhase.java,v $
 * Revision 1.2  2008/04/19 01:16:59  acote
 * Getting closer to being able to report the unreadable files.
 *
 * Revision 1.1  2008/04/16 13:06:48  acote
 * Reorganized source code.
 *
 * Revision 1.3  2008/04/15 17:12:12  acote
 * Cleaned up some javadoc comments.
 *
 * Revision 1.2  2008/04/15 00:54:40  acote
 * Cleared up warnings.
 *
 * Revision 1.1  2008/04/14 12:02:21  acote
 * Renamed packages to make the evolved DupFileFinder implementation the main
 * one, but keep the old one around.
 *
 * Revision 1.6  2008/04/12 13:43:23  acote
 * Enabled cancellation of the forest search phase.
 *
 * Revision 1.5  2008/04/12 13:17:17  acote
 * Provided the phases access to the isCancelled() method of SwingWorker.
 *
 * Revision 1.4  2008/04/11 20:01:11  acote
 * Checkpoint.
 *
 * Revision 1.3  2008/04/11 18:10:14  acote
 * Checkpoint.
 *
 * Revision 1.2  2008/04/10 19:50:32  acote
 * Still evolving.
 *
 * Revision 1.1  2008/04/10 18:59:30  acote
 * Still evolving.
 *
 */
package net.sf.cotelab.app.dupfilefinder.hunter;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.logging.Logger;

import net.sf.cotelab.app.dupfilefinder.beans.PhaseStats;

/**
 * The "forest search" duplicate file finder phase identifies the files to be
 * examined for duplication.
 * <p>
 * This phase ignores <tt>input</tt>, so it is useful only as the first phase in
 * a pipeline.
 * 
 * @author cote
 */
public class ForestSearchPhaseUsingPath extends Phase {
	@SuppressWarnings("unused")
	private static final Logger log =
			Logger.getLogger(ForestSearchPhaseUsingPath.class.getName());

    public class FileCollector extends SimpleFileVisitor<Path> {
    	@Override
    	public FileVisitResult preVisitDirectory(
    			Path dir, BasicFileAttributes attrs) throws IOException {
    		output.identifyDirectory(dir.toFile());
    		output.setProgressMessage(dir.toString());
    		output.setPhaseExitStamp(new Date());
    		
//    		log.info("fileKey=" + attrs.fileKey() + "; dir=" + dir);
    		
    		return FileVisitResult.CONTINUE;
    	}

    	@Override
    	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
    			throws IOException {
    		File asFile = file.toFile();
    		
    		if (asFile.canRead()) {
                output.addRegularFile(asFile);
                readableFiles.add(asFile);
    		} else {
                output.identifyUnreadableFile(asFile);
    		}
    		
//    		log.info("fileKey=" + attrs.fileKey() + "; file=" + file);
//    		if (!attrs.isRegularFile()) {
//    			String type = "UNKNOWN";
//    			
//    			if (attrs.isDirectory()) {
//    				type = "DIR";
//    			}
//    			
//    			if (attrs.isOther()) {
//    				type = "OTHER";
//    			}
//    			
//    			if (attrs.isSymbolicLink()) {
//    				Path referent = Files.readSymbolicLink(file);
//    				
//    				type = "SYMLINK";
//    				
//    				try {
//    					referent = Files.readSymbolicLink(file);
//        				
//        				if (referent == null) {
//        					type = type + "(" + referent + ")";
//        				} else {
//        					type = type + "(<null>)";
//        				}
//    				} catch (Exception e) {
//    					type = type + "(" + e.getMessage() + ")";
//    				}
//    			}
//    			
//    			log.info("type=" + type + "; file=" + file);
//    		}
    		
    		return FileVisitResult.CONTINUE;
    	}

    	@Override
    	public FileVisitResult visitFileFailed(Path file, IOException exc)
    			throws IOException {
            output.identifyUnreadableFile(file.toFile());
            
    		return FileVisitResult.CONTINUE;
    	}
    }
    
    public static final double PROGRESS_WEIGHT = 156350;
    public static final double PROGRESS_WEIGHT_USING_CKSUM = 7096;

    /**
     * The readable regular files enumerated by the search.
     */
    protected List<File> readableFiles = new LinkedList<File>();

    /**
     * The roots of the forest to search.
     */
    protected Collection<File> roots;
    /**
     * Construct a new object.
     * 
     * @param input
     *            standard input to the phase.
     * @param output
     *            standard output from the phase.
     * @param worker
     *            the caller.
     * @param roots
     *            roots of the forest to search.
     */
    public ForestSearchPhaseUsingPath(PhaseStats input, PhaseStats output,
    		Future<Void> worker, Collection<File> roots) {
        super(input, output, worker);

        this.roots = roots;
        progressWeight = PROGRESS_WEIGHT;
        progressWeightUsingCksum = PROGRESS_WEIGHT_USING_CKSUM;
    }

    /**
     * Enumerate the files and directories in the subtree rooted at
     * <tt>root</tt>.
     * @param root the root of the subtree to be enumerated.
     */
    // This is a recursive enumeration of a directory tree.
    protected void enumerate(File root) {
        if (isCancelled()) {
            return;
        }

        output.setProgressMessage(root.toString());
		output.setPhaseExitStamp(new Date());

//        if (root.canRead()) {
//            if (root.isDirectory()) {
//                try {
//                    File[] kids;
//
//                    output.setProgressMessage(root.toString());
//
//                    kids = root.listFiles();
//                    for (File kid : kids) {
//                        enumerate(kid);
//                    }
//
//                    output.identifyDirectory(root);
//                } catch (Throwable t) {
//                    //t.printStackTrace(System.err);
//                    output.identifyUnreadableFile(root);
//                    //System.out.println(root.toString()); System.exit(1);
//                }
//            } else {
//                readableFiles.add(root);
//                output.addRegularFile(root);
//            }
//        } else {
//            output.identifyUnreadableFile(root);
//        }
		FileCollector collector = new FileCollector();
		
		try {
			Files.walkFileTree(root.toPath(), collector);
		} catch (IOException e) {
			System.err.println("Caught while examining " + root + ": " + e);
			e.printStackTrace(System.err);
		}
    }
    
    /* (non-Javadoc)
     * @see net.cote.app.dff2.Phase#runPhase()
     */
    @Override
    protected void runPhase() {
        try {
        	int rootCount = roots.size();
        	int nbrRootsExamined = 0;
        	
        	output.setPercentComplete(0);
        	output.setMeaningfulProgress(rootCount > 1);
        	
            output.addGroup(readableFiles);

            for (File root : roots) {
                enumerate(root);
                
                ++nbrRootsExamined;
                output.setPercentComplete((nbrRootsExamined * 100) / rootCount);
            }

            output.setProgressMessage("Finished.");
        } catch (Throwable t) {
            t.printStackTrace(System.err);
        }
    }
}

/**
 * $Id: GroupByChecksumPhase.java,v 1.1 2008/04/16 13:06:48 acote Exp $
 * $Log: GroupByChecksumPhase.java,v $
 * Revision 1.1  2008/04/16 13:06:48  acote
 * Reorganized source code.
 *
 * Revision 1.1  2008/04/14 12:02:22  acote
 * Renamed packages to make the evolved DupFileFinder implementation the main
 * one, but keep the old one around.
 *
 * Revision 1.4  2008/04/12 13:53:56  acote
 * Enabled cancellation of the group by checksum phase.
 *
 * Revision 1.3  2008/04/12 13:17:17  acote
 * Provided the phases access to the isCancelled() method of SwingWorker.
 *
 * Revision 1.2  2008/04/11 21:20:51  acote
 * Corrected failure to extract unique files from phase results and failure to update the relevant display.
 *
 * Revision 1.1  2008/04/11 21:04:33  acote
 * Grouping by checksum incorporated.
 *
 */
package net.sf.cotelab.app.dupfilefinder.hunter;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.Future;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

import net.sf.cotelab.app.dupfilefinder.beans.PhaseStats;
import net.sf.cotelab.util.collections.HashMultiMap;

/**
 * The "group by checksum" duplicate file finder phase partitions the input
 * files into groups by checksum of the first <tt>SAMPLE_SIZE</tt> bytes of each
 * file.
 * 
 * @author cote
 */
public class GroupByChecksumPhase extends Phase {
	public static final int BUFFER_SIZE = 16384;
	public static final double PROGRESS_WEIGHT = 0;
    public static final double PROGRESS_WEIGHT_USING_CKSUM = 23433878;
	
	public static final long SAMPLE_SIZE = 32768;

	public GroupByChecksumPhase(PhaseStats input, PhaseStats output,
			Future<Void> worker) {
		super(input, output, worker);

        progressWeight = PROGRESS_WEIGHT;
        progressWeightUsingCksum = PROGRESS_WEIGHT_USING_CKSUM;
		
		output.setMeaningfulProgress(false);
    }
    protected long calcSampleChecksum(File aFile) throws IOException {
		byte[] buffer = new byte[BUFFER_SIZE];
		long bytesRemaining = SAMPLE_SIZE;
		BufferedInputStream bis =
				new BufferedInputStream(new FileInputStream(aFile));
        Checksum sum = new CRC32();
        
        while (bytesRemaining > 0) {
        	int bytesToTry = ((long) BUFFER_SIZE < bytesRemaining) ?
        			BUFFER_SIZE : (int) bytesRemaining;
        	int bytesRead = bis.read(buffer, 0, bytesToTry);
        	
        	if (bytesRead < 0) {
        		break;
        	}
        	
        	sum.update(buffer, 0, bytesRead);
        	
        	bytesRemaining -= bytesRead;
        }
        
        bis.close();
        
		return sum.getValue();
	}

	/* (non-Javadoc)
	 * @see net.cote.app.dff2.Phase#runPhase()
	 */
	@Override
	protected void runPhase() {
		long filesToGroup = 0;
		long filesGrouped = 0;
		
		for (Collection<File> files : input.getGroups()) {
			filesToGroup += files.size();
		}
		
		if (filesToGroup > 0) {
			output.setPercentComplete(0);
			output.setMeaningfulProgress(true);
			
			for (Collection<File> files : input.getGroups()) {
				HashMultiMap<Long, File> filesByChecksum =
					new HashMultiMap<Long, File>();
				
				for (File aFile : files) {
		            long fileLen = aFile.length();
		            Long cksum;

					if (isCancelled()) {
						return;
					}
					
		            output.setProgressMessage(
		            		aFile.getPath() + " (" + fileLen + " bytes)");
		            
		            try {
						cksum = calcSampleChecksum(aFile);
						filesByChecksum.put(cksum, aFile);
					} catch (IOException e) {
						output.getRegularFiles().remove(aFile);
						output.getUnreadableFilesIdentified().add(aFile);
					}
		            
		            output.setNbrBytesConsidered(
		            		fileLen + output.getNbrBytesConsidered());

		            ++filesGrouped;
					output.setPercentComplete(
							(int) ((filesGrouped * 100) / filesToGroup));
				}

				output.setPhaseExitStamp(new Date());
				
				for (Long key : filesByChecksum.keySet()) {
					Collection<File> val = filesByChecksum.get(key);
					
					if (val.size() > 1) {
						output.addGroup(val);
					} else if (val.size() == 1) {
						File aFile = val.iterator().next();
						
						output.identifyUniqueFile(aFile);
					}
				}

				output.setPhaseExitStamp(new Date());
			}
		}

		output.setPercentComplete(100);
	}
}

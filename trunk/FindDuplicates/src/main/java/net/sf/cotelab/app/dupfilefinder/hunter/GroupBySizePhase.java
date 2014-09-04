/**
 * $Id: GroupBySizePhase.java,v 1.1 2008/04/16 13:06:48 acote Exp $
 * $Log: GroupBySizePhase.java,v $
 * Revision 1.1  2008/04/16 13:06:48  acote
 * Reorganized source code.
 *
 * Revision 1.1  2008/04/14 12:02:22  acote
 * Renamed packages to make the evolved DupFileFinder implementation the main
 * one, but keep the old one around.
 *
 * Revision 1.4  2008/04/12 13:51:14  acote
 * Enabled cancellation of the group by size phase.
 *
 * Revision 1.3  2008/04/12 13:17:17  acote
 * Provided the phases access to the isCancelled() method of SwingWorker.
 *
 * Revision 1.2  2008/04/11 21:20:51  acote
 * Corrected failure to extract unique files from phase results and failure to update the relevant display.
 *
 * Revision 1.1  2008/04/11 20:01:11  acote
 * Checkpoint.
 *
 */
package net.sf.cotelab.app.dupfilefinder.hunter;

import java.io.File;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.Future;

import net.sf.cotelab.app.dupfilefinder.beans.PhaseStats;
import net.sf.cotelab.util.collections.HashMultiMap;

/**
 * The "group by size" duplicate file finder phase partitions the input files
 * into groups by file size.
 * 
 * @author cote
 */
public class GroupBySizePhase extends Phase {
    public static final double PROGRESS_WEIGHT = 136434;
    public static final double PROGRESS_WEIGHT_USING_CKSUM = 148354;

    public GroupBySizePhase(PhaseStats input, PhaseStats output,
    		Future<Void> worker) {
        super(input, output, worker);
        
        progressWeight = PROGRESS_WEIGHT;
        progressWeightUsingCksum = PROGRESS_WEIGHT_USING_CKSUM;

        output.setMeaningfulProgress(false);
    }

    /* (non-Javadoc)
     * @see net.cote.app.dff2.Phase#runPhase()
     */
    @Override
    protected void runPhase() {
        HashMultiMap<Long, File> filesBySize = new HashMultiMap<Long, File>();
        int totalFilesToGroup = 0;
        int totalFilesGrouped = 0;

        for (Collection<File> files : input.getGroups()) {
            totalFilesToGroup += files.size();
        }

        if (totalFilesToGroup > 0) {
            output.setPercentComplete(0);
            output.setMeaningfulProgress(true);

            for (Collection<File> files : input.getGroups()) {
                for (File aFile : files) {
                    long fileLen = aFile.length();
                    Long size = new Long(fileLen);

                    output.setProgressMessage(
                            "Examining " + aFile.getPath() +
                            " (" + fileLen + " bytes)");
                    output.setNbrBytesConsidered(
                            fileLen + output.getNbrBytesConsidered());

                    filesBySize.put(size, aFile);

                    ++totalFilesGrouped;
                    output.setPercentComplete(
                            (totalFilesGrouped * 100) / totalFilesToGroup);

    				output.setPhaseExitStamp(new Date());

                    if (isCancelled()) {
                        return;
                    }
                }
            }
        }

        for (Long key : filesBySize.keySet()) {
            Collection<File> val = filesBySize.get(key);

            if (isCancelled()) {
                return;
            }

            if (val.size() > 1) {
                output.addGroup(val);
            } else if (val.size() == 1) {
                File aFile = val.iterator().next();

                output.identifyUniqueFile(aFile);
            }
        }
    }
}

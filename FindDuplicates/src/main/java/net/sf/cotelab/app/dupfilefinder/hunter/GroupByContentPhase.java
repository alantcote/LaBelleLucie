/**
 * $Id: GroupByContentPhase.java,v 1.3 2011/11/04 19:42:35 acote Exp $
 * $Log: GroupByContentPhase.java,v $
 * Revision 1.3  2011/11/04 19:42:35  acote
 * better buffer management when comparing files.
 *
 * Revision 1.2  2011/11/04 18:18:02  acote
 * enlarged BufferedInputStream buffers to improve file comparison speed.
 *
 * Revision 1.1  2008/04/16 13:06:48  acote
 * Reorganized source code.
 *
 * Revision 1.2  2008/04/15 00:54:40  acote
 * Cleared up warnings.
 *
 * Revision 1.1  2008/04/14 12:02:22  acote
 * Renamed packages to make the evolved DupFileFinder implementation the main
 * one, but keep the old one around.
 *
 * Revision 1.4  2008/04/12 13:57:21  acote
 * Enabled cancellation of the group by content phase.
 *
 * Revision 1.3  2008/04/12 13:17:17  acote
 * Provided the phases access to the isCancelled() method of SwingWorker.
 *
 * Revision 1.2  2008/04/11 21:50:55  acote
 * Grouping by content comparison incorporated.
 *
 * Revision 1.1  2008/04/11 21:20:51  acote
 * Corrected failure to extract unique files from phase results and failure to update the relevant display.
 *
 */
package net.sf.cotelab.app.dupfilefinder.hunter;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.sf.cotelab.app.dupfilefinder.beans.PhaseStats;
import net.sf.cotelab.util.collections.HashMultiMap;
import net.sf.cotelab.util.io.Streams;

/**
 * The "group by content" duplicate file finder phase partitions the input files
 * into groups by identical content.
 * 
 * @author cote
 */
public class GroupByContentPhase extends Phase {

    public static final int BIS_BUFFER_SIZE = 256 * 1024;
    public static final int NWCES_BUFFER_SIZE = 1024;
    public static final double PROGRESS_WEIGHT = 623905;
    public static final double PROGRESS_WEIGHT_USING_CKSUM = 119320;

    public GroupByContentPhase(PhaseStats input, PhaseStats output,
    		Future<Void> worker) {
        super(input, output, worker);

        progressWeight = PROGRESS_WEIGHT;
        progressWeightUsingCksum = PROGRESS_WEIGHT_USING_CKSUM;

        output.setMeaningfulProgress(false);
    }

    public Collection<Collection<BufferedInputStream>> nWayCompareEqualStreams(
    		Collection<BufferedInputStream> src) {
        ArrayList<Collection<BufferedInputStream>> retValue =
                new ArrayList<Collection<BufferedInputStream>>();
        HashMultiMap<Integer, BufferedInputStream> stepResult =
                new HashMultiMap<Integer, BufferedInputStream>();
        Set<Integer> stepKeys;
        int srcSize = src.size();
        Collection<Collection<BufferedInputStream>> recursiveResult;
        BufferedInputStream srcArray[] =
                src.<BufferedInputStream>toArray(
                new BufferedInputStream[src.size()]);

        if (srcSize == 0) {
            return retValue;
        }

        if (srcSize == 1) {
            retValue.add(src);

            return retValue;
        }

        // skip over the matching prefixes.
        if (streamsMatch(src)) {
            retValue.add(src);

            return retValue;
        }

        // OK.  So they're not identical.  Back up and analyze byte for byte.
        for (BufferedInputStream bis : srcArray) {
            try {
                bis.reset();
            } catch (IOException ex) {
                Logger.getLogger(Streams.class.getName()).log(
                        Level.SEVERE, null, ex);
            }
        }

        while (true) {
            int theByte = 0;

            stepResult.clear();

            for (BufferedInputStream is : srcArray) {
                Integer theInteger;

                try {
                    theByte = is.read();
                } catch (IOException ex) {
                    theByte = -1;
                }

                theInteger = new Integer(theByte);
                stepResult.put(theInteger, is);
            }

            stepKeys = stepResult.keySet();
            if (stepKeys.size() != 1) {
                break;
            }

            if (theByte < 0) {
                retValue.add(src);

                return retValue;
            }
        }

        // if we get here, there are multiple groups in stepResult

        for (Integer key : stepResult.keySet()) {
            recursiveResult = nWayCompareEqualStreams(stepResult.get(key));
            retValue.addAll(recursiveResult);
        }

        return retValue;
    }
    
    protected Collection<Collection<File>> nWayCompareEqualFiles(
    		Collection<File> fileColl) {
        List<Collection<File>> retValue = new ArrayList<Collection<File>>();
        HashMap<BufferedInputStream, File> inputStream2File =
                new HashMap<BufferedInputStream, File>();
        ArrayList<BufferedInputStream> inputStreams =
                new ArrayList<BufferedInputStream>();
        Collection<Collection<BufferedInputStream>> nwcesRC;

        for (File aFile : fileColl) {
            long fileLen = aFile.length();
            int bisBufferSize = BIS_BUFFER_SIZE;
            BufferedInputStream is = null;

            if (fileLen < bisBufferSize) {
                bisBufferSize = (int) fileLen + 1;
            }

            try {
                is = new BufferedInputStream(
                        new FileInputStream(aFile), bisBufferSize);
                inputStream2File.put(is, aFile);
                inputStreams.add(is);
            } catch (IOException e) {
                output.getUnreadableFilesIdentified().add(aFile);
                output.getRegularFiles().remove(aFile);
            }
        }

//        nwcesRC = Streams.nWayCompareEqualStreams(inputStreams);
        nwcesRC = nWayCompareEqualStreams(inputStreams);

        for (Collection<BufferedInputStream> isColl : nwcesRC) {
            LinkedList<File> fileList = new LinkedList<File>();

            for (InputStream is : isColl) {
                fileList.add(inputStream2File.get(is));
                try {
                    is.close();
                } catch (IOException e) {
                }
            }

            retValue.add(fileList);
        }

        return retValue;
    }
    
    /* (non-Javadoc)
     * @see net.cote.app.dff2.Phase#runPhase()
     */
    @Override
    protected void runPhase() {
        long bytesToGroup = 0;
        long bytesGrouped = 0;
        List<Collection<File>> groups = input.getGroups();
        
        Collections.shuffle(groups);

        for (Collection<File> files : groups) {
            if (!files.isEmpty()) {
                File aFile = files.iterator().next();

                bytesToGroup += aFile.length() * files.size();
            }
        }

        if (bytesToGroup > 0) {
            output.setPercentComplete(0);
            output.setMeaningfulProgress(true);

            for (Collection<File> files : groups) {
                long bytesProcessed;
                int count = files.size();
                File aFile = files.iterator().next();
                long fileLen = aFile.length();
                Collection<Collection<File>> filesGroupedByContent;

                output.setProgressMessage(
                        "Comparing " + count + " files at "
                        + fileLen + " bytes each");

				output.setPhaseExitStamp(new Date());

                filesGroupedByContent = nWayCompareEqualFiles(files);

                if (isCancelled()) {
                    return;
                }

                for (Collection<File> val : filesGroupedByContent) {
                    if (val.size() > 1) {
                        output.addGroup(val);
                    } else if (val.size() == 1) {
                        File bFile = val.iterator().next();

                        output.identifyUniqueFile(bFile);
                    }
                }

                bytesProcessed = fileLen * count;
                output.setNbrBytesConsidered(
                        bytesProcessed + output.getNbrBytesConsidered());

                bytesGrouped += bytesProcessed;
                output.setPercentComplete(
                        (int) ((bytesGrouped * 100) / bytesToGroup));
            }
        }
    }

    /**
     * Determine whether a collection of equal-length streams contain identical
     * data.
     * <p>
     * There are 2 notable side-effects: The streams are consumed to the end, if
     * they match, and the streams are marked at suitable points at which to
     * begin byte-by-byte comparison, if they don't match.
     * @param src the collection of equal-length streams.
     * @return the truth-value of the expression, "the streams contain identical
     * 		data".
     */
    protected boolean streamsMatch(Collection<BufferedInputStream> src) {
        int srcSize = src.size();
        BufferedInputStream srcArray[] =
                src.<BufferedInputStream>toArray(
                new BufferedInputStream[srcSize]);
        byte[] masterBuffer = new byte[NWCES_BUFFER_SIZE];
        boolean buffersMatch = true;

        do {
            int masterCount = 0;

            for (BufferedInputStream bis : srcArray) {
                bis.mark(2 * NWCES_BUFFER_SIZE);
            }

            try {
                masterCount = srcArray[0].read(
                        masterBuffer, 0, NWCES_BUFFER_SIZE);
            } catch (IOException e) {
                buffersMatch = false;
            }

            if (masterCount < 0) {
            	// this means we hit end of file.
            	// we already know all the streams are the same length.
            	// there has been no mismatch to this point.
            	// thus, the streams have matched, entirely.

                return true;
            }

            for (int i = 1; buffersMatch && (i < srcArray.length); ++i) {
                int slaveCount = 0;
                byte[] slaveBuffer = new byte[masterCount];

                try {
                    slaveCount = srcArray[i].read(slaveBuffer, 0, masterCount);
                    buffersMatch =
                            (slaveCount == masterCount)
                            && Arrays.equals(slaveBuffer, masterBuffer);
                } catch (IOException e) {
                    buffersMatch = false;
                }

            }
        } while (buffersMatch);
        
    	return false;
    }
}

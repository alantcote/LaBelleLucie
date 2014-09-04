/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sf.cotelab.util.io;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.sf.cotelab.util.collections.HashMultiMap;

/**
 *
 * @author Owner
 */
public abstract class Streams {
    public static final int BUFFER_SIZE = 1024;

    public static Collection<Collection<BufferedInputStream>> nWayCompareEqualStreams(
            Collection<BufferedInputStream> src) {
        ArrayList<Collection<BufferedInputStream>> retValue =
                new ArrayList<Collection<BufferedInputStream>>();
        HashMultiMap<Integer, BufferedInputStream> stepResult =
                new HashMultiMap<Integer, BufferedInputStream>();
        Set<Integer> stepKeys;
        int srcSize = src.size();
        Collection<Collection<BufferedInputStream>> recursiveResult;
        boolean buffersMatch = true;
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
        
        // If the inputs are identical, we can save time by reading in blocks.
        do {
            int masterCount = 0;
            byte[] masterBuffer = new byte[BUFFER_SIZE];
            
            for (BufferedInputStream bis : srcArray) {
                bis.mark(2 * BUFFER_SIZE);
            }
            
            try {
                masterCount = srcArray[0].read(masterBuffer, 0, BUFFER_SIZE);
            } catch (IOException e) {
                buffersMatch = false;
            }
            
            if (masterCount < 0) {
                buffersMatch = false;
            }
            
            for (int i = 1; buffersMatch && (i < srcArray.length); ++i) {
                int slaveCount = 0;
                byte[] slaveBuffer = new byte[masterCount];

                try {
                    slaveCount = srcArray[i].read(masterBuffer, 0, masterCount);
                    buffersMatch =
                            (slaveCount == masterCount) &&
                            Arrays.equals(slaveBuffer, masterBuffer);
                } catch (IOException e) {
                    buffersMatch = false;
                }
                
            }
        } while (buffersMatch);
            
        for (BufferedInputStream bis : srcArray) {
            try {
                bis.reset();
            } catch (IOException ex) {
                Logger.getLogger(Streams.class.getName()).log(
                        Level.SEVERE, null, ex);
            }
        }

        // OK.  So they're not identical.  Back up and analyze byte for byte.
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
}

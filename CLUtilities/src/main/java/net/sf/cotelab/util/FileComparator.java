/*
 * FileComparator.java
 * 
 * Created on Nov 29, 2007, 11:27:26 AM
 */

package net.sf.cotelab.util;

import java.io.File;
import java.util.Comparator;

import javax.swing.filechooser.FileSystemView;

import net.sf.cotelab.util.io.DecoratedFile;

/**
 * A <code>Comparator</code> for <code>File</code>s.
 * Objects of this class collate their arguments as follows:
 * <ol>
 * <li><code>File</code>s that are not in filesystems, have no parents, or for which
 *     <code>
 *         !file.getAbsoluteFile.getParentFile().equals(file.getParentFile())
 *     </code>.
 * </ol>
 * @author us80653h
 */
public class FileComparator implements Comparator<File> {
    @Override
    public int compare(File o1, File o2) {
        int retValue = 0;
        File file1 = o1;
        File file2 = o2;
        boolean o1IsSpecial;
        boolean o2IsSpecial;
        boolean o1IsDir;
        boolean o2IsDir;
        
        if (o1 instanceof DecoratedFile) {
            file1 = ((DecoratedFile) o1).getDelegate();
        }
        
        if (o2 instanceof DecoratedFile) {
            file2 = ((DecoratedFile) o2).getDelegate();
        }
        
        o1IsSpecial = isSpecial(file1);
        o2IsSpecial = isSpecial(file2);
        o1IsDir = file1.isDirectory();
        o2IsDir = file2.isDirectory();
        
        if ((retValue == 0) && o1IsSpecial && !o2IsSpecial) {
            retValue = -1;
        }
        
        if ((retValue == 0) && o2IsSpecial && !o1IsSpecial) {
            retValue = 1;
        }
        
        if ((retValue == 0) && o1IsDir && !o2IsDir) {
            retValue = -1;
        }
        
        if ((retValue == 0) && o2IsDir && !o1IsDir) {
            retValue = 1;
        }
        
        if (retValue == 0) {
            retValue = normalCompare(o1, o2);
        }
        
        /*
        System.out.println();
        System.out.println("o1IsSpecial = " + o1IsSpecial +
                "; o1IsDir = " + o1IsDir +
                "; o1 = " + o1);
        System.out.println("o2IsSpecial = " + o2IsSpecial +
                "; o2IsDir = " + o2IsDir +
                "; o2 = " + o2);
        System.out.println("retValue = " + retValue);
        System.out.flush();
        */
        
        return retValue;
    }
    
    protected boolean isSpecial(File f) {
        FileSystemView fileSystemView = FileSystemView.getFileSystemView();
        File abs = null;
        File absPar = null;
        File par = null;
        
        if (!fileSystemView.isFileSystem(f)) {
            return true;
        }
        
        abs = f.getAbsoluteFile();
        if (abs != null) {
            absPar = abs.getParentFile();
        }
        par = f.getParentFile();
        
        if (absPar == null) {
            if (par == null) {
                return false;
            } else {
                return true;
            }
        } else {
            if (par == null) {
                return true;
            } else {
                return !par.equals(absPar);
            }
        }
    }

    /**
     * Perform the usual comparison of values.
     * This method is used iff the directory status of both arguments match.
     * This method delegates to <code>f1.compareTo(f2)</code>.
     * @param f1 the first file to be compared.
     * @param f2 the second file to be compared.
     * @return a negative integer, zero, or a positive integer as the first
     *         argument is less than, equal to, or greater than the second.
     */
    protected int normalCompare(File f1, File f2) {
        return f1.compareTo(f2);
    }
}

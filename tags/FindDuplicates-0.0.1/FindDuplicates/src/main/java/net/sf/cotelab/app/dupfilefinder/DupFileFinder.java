/*
 * DupFileFinder.java
 *
 * Created on November 29, 2007, 11:50 AM
 */
package net.sf.cotelab.app.dupfilefinder;

import net.sf.cotelab.app.dupfilefinder.gui.DFFFrame;

/**
 * An application that locates duplicate files in user-specified areas of the
 * filesystem.
 * @author Cote'
 */
public class DupFileFinder {
    /**
     * @param args the command line arguments are not used.
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                DFFFrame dff = new DFFFrame();
                
                dff.setVisible(true);
                dff.pack();
            }
        });
    }
}

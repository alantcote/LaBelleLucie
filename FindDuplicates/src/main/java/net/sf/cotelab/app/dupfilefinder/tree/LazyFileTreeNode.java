/*
 * LazyFileTreeNode.java
 *
 * Created on February 6, 2002, 10:35 AM
 */

package net.sf.cotelab.app.dupfilefinder.tree;

import java.io.File;
import java.util.Arrays;

import javax.swing.tree.DefaultTreeModel;

import net.sf.cotelab.util.FileComparator;
import net.sf.cotelab.util.swing.tree.ActiveFileTreeNode;

/**
 * An <tt>ActiveFileTreeNode</tt> that sorts its children.
 * @author  acote
 */
public class LazyFileTreeNode extends ActiveFileTreeNode {
    /**
     * Serialization support
     */
    private static final long serialVersionUID = 1L;

    /**
     * Construct a new object to represent the parent of nodes representing the
     * <tt>File</tt>s returned from <tt>File.listRoots()</tt>.
     * @param containingModel the <tt>DefaultTreeModel</tt> of which the new
     *                        object will be a part.
     */
    public LazyFileTreeNode(DefaultTreeModel containingModel) {
        super(containingModel);
    }
    
    /**
     * Construct a new object.
     * @param containingModel the <tt>DefaultTreeModel</tt> of which the new
     *                        object will be a part.
     * @param file the <tt>File</tt> to be represented by the new object, or
     *             <tt>null</tt>, which causes this object to represent the
     *             parent of nodes representing the <tt>File</tt>s returned from
     *             <tt>File.listRoots()</tt>.
     */
    public LazyFileTreeNode(DefaultTreeModel containingModel, File file) {
        super(containingModel, file);
    }

    @Override
    protected File[] listKidFiles() {
        File[] rawKids = super.listKidFiles();
        
        if (rawKids != null) {
            Arrays.<File>sort(rawKids, new FileComparator());
        }

        return rawKids;
    }

    @Override
    protected ActiveFileTreeNode newNode(File f) {
        LazyFileTreeNode lftn = new LazyFileTreeNode(containingModel, f);
        
        return lftn;
    }
}

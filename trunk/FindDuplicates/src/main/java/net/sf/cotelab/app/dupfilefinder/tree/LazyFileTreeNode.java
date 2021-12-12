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
 * An <code>ActiveFileTreeNode</code> that sorts its children.
 * @author  acote
 */
public class LazyFileTreeNode extends ActiveFileTreeNode {
    /**
     * Serialization support
     */
    private static final long serialVersionUID = 1L;

    /**
     * Construct a new object to represent the parent of nodes representing the
     * <code>File</code>s returned from <code>File.listRoots()</code>.
     * @param containingModel the <code>DefaultTreeModel</code> of which the new
     *                        object will be a part.
     */
    public LazyFileTreeNode(DefaultTreeModel containingModel) {
        super(containingModel);
    }
    
    /**
     * Construct a new object.
     * @param containingModel the <code>DefaultTreeModel</code> of which the new
     *                        object will be a part.
     * @param file the <code>File</code> to be represented by the new object, or
     *             <code>null</code>, which causes this object to represent the
     *             parent of nodes representing the <code>File</code>s returned from
     *             <code>File.listRoots()</code>.
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

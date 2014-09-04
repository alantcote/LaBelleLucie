/*
 * ActiveFileTreeNode.java
 *
 * Created on November 6, 2002, 4:32 PM
 */
package net.sf.cotelab.util.swing.tree;

import java.io.File;
import java.util.Enumeration;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

/**
 * A <tt>DefaultMutableTreeNode</tt> that represents a <tt>File</tt>.
 * Objects of this class create their child nodes dynamically, as needed, based
 * upon the children of the <tt>File</tt>.
 * @author  acote
 */
public class ActiveFileTreeNode
        extends DefaultMutableTreeNode {
    /**
     * Serialization support.
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * The <tt>DefaultTreeModel</tt> of which this object is a part.
     */
    protected DefaultTreeModel containingModel = null;
    
    /**
     * The file around which this node is wrapped.
     */
    protected File file = null;
    protected boolean kidsLoaded = false;
    protected boolean loadingKids = false;

    /**
     * Construct a new object to represent the parent of nodes representing the
     * <tt>File</tt>s returned from <tt>File.listRoots()</tt>.
     * @param containingModel the <tt>DefaultTreeModel</tt> of which the new
     *                        object will be a part.
     */
    public ActiveFileTreeNode(DefaultTreeModel containingModel) {
        this(containingModel, null);
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
    public ActiveFileTreeNode(DefaultTreeModel containingModel, File file) {
        super(file);

        this.containingModel = containingModel;
        this.file = file;

        setAllowsChildren((file == null) || file.isDirectory());
    }

    /** Removes <code>newChild</code> from its parent and makes it a child of
     * this node by adding it to the end of this node's child array.
     *
     * @see		#insert
     * @param	newChild	node to add as a child of this node
     * @exception	IllegalArgumentException    if <code>newChild</code>
     * 						is null or not an instance of
     *                                          <code>ActiveFileTreeNode</code>
     * @exception	IllegalStateException	if this node does not allow
     * 						children
     *
     */
    public void add(MutableTreeNode newChild) {
        loadKidsIfNeeded();
        
        super.add(newChild);
    }

    /** Creates and returns a forward-order enumeration of this node's
     * children.  Modifying this node's child array invalidates any child
     * enumerations created before the modification.
     *
     * @return	an Enumeration of this node's children
     *
     */
    @SuppressWarnings("unchecked")
    public Enumeration<TreeNode> children() {
        Enumeration<TreeNode> retValue;

        loadKidsIfNeeded();
        retValue = super.children();
        return retValue;
    }

    /** Returns the child in this node's child array that immediately
     * follows <code>aChild</code>, which must be a child of this node.  If
     * <code>aChild</code> is the last child, returns null.  This method
     * performs a linear search of this node's children for
     * <code>aChild</code> and is O(n) where n is the number of children; to
     * traverse the entire array of children, use an enumeration instead.
     *
     * @see		#children
     * @exception	IllegalArgumentException if <code>aChild</code> is
     * 					null or is not a child of this node
     * @return	the child of this node that immediately follows
     * 		<code>aChild</code>
     *
     */
    public TreeNode getChildAfter(TreeNode aChild) {
        TreeNode retValue;

        loadKidsIfNeeded();
        retValue = super.getChildAfter(aChild);
        return retValue;
    }

    /** Returns the child at the specified index in this node's child array.
     *
     * @param	index	an index into this node's child array
     * @exception	ArrayIndexOutOfBoundsException	if <code>index</code>
     * 						is out of bounds
     * @return	the TreeNode in this node's child array at  the specified index
     *
     */
    public TreeNode getChildAt(int index) {
        TreeNode retValue;

        loadKidsIfNeeded();
        retValue = super.getChildAt(index);
        return retValue;
    }

    /** Returns the child in this node's child array that immediately
     * precedes <code>aChild</code>, which must be a child of this node.  If
     * <code>aChild</code> is the first child, returns null.  This method
     * performs a linear search of this node's children for <code>aChild</code>
     * and is O(n) where n is the number of children.
     *
     * @exception	IllegalArgumentException if <code>aChild</code> is null
     * 						or is not a child of this node
     * @return	the child of this node that immediately precedes
     * 		<code>aChild</code>
     *
     */
    public TreeNode getChildBefore(TreeNode aChild) {
        TreeNode retValue;

        loadKidsIfNeeded();
        retValue = super.getChildBefore(aChild);
        return retValue;
    }

    /** Returns the number of children of this node.
     *
     * @return	an int giving the number of children of this node
     *
     */
    public int getChildCount() {
        int retValue;

        loadKidsIfNeeded();
        retValue = super.getChildCount();

        return retValue;
    }

    public DefaultTreeModel getContainingModel() {
        return containingModel;
    }

    /** Returns the depth of the tree rooted at this node -- the longest
     * distance from this node to a leaf.  If this node has no children,
     * returns 0.  This operation is much more expensive than
     * <code>getLevel()</code> because it must effectively traverse the entire
     * tree rooted at this node.
     *
     * @see	#getLevel
     * @return	the depth of the tree whose root is this node
     *
     */
    public int getDepth() {
        int retValue;

        loadKidsIfNeeded();
        retValue = super.getDepth();
        return retValue;
    }

    /** Returns this node's first child.  If this node has no children,
     * throws NoSuchElementException.
     *
     * @return	the first child of this node
     * @exception	NoSuchElementException	if this node has no children
     *
     */
    public TreeNode getFirstChild() {
        TreeNode retValue;

        loadKidsIfNeeded();
        retValue = super.getFirstChild();
        return retValue;
    }

    /** Finds and returns the first leaf that is a descendant of this node --
     * either this node or its first child's first leaf.
     * Returns this node if it is a leaf.
     *
     * @see	#isLeaf
     * @see	#isNodeDescendant
     * @return	the first leaf in the subtree rooted at this node
     *
     */
    public DefaultMutableTreeNode getFirstLeaf() {
        DefaultMutableTreeNode retValue;

        loadKidsIfNeeded();
        retValue = super.getFirstLeaf();
        return retValue;
    }

    /** Returns the index of the specified child in this node's child array.
     * If the specified node is not a child of this node, returns
     * <code>-1</code>.  This method performs a linear search and is O(n)
     * where n is the number of children.
     *
     * @param	aChild	the TreeNode to search for among this node's children
     * @exception	IllegalArgumentException	if <code>aChild</code>
     * 							is null
     * @return	an int giving the index of the node in this node's child
     *          array, or <code>-1</code> if the specified node is a not
     *          a child of this node
     *
     */
    public int getIndex(TreeNode aChild) {
        int retValue;

        loadKidsIfNeeded();
        retValue = super.getIndex(aChild);
        return retValue;
    }

    /** Returns this node's last child.  If this node has no children,
     * throws NoSuchElementException.
     *
     * @return	the last child of this node
     * @exception	NoSuchElementException	if this node has no children
     *
     */
    public TreeNode getLastChild() {
        TreeNode retValue;

        loadKidsIfNeeded();
        retValue = super.getLastChild();
        return retValue;
    }

    /** Finds and returns the last leaf that is a descendant of this node --
     * either this node or its last child's last leaf.
     * Returns this node if it is a leaf.
     *
     * @see	#isLeaf
     * @see	#isNodeDescendant
     * @return	the last leaf in the subtree rooted at this node
     *
     */
    public DefaultMutableTreeNode getLastLeaf() {
        DefaultMutableTreeNode retValue;

        loadKidsIfNeeded();
        retValue = super.getLastLeaf();
        return retValue;
    }

    /** Returns the total number of leaves that are descendants of this node.
     * If this node is a leaf, returns <code>1</code>.  This method is O(n)
     * where n is the number of descendants of this node.
     *
     * @see	#isNodeAncestor
     * @return	the number of leaves beneath this node
     *
     */
    public int getLeafCount() {
        int retValue;

        loadKidsIfNeeded();
        retValue = super.getLeafCount();
        return retValue;
    }

    /** Returns the leaf after this node or null if this node is the
     * last leaf in the tree.
     * <p>
     * In this implementation of the <code>MutableNode</code> interface,
     * this operation is very inefficient. In order to determine the
     * next node, this method first performs a linear search in the
     * parent's child-list in order to find the current node.
     * <p>
     * That implementation makes the operation suitable for short
     * traversals from a known position. But to traverse all of the
     * leaves in the tree, you should use <code>depthFirstEnumeration</code>
     * to enumerate the nodes in the tree and use <code>isLeaf</code>
     * on each node to determine which are leaves.
     *
     * @see	#depthFirstEnumeration
     * @see	#isLeaf
     * @return	returns the next leaf past this node
     *
     */
    public DefaultMutableTreeNode getNextLeaf() {
        DefaultMutableTreeNode retValue;

        loadKidsIfNeeded();
        retValue = super.getNextLeaf();
        return retValue;
    }

    /** Returns the node that follows this node in a preorder traversal of this
     * node's tree.  Returns null if this node is the last node of the
     * traversal.  This is an inefficient way to traverse the entire tree; use
     * an enumeration, instead.
     *
     * @see	#preorderEnumeration
     * @return	the node that follows this node in a preorder traversal, or
     * 		null if this node is last
     *
     */
    public DefaultMutableTreeNode getNextNode() {
        DefaultMutableTreeNode retValue;

        loadKidsIfNeeded();
        retValue = super.getNextNode();
        return retValue;
    }

    /** Returns the next sibling of this node in the parent's children array.
     * Returns null if this node has no parent or is the parent's last child.
     * This method performs a linear search that is O(n) where n is the number
     * of children; to traverse the entire array, use the parent's child
     * enumeration instead.
     *
     * @see	#children
     * @return	the sibling of this node that immediately follows this node
     *
     */
    public DefaultMutableTreeNode getNextSibling() {
        DefaultMutableTreeNode retValue;

        loadKidsIfNeeded();
        retValue = super.getNextSibling();
        return retValue;
    }

    /** Returns the leaf before this node or null if this node is the
     * first leaf in the tree.
     * <p>
     * In this implementation of the <code>MutableNode</code> interface,
     * this operation is very inefficient. In order to determine the
     * previous node, this method first performs a linear search in the
     * parent's child-list in order to find the current node.
     * <p>
     * That implementation makes the operation suitable for short
     * traversals from a known position. But to traverse all of the
     * leaves in the tree, you should use <code>depthFirstEnumeration</code>
     * to enumerate the nodes in the tree and use <code>isLeaf</code>
     * on each node to determine which are leaves.
     *
     * @see		#depthFirstEnumeration
     * @see		#isLeaf
     * @return	returns the leaf before this node
     *
     */
    public DefaultMutableTreeNode getPreviousLeaf() {
        DefaultMutableTreeNode retValue;

        loadKidsIfNeeded();
        retValue = super.getPreviousLeaf();
        return retValue;
    }

    /** Returns the node that precedes this node in a preorder traversal of
     * this node's tree.  Returns <code>null</code> if this node is the
     * first node of the traversal -- the root of the tree.
     * This is an inefficient way to
     * traverse the entire tree; use an enumeration, instead.
     *
     * @see	#preorderEnumeration
     * @return	the node that precedes this node in a preorder traversal, or
     * 		null if this node is the first
     *
     */
    public DefaultMutableTreeNode getPreviousNode() {
        DefaultMutableTreeNode retValue;

        loadKidsIfNeeded();
        retValue = super.getPreviousNode();
        return retValue;
    }

    /** Returns true if this node has no children.  To distinguish between
     * nodes that have no children and nodes that <i>cannot</i> have
     * children (e.g. to distinguish files from empty directories), use this
     * method in conjunction with <code>getAllowsChildren</code>
     *
     * @see	#getAllowsChildren
     * @return	true if this node has no children
     *
     */
    public boolean isLeaf() {
        boolean retValue;

        loadKidsIfNeeded();
        retValue = super.isLeaf();
        return retValue;
    }

    /** Returns true if <code>aNode</code> is a child of this node.  If
     * <code>aNode</code> is null, this method returns false.
     *
     * @return	true if <code>aNode</code> is a child of this node; false if
     *  		<code>aNode</code> is null
     *
     */
    public boolean isNodeChild(TreeNode aNode) {
        boolean retValue;

        loadKidsIfNeeded();
        retValue = super.isNodeChild(aNode);
        return retValue;
    }

    /** Returns true if <code>anotherNode</code> is a descendant of this node
     * -- if it is this node, one of this node's children, or a descendant of
     * one of this node's children.  Note that a node is considered a
     * descendant of itself.  If <code>anotherNode</code> is null, returns
     * false.  This operation is at worst O(h) where h is the distance from the
     * root to <code>anotherNode</code>.
     *
     * @see	#isNodeAncestor
     * @see	#getSharedAncestor
     * @param	anotherNode	node to test as descendant of this node
     * @return	true if this node is an ancestor of <code>anotherNode</code>
     *
     */
    public boolean isNodeDescendant(DefaultMutableTreeNode anotherNode) {
        boolean retValue;

        loadKidsIfNeeded();
        retValue = super.isNodeDescendant(anotherNode);
        return retValue;
    }

    /** Returns true if and only if <code>aNode</code> is in the same tree
     * as this node.  Returns false if <code>aNode</code> is null.
     *
     * @see	#getSharedAncestor
     * @see	#getRoot
     * @return	true if <code>aNode</code> is in the same tree as this node;
     * 		false if <code>aNode</code> is null
     *
     */
    public boolean isNodeRelated(DefaultMutableTreeNode aNode) {
        boolean retValue;

        loadKidsIfNeeded();
        retValue = super.isNodeRelated(aNode);
        return retValue;
    }

    /** Returns true if this node is the root of the tree.  The root is
     * the only node in the tree with a null parent; every tree has exactly
     * one root.
     *
     * @return	true if this node is the root of its tree
     *
     */
    public boolean isRoot() {
        boolean retValue;

        retValue = super.isRoot();
        return retValue;
    }

    /** Creates and returns an enumeration that traverses the subtree rooted at
     * this node in postorder.  The first node returned by the enumeration's
     * <code>nextElement()</code> method is the leftmost leaf.  This is the
     * same as a depth-first traversal.<P>
     *
     * Modifying the tree by inserting, removing, or moving a node invalidates
     * any enumerations created before the modification.
     *
     * @see	#depthFirstEnumeration
     * @see	#preorderEnumeration
     * @return	an enumeration for traversing the tree in postorder
     *
     */
    @SuppressWarnings("unchecked")
    public Enumeration<TreeNode> postorderEnumeration() {
        Enumeration<TreeNode> retValue;

        loadKidsIfNeeded();
        retValue = super.postorderEnumeration();
        return retValue;
    }

    /** Creates and returns an enumeration that traverses the subtree rooted at
     * this node in preorder.  The first node returned by the enumeration's
     * <code>nextElement()</code> method is this node.<P>
     *
     * Modifying the tree by inserting, removing, or moving a node invalidates
     * any enumerations created before the modification.
     *
     * @see	#postorderEnumeration
     * @return	an enumeration for traversing the tree in preorder
     *
     */
    @SuppressWarnings("unchecked")
    public Enumeration<TreeNode> preorderEnumeration() {
        Enumeration<TreeNode> retValue;

        loadKidsIfNeeded();
        retValue = super.preorderEnumeration();
        return retValue;
    }

    /** Removes <code>aChild</code> from this node's child array, giving it a
     * null parent.
     *
     * @param	aChild	a child of this node to remove
     * @exception	IllegalArgumentException	if <code>aChild</code>
     * 					is null or is not a child of this node
     *
     */
    public void remove(MutableTreeNode aChild) {
        loadKidsIfNeeded();
        super.remove(aChild);
    }
    
    public void setontainingModel(DefaultTreeModel containingModel) {
        this.containingModel = containingModel;
    }

    /** Returns the result of sending <code>toString()</code> to this node's
     * user object, or null if this node has no user object.
     *
     * @see	#getUserObject
     *
     */
    public String toString() {
        String retValue;

        retValue = (file == null) ? "File Systems" : super.toString();

        return retValue;
    }

    protected File[] listKidFiles() {
        File[] rawKids = null;

        if (file == null) {
            // children are root filesystems
            rawKids = File.listRoots();
        } else {
            // load from a folder
            rawKids = file.listFiles();
        }

        return rawKids;
    }

    protected void loadKids() {
        File[] kids = null;

        if (allowsChildren) {
            kids = listKidFiles();
            if ((kids != null) && (kids.length > 0)) {
                for (int i = 0; i < kids.length; ++i) {
                    super.add(newNode(kids[i]));
                }
            }
        }
        kidsLoaded = true;
    }

    protected void loadKidsIfNeeded() {
        if (!kidsLoaded) {
            if (!loadingKids) {
                loadingKids = true;
                loadKids();
                loadingKids = false;
            }
        }
    }

    protected ActiveFileTreeNode newNode(File f) {
        return new ActiveFileTreeNode(containingModel, f);
    }
}

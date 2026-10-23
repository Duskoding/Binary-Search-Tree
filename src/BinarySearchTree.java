public class BinarySearchTree<AnyType extends Comparable<? super AnyType>> {
    //The tree root
    private BinaryNode<AnyType> root;

    // Basic node stored in unbalanced binary search trees
    private static class BinaryNode<AnyType> {
        AnyType element; // The data in the node
        int count; // number of times the element appears
        BinaryNode<AnyType> left; // Left child
        BinaryNode<AnyType> right; // Right child
        // Constructors
        BinaryNode(AnyType theElement) {
            this(theElement, null, null, 1);
        }
        BinaryNode(AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt, int i) {
            element = theElement;
            left = lt;
            right = rt;
            count = i;
        }

        public String toString()
        {
            return "" + element + "(" + count + ")";
        }
    }

    //Construct the tree
    public BinarySearchTree() {
        root = null;
    }
    /**
     * Insert into the tree; duplicates increment the count.
     *@param x the item to insert.
     * */
    public void insert(AnyType x) {
        root = insert(x, root);
    }
    /**
     * Remove from the tree. Nothing is done if x is not found.
     * If x appears more than once its count is decremented instead.
     *
     * @param x the item to remove.
     */
    public void remove(AnyType x) {
        root = remove(x, root);
    }
    /**
     * Find the smallest item in the tree.
     *
     * @return smallest item or null if empty.
     */
    public AnyType findMin() {
        AnyType retVal = null;
        if (!isEmpty())
            retVal = findMin(root).element;
        return retVal;
    }
    /**
     * Find the largest item in the tree.
     *
     * @return the largest item or null if empty.
     */
    public AnyType findMax()
    {
        AnyType retVal = null;
        if (!isEmpty())
            retVal = findMax(root).element;
        return retVal;
    }
    /**
     * Find an item in the tree.
     *
     * @param x the item to search for.
     * @return true if found.
     */
    public boolean contains(AnyType x)
    {
        return contains(x, root);
    }
    /**
     * Make the tree logically empty.
     */
    public void makeEmpty()
    {
        root = null;
    }
    /**
     * Test if the tree is logically empty.
     *
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty()
    {
        return root == null;
    }
    /**
     * Print the tree contents in sorted order.
     */
    public void printTree()
    {
        if (isEmpty())
            System.out.println("Empty tree");
        else
            printTree(root);
    }
    /**
     * a string presentation of this tree
     *
     * @return String representing this tree
     */
    public String toString()
    {
        String strTree = "empty";
        if (root != null)
        {
            StringBuilder buffer = new StringBuilder();
            toStr(root, buffer, "", "");
            strTree = buffer.toString();
        }
        return strTree;
    }
///////////////////////////////////////////////////
////// //////
////// private methods //////
////// //////
///////////////////////////////////////////////////
    /**
     * Internal method to insert into a subtree.
     *
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> insert(AnyType x, BinaryNode<AnyType> t) {
        if (t == null)
            return new BinaryNode<>(x, null, null, 1);
        int compareResult = x.compareTo(t.element);
        if (compareResult < 0)
            t.left = insert(x, t.left);
        else if (compareResult > 0)
            t.right = insert(x, t.right);
        else
            t.count++;
        return t;
    }
    /**
     * Internal method to remove from a subtree.
     *
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> remove(AnyType x, BinaryNode<AnyType> t)
    {
        if (t == null)
            return t; // Item not found; do nothing
        int compareResult = x.compareTo(t.element);
        if (compareResult < 0)
            t.left = remove(x, t.left);
        else if (compareResult > 0)
            t.right = remove(x, t.right);
        else if (t.count > 1) // word appears more than once
        {
            t.count--; // just decrement, dont remove the node
            return t;
        }
        else if (t.left != null && t.right != null) // Two children
        {
            BinaryNode<AnyType> min = findMin(t.right);
            t.element = min.element;
            t.count = min.count; // also copy the count or it gets lost
            min.count = 1; // so the remove below actually deletes the min node instead of just decrementing it
            t.right = remove(t.element, t.right);
        }
        else
            t = (t.left != null) ? t.left : t.right;
        return t;
    }
    /**
     * Internal method to find the smallest item in a subtree.
     *
     * @param t the node that roots the subtree.
     * @return node containing the smallest item.
     */
    private BinaryNode<AnyType> findMin(BinaryNode<AnyType> t)
    {
        if (t == null)
            return null;
        else if (t.left == null)
            return t;
        return findMin(t.left);
    }
    /**
     * Internal method to find the largest item in a subtree.
     *
     * @param t the node that roots the subtree.
     * @return node containing the largest item.
     */
    private BinaryNode<AnyType> findMax(BinaryNode<AnyType> t)
    {
        if (t != null)
            while (t.right != null)
                t = t.right;
        return t;
    }
    /**
     * Internal method to find an item in a subtree.
     *
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     * @return node containing the matched item.
     */
    private boolean contains(AnyType x, BinaryNode<AnyType> t)
    {
        if (t == null)
            return false;
        int compareResult = x.compareTo(t.element);
        if (compareResult < 0)
            return contains(x, t.left);
        else if (compareResult > 0)
            return contains(x, t.right);
        else
            return true; // Match
    }

    public int getTotalWordCount(){
        return getTotalWordCount(root); //this method is implemented for encapsulation and cleanliness
    }
    private int getTotalWordCount(BinaryNode<AnyType> t){
        if( t == null){
            return 0;
        }

        return t.count + getTotalWordCount(t.left) + getTotalWordCount(t.right);//gets the count of this node, then recursively gets the count of the childs
    }

    public int getUniqueWordCount(){
        return getUniqueWordCount(root);
    }

    private int getUniqueWordCount(BinaryNode<AnyType> t){
        if (t == null){
            return 0;
        }

        return 1 + getUniqueWordCount(t.left) + getUniqueWordCount(t.right); //counts the node and then the childs nodes
    }

    public double getTtr(){
        int unique = getUniqueWordCount(); //i didnt want to make the varaibles but if i just do return double get /get itd be slower and you would not like that
        int total = getTotalWordCount();
        if(total == 0){
            return 0.0;
        }

        return (double) unique / total;
    }
    /**
     * Internal method to print a subtree in sorted order.
     *
     * @param t the node that roots the subtree.
     */
    private void printTree(BinaryNode<AnyType> t)
    {
        if (t != null) {
            printTree(t.left);
            System.out.println(t.element + " (" + t.count + ")");//if we are printing the element we should also print the count
            printTree(t.right);
        }
    }
    /**
     * Intenal method to convert a subtree to a tree-like string
     *
     * @param node that roots the subtree
     * @param buffer StringBuilder collecting the evergrowing string
     * @param prefix prefix of node display - so far
     * @param childrenPrefix prefix for subsequent children
     */
    private void toStr(BinaryNode<AnyType> node,
                       StringBuilder buffer,
                       String prefix,
                       String childrenPrefix)
    {
        buffer.append(prefix);
        buffer.append(node.element.toString());
        buffer.append('\n');
        if (node.right != null)
        {
            toStr(node.right, buffer, childrenPrefix + ">── ",
                    node.left != null
                            ? childrenPrefix + "│ "
                            : childrenPrefix + " ");
        }
        if (node.left != null)
        {
            toStr(node.left, buffer, childrenPrefix + "<── ", childrenPrefix + " ");
        }
    }
}
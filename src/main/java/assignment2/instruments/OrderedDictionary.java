package assignment2.instruments;

/* Spencer Smith, Anna Fortenberry
 * sms0697, akf0099
 * This file implements the functions declared in OrderedDictionaryADT. It serves as a simple database manager
 * that stores objects in an ordered dictionary using a binary search tree.
 */

public class OrderedDictionary implements OrderedDictionaryADT {

    // initial memory allocation for a root node
     Node root;

    OrderedDictionary() {
        root = new Node();
    }

    // Func: Find
    /* Parameters:  DataKey k (string name, string type)
       Return:      InstrumentRecord (DataKey key, string about, string sound, string image)
       Desc:        This function first calls the .isEmpty() function to check whether the tree is empty.
                    If the tree is not empty, it uses the compareTo() function to compare each node in the
                    tree with the given DataKey k.
     */
    public InstrumentRecord find(DataKey k) throws DictionaryException {

        Node curr = root;       // allocate a new node and set it equal to value of root to start
        int comparison;         // placeholder for return value of compareTo()

        // If the root is empty, no reason to call search function.

        if (root.isEmpty()) {
            throw new DictionaryException("There is no record matching the given key.");
        }

        // Otherwise, traverse the tree, updating the InstrumentRecord stored in the current node.
        // Use compareTo() with the current node and the passed DataKey k.

        while (true) {

            // If DataKey k == curr's (this) DataKey: compareTo() returns 0.
            // If DataKey k >  curr's (this) DataKey: compareTo() returns -1.
            // If DataKey k <  curr's (this) DataKey: compareTo() returns 1.

            comparison = curr.getData().getDataKey().compareTo(k);

            // DataKey matches the DataKey of the curr node - match found
            if (0 == comparison) {
                return curr.getData();
            }

            if (1 == comparison) {
                // The DataKey of curr is larger than the parameter DataKey.
                // This means, if the node exists, it would be stored in the
                // left subtree, in an ordered BST.
                // If the left child does not exist, nothing left to search - no match found
                if (!curr.hasLeftChild()) {
                    throw new DictionaryException("There is no record matching the given key.");
                }
                curr = curr.getLeftChild(); // set equal to leftChild (root of left subtree)
            } else if (-1 == comparison) {
                // The DataKey of curr is smaller than the parameter DataKey.
                // This means, if the node exists, it would be stored in the
                // right subtree, in an ordered BST.
                // If the right child does not exist, nothing left to search - no match found
                if (!curr.hasRightChild()) {
                    throw new DictionaryException("There is no record matching the given key.");
                }
                curr = curr.getRightChild(); // set equal to rightChild (root of right subtree)
            }

            // Exit iteration of loop with either left or right child if it exists.
            // Repeat comparison process until compareTo() returns 0 (find successful)
            // or an exception is thrown (find unsuccessful)
        }
    }

    // Func: Insert
    /* Parameters:  InstrumentRecord (DataKey key, string about, string sound, string image)
       Return:      void
       Desc:        This function calls the recursive function recurInsert to find the correct
                    place in the BST to insert a new node and updates the tree accordingly.
     */
    public void insert(InstrumentRecord key) throws DictionaryException {
        if(root.isEmpty()) {
            root.setData(key);
        }
        else
            root = recurInsert(root, key);
    }

    // Func: Recursive Insert
    /* Parameters:  Node n (InstrumentRecord r, Node parent, Node left, Node right)
                    InstrumentRecord r (DataKey key, string about, string sound, string image)
       Return:      Node n (InstrumentRecord r, Node parent, Node left, Node right)
       Desc:        This function uses the compareTo() function to update the node where the
                    new value should be stored in the BST.
     */
    public Node recurInsert(Node r, InstrumentRecord key) throws DictionaryException {

        // case 1: first element inserted into BST

        if (r.isEmpty()) {
            // update root with InstrumentRecord
            r.setData(key);
            return r;
        }

        // case 2: BST has already been initialized
        // access the DataKey of current node and compare to parameter DataKey
        // If DataKey k == curr's (this) DataKey: compareTo() returns 0.
        // If DataKey k >  curr's (this) DataKey: compareTo() returns -1.
        // If DataKey k <  curr's (this) DataKey: compareTo() returns 1.

        int comparison = r.getData().getDataKey().compareTo(key.getDataKey());

        if (1 == comparison) {
            if (!r.hasLeftChild()) {
                Node leftChild = new Node();
                r.setLeftChild(recurInsert(leftChild, key));
            }
            else {
                r.setLeftChild(recurInsert(r.getLeftChild(), key));
            }
        }
        else if (-1 == comparison) {
            if (!r.hasRightChild()) {
                Node rightChild = new Node();
                r.setRightChild(recurInsert(rightChild, key));
            } else
                r.setRightChild(recurInsert(r.getRightChild(), key));
        }

        return r;
    }

    // Func: Remove
    /* Parameters:  DataKey k, (data, left child, right child)
       Return:      void
       Desc:        This function calls the search function given a DataKey k. When it finds the
                    matching key, it removes it from the BST and updates it accordingly. The
                    function cannot be called unless there is at least one element in the BST.
     */
    public void remove(InstrumentRecord key) throws DictionaryException {
        root = recurRemove(root, key);
    }

    // utility function for remove function
    public Node recurRemove(Node root, InstrumentRecord key) throws DictionaryException {

        // If r.getDataKey() == n.getData().getDataKey(): compareTo() returns 0.
        // If r.getDataKey() > n.getData().getDataKey(): compareTo() returns -1.
        // If r.getDataKey() < n.getData().getDataKey():: compareTo() returns 1.

        int comparison = root.getData().getDataKey().compareTo(key.getDataKey());

        if (1 == comparison)
            root.setLeftChild(recurRemove(root.getLeftChild(), key));
        else if (-1 == comparison)
            root.setRightChild(recurRemove(root.getRightChild(), key));
        else { // compareTo() returned 0, node to remove has been located

            // Case 1: leaf node or only has one child

            if (!root.hasLeftChild())
                return root.getRightChild();
            else if (!root.hasRightChild())
                return root.getLeftChild();

            // Case 2: has two children
            // 1. set the InstrumentRecord of the node to delete to the InstrumentRecord of its predecessor.
            // 2.
            root.setData(predecessor(root.getData().getDataKey()));
            root.setRightChild(recurRemove(root.getRightChild(), root.getData()));
        }
        return root;
    }

    // Func: Successor
    /* Parameters:  none
       Return:      DataKey k, (data, left child, right child)
       Desc:        This function traverses through the tree and returns the successor.
                    This is the smallest element in the right subtree.
     */
    public InstrumentRecord successor(DataKey k) throws DictionaryException {
        return (findSuccessor(root, k).getData());
    }

    // utility function for successor
    public Node findSuccessor(Node r, DataKey k) throws DictionaryException {

        int comparison = r.getData().getDataKey().compareTo(k);

        if (1 == comparison)
            r.setLeftChild(findSuccessor(r.getLeftChild(), k));
        else if (-1 == comparison)
            r.setRightChild(findSuccessor(r.getRightChild(), k));
        else { // compareTo() returned 0, node to start from has been located
            while (r.hasRightChild()) {
                r = r.getLeftChild();
            }
        }

        return r;
    }

    // Func: Predecessor
    /* Parameters:  none
       Return:      DataKey k, (data, left child, right child)
       Desc:        This function traverses through the tree and returns the predecessor.
                    This is the largest element in the left subtree.
     */
    public InstrumentRecord predecessor(DataKey k) throws DictionaryException {
        return (findPredecessor(root, k).getData());
    }

    // utility function for predecessor
    public Node findPredecessor(Node r, DataKey k) throws DictionaryException {

        int comparison = r.getData().getDataKey().compareTo(k);

        if (1 == comparison)
            r.setLeftChild(findSuccessor(r.getLeftChild(), k));
        else if (-1 == comparison)
            r.setRightChild(findSuccessor(r.getRightChild(), k));
        else { // compareTo() returned 0, node to start from has been located
            while (r.hasLeftChild()) {
                r = r.getRightChild();
            }
        }

        return r;
    }

    public InstrumentRecord smallest() throws DictionaryException {

        Node smallest = root;

        while (smallest.hasLeftChild()) {

            smallest = smallest.getLeftChild();
        }

        return smallest.getData();
    }

    public InstrumentRecord largest() throws DictionaryException {

        Node largest = root;

        while (largest.hasRightChild()) {

            largest = largest.getRightChild();
        }

        return largest.getData();
    }

    public boolean isEmpty() { return root.getData() == null; }
}

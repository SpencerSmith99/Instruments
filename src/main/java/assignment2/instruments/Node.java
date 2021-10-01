package assignment2.instruments;

public class Node {

    /*Private data variables for the node. Stores instrument data, leftChild,
    rightChild and parent of a given node */
    private InstrumentRecord data;
    private Node leftChild;
    private Node rightChild;
    private Node parent;

    //default constructor
    public Node() {this(new InstrumentRecord());} //stores NULL Record objects

    //constructor for an instrument
    public Node(InstrumentRecord data) {this(data, null, null);}

    //constructor for instrument, leftchild and rightchild
    public Node(InstrumentRecord data, Node leftChild, Node rightChild) {
        this.data = data;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    //getter for the nodes data
    public InstrumentRecord getData() {return this.data;}

    public void setData(InstrumentRecord data) {
        this.data = data;
    }

    public Node getLeftChild() {
        return this.leftChild;
    }

    public Node getParent() {
        return this.parent;
    }

    public Node getRightChild() { return this.rightChild; }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
        if (leftChild != null ) //&& leftChild.hasLeftChild())
            leftChild.setParent(this);
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
        if (rightChild != null )//&& rightChild.hasRightChild())
            rightChild.setParent(this);
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public boolean hasLeftChild() {
        return (leftChild != null);
    }

    public boolean hasRightChild() {
        return (rightChild != null);
    }

    public boolean hasParent()	{
        return (parent != null);
    }

    public boolean isLeaf() {
        return ((leftChild == null) && (rightChild == null));
    }

    public boolean isEmpty() {
        return (data.getDataKey() == null) ;
    }

}

package assignment2.instruments;

/* Spencer Smith, Anna Fortenberry
 * sms0697, akf0099
 * This file declares functions necessary for the implementation of a simple database manager that
 * stores objects in an ordered dictionary using a binary search tree.
 */

public interface OrderedDictionaryADT {

    public InstrumentRecord find(DataKey k) throws DictionaryException;

    public void insert(InstrumentRecord key) throws DictionaryException;
    public Node recurInsert(Node root, InstrumentRecord key) throws DictionaryException;

    public void remove(InstrumentRecord key) throws DictionaryException;
    public Node recurRemove(Node root, InstrumentRecord key) throws DictionaryException;

    public InstrumentRecord successor(DataKey k) throws DictionaryException;
    public Node findSuccessor(Node root, DataKey k) throws DictionaryException;

    public InstrumentRecord predecessor(DataKey k) throws DictionaryException;
    public Node findPredecessor(Node root, DataKey k) throws DictionaryException;

    public InstrumentRecord smallest() throws DictionaryException;

    public InstrumentRecord largest() throws DictionaryException;

    public boolean isEmpty();
}

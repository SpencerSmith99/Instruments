package assignment2.instruments;

import java.util.Objects;

public class DataKey {

    private String instrumentName;
    private String instrumentType;

    //default constructor, sets values to NULL
    public DataKey() { this(null, null); }

    //constructor with two parameters
    public DataKey(String name, String type) {
        this.instrumentName = name;
        this.instrumentType = type;
    }

    //public getters
    public String getInstrumentName() { return this.instrumentName; }
    public String getInstrumentType() { return this.instrumentType; }

    /**
     * Returns 0 if this DataKey is equal to k, returns -1 if this DataKey is smaller
     * than k, and it returns 1 otherwise.
    **/
    public int compareTo(DataKey k) {
        //if the InstrumentTypes are equal then compare the DataKey's alphabetically by name
        if (Objects.equals(this.getInstrumentType(), k.getInstrumentType())) {
            int compare = this.getInstrumentName().compareTo(k.getInstrumentName());
            //if the InstrumentNames are exactly the same, return 0
            if (compare == 0) {
                return 0;
            }
            //else if this DataKey's InstrumentName is smaller than k's, then return -1
            else if (compare < 0) {
                return -1;
            }
        }
        //else if this InstrumentType's is smaller than k's (alphabetically) return -1
        else if (this.getInstrumentType().compareTo(k.getInstrumentType()) < 0) {
            return -1;
        }
        //else return 1
        return 1;
    }
}

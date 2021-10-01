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
        if (stringCompare(this.getInstrumentType(), k.getInstrumentType()) == 2) {
            int compare = stringCompare(this.getInstrumentName(), k.getInstrumentName());
            //if the InstrumentNames are exactly the same, return 0
            if (compare == 2){
                return 0;
            }
            //else if this DataKey's InstrumentName is smaller than k's, then return -1
            else if (compare == 0) {
                return -1;
            }
        }
        //else if this InstrumentType's is smaller than k's (alphabetically) return -1
        else if(stringCompare(this.getInstrumentType(), k.getInstrumentType()) == 0){
            return -1;
        }
        //else return 1
        return 1;
     }

     /**
      * returns 0 is string1 is smaller and returns 1 is string2 is smaller,
      * returns 2 is they are the same
      */
     private int stringCompare(String str1, String str2) {

        int length1 = str1.length();
        int length2 = str2.length();

        //gets the minimum length for the for loop
        int minLength = Math.min(length1, length2);
        /* set returnNum = 2, automatically assuming the strings are equal.
            if they are not equal then this will get fixed in the for loop */
        int returnNum = 2;

        for(int i = 0; i < minLength; i++) {
            int c1 = (int)str1.charAt(i);
            int c2 = (int)str2.charAt(i);

            //if string1 is smaller, return 0
            //else if string2 is smaller, return 1
            if(c1 < c2)
                returnNum = 0;
            else if(c2 < c1)
                returnNum = 1;
        }
        return returnNum;
     }

}

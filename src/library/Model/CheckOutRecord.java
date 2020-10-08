package library.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CheckOutRecord implements Serializable {
    List<RecordEntry> recordEntries;
    
    //SAMU CODE ATTRIBUTES
	private static final long serialVersionUID = 1L;
	List<CheckoutRecordEntry> entries = new ArrayList<>();
	//END SAMU ATTRIBUTES

    public CheckOutRecord() {
        this.recordEntries = new ArrayList<RecordEntry>();
    }

    //SAMU CONSTRUCTOR
	public CheckOutRecord(ArrayList<CheckoutRecordEntry> entries) {
		this.entries = entries;
	}
	//END SAMU CONSTRUCTOR
    
    public void addRecord(RecordEntry r) {
        this.recordEntries.add(r);
    }

    public List<RecordEntry> getRecords() {
        return this.recordEntries;
    }

    /*ghirmay code */

    //SAMU METHODS
	@SuppressWarnings("unchecked")
	public CheckOutRecord addEntry(CheckoutRecordEntry c) {
		ArrayList<CheckoutRecordEntry> entriesCopy 
		   = (ArrayList<CheckoutRecordEntry>) ((ArrayList<CheckoutRecordEntry>) entries).clone();
		entriesCopy.add(c);
		return new CheckOutRecord(entriesCopy);
		
	}
	
	public List<CheckoutRecordEntry> getCheckoutRecordEntries() {
		return Collections.unmodifiableList(entries);
	}
	//END SAMU METHODS
    

}

package library.Model;

import java.util.ArrayList;
import java.util.List;

public class CheckOutRecord {
    List<RecordEntry> recordEntries;

    public CheckOutRecord() {
        this.recordEntries = new ArrayList<RecordEntry>();
    }

    public void addRecord(RecordEntry r) {
        this.recordEntries.add(r);
    }

    public List<RecordEntry> getRecords() {
        return this.recordEntries;
    }

    /*ghirmay code */


}

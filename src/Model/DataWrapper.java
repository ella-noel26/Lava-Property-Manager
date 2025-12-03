package Model;

import java.util.HashMap;

class DataWrapper {
    public HashMap<Integer, Guest> guestMap;
    public HashMap<Integer, Stay> stayMap;
    public HashMap<Integer, Issue> issueMap;
    public int guestIdCounter;
    public int stayIdCounter;
    public int issueIdCounter;

    public DataWrapper() {
        this.guestMap = new HashMap<>();
        this.stayMap = new HashMap<>();
        this.issueMap = new HashMap<>();
        this.guestIdCounter = 1;  // Start from 1 instead of 0
        this.stayIdCounter = 1;
        this.issueIdCounter = 1;
    }
    
    public DataWrapper(HashMap<Integer, Guest> guestMap, HashMap<Integer, Stay> stayMap, 
                       HashMap<Integer, Issue> issueMap, int guestIdCounter, 
                       int stayIdCounter, int issueIdCounter) {
        this.guestMap = guestMap;
        this.stayMap = stayMap;
        this.issueMap = issueMap;
        this.guestIdCounter = guestIdCounter;
        this.stayIdCounter = stayIdCounter;
        this.issueIdCounter = issueIdCounter;
    }
}

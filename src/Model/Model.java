package Model;
import com.google.gson.Gson;
import Controller.Controller;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Model {
    private Controller controller;
    private HashMap<Integer, Guest> guestMap;
    private HashMap<Integer, Stay> stayMap;
    private HashMap<Integer, Issue> issueMap;
    private Gson gson;
    private int guestIdCounter;
    private int stayIdCounter;
    private int issueIdCounter;

    public Model(Controller controller){
        this.controller = controller;
        this.guestMap = new HashMap<>();
        this.stayMap = new HashMap<>();
        this.issueMap = new HashMap<>();
        gson = new Gson();
        loadData();
    }

    private void loadData(){
        try (Scanner reader = new Scanner(new File("data.json"))){
            StringBuilder sb = new StringBuilder();
            while (reader.hasNextLine()){
                sb.append(reader.nextLine());
            }
            String dataFromFile = sb.toString();
            
            if (!dataFromFile.isEmpty()) {
                DataWrapper wrapper = gson.fromJson(dataFromFile, DataWrapper.class);
                this.guestMap = wrapper.guestMap;
                this.stayMap = wrapper.stayMap;
                this.issueMap = wrapper.issueMap;
                this.guestIdCounter = wrapper.guestIdCounter;
                this.stayIdCounter = wrapper.stayIdCounter;
                this.issueIdCounter = wrapper.issueIdCounter;
            } else {
                this.guestIdCounter = 1;
                this.stayIdCounter = 1;
                this.issueIdCounter = 1;
            }
        }
        catch (IOException error){
            this.guestIdCounter = 1;
            this.stayIdCounter = 1;
            this.issueIdCounter = 1;
        }
    }

    public ArrayList<Stay> getStaysOnDate(LocalDate date) {
        ArrayList<Stay> results = new ArrayList<>();
        if (date == null) return results;
        for (Stay stay : stayMap.values()) {
            if (stay != null && stay.includesDate(date)) {
                results.add(stay);
            }
        }
        return results;
    }

    public boolean isLodgeBookedOn(LocalDate date) {
        for (Stay s : stayMap.values()) {
            if (s != null && s.includesDate(date) && s.getLocation() == 1) return true;
        }
        return false;
    }

    public boolean isBunkhouseBookedOn(LocalDate date) {
        for (Stay s : stayMap.values()) {
            if (s != null && s.includesDate(date) && s.getLocation() == 2) return true;
        }
        return false;
    }

    public void saveData(){
        try (FileWriter writer = new FileWriter("data.json")){
            DataWrapper wrapper = new DataWrapper(guestMap, stayMap, issueMap, guestIdCounter, stayIdCounter, issueIdCounter);
            writer.write(gson.toJson(wrapper));
            writer.close();
        }
        catch(IOException error){
            System.out.println(error);
        }
    }
    
    public void addGuest(Guest guest) {
        guest.setId(guestIdCounter);
        guestMap.put(guestIdCounter, guest);
        guestIdCounter++;
    }
    
    public void addStay(Stay stay, int guestId) {
        stay.setId(stayIdCounter);
        stay.setParentGuestId(guestId);
        stayMap.put(stayIdCounter, stay);
        stayIdCounter++;
    }
    
    public ArrayList<Issue> getAllIssues() {
        return new ArrayList<>(issueMap.values());
    }
    public void addIssue(Issue issue, int stayId) {
        issue.setId(issueIdCounter);
        issue.setParentStayId(stayId);
        issueMap.put(issueIdCounter, issue);
        issueIdCounter++;
    }

    public Guest getGuestById(int id) {
        return guestMap.get(id);
    }
    
    public Stay getStayById(int id) {
        return stayMap.get(id);
    }
    
    public Issue getIssueById(int id) {
        return issueMap.get(id);
    }
    
    public ArrayList<Stay> getStaysForGuest(int guestId) {
        ArrayList<Stay> guestStays = new ArrayList<>();
        for (Stay stay : stayMap.values()) {
            if (stay.getParentGuestId() == guestId) {
                guestStays.add(stay);
            }
        }
        return guestStays;
    }
    
    public ArrayList<Issue> getIssuesForStay(int stayId) {
        ArrayList<Issue> stayIssues = new ArrayList<>();
        for (Issue issue : issueMap.values()) {
            if (issue.getParentStayId() == stayId) {
                stayIssues.add(issue);
            }
        }
        return stayIssues;
    }


    public ArrayList<Guest> searchGuests(ArrayList<String> searchTerms){
        ArrayList<Guest> results = new ArrayList<>();
        String guestFirstName = searchTerms.get(0);
        String guestLastName = searchTerms.get(1);
        String guestPhoneNumber = searchTerms.get(2);
        String guestEmail = searchTerms.get(3);

        for (Guest guest : guestMap.values()) {
            boolean matches = true;
            
            if (!guestFirstName.isEmpty() && !guest.getFirstName().toLowerCase().contains(guestFirstName.toLowerCase())) {
                matches = false;
            }
            if (!guestLastName.isEmpty() && !guest.getLastName().toLowerCase().contains(guestLastName.toLowerCase())) {
                matches = false;
            }
            if (!guestPhoneNumber.isEmpty() && !String.valueOf(guest.getPhoneNumber()).contains(guestPhoneNumber)) {
                matches = false;
            }
            if (!guestEmail.isEmpty() && !guest.getEmail().toLowerCase().contains(guestEmail.toLowerCase())) {
                matches = false;
            }
            
            if (matches) {
                results.add(guest);
            }
        }
        return results;
    }

    public ArrayList<Stay> searchStays(ArrayList<String> searchTerms){
        ArrayList<Stay> results = new ArrayList<>();
        String guestIdStr = searchTerms.get(0);
        String checkInStr = searchTerms.get(1);
        String checkOutStr = searchTerms.get(2);
        String priceStr = searchTerms.get(3);
        String locationStr = searchTerms.get(4);
        
        for (Stay stay : stayMap.values()) {
            boolean matches = true;
            
            if (!guestIdStr.isEmpty() && !String.valueOf(stay.getParentGuestId()).contains(guestIdStr)) {
                matches = false;
            }
            if (!checkInStr.isEmpty() && !dateToString(stay.getStartDate()).contains(checkInStr)) {
                matches = false;
            }
            if (!checkOutStr.isEmpty() && !dateToString(stay.getEndDate()).contains(checkOutStr)) {
                matches = false;
            }
            if (!priceStr.isEmpty() && !String.valueOf(stay.getPrice()).contains(priceStr)) {
                matches = false;
            }
            if (!locationStr.isEmpty() && !String.valueOf(stay.getLocation()).contains(locationStr)) {
                matches = false;
            }
            
            if (matches) {
                results.add(stay);
            }
        }
        return results;
    }

    public ArrayList<Issue> searchIssues(ArrayList<String> searchTerms){
        ArrayList<Issue> results = new ArrayList<>();
        String title = searchTerms.get(0);
        String stayId = searchTerms.get(1);
        String description = searchTerms.get(2);
        String reportedDateStr = searchTerms.get(3);
        String startedDateStr = searchTerms.get(4);
        String resolvedDateStr = searchTerms.get(5);
        
        for (Issue issue : issueMap.values()) {
            boolean matches = true;
            
            if (!title.isEmpty() && !issue.getTitle().toLowerCase().contains(title.toLowerCase())) {
                matches = false;
            }
            if (!stayId.isEmpty() && !String.valueOf(issue.getParentStayId()).contains(stayId)) {
                matches = false;
            }
            if (!description.isEmpty() && !issue.getDescription().toLowerCase().contains(description.toLowerCase())) {
                matches = false;
            }
            if (!reportedDateStr.isEmpty() && !dateToString(issue.getReportedDate()).contains(reportedDateStr)) {
                matches = false;
            }
            if (!startedDateStr.isEmpty() && !dateToString(issue.getStartedDate()).contains(startedDateStr)) {
                matches = false;
            }
            if (!resolvedDateStr.isEmpty() && !dateToString(issue.getResolvedDate()).contains(resolvedDateStr)) {
                matches = false;
            }
            
            if (matches) {
                results.add(issue);
            }
        }
        return results;
    }

    private String dateToString(int[] date){
        return date[0] + "/" + date[1] + "/" + date[2];
    }
    public void populateSampleData() {
        // Guests
        Guest g1 = new Guest("Ella", "Frandsen", 3852991133L, "ella@example.com");
        addGuest(g1);
        Guest g2 = new Guest("Jerry", "Zheng", 3852991100L, "jerry@example.com");
        addGuest(g2);

        // Stays
        Stay s1 = new Stay(new int[]{2,20,2024}, new int[]{2,22,2024}, 500.50, 1);
        addStay(s1, g1.getId());
        Stay s2 = new Stay(new int[]{3,10,2024}, new int[]{3,12,2024}, 200.00, 2);
        addStay(s2, g2.getId());

        // Issues (one unresolved, one resolved)
        Issue i1 = new Issue("Toilet Broken", new int[]{5,17,2015}, new int[]{5,15,2015}, "Toilet won't flush");
        addIssue(i1, s1.getId());
        Issue i2 = new Issue("Light Out", new int[]{6,1,2025}, new int[]{6,2,2025}, new int[]{6,5,2025}, "Electrician fixed it");
        addIssue(i2, s2.getId());

        // Add a couple more to exercise search sorting
        Issue i3 = new Issue("No Hot Water", new int[]{10,10,2024}, new int[]{10,11,2024}, "Boiler issue");
        addIssue(i3, s1.getId());

        // Persist
        saveData();
    }   
}
/*package Model;
import com.google.gson.Gson;
import Controller.Controller;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;



public class Model {
    private Controller controller;
    //private ArrayList<Guest> listOfGuests;
    private HashMap<Integer, Guest> guestMap;
    private HashMap<Integer, Stay> stayMap;
    private HashMap<Integer, Issue> issueMap;
    private Gson gson;
    private int guestIdCounter;
    private int stayIdCounter;
    private int issueIdCounter;

    public Model(Controller controller){
        this.controller = controller;
        this.guestMap = new HashMap<>();
        this.stayMap = new HashMap<>();
        this.issueMap = new HashMap<>();
        gson = new Gson();
        loadData();
    }

    private void loadData(){
        try (Scanner reader = new Scanner(new File("data.json"))){
            if (reader.hasNextLine()){
                String dataFromFile = reader.nextLine();
                DataWrapper wrapper = gson.fromJson(dataFromFile, DataWrapper.class);
                this.guestMap = wrapper.guestMap;
                this.stayMap = wrapper.stayMap;
                this.issueMap = wrapper.issueMap;
                this.guestIdCounter = wrapper.guestIdCounter;
                this.stayIdCounter = wrapper.stayIdCounter;
                this.issueIdCounter = wrapper.issueIdCounter;
            } else {
                this.guestIdCounter = 1;
                this.stayIdCounter = 1;
                this.issueIdCounter = 1;
            }
        }
        catch (IOException error){
            this.guestIdCounter = 1;
            this.stayIdCounter = 1;
            this.issueIdCounter = 1;
        }
    }

    public void saveData(){
        try (FileWriter writer = new FileWriter("data.json")){
            DataWrapper wrapper = new DataWrapper(guestMap, stayMap, issueMap, guestIdCounter, stayIdCounter, issueIdCounter);
            writer.write(gson.toJson(wrapper));
            writer.close();
        }
        catch(IOException error){
            System.out.println(error);
        }
    }
    
    public void addGuest(Guest guest) {
        guest.setId(guestIdCounter);
        guestMap.put(guestIdCounter, guest);
        guestIdCounter++;
    }
    
    public void addStay(Stay stay, int guestId) {
        stay.setId(stayIdCounter);
        stay.setParentGuestId(guestId);
        stayMap.put(stayIdCounter, stay);
        stayIdCounter++;
    }
    
    public void addIssue(Issue issue, int stayId) {
        issue.setId(issueIdCounter);
        issue.setParentStayId(stayId);
        issueMap.put(issueIdCounter, issue);
        issueIdCounter++;
    }
    public Guest getGuestById(int id) {
        return guestMap.get(id);
    }
    
    public Stay getStayById(int id) {
        return stayMap.get(id);
    }
    
    public Issue getIssueById(int id) {
        return issueMap.get(id);
    }
    
    public ArrayList<Stay> getStaysForGuest(int guestId) {
        ArrayList<Stay> guestStays = new ArrayList<>();
        for (Stay stay : stayMap.values()) {
            if (stay.getParentGuestId() == guestId) {
                guestStays.add(stay);
            }
        }
        return guestStays;
    }
    
    public ArrayList<Issue> getIssuesForStay(int stayId) {
        ArrayList<Issue> stayIssues = new ArrayList<>();
        for (Issue issue : issueMap.values()) {
            if (issue.getParentStayId() == stayId) {
                stayIssues.add(issue);
            }
        }
        return stayIssues;
    }



    public ArrayList<Guest> searchGuestsByName(String firstName, String lastName) {
        ArrayList<Guest> results = new ArrayList<>();
        for (Guest guest : guestMap.values()) {
            if (guest.getFirstName().equalsIgnoreCase(firstName) && 
                guest.getLastName().equalsIgnoreCase(lastName)) {
                results.add(guest);
            }
        }
        return results;
    }
    
    public Guest searchGuestByPhone(long phoneNumber) {
        for (Guest guest : guestMap.values()) {
            if (guest.getPhoneNumber() == phoneNumber) {
                return guest;
            }
        }
        return null;
    }
}*/
        /*Guest guest = new Guest("Ella", "Frandsen", 3852991133l, "ella.n.frandsen@gmail.com");
        Issue issue = new Issue("Toilet Broken", new int[]{5,17,2015}, new int[]{5,15,2015}, "Jerry broke the toilet");
        Stay stay = new Stay(new int[]{2,20,2024}, new int[]{2,22,2024}, 500.50, 1);
        issue.addParentStay(stay);
        stay.addChildIssue(issue);
        stay.addParentGuest(guest);
        guest.addChildStay(stay);

        Guest guestTwo = new Guest("Jerry", "Zheng", 3852991100l, "jerry.zhend@gmail.com");
        listOfGuests = new ArrayList<>();
        listOfGuests.add(guest);
        listOfGuests.add(guestTwo);
        //String ellaJson = gson.toJson(guest);
        String ellaJson = gson.toJson(listOfGuests);
        System.out.println(ellaJson);
        System.out.println(stay.getParentGuests());*/
        
        /*Guest practiceGuest = gson.fromJson(ellaJson, Guest.class);
        practiceGuest.setEmail("yourmomsahoe@gmail.com");
        System.out.println(gson.toJson(practiceGuest));
        System.out.println(practiceGuest.getFirstName());
        System.out.println("break");*/



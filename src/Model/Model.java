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

    public ArrayList<Guest> getTopRatedGuests(int limit) {
        ArrayList<Guest> guests = new ArrayList<>(guestMap.values());
        
        // Filter guests with ratings and sort by average rating descending
        ArrayList<Guest> ratedGuests = new ArrayList<>();
        for (Guest g : guests) {
            Double avg = g.getAverageRating(this);
            if (avg != null) {
                ratedGuests.add(g);
            }
        }
        
        ratedGuests.sort((g1, g2) -> {
            Double avg1 = g1.getAverageRating(this);
            Double avg2 = g2.getAverageRating(this);
            return avg2.compareTo(avg1); // descending
        });
        
        return new ArrayList<>(ratedGuests.subList(0, Math.min(limit, ratedGuests.size())));
    }

    public ArrayList<Guest> getLowestRatedGuests(int limit) {
        ArrayList<Guest> guests = new ArrayList<>(guestMap.values());
        
        // Filter guests with ratings and sort by average rating ascending
        ArrayList<Guest> ratedGuests = new ArrayList<>();
        for (Guest g : guests) {
            Double avg = g.getAverageRating(this);
            if (avg != null) {
                ratedGuests.add(g);
            }
        }
        
        ratedGuests.sort((g1, g2) -> {
            Double avg1 = g1.getAverageRating(this);
            Double avg2 = g2.getAverageRating(this);
            return avg1.compareTo(avg2); // ascending
        });
        
        return new ArrayList<>(ratedGuests.subList(0, Math.min(limit, ratedGuests.size())));
    }


    public ArrayList<Guest> searchGuests(ArrayList<String> searchTerms){
        ArrayList<Guest> results = new ArrayList<>();
        String idStr = safeIdx(searchTerms, 0);
        String firstName = safeIdx(searchTerms, 1);
        String lastName = safeIdx(searchTerms, 2);
        String phoneNumber = safeIdx(searchTerms, 3);
        String email = safeIdx(searchTerms, 4);
        String childStayIdStr = safeIdx(searchTerms, 5);

        for (Guest guest : guestMap.values()) {
            boolean matches = true;

            if (!idStr.isEmpty() && !String.valueOf(guest.getId()).contains(idStr)) {
                matches = false;
            }
            if (!firstName.isEmpty() && !guest.getFirstName().toLowerCase().contains(firstName.toLowerCase())) {
                matches = false;
            }
            if (!lastName.isEmpty() && !guest.getLastName().toLowerCase().contains(lastName.toLowerCase())) {
                matches = false;
            }
            if (!phoneNumber.isEmpty() && !String.valueOf(guest.getPhoneNumber()).contains(phoneNumber)) {
                matches = false;
            }
            if (!email.isEmpty() && !guest.getEmail().toLowerCase().contains(email.toLowerCase())) {
                matches = false;
            }
            if (!childStayIdStr.isEmpty()) {
                boolean hasChild = false;
                for (Integer sid : guest.getChildStays()) {
                    if (String.valueOf(sid).contains(childStayIdStr)) { hasChild = true; break; }
                }
                if (!hasChild) matches = false;
            }

            if (matches) results.add(guest);
        }
        return results;
    }

    public ArrayList<Stay> searchStays(ArrayList<String> searchTerms){
        ArrayList<Stay> results = new ArrayList<>();
        String idStr = safeIdx(searchTerms, 0);
        String guestIdStr = safeIdx(searchTerms, 1);
        String checkInStr = safeIdx(searchTerms, 2);
        String checkOutStr = safeIdx(searchTerms, 3);
        String priceStr = safeIdx(searchTerms, 4);
        String locationStr = safeIdx(searchTerms, 5);
        String childIssueIdStr = safeIdx(searchTerms, 6);
        
        for (Stay stay : stayMap.values()) {
            boolean matches = true;
            
            if (!idStr.isEmpty() && !String.valueOf(stay.getId()).contains(idStr)) {
                matches = false;
            }
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
            if (!childIssueIdStr.isEmpty()) {
                boolean hasChild = false;
                for (Integer iid : stay.getChildIssueIds()) {
                    if (String.valueOf(iid).contains(childIssueIdStr)) { hasChild = true; break; }
                }
                if (!hasChild) matches = false;
            }
            
            if (matches) {
                results.add(stay);
            }
        }
        return results;
    }

    public ArrayList<Issue> searchIssues(ArrayList<String> searchTerms){
        ArrayList<Issue> results = new ArrayList<>();
        String idStr = safeIdx(searchTerms, 0);
        String title = safeIdx(searchTerms, 1);
        String stayId = safeIdx(searchTerms, 2);
        String description = safeIdx(searchTerms, 3);
        String reportedDateStr = safeIdx(searchTerms, 4);
        String startedDateStr = safeIdx(searchTerms, 5);
        String resolvedDateStr = safeIdx(searchTerms, 6);
        
        for (Issue issue : issueMap.values()) {
            boolean matches = true;
            
            if (!idStr.isEmpty() && !String.valueOf(issue.getId()).contains(idStr)) {
                matches = false;
            }
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

    private String safeIdx(ArrayList<String> list, int idx){
        if (list == null) return "";
        if (idx < 0 || idx >= list.size()) return "";
        String s = list.get(idx);
        return s == null ? "" : s;
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

    public void updateGuest(Guest g) {
        if (g == null) return;
        if (g.getId() <= 0) return;
        guestMap.put(g.getId(), g);
    }

    public void updateStay(Stay s) {
        if (s == null) return;
        if (s.getId() <= 0) return;
        stayMap.put(s.getId(), s);
    }

    public void updateIssue(Issue i) {
        if (i == null) return;
        if (i.getId() <= 0) return;
        issueMap.put(i.getId(), i);
    }
}



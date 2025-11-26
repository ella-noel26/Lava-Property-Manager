package Model;
import java.time.LocalDate;
import java.util.ArrayList;

public class Stay implements Comparable<Stay>{
    private int id;
    private int parentGuestId;
    private int[] startDate;
    private int[] endDate;
    private double price;
    private int location; //Lodge is 1, bunkhouse is 2
    private ArrayList<Integer> childIssueIds;
    private Integer rating;


    public Stay(int[] arrivalDate, int[] leaveDate, double pricePaid, int place){
        this.startDate = new int[arrivalDate.length];
        for (int index = 0; index < arrivalDate.length; index++){
            this.startDate[index] = arrivalDate[index];
        }

        this.endDate = new int[leaveDate.length];
        for (int index = 0; index < leaveDate.length; index++){
            this.endDate[index] = leaveDate[index];
        }

        this.price = pricePaid;
        this.location = place;
        this.childIssueIds = new ArrayList<>();
        this.rating = null;
    }

    public Integer getRating(){
        return this.rating;
    }

    public void setRating(Integer rating){
        if (rating != null && (rating < 1 || rating > 5)) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }
        this.rating = rating;
    }

    public LocalDate getStartLocalDate() {
        if (this.startDate == null || this.startDate.length < 3){
            return null;
        }
        // stored as [month, day, year]
        return LocalDate.of(this.startDate[2], this.startDate[0], this.startDate[1]);
    }

    public LocalDate getEndLocalDate() {
        if (this.endDate == null || this.endDate.length < 3){

        return null;}
        return LocalDate.of(this.endDate[2], this.endDate[0], this.endDate[1]);
    }

    // Check whether a LocalDate falls inside this stay (inclusive)
    public boolean includesDate(LocalDate date) {
        LocalDate start = getStartLocalDate();
        LocalDate end = getEndLocalDate();
        if (start == null || end == null || date == null){
            return false;
        } 
        return !(date.isBefore(start) || date.isAfter(end));
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getId() {
        return this.id;
    }
    
    public void setParentGuestId(int guestId) {
        this.parentGuestId = guestId;
    }
    
    public int getParentGuestId() {
        return this.parentGuestId;
    }

    public ArrayList<Integer> getChildIssueIds(){
        return this.childIssueIds;
    }

    public void addChildIssueId(int issueId){
        this.childIssueIds.add(issueId);
    }

    public void addChildIssueIds(ArrayList<Integer> issueIds){
        for (int issueId : issueIds){
            this.childIssueIds.add(issueId);
        }
    }

    public int[] getStartDate(){
        return this.startDate;
    }

    public void setStartDate(int[] date){
        for (int index = 0; index < date.length; index++){
            this.startDate[index] = date[index];
        }
    }

    public int[] getEndDate(){
        return this.endDate;
    }

    public void setEndDate(int[] date){
        for (int index = 0; index < date.length; index++){
            this.endDate[index] = date[index];
        }
    }

    public double getPrice(){
        return this.price;
    }

    public void setPrice(double num){
        this.price = num;
    }

    public int getLocation(){
        return this.location;
    }

    public void setLocation(int place){
        this.location = place;
    }

    
    @Override
    public int compareTo(Stay other) {
        java.time.LocalDate aStart = this.getStartLocalDate();
        java.time.LocalDate bStart = other.getStartLocalDate();
        if (aStart != null && bStart != null) {
            int cmp = aStart.compareTo(bStart);
            if (cmp != 0) return cmp;
        } else if (aStart == null && bStart != null) return -1;
        else if (aStart != null && bStart == null) return 1;

        java.time.LocalDate aEnd = this.getEndLocalDate();
        java.time.LocalDate bEnd = other.getEndLocalDate();
        if (aEnd != null && bEnd != null) {
            int cmp = aEnd.compareTo(bEnd);
            if (cmp != 0) return cmp;
        } else if (aEnd == null && bEnd != null) return -1;
        else if (aEnd != null && bEnd == null) return 1;

        int cmpLoc = Integer.compare(this.getLocation(), other.getLocation());
        if (cmpLoc != 0) return cmpLoc;

        return Double.compare(this.getPrice(), other.getPrice());
    }
    
    /*@Override
    public int compareTo(Stay other){
        if (this.getStartDate()[2] != other.getStartDate()[2]){
            return this.getStartDate()[2] - other.getStartDate()[2];
        }
        else if (this.getStartDate()[0] != other.getStartDate()[0]){
            return this.getStartDate()[0] - other.getStartDate()[0];
        }
        else if (this.getStartDate()[1] != other.getStartDate()[1]){
            return this.getStartDate()[1] - other.getStartDate()[1];
        }
        else if (this.getEndDate()[2] != other.getEndDate()[2]){
            return this.getEndDate()[2] - other.getEndDate()[2];
        }
        else if (this.getEndDate()[0] != other.getEndDate()[0]){
            return this.getEndDate()[0] - other.getEndDate()[0];
        }
        else if (this.getEndDate()[1] != other.getEndDate()[1]){
            return this.getEndDate()[1] - other.getEndDate()[1];
        }
        else if (this.getLocation() != other.getLocation()){
            return this.getLocation() - other.getLocation();
        }
        else if(this.getPrice() != other.getPrice()){
            return (int) (this.getPrice() - other.getPrice());
        }
        else{
            return 0;
        }
    }*/


}

package Model;
import java.util.ArrayList;

public class Stay implements Comparable<Stay>{
    private int[] startDate;
    private int[] endDate;
    private double price;
    private int location; //Lodge is 1, bunkhouse is 2
    private ArrayList<Issue> childrenIssues;
    private transient ArrayList<Guest> parentGuests;


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

        this.childrenIssues = new ArrayList<>();

        this.parentGuests = new ArrayList<>();
    }

    public ArrayList<Issue> getChildrenIssues(){
        return this.childrenIssues;
    }

    public void addChildIssue(Issue issue){
        this.childrenIssues.add(issue);
    }

    public void addChildrenIssues(ArrayList<Issue> issues){
        for (Issue issue : issues){
            this.childrenIssues.add(issue);
        }
    }

    public ArrayList<Guest> getParentGuests(){
        return this.parentGuests;
    }

    public void addParentGuest (Guest guest){
        this.parentGuests.add(guest);
    }

    public void addParentGuests(ArrayList<Guest> guests){
        for (Guest guest : guests){
            this.parentGuests.add(guest);
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
    }
}

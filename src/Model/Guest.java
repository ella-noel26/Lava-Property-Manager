package Model;
import java.util.ArrayList;

public class Guest implements Comparable<Guest>{
    private int id;
    private String firstName;
    private String lastName;
    private long phoneNumber;
    private String email;
    private ArrayList<Integer> childStayIds;
    private String description;
    private double discountModifier;


    public Guest (String firstName, String lastName, long phoneNumber, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.childStayIds = new ArrayList<>();
        this.description = "";
        this.discountModifier = 1.0;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getId() {
        return this.id;
    }

    public ArrayList<Integer> getChildStays(){
        return this.childStayIds;
    }

    public void addChildStayId(int stayId){
        this.childStayIds.add(stayId);
    }

    public void addChildStays(ArrayList<Integer> stayIds){
        for (int stayId : stayIds){
            this.childStayIds.add(stayId);
        }
    }
    
    public String getFirstName(){
        return this.firstName;
    }

    public void setFirstName(String name){
        this.firstName = name;
    }

    public String getLastName(){
        return this.lastName;
    }

    public void setLastName(String name){
        this.lastName = name;
    }

    public long getPhoneNumber(){
        return this.phoneNumber;
    }

    public void setPhoneNumber(long number){
        this.phoneNumber = number;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public Double getAverageRating(Model model) {
        if (model == null || this.childStayIds.isEmpty()) {
            return null;
        }
        
        int ratedCount = 0;
        double totalRating = 0;
        
        for (Integer stayId : this.childStayIds) {
            Stay stay = model.getStayById(stayId);
            if (stay != null && stay.getRating() != null) {
                totalRating += stay.getRating();
                ratedCount++;
            }
        }
        
        if (ratedCount == 0) {
            return null;
        }
        
        return totalRating / ratedCount;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String desc) {
        this.description = (desc == null) ? "" : desc;
    }

    public double getDiscountModifier() {
        return this.discountModifier;
    }

    public void setDiscountModifier(double modifier) {
        if (modifier < 0.0 || modifier > 3.0) throw new IllegalArgumentException("Discount modifier must be between 0.0 and 3.0");
        this.discountModifier = modifier;
    }

    @Override
    public int compareTo(Guest other){
        if (this.getLastName().compareTo(other.getLastName()) > 0){
            return 1;
        }
        else if (this.getLastName().compareTo(other.getLastName()) < 0){
            return -1;
        }
        else{
            if (this.getFirstName().compareTo(other.getFirstName()) > 0){
                return 1;
            }
            else if (this.getFirstName().compareTo(other.getFirstName()) < 0){
                return -1;
            }
            else{
                if (this.getPhoneNumber() > other.getPhoneNumber()){
                    return 1;
                }
                else if (this.getPhoneNumber() < other.getPhoneNumber()){
                    return -1;
                }
                else{
                    if (this.getEmail().compareTo(other.getEmail()) > 0){
                        return 1;
                    }
                    else if (this.getEmail().compareTo(other.getEmail()) > 0){
                        return -1;
                    }
                    else{
                        return 0;
                    }
                }
            }
        }
    }
}

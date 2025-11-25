package Model;
import java.util.ArrayList;

public class Guest implements Comparable<Guest>{
    private String firstName;
    private String lastName;
    private long phoneNumber;
    private String email;
    private ArrayList<Stay> childStays;


    public Guest (String firstName, String lastName, long phoneNumber, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.childStays = new ArrayList<>();
    }

    public ArrayList<Stay> getChildStays(){
        return this.childStays;
    }

    public void addChildStay(Stay stay){
        this.childStays.add(stay);
    }

    public void addChildStays(ArrayList<Stay> stays){
        for (Stay stay : stays){
            this.childStays.add(stay);
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

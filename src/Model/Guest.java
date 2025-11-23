package Model;

public class Guest {
    private String firstName;
    private String lastName;
    private long phoneNumber;
    private String email;

    public Guest (String firstName, String lastName, long phoneNumber, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
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
}

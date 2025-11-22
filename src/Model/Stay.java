package Model;

public class Stay {
    private int[] startDate;
    private int[] endDate;
    double price;
    int location; //Lodge is 1, bunkhouse is 2

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
}

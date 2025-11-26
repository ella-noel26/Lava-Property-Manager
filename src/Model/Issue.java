package Model;
import java.time.LocalDate;
import java.util.ArrayList;

public class Issue implements Comparable<Issue>{
    private int id;
    private int parentStayId;
    private int[] reportedDate;
    private int[] startedDate;
    private int[] resolvedDate;
    private String description;
    private String title;

    public Issue(String name, int[] reportDate, int[] startDate, String desc){
        this.title = name;
        
        this.reportedDate = new int[reportDate.length];
        for (int index = 0; index < reportDate.length; index++){
            this.reportedDate[index] = reportDate[index];
        }

        this.startedDate = new int[startDate.length];
        for (int index = 0; index < startDate.length; index++){
            this.startedDate[index] = startDate[index];
        }

        this.description = desc;

        this.resolvedDate = null;
    }
    
    public Issue(String name, int[] reportDate, int[] startDate, int[] resolveDate, String desc){
        this(name, reportDate, startDate, desc);

        this.resolvedDate = new int[resolveDate.length];  
        for (int index = 0; index < resolveDate.length; index++){
            this.resolvedDate[index] = resolveDate[index];
        }

    }

    public LocalDate getReportedLocalDate() {
        if (reportedDate == null || reportedDate.length < 3) return null;
        return LocalDate.of(reportedDate[2], reportedDate[0], reportedDate[1]);
    }

    public LocalDate getStartedLocalDate() {
        if (startedDate == null || startedDate.length < 3) return null;
        return LocalDate.of(startedDate[2], startedDate[0], startedDate[1]);
    }

    public LocalDate getResolvedLocalDate() {
        if (resolvedDate == null || resolvedDate.length < 3) return null;
        return LocalDate.of(resolvedDate[2], resolvedDate[0], resolvedDate[1]);
    }

    public void setReportedLocalDate(LocalDate d){
        if (d == null) return;
        this.reportedDate = new int[]{d.getMonthValue(), d.getDayOfMonth(), d.getYear()};
    }

    public void setStartedLocalDate(LocalDate d){
        if (d == null) return;
        this.startedDate = new int[]{d.getMonthValue(), d.getDayOfMonth(), d.getYear()};
    }

    public void setResolvedLocalDate(LocalDate d){
        if (d == null) { this.resolvedDate = null; return; }
        this.resolvedDate = new int[]{d.getMonthValue(), d.getDayOfMonth(), d.getYear()};
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getId() {
        return this.id;
    }
    
    public void setParentStayId(int stayId) {
        this.parentStayId = stayId;
    }
    
    public int getParentStayId() {
        return this.parentStayId;
    }

    public String getTitle(){
        return this.title;
    }

    public void setTitle(String name){
        this.title = name;
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String desc){
        this.description = desc;
    }

    public int[] getReportedDate(){
        return this.reportedDate;
    }

    public void setReportedDate(int[] date){
        for (int index = 0; index < date.length; index++){
            this.reportedDate[index] = date[index];
        }
    }

    public int[] getStartedDate(){
        return this.startedDate;
    }

    public void setStartedDate(int[] date){
        for (int index = 0; index < date.length; index++){
            this.startedDate[index] = date[index];
        }
    }

    public int[] getResolvedDate(){
        return this.resolvedDate;
    }

    public void setResolvedDate(int[] date){
        for (int index = 0; index < date.length; index++){
            this.resolvedDate[index] = date[index];
        }
    }


    @Override
    public int compareTo(Issue other){
        if (this.getReportedDate()[2] != other.getReportedDate()[2]){
            return this.getReportedDate()[2] - other.getReportedDate()[2];
        }
        else if (this.getReportedDate()[0] != other.getReportedDate()[0]){
            return this.getReportedDate()[0] - other.getReportedDate()[0];
        }
        else if (this.getReportedDate()[1] != other.getReportedDate()[1]){
            return this.getReportedDate()[1] - other.getReportedDate()[1];
        }
        else if (this.getStartedDate()[2] != other.getStartedDate()[2]){
            return this.getStartedDate()[2] - other.getStartedDate()[2];
        }
        else if (this.getStartedDate()[0] != other.getStartedDate()[0]){
            return this.getStartedDate()[0] - other.getStartedDate()[0];
        }
        else if (this.getStartedDate()[1] != other.getStartedDate()[1]){
            return this.getStartedDate()[1] - other.getStartedDate()[1];
        }
        else if (this.getTitle().compareTo(other.getTitle()) != 0){
            return this.getTitle().compareTo(other.getTitle());
        }
        else if (this.getDescription().compareTo(other.getDescription()) != 0){
            return this.getDescription().compareTo(other.getDescription());
        }
        else{
            return 0;
        }

    }
}
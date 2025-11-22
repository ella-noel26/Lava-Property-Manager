package Model;

public class Issue {
    private int[] reportedDate;
    private int[] startedDate;
    private int[] resolvedDate;
    private String description;
    private String title;

    public Issue(String name, int[] reportDate, int[] startDate, int[] resolveDate, String desc){
        this.title = name;
        
        this.reportedDate = new int[reportDate.length];
        for (int index = 0; index < reportDate.length; index++){
            this.reportedDate[index] = reportDate[index];
        }

        this.startedDate = new int[startDate.length];
        for (int index = 0; index < startDate.length; index++){
            this.startedDate[index] = startDate[index];
        }

        this.resolvedDate = new int[resolveDate.length];  
        for (int index = 0; index < resolveDate.length; index++){
            this.resolvedDate[index] = resolveDate[index];
        }

        this.description = desc;
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
}

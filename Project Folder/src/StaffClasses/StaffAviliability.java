package StaffClasses;

public class StaffAviliability {
    private StaffMember staff = null;
    /**
     * date in format DDMMYY 231124 means 23rd November 2024
     */
    private int date;
    /**
     * start in format HHMM 830 means 8:30 and 1803 means 18:03
     */
    private int start;
    /**
     * end in format HHMM 830 means 8:30 and 1803 means 18:03
     */
    private int end;

    public StaffAviliability(StaffMember staff, int date, int start, int end) {
        this.staff = staff;
        this.date = date;
        this.start = start;
        this.end = end;
    }
    // Shorter constructor
    public StaffAviliability(int date, int start, int end) {
        this.date = date;
        this.start = start;
        this.end = end;
    }
    public StaffMember getStaff() {
        return staff;
    }
    public void setStaff(StaffMember staff) {
        this.staff = staff;
    }
    public int getDate() {
        return date;
    }
    public void setDate(int date) {
        this.date = date;
    }
    public String getDateString() {
        return String.valueOf(date%100) +
                "/" + String.valueOf((date%10000)/100) +
                "/" + String.valueOf(date/10000);
    }
    public int getStart() {
        return start;
    }
    public void setStart(int start) {
        this.start = start;
    }

    public String getStartString() {
        return String.valueOf(start/100) + ":" + String.valueOf(start%100);
    }
    public int getEnd() {
        return end;
    }
    public void setEnd(int end) {
        this.end = end;
    }
    public String getEndString() {
        return String.valueOf(end/100) + ":" + String.valueOf(end%100);
    }
}

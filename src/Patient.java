import java.text.DecimalFormat;

public class Patient {

    DecimalFormat df = new DecimalFormat("0.00");
    private String username;
    private String icNum;
    private String email;
    private String dob;
    private String serviceType;

    private int day;
    private double duration;

    public Patient(String u, String i, String e, String d, String st, int dy, double dur)
    {
        username = u;
        icNum = i;
        email = e;
        dob = d;
        serviceType = st;
        day = dy;
        duration = dur;
    }



    public String getUsername() {
        return username;
    }

    public String getIcNum() {
        return icNum;
    }

    public String getEmail() {
        return email;
    }

    public int getDay() {
        return day;
    }

    public String getDob() {
        return dob;
    }

    public double getDuration() {
        return duration;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setAll(String newUsername, String newIcNum, String newEmail, String newDob, String newServiceType, int newDay, double newDuration)
    {
        username = newUsername;
        icNum = newIcNum;
        email = newEmail;
        dob = newDob;
        serviceType = newServiceType;
        day = newDay;
        duration = newDuration;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setDuration(double dura) {
        duration = dura;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIcNum(String icNum) {
        this.icNum = icNum;
    }

    public void setServiceType(String servType) {
        serviceType = servType;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    //Processor
    public double calculate()
    {
        double total = 0.0;
        if(serviceType.equalsIgnoreCase("IC"))
            total = (50 * day) + (duration * 10);
        else if(serviceType.equalsIgnoreCase("ST"))
            total = (100 * day) + (duration * 20);
        else //MP
            total = (60 * day) + (duration * 30);
        return  total;
    }

    public double getAge()
    {
        int age;
        String birth = dob.substring(0,3);
        age = (2023 - Integer.parseInt(birth));
        return age;
    }


    public String toString() {
        return String.format("%-30s %-20s %-30s %-15s %-10s %-10d %-3s", username,icNum,email,dob,serviceType,day,"") + df.format(duration);
    }
}

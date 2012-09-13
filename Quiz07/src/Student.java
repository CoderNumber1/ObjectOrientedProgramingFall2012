public class Student extends Person {
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    public Student(){super();}
    
    public Student(String name, String emailAddress, String status, double gpa){
        super(name, emailAddress);
        
        setStatus(status);
        setGpa(gpa);
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Queries">
    public String toString(){
        return String.format("%s, %s, %.1f", super.toString(), getStatus(), getGpa());
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public double getGpa(){
        return gpa;
    }
    
    public void setGpa(double gpa){
        this.gpa = gpa;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Fields">
    private String status;
    private double gpa;
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constants">
    public static final String STATUS_FRESHMAN = "Freshman";
    public static final String STATUS_SOPHMORE = "Sophmore";
    public static final String STATUS_JUNIOR = "Junior";
    public static final String STATUS_SENIOR = "Senior";
    // </editor-fold>
}

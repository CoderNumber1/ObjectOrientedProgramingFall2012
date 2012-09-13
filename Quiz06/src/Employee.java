public class Employee extends Person {
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    public Employee(){super();}
    
    public Employee(String name, String emailAddress, String office, double salary){
        super(name, emailAddress);
        
        setOffice(office);
        setSalary(salary);
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Queries">
    public String toString(){
        return String.format("%s, %s, $%.2f", super.toString(), getOffice(), getSalary());
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Fields">
    private String office;
    private double salary;
    // </editor-fold>
}

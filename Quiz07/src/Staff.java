public class Staff extends Employee {
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    public Staff(){super();}
    
    public Staff(String name, String emailAddress, String office, double salary, String title){
        super(name, emailAddress, office, salary);
        
        setTitle(title);
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Queries">
    public String toString(){
        return String.format("%s, %s", super.toString(), getTitle());
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Fields">
    private String title;
    // </editor-fold>
}

public class Person {
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    public Person(){}
    
    public Person(String name, String emailAddress){
        setName(name);
        setEmailAddress(emailAddress);
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Queries">
    public String toString(){
        return String.format("%s, %s", getName(), getEmailAddress());
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Fields">
    private String name;
    private String emailAddress;
    // </editor-fold>
}

    // <editor-fold defaultstate="collapsed" desc="Constructors">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Queries">

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Getters and Setters">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Fields">
    // </editor-fold>

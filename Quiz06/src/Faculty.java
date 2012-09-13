public class Faculty extends Employee {
    // <editor-fold defaultstate="collapsed" desc="Constructors">
    public Faculty(){super();}
    
    public Faculty(String name, String emailAddress, String office, double salary, String rank){
        super(name, emailAddress, office, salary);
        
        setRank(rank);
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Queries">
    public String toString(){
        return String.format("%s, %s", super.toString(), getRank());
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Fields">
    private String rank;
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constants">
    public static final String RANK_PROFESSOR = "Professor";
    public static final String RANK_ASSOICIATE_PROFESSOR = "Associate Professor";
    public static final String RANK_ASSISTANT_PROFESSOR = "Assistant Professor";
    public static final String RANK_LECTURER = "Lecturer";
    // </editor-fold>
}

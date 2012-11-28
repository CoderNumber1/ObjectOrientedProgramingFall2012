public class FieldSection {
    int x;
    int y;
    
    public FieldSection(){}
    public FieldSection(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public boolean equals(Object obj) {
        FieldSection compareTo = (FieldSection)obj;
        
        return this.x == compareTo.x && this.y == compareTo.y;
    }
}

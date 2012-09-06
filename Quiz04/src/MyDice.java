public class MyDice {
    public void rollDice(int numberOfRolls){
        for(int i = 0; i < numberOfRolls; i ++){
            int roll = (int)(Math.random() * 6 + 1);
            
            switch(roll){
                case 1:
                    this.OnesCount++;
                    break;
                case 2:
                    this.TwosCount++;
                    break;
                case 3:
                    this.ThreesCount++;
                    break;
                case 4:
                    this.FoursCount++;
                    break;
                case 5:
                    this.FivesCount++;
                    break;
                case 6:
                    this.SixesCount++;
                    break;
            }
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public int getOnesCount() {
        return OnesCount;
    }

    public void setOnesCount(int OnesCount) {
        this.OnesCount = OnesCount;
    }

    public int getTwosCount() {
        return TwosCount;
    }

    public void setTwosCount(int TwosCount) {
        this.TwosCount = TwosCount;
    }

    public int getThreesCount() {
        return ThreesCount;
    }

    public void setThreesCount(int ThreesCount) {
        this.ThreesCount = ThreesCount;
    }

    public int getFoursCount() {
        return FoursCount;
    }

    public void setFoursCount(int FoursCount) {
        this.FoursCount = FoursCount;
    }

    public int getFivesCount() {
        return FivesCount;
    }

    public void setFivesCount(int FivesCount) {
        this.FivesCount = FivesCount;
    }

    public int getSixesCount() {
        return SixesCount;
    }

    public void setSixesCount(int SixesCount) {
        this.SixesCount = SixesCount;
    }
    // </editor-fold>
    
    //  <editor-fold defaultstate="collapsed" desc="Fields">
    private int OnesCount;
    private int TwosCount;
    private int ThreesCount;
    private int FoursCount;
    private int FivesCount;
    private int SixesCount;
    //  </editor-fold>
}

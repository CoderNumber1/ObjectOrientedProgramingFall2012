public class MyDeck {
    public MyDeck(){
        this.myCards = new MyCard[52];
        
        char[] suits = {'S', 'D', 'H', 'C'};
        
        for(int s = 0; s < 4; s++){
            for(int v = 1; v <= 13; v ++){
                int cardIndex = 13 * s + v - 1;
                
                this.myCards[cardIndex] = new MyCard(suits[s], v);
            }
        }
    }
    
    public void shuffleDeck(){
        for(int i = 0; i < this.myCards.length; i ++){
            int targetIndex = -1;
            
            //Find a new place to put the card that isn't its current position.
            do{
                targetIndex = (int)(Math.random() * (this.myCards.length));
            } while(targetIndex == i);
            
            //Grab the current card, put the target index card in the current
            // cards place, and the current card at the target index.
            MyCard thisCard = this.myCards[i];
            this.myCards[i] = this.myCards[targetIndex];
            this.myCards[targetIndex] = thisCard;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public MyCard[] getMyCards() {
        return myCards;
    }

    public void setMyCards(MyCard[] myCards) {
        this.myCards = myCards;
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Fields">
    private MyCard[] myCards;
    // </editor-fold>
}

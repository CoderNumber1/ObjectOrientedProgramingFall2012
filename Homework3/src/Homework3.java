
import java.util.Scanner;

public class Homework3 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        TicTacToe game = new TicTacToe();
        TicTacToeAI ai = new TicTacToeAI(game, TicTacToe.PLAYER_X_MARK);
        
        game.startGame();
        
        do{
            System.out.println(game.getGameStatusString());
            
            if(game.doesValidMoveExist()){
                String input;

                do{
                    System.out.print("Your turn - where (row col)? ");

                    input = scanner.nextLine();
                }while(!game.isMoveValid(input));

                game.move(input, TicTacToe.PLAYER_O_MARK);
                game.evaluateWinner();
            }
            
            if(!game.isGameWon() && game.doesValidMoveExist()){
                Move aiMove = ai.determineMove();
                
                if(aiMove != null){
                    game.move(aiMove.getRow(), aiMove.getColumn(), TicTacToe.PLAYER_X_MARK);
                    game.evaluateWinner();
                }
            }
        }while(!game.isGameWon() && game.doesValidMoveExist());
        
        System.out.println(game.getGameStatusString());
        
        String message;
        
        if(game.isGameWon()){
            if(game.didPlayerWin(TicTacToe.PLAYER_O_MARK)){
                message = "You WON!!!";
            }
            else{
                message = "The AI bested you.";
            }
        }
        else{
            message = "It was a tie.";
        }
        
        System.out.println(message);
    }
}

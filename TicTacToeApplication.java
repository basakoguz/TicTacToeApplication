import java.util.Scanner;

public class TicTacToeApplication {

  public static void main (String[] args) {

    Scanner scan = new Scanner(System.in);

    boolean doYouWantToPlay = true;

    while (doYouWantToPlay) {

      System.out.println("\nWelcome to Tic Tac Toe! Your are about to go against "
      + "the master of Tic Tac Toe. \nAre you ready? I hope you are!\n\nBUT FIRST, you "
      + "must pick what character you want to be and which character I will be...");

      System.out.println();

      System.out.println("Enter a single character that will represent you on the board.");
      char playerToken = scan.next().charAt(0);

      System.out.println("Enter a single character that will represent your opponent on the board.");
      char opponentToken = scan.next().charAt(0);

      TicTacToe game = new TicTacToe(playerToken, opponentToken);
      AI ai = new AI();

      System.out.println();
      System.out.println("Now we can start the game. To play, enter a number and your token shall be put "
      + "in its place.\nThe numbrs go from 1-9, left to right. We shall see who will win this round.");
      TicTacToe.printIndexBoard();
      System.out.println();

      while(game.gameOver().equals("notOver")){
        if(game.currentMarker == game.userMarker){
          System.out.println("\nIt's your turn! Enter a spot for your token");
          int spot = scan.nextInt();
          while (!game.playTurn(spot)){
            System.out.println("Try again. " + spot + " is taken or out of range.");
            spot = scan.nextInt();
          }
          System.out.println("\nYou picked " + spot + "!");
        } else {
          System.out.println("\nIt's my turn!");
          int aiSpot = ai.pickSpot(game);
          game.playTurn(aiSpot);
          System.out.println("I picked " + aiSpot + "...");
        }
        System.out.println();
        game.printBoard();
      }
      System.out.println(game.gameOver());
      System.out.println();
      System.out.println("Do you want to play again? Enter Y if you do. "
      + "Enter anything else if you are tired of me.");
      char response = scan.next().charAt(0);
      doYouWantToPlay = (response == 'Y');
      System.out.println();
      System.out.println();
    }
  }
}

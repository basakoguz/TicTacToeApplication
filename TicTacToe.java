//Game visualisation:

//WHAT WE STORE AS OUR BOARD:
// 0 | 1 | 2
//-----------
// 3 | 4 | 5
//-----------
// 6 | 7 | 8
//-----------

//WHAT THE USER THINKS THEY CHOOSE:
// 1 | 2 | 3
//-----------
// 4 | 5 | 6
//-----------
// 7 | 8 | 9
//-----------

//EMPTY BOARD LOOKS LIKE:
// - | - | -
//-----------
// - | - | -
//-----------
// - | - | -
//-----------
//EXAMPLE OF BOARD DURING A GAME:
// O | - | O
//-----------
// - | X | -
//-----------
// - | - | X
//-----------


public class TicTacToe {

  protected char[] board;
  protected char userMarker;
  protected char aiMarker;
  protected char winner;
  protected char currentMarker;

  /*To determine parameters, think what we would want to customise
  when starting a new game.*/
  public TicTacToe(char playerToken, char aiMarker) {
    this.userMarker = playerToken;
    this.aiMarker = aiMarker;
    this.winner = '-'; //There is no winner yet
    this.board = setBoard();
    this.currentMarker = userMarker;
  }

/*We didn't use any properties of our instance within this method.
So this method can be static.*/

  public static char[] setBoard() {
    char[] board = new char[9];
    for (int i = 0; i<board.length; i++){
      board[i] ='-'; //All slots are empty at the beginning of the game.
    }
    return board;
  }

/*Spot is the point that the user wants to make their move.*/
  public boolean playTurn(int spot) {
    /*Checks if we are able to play at the given time. (If it's our turn)*/
      boolean isValid = withinRange(spot) && !isSpotTaken(spot);
      if(isValid){
        //User thinks they choose 5 but its corresponding slot in the array is 4.
        //Put the marker(whoever it belongs to X or O) into the board.
        board[spot-1] = currentMarker; //Saves the move.
        //Flips the turn to the other side. This is an if statement.
        currentMarker = (currentMarker == userMarker) ? aiMarker : userMarker;
      }
        return isValid;
        //Returns false to ask the user to choose a valid slot for their move.
  }

  //Checks if the choosen spot is within our range of 9 slots.
  public boolean withinRange(int number) {
    return number > 0 && number < board.length + 1;
  }

  //Checks if the chosen spot is taken (not empty)
  public boolean isSpotTaken(int number) {
    return board[number-1] != '-';
  }

  //Prints the current board at a given time.
  public void printBoard() {
    // | - | - | - |
    // -------------
    // | - | - | - |
    // -------------
    // | - | - | - |
    // -------------
    System.out.println();
    for (int i = 0; i<9; i++){
      if(i % 3 == 0 && i != 0){
        System.out.print(" | \n");
        System.out.println("--------------");
      }
      System.out.print(" | " + (board[i]));
    }
    System.out.print(" | ");
    System.out.println();
  }

  //Prints an informative board with the index numbers in the slots.
  //Indexes will be shown like the user version. (Starting from 1 not 0 as in the array.)
  public static void printIndexBoard(){

    System.out.println();
    for (int i = 0; i<9; i++){
      if(i % 3 == 0 && i != 0){
        System.out.print(" | \n");
        System.out.println("--------------");
      }
      System.out.print(" | " + (i+1));
    }
    System.out.print(" | ");
    System.out.println();
  }

  //Check if there is a "three in a row" of the same marker type.
  public boolean isThereAWinner(){
    /*Try to group winning scenarios into smallest possible way that
     groups have a common slot.*/
    boolean diagonalsAndMiddles = (rightDi() || leftDi() || middleRow() || secondCol()) && board[4] != '-';
    boolean topAndFirst = (topRow() || firstCol()) && board[0] != '-';
    boolean bottomAndThird = (bottomRow() || thirdCol()) && board[8] != '-';
    if (diagonalsAndMiddles){
      this.winner = board[4];
    } else if (topAndFirst) {
      this.winner = board[0];
    } else if (bottomAndThird) {
      this.winner = board[8];
    }
    //One of them is winner (returns true) if there is a winner.
    return diagonalsAndMiddles || topAndFirst || bottomAndThird;
  }

  public boolean topRow() {
    return board[0] == board[1] && board[1] == board[2];
  }

  public boolean middleRow() {
      return board[3] == board[4] && board[4] == board[5];
  }

  public boolean bottomRow() {
    return board[6] == board[7] && board[7] == board[8];
  }

  public boolean firstCol() {
    return board[0] == board[3] && board[3] == board[6];
  }

  public boolean secondCol() {
    return board[1] == board[4] && board[4] == board[7];
  }

  public boolean thirdCol() {
    return board[2] == board[5] && board[5] == board[8];
  }

  public boolean rightDi() {
    return board[0] == board[4] && board[4] == board[8];
  }

  public boolean leftDi() {
    return board[2] == board[4] && board[4] == board[6];
  }

  public boolean isTheBoardFilled() {
    for (int i = 0; i<board.length; i++){
      if(board[i] == '-'){
        return false;
      }
    }
    return true;
  }

  public String gameOver(){
    boolean didSomeoneWin = isThereAWinner();
    if (didSomeoneWin){
      return "\nWe have a winner! The winner is " + this.winner + ".";
    } else if (isTheBoardFilled()) {
      return "Draw: Game Over!";
    } else {
      return "notOver";
    }
  }


}

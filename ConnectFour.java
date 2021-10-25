import java.util.Scanner;

public class ConnectFour {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // initialize variables
        int row;
        int column;
        boolean exit;
        boolean game = true;
        char player1 = 'x';
        char player2 = 'o';
        int colChoice;
        int turns = 0;
        int num;
        int choiceNum = 0;

        // prompt the user to assign the length and height of the board
        System.out.println("What would you like the height of the board to be?");
        row = scanner.nextInt();
        System.out.println("What would you like the length of the board to be?");
        column = scanner.nextInt();
        char[][] board = new char[row][column];

        // print the board
        initializeBoard(board);
        printBoard(board);

        // assign player's roles
        System.out.println("Player 1: x");
        System.out.println("Player 2: o");

        // loop that will continue until game is over
        while (game) {
           // if/else statement to interchange between the players' turns
           if (turns % 2 == 0) {
               System.out.println("Player 1: Which column would you like to choose?");
           }
           else {
               System.out.println("Player 2: Which column would you like to choose?");
           }
           colChoice = scanner.nextInt();
           turns++;
           choiceNum++;

           // insert player 1's choice into the board and check if they won
           if (choiceNum % 2 == 1) {
               num = insertChip(board, colChoice, player1);
               printBoard(board);
               exit = checkIfWinner(board, colChoice, num, player1);

               if (exit) {
                   System.out.println("Player 1 won the game!");
                   game = false;
                   break;
               }
               else if (choiceNum == row * column) {
                   System.out.println("Draw. Nobody wins.");
                   game = false;
               }
           }
           // insert player 2's choice into the board and check if they won
           else if (choiceNum % 2 == 0) {
               num = insertChip(board, colChoice, player2);
               printBoard(board);
               exit = checkIfWinner(board, colChoice, num, player2);

               if (exit) {
                   System.out.print("Player 2 won the game!");
                   game = false;
                   break;
               }
               else if (choiceNum == row * column) {
                   System.out.println("Draw. Nobody wins.");
                   game = false;
                   break;
               }
           }
        }
    }

    // method for assigning dashes to the empty board
    public static void initializeBoard(char[][] array) {
        for (int row = 0; row < array.length; row++) {
            for (int col = 0; col < array[0].length; col++) {
                array[row][col] = '-';
            }
        }
    }

    // method for displaying the board
    public static void printBoard(char[][] board) {
        for (int row = board.length - 1; row >= 0; row--) {
            for (int col = 0; col < board[0].length; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }

    // method for inserting chips into the game board
    public static int insertChip(char[][]array, int col, char chipType) {
        for (int row = 0; row < array.length; row++) {
            if (array[row][col] == '-') {
                array[row][col] = chipType;
                return row;
            }
        }
        return -1;
    }


    // method to determine if there is a winner in the game
    public static boolean checkIfWinner(char[][] array, int col, int row, char chipType) {
        int num = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i][col] == chipType) {
                num++;
                if (num == 4) {
                    return true; // if there are four of the same chipType in the same column, that player wins
                }
            }
            else {
                num = 0;
            }
        }
            num = 0;
        for (int i = 0; i < array[0].length; i++) {
            if (array[row][i] == chipType) {
                num++;
                if (num == 4) {
                    return true; // if there are four of the same chipType in the same row, that player wins
                }
            }
            else {
                num = 0;
            }
        }
        return false; // there is no winner yet or it is a draw
    }
}

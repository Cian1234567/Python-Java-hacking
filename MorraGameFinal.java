import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class MorraGameFinal {
    static Scanner scan = new Scanner(System.in); // makes scanner available in all methods
    static Random random = new Random(); // random available in all methods
    
    // Declare these as static so they can be accessed from any method
    static ArrayList<Integer> userHistory = new ArrayList<>();
    static ArrayList<Integer> compHistory = new ArrayList<>();
    
    public static void menu() { // Menu for game start, history, exit
        char menuChoice;
        
        System.out.println("Pick Option A to start the game, B to show the game history, or C to quit the game");
        menuChoice = scan.next().toUpperCase().charAt(0);
        
        if (menuChoice == 'A') {
            startGame(); // calls startGame method
        } else if (menuChoice == 'B') {
            viewHistory(); // calls viewHistory method
        } else if (menuChoice == 'C') {
            System.out.println("You have quit the game");
            System.exit(0); // exits game
        }
    }

    public static void startGame() {
        char userChoice;
        int userNum;
        int compNum;
        char compChoice;
        int userPoints = 0;
        int compPoints = 0;
        
        // User picks Odd or Even
        do {
            System.out.println("Pick (O)dd or (E)ven:");
            scan.nextLine(); // Added to clear the newline character from previous input
            userChoice = scan.nextLine().toUpperCase().charAt(0);
            
            if (userChoice == 'O') {
                System.out.println("You have chosen Odd");
                compChoice = 'E';
                break;
            } else if (userChoice == 'E') {
                System.out.println("You have chosen Even");
                compChoice = 'O';
                break;
            } else {
                System.out.println("Invalid choice, pick O or E");
            }
        } while (true);
        
        // Game loop
        while (userPoints < 6 && compPoints < 6) {
            System.out.println("Pick a number between 1 and 10:");
            userNum = scan.nextInt();
            
            while (userNum < 1 || userNum > 10) {
                System.out.println("Invalid number, pick between 1 and 10:");
                userNum = scan.nextInt();
            }
            
            compNum = random.nextInt(10) + 1;
            System.out.println("The computer chose: " + compNum);
            System.out.println("The computer picked: " + compChoice);
            
            userHistory.add(userNum); // adds userNum to history
            compHistory.add(compNum); // adds compNum to history
            
            int roundWin = userNum + compNum;
            int userDiff = Math.abs(roundWin - userNum);
            int compDiff = Math.abs(roundWin - compNum);
            
            if (userDiff < compDiff) {
                userPoints += 1;
                System.out.println("Your number was closer to the sum, you get one point");
            } else if (userDiff > compDiff) {
                compPoints += 1;
                System.out.println("The computer's number was closer to the sum, computer gets one point");
            }
            
            if (roundWin % 2 == 0 && userChoice == 'E') {
                System.out.println("You win this round! You get 2 points");
                userPoints += 2;
            } else if (roundWin % 2 == 1 && userChoice == 'O') {
                System.out.println("You win this round! You get 2 points");
                userPoints += 2;
            } else {
                System.out.println("You lost this round");
                compPoints += 2;
            }
            
            System.out.println("You now have " + userPoints + " points");
            System.out.println("Computer now has " + compPoints + " points");
            System.out.println("--------------------------------------"); // lines to separate each round visually
        }
        int again;
        // Declare winner
        if (userPoints >= 6) {
            System.out.println("Congratulations! You win the game!");
            System.out.println("Would you like to play again? Type 1 if you would: ");
            again = scan.nextInt();
            if (again == 1) {
                startGame();
            } else {
                System.exit(0);
            }
        } else {
            System.out.println("Game over! The computer wins!");
            System.out.println("Would you like to play again? Type 1 if you would: ");
            again = scan.nextInt();
            if (again == 1) {
                startGame();
            } else {
                System.exit(0);
            }
        }
    }

    public static void viewHistory() {
        // View history of the game
        System.out.println("User history: ");
        for (int i = 0; i < userHistory.size(); i++) {
            System.out.println("Round " + (i + 1) + ": " + userHistory.get(i));
        }
        
        System.out.println("Computer history: ");
        for (int i = 0; i < compHistory.size(); i++) {
            System.out.println("Round " + (i + 1) + ": " + compHistory.get(i));
        }
    }
    
    public static void main(String[] args) {
        menu(); // Starts the menu and allows the user to choose an option
    }
}







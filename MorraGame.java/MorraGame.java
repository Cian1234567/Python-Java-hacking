import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MorraGame {
    static ArrayList<Integer> userHistory = new ArrayList<>();
    static ArrayList<Integer> compHistory = new ArrayList<>();
    static ArrayList<Integer> userWins = new ArrayList<>();
    static ArrayList<Integer> compWins = new ArrayList<>();
    static int[] userOddEven = {0, 0}; // Odd, Even count for user
    static int[] compOddEven = {0, 0}; // Odd, Even count for comp
    static ArrayList<Integer> extraPoints = new ArrayList<>();
    static Random random = new Random();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        while (true) {
            System.out.println("Morra Game rules: Pick either odd or even, then both players pick between 1 and 10,\n2 points if the sum of the two chosen numbers matches the odd or even you picked\n1 point if your number is closer to the sum. You win once you get to 6 points.");
            System.out.println("Pick Option A to start the game, B to show the game history, or C to quit the game");
            String menuChoice = scanner.next().toUpperCase();

            if (menuChoice.equals("A")) {
                startGame();
            } else if (menuChoice.equals("B")) {
                viewHistory();
            } else if (menuChoice.equals("C")) {
                System.out.println("You have quit the game");
                System.exit(0);
            } else {
                System.out.println("Invalid choice. Please choose A, B, or C.");
            }
        }
    }

    public static void startGame() {
        int userPoints = 0;
        int compPoints = 0;
        String userChoice;
        String compChoice;

        while (true) {
            System.out.println("Pick (O)dd or (E)ven:");
            userChoice = scanner.next().toUpperCase();

            if (userChoice.equals("O")) {
                System.out.println("You have chosen Odd");
                compChoice = "E";
                break;
            } else if (userChoice.equals("E")) {
                System.out.println("You have chosen Even");
                compChoice = "O";
                break;
            } else {
                System.out.println("Invalid choice, pick O or E");
            }
        }

        while (userPoints < 6 && compPoints < 6) {
            System.out.println("Pick a number between 1 and 10:");
            int userNum = scanner.nextInt();

            while (userNum < 1 || userNum > 10) {
                System.out.println("Invalid number, pick between 1 and 10:");
                userNum = scanner.nextInt();
            }

            int compNum = random.nextInt(10) + 1; // Ensures range is 1 to 10
            System.out.println("The computer chose: " + compNum);
            System.out.println("The computer picked: " + compChoice);

            userHistory.add(userNum);
            compHistory.add(compNum);

            if (userNum % 2 == 0) {
                userOddEven[1]++;
            } else {
                userOddEven[0]++;
            }

            if (compNum % 2 == 0) {
                compOddEven[1]++;
            } else {
                compOddEven[0]++;
            }

            int roundWin = userNum + compNum;
            int userDiff = Math.abs(roundWin - userNum);
            int compDiff = Math.abs(roundWin - compNum);
            int roundExtraPoints = 0;

            if (userDiff < compDiff) {
                userPoints++;
                System.out.println("Your number was closer to the sum, you get 1 point.");
            } else if (userDiff > compDiff) {
                compPoints++;
                System.out.println("The computer's number was closer to the sum, computer gets 1 point.");
            } else {
                System.out.println("Both numbers were equally close, no points awarded for closeness.");
            }

            if (roundWin % 2 == 0) {
                if (userChoice.equals("E")) {
                    userPoints += 2;
                    roundExtraPoints = 2;
                    System.out.println("You win this round! You get 2 extra points.");
                } else {
                    compPoints += 2;
                    System.out.println("Computer wins the round! It gets 2 extra points.");
                }
            } else {
                if (userChoice.equals("O")) {
                    userPoints += 2;
                    roundExtraPoints = 2;
                    System.out.println("You win this round! You get 2 extra points.");
                } else {
                    compPoints += 2;
                    System.out.println("Computer wins the round! It gets 2 extra points.");
                }
            }

            extraPoints.add(roundExtraPoints);
            System.out.println("You now have " + userPoints + " points");
            System.out.println("Computer now has " + compPoints + " points");
            System.out.println("--------------------------------------");
        }

        if (userPoints >= 6) {
            System.out.println("Congratulations! You win the game!");
        } else {
            System.out.println("Game over! The computer wins!");
        }

        System.out.println("Would you like to play again? Type 1 if you would, 2 to go back to the menu: ");
        int again = scanner.nextInt();

        if (again == 1) {
            startGame();
        } else {
            menu();
        }
    }

    public static void viewHistory() {
        System.out.println("User history: ");
        for (int i = 0; i < userHistory.size(); i++) {
            System.out.println("Round " + (i + 1) + ": " + userHistory.get(i));
        }

        System.out.println("Computer history: ");
        for (int i = 0; i < compHistory.size(); i++) {
            System.out.println("Round " + (i + 1) + ": " + compHistory.get(i));
        }
    }
}

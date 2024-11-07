import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Game settings
        int lowerBound = 1;
        int upperBound = 100;  // Change this to 1000 for a wider range
        int maxAttempts = 10;   // Limited attempts
        int numberToGuess = generateRandomNumber(lowerBound, upperBound);

        int attemptsLeft = maxAttempts;
        boolean hasGuessedCorrectly = false;

        System.out.println("Welcome to the number game! Try to guess the number in as few attempts as possible.");
        System.out.println("Guess a number between " + lowerBound + " and " + upperBound);
        System.out.println("You have " + maxAttempts + " attempts to guess the correct number.");
        
        while (attemptsLeft > 0 && !hasGuessedCorrectly) {
            System.out.print("Enter your guess: ");
            
            // Validate user input to ensure it's an integer and within bounds
            int playerGuess = -1;
            boolean validInput = false;

            while (!validInput) {
                try {
                    playerGuess = scanner.nextInt(); // Try reading the number

                    // Check if the number is within the valid range
                    if (playerGuess < lowerBound || playerGuess > upperBound) {
                        System.out.println("Please enter a number between " + lowerBound + " and " + upperBound + ".");
                    } else {
                        validInput = true;  // Valid input, exit the loop
                    }
                } catch (InputMismatchException e) {
                    // Handle non-integer input (e.g., letters or special characters)
                    System.out.println("Invalid input! Please enter a valid integer.");
                    scanner.nextLine(); // Clear the invalid input
                }
            }

            // Decrement attempts after a valid input
            attemptsLeft--;

            // Check if the guessed number is correct
            if (playerGuess == numberToGuess) {
                hasGuessedCorrectly = true;
                System.out.println("Congratulations! You guessed the correct number: " + numberToGuess);
            } else {
                if (playerGuess < numberToGuess) {
                    System.out.println("Too Low!");
                } else {
                    System.out.println("Too High!");
                }
                if (attemptsLeft > 0) {
                    System.out.println("You have " + attemptsLeft + " attempts left.");
                }
            }
        }

        if (!hasGuessedCorrectly) {
            System.out.println("Sorry! You've used all your attempts. The correct number was: " + numberToGuess);
        }

        scanner.close();
    }

    // Generate a random number between lowerBound and upperBound
    private static int generateRandomNumber(int lowerBound, int upperBound) {
        Random random = new Random();
        return random.nextInt(upperBound - lowerBound + 1) + lowerBound;
    }
}

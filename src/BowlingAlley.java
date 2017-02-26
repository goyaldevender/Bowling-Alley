/**
 * Created by Devender on 14/02/17.
 */

// Game Class
class Game implements Runnable {

    private Integer totalScore;
    //public void basicConfig(){}

    public void simulateExampleDemo() {

        // Initializing the total score
        totalScore = 0;

        calculateScore(1, 4);
        calculateScore(4, 5);
        calculateScore(6, 4);
        calculateScore(5, 5);
        calculateScore(0, 10);
        calculateScore(0, 1);
        calculateScore(7, 3);
        calculateScore(6, 4);
        calculateScore(5, 0);
        calculateScore(2, 0);

    }

    public void simulateRandomDemo() {
        int number1, number2, number3;

        totalScore = 0;

        for (int i = 0; i < 10; i++) {

            number1 = (int) (Math.random() * 11);

            if (number1 != 10)
                number2 = (int) (Math.random() * 11) % (10 - number1);
            else
                number2 = 0;

            if (i == 9) {
                if (number1 + number2 == 10 || number1 == 10 || number2 == 10) {

                    number3 = (int) (Math.random() * 11);
                    calculateScore(number1, number2, number3);
                } else {
                    calculateScore(number1, number2);
                }
            } else {
                calculateScore(number1, number2);
            }
        }

    }

    private void calculateScore(int firstTry, int secondTry) {

        // Printing the current set numbers
//        System.out.println(firstTry + " " + secondTry);

        totalScore += firstTry;
        totalScore += secondTry;

        if (firstTry == 10 || secondTry == 10)
            totalScore += 10;
        else if (firstTry + secondTry == 10)
            totalScore += 5;

    }

    // Calculate the score in case of 10th set and spare/strike
    private void calculateScore(int firstTry, int secondTry, int thirdTry) {

        // Printing the current set numbers
//        System.out.println(firstTry + " " + secondTry + " " + secondTry);

        totalScore += firstTry;
        totalScore += secondTry;
        totalScore += thirdTry;

        if (firstTry == 10 || secondTry == 10)
            totalScore += 10;
        else if (firstTry + secondTry == 10)
            totalScore += 5;

        if (thirdTry == 10)
            totalScore += 10;
    }

    // Function to return the total score
    public Integer getTotalScore() {
        return totalScore;
    }

    @Override
    public void run() {
        simulateRandomDemo();
    }
}

public class BowlingAlley {

    // Storing the total sum of the Game.
    Integer totalSum;

    public static void main(String[] args) {

        // Creating a game object
        Game game = new Game();

        // Simulate an example
        game.simulateExampleDemo();
        System.out.println(game.getTotalScore());

        System.out.println();

        // Simulate a random game for player 1
        Game game1 = new Game();
        Thread player1 = new Thread(game1);
        player1.start();


        // Simulate a random game for player 2
        Game game2 = new Game();
        Thread player2 = new Thread(game2);
        player2.start();

        // Printing the total Scores
        try {
            player1.join();
            System.out.println("Score for player 1 = " + game1.getTotalScore());

            System.out.println();

            player2.join();
            System.out.println("Score for player 2 = " + game2.getTotalScore());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

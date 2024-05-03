package hu.gde.hzoxye.eloalk;

import hu.gde.hzoxye.eloalk.boosters.DoubleSteps;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {

    static Random rand = new Random();
    private List<Snail> snails;
    private List<Snail> winners;
    private Snail userBet;
    private int currentTurn = 0;
    private boolean isGameOver = false;

    public Game(List<Snail> snails) {
        this.snails = snails;

        System.out.println("Let's start the game…");
    }

    public void makeTurn() throws Exception {
        if (isGameOver) {
            throw new Exception("Game is over.");
        }

        currentTurn++;

        System.out.printf("Round #%d\n", currentTurn);

        int twentyPercentChanceOfZero = rand.nextInt(100 / 20);
        if (twentyPercentChanceOfZero == 0) {
            Snail randomSnail = getRandomSnail(snails);
            randomSnail.addBooster(new DoubleSteps());
        }

        snails.forEach(Snail::step);
    }

    public void gameOver() {
        isGameOver = true;
        winners = new ArrayList<>(List.copyOf(snails));
    }

    public void printResults() throws Exception {
        if (!isGameOver) {
            throw new Exception("Game is not over.");
        }

        winners.sort(Comparator.comparing(Snail::getStepsMade, Comparator.reverseOrder()));

        for (int place = 1; place <= winners.size(); place++) {
            System.out.printf("Place #%d goes to snail #%d\n", place, winners.get(place - 1).getId());
        }
    }

    public boolean isUserBetIsTheWinnerSnail() throws Exception {
        if (!isGameOver) {
            throw new Exception("Game is not over.");
        }

        return winners.getFirst().equals(userBet);
    }

    public void makeUserBet() {
        List<Integer> ids = snails.stream().map(Snail::getId).toList();
        String choices = String.join(", ", ids.stream().map(String::valueOf).toList());

        do {
            Scanner scanner = new Scanner(System.in);
            System.out.printf("Which snail will win? (%s) ", choices);
            String bet = scanner.nextLine();

            try {
                int snailId = Integer.parseInt(bet);
                if (ids.contains(snailId)) {
                    userBet = snails.stream().filter(s -> s.getId() == snailId).findFirst().orElseThrow();
                    System.out.printf("User bet is on snail #%d.\n", userBet.getId());
                    return;
                }
            } catch (NumberFormatException _) {
            }
        } while (true);
    }

    private Snail getRandomSnail(List<Snail> snails) {
        return snails.get(rand.nextInt(snails.size()));
    }
}

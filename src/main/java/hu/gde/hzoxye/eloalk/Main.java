package hu.gde.hzoxye.eloalk;

import hu.gde.hzoxye.eloalk.boosters.DoubleSteps;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static Random rand = new Random();

    public static void main(String[] args) {
        List<Snail> snails = List.of(
                new Snail(1, Snail.Color.RED),
                new Snail(2, Snail.Color.GREEN),
                new Snail(3, Snail.Color.BLUE)
        );

        Snail userBet = getBetFromUser(snails);

        System.out.println("Let's start the game…");

        int turns = 5;

        for (int turn = 1; turn <= turns; turn++) {
            makeTurn(turn, snails);
        }

        printResults(snails, userBet);
    }

    private static void makeTurn(int turn, List<Snail> snails) {
        System.out.printf("Round #%d\n", turn);

        int twentyPercentChanceOfZero = rand.nextInt(100 / 20);
        if (twentyPercentChanceOfZero == 0) {
            Snail randomSnail = getRandomSnail(snails);
            randomSnail.addBooster(new DoubleSteps());
        }

        snails.forEach(Snail::step);
    }


    private static Snail getBetFromUser(List<Snail> snails) {
        List<Integer> ids = snails.stream().map(Snail::getId).toList();
        String choices = String.join(", ", ids.stream().map(String::valueOf).toList());

        do {
            Scanner scanner = new Scanner(System.in);
            System.out.printf("Which snail will win? (%s) ", choices);
            String bet = scanner.nextLine();

            try {
                int snailId = Integer.parseInt(bet);
                if (ids.contains(snailId)) {
                    Snail userBet = snails.stream().filter(s -> s.getId() == snailId).findFirst().orElseThrow();
                    System.out.printf("Your bet is on snail #%d.\n", userBet.getId());
                    return userBet;
                }
            } catch (NumberFormatException _) {
            }
        } while (true);
    }

    private static Snail getRandomSnail(List<Snail> snails) {
        return snails.get(rand.nextInt(snails.size()));
    }

    private static void printResults(List<Snail> snails, Snail userBet) {
        List<Snail> winners = new ArrayList<>(List.copyOf(snails));

        winners.sort(Comparator.comparing(Snail::getStepsMade, Comparator.reverseOrder()));

        for (int place = 1; place <= winners.size(); place++) {
            System.out.printf("Place #%d goes to snail #%d\n", place, winners.get(place - 1).getId());
        }

        if (winners.getFirst().equals(userBet)) {
            System.out.println("Your bet was right, you won also!");
        }
    }
}

package hu.gde.hzoxye.eloalk;

import hu.gde.hzoxye.eloalk.boosters.DoubleSteps;

import java.util.Scanner;
import java.util.List;
import java.util.Random;

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
            System.out.printf("Round #%d\n", turn);

            int twentyPercentChanceOfZero = rand.nextInt(100 / 20);
            if (twentyPercentChanceOfZero == 0) {
                Snail randomSnail = getRandomSnail(snails);
                randomSnail.addBooster(new DoubleSteps());
            }

            snails.forEach(Snail::step);
        }

        // print result
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
}

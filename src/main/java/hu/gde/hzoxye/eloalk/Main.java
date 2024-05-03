package hu.gde.hzoxye.eloalk;

import java.util.Scanner;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Snail> snails = List.of(
                new Snail(1, Snail.Color.RED),
                new Snail(2, Snail.Color.GREEN),
                new Snail(3, Snail.Color.BLUE)
        );

        Snail userBet = getBetFromUser(snails);

        System.out.printf("Your bet is on snail #%d, let's start the game…\n", userBet.getId());

        int turns = 5;

        // run each turn
        for (int turn = 1; turn <= turns; turn++) {
            System.out.printf("Round #%d\n", turn);
            //      select a snail and add the booster

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
                    return snails.stream().filter(s -> s.getId() == snailId).findFirst().orElseThrow();
                }
            } catch (NumberFormatException e) {
            }
        } while (true);
    }

}

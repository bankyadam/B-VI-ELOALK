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

        int userBid = getBidFromUser(snails);

        System.out.println(userBid);

        // run each turn
        //      select a snail and add the booster
        //      step each snail
        // print result
    }


    private static Integer getBidFromUser(List<Snail> snails) {
        List<Integer> ids = snails.stream().map(Snail::getId).toList();
        String choices = String.join(", ", ids.stream().map(String::valueOf).toList());

        do {
            Scanner scanner = new Scanner(System.in);
            System.out.printf("Which snail will win? (%s) ", choices);
            String userBid = scanner.nextLine();

            try {
                Integer snailId = Integer.valueOf(userBid);
                if (ids.contains(snailId)) {
                    return snailId;
                }
            } catch (NumberFormatException e) {
            }
        } while (true);
    }

}

package hu.gde.hzoxye.eloalk;

import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        Game game = new Game(List.of(
                new Snail(1, Snail.Color.RED),
                new Snail(2, Snail.Color.GREEN),
                new Snail(3, Snail.Color.BLUE)
        ));

        game.makeUserBet();

        for (int turn = 5; turn > 0; turn--) {
            game.makeTurn();
        }

        game.gameOver();

        game.printResults();

        if (game.isUserBetIsTheWinnerSnail()) {
            System.out.println("Your bet was right, you won also!");
        }
    }

}

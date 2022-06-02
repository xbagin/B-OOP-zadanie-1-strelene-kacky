package sk.stuba.fei.uim.oop.duckhunt;

import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.cards.pond.*;
import sk.stuba.fei.uim.oop.cards.actions.ActionCard;
import sk.stuba.fei.uim.oop.cards.actions.aimandshoot.*;
import sk.stuba.fei.uim.oop.cards.actions.duckmovement.*;
import sk.stuba.fei.uim.oop.pond.Pond;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;
import java.util.Collections;

public class DuckHunt {
    private final Player[] players;
    private final Pond pond;
    private int roundCounter;

    public DuckHunt() {
        System.out.println("Welcome to DUCK HUNT");
        this.players = this.playersInitialization();
        this.pond = new Pond(new ArrayList<>(this.players.length * 5 + 5), new ArrayList<>(34));
        this.fillPondDeck(this.pond.getPondDeck(), this.players);
        this.fillActionDeck(this.pond.getActionDeck(), this.pond);
        Collections.shuffle(this.pond.getPondDeck());
        Collections.shuffle(this.pond.getActionDeck());
        this.pond.fill();
        this.playersGetThreeActionCards();
        this.startGame();
    }

    private Player[] playersInitialization() {
        int numberPlayers;
        do {
            numberPlayers = ZKlavesnice.readInt("Enter number of players: ");
            if (!(numberPlayers >= 2 && numberPlayers <= 6)) {
                System.out.println("This game is for 2 - 6 players!");
            }
        } while (!(numberPlayers >= 2 && numberPlayers <= 6));
        Player[] players = new Player[numberPlayers];
        for (int i = 0; i < numberPlayers; i++) {
            players[i] = new Player(ZKlavesnice.readString("Enter player " + (i + 1) + " name: "));
        }
        return players;
    }

    private void fillPondDeck(ArrayList<PondCard> pondDeck, Player[] players) {
        for (int i = 0; i < 5; i++) {
            pondDeck.add(new Water());
        }
        for (Player player : players) {
            player.putDucksInPondDeck(pondDeck);
        }
    }

    public void fillActionDeck(ArrayList<ActionCard> actionDeck, Pond pond) {
        for (int i = 0; i < 10; i++) {
            actionDeck.add(new Aim(pond));
        }
        for (int i = 0; i < 12; i++) {
            actionDeck.add(new Shoot(pond));
        }
        for (int i = 0; i < 2; i++) {
            actionDeck.add(new WildBill(pond));
        }
        for (int i = 0; i < 6; i++) {
            actionDeck.add(new DuckMarch(pond));
        }
        actionDeck.add(new TurboDuck(pond));
        for (int i = 0; i < 2; i++) {
            actionDeck.add(new Scatter(pond));
        }
        actionDeck.add(new DuckDance(pond));
    }

    private void playersGetThreeActionCards() {
        for (Player player : this.players) {
            for (int i = 0; i < 3; i++) {
                player.drawActionCard(this.pond.getActionDeck());
            }
        }
    }

    private void startGame() {
        System.out.println("|***** GAME STARTED *****|");
        while (this.getActivePlayers() > 1) {
            this.roundCounter++;
            System.out.println("|===== ROUND " + this.roundCounter + " STARTS =====|");
            for (Player player : this.players) {
                if (player.isActive()) {
                    System.out.println("|----- PLAYER " + player.getName() + " STARTS TURN -----|");
                    System.out.println("Ducks left: " + player.getDucksLeft());
                    this.printPond();
                    System.out.println("Players action cards: " + player.getActionCards());
                    player.playActionCard(this.pond);
                    player.drawActionCard(this.pond.getActionDeck());
                    this.printPond();
                    System.out.println("|----- PLAYER " + player.getName() + " ENDS TURN -----|");
                    ZKlavesnice.readString(">> Press Enter To Continue <<");
                    this.updatePlayers();
                    if (this.lastPlayer()) {
                        break;
                    }
                }
            }
        }
        System.out.println("|***** GAME FINISHED *****|");
        if (this.getWinner() != null) {
            System.out.println("\u001B[32m" + "Winner: " + this.getWinner().getName() + "\u001B[0m");
            System.out.println("Ducks left: " + this.getWinner().getDucksLeft());
        }
    }

    private int getActivePlayers() {
        int activePlayers = 0;
        for (Player player : this.players) {
            if (player.isActive()) {
                activePlayers++;
            }
        }
        return activePlayers;
    }

    private void printPond() {
        for (int i = 0; i < pond.getTilesLength(); i++) {
            System.out.print((i + 1) + ") ");
            System.out.print((pond.getTile(i).isAimed() ? "    A" : "Not a") + "imed at\t");
            System.out.println(pond.getTile(i).getCard().getName());
        }
    }

    private void updatePlayers() {
        for (Player player : this.players) {
            player.checkStatus(this.pond.getActionDeck());
        }
    }

    private boolean lastPlayer() {
        return this.getActivePlayers() < 2;
    }

    private Player getWinner() {
        for (Player player : this.players) {
            if (player.isActive()) {
                return player;
            }
        }
        return null;
    }
}

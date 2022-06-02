package sk.stuba.fei.uim.oop.player;

import sk.stuba.fei.uim.oop.cards.actions.ActionCard;
import sk.stuba.fei.uim.oop.cards.pond.Duck;
import sk.stuba.fei.uim.oop.cards.pond.PondCard;
import sk.stuba.fei.uim.oop.pond.Pond;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;

public class Player {
    private boolean active;
    private final String name;
    private final ArrayList<Duck> ducks;
    private final ArrayList<ActionCard> actionCards;

    public Player(String name) {
        this.active = true;
        this.name = name;
        this.ducks = new ArrayList<>(5);
        this.actionCards = new ArrayList<>(3);
        for (int i = 0; i < 5; i++) {
            this.ducks.add(new Duck(this));
        }
    }

    public String getName() {
        return this.name;
    }

    public void putDucksInPondDeck(ArrayList<PondCard> pondDeck) {
        pondDeck.addAll(this.ducks);
    }

    public int getDucksLeft() {
        return this.ducks.size();
    }

    public void playActionCard(Pond pond) {
        if (this.cardsAreUnplayable()) {
            System.out.println("None of your cards can be played.");
            this.putCardInActionDek(this.readIntWhileInBoundsFromOneTo(3, "Discard card [1 2 3]: ") - 1, pond.getActionDeck());
            return;
        }
        boolean moveDone;
        do {
            int index = this.readIntWhileInBoundsFromOneTo(3, "Play card [1 2 3]: ");
            int tile = 0;
            if (this.actionCards.get(index - 1).isPlayedOnTile()) {
                tile = this.readIntWhileInBoundsFromOneTo(6, "Play at tile [1 - 6]: ");
            }
            moveDone = this.playActionCard(index - 1, pond, tile - 1);
            if (!moveDone) {
                System.out.println("Can not be played!");
            }
        } while (!moveDone);
    }

    public void drawActionCard(ArrayList<ActionCard> actionDeck) {
        this.actionCards.add(actionDeck.get(0));
        actionDeck.remove(0);
    }

    public String getActionCards() {
        StringBuilder onHand = new StringBuilder();
        for (int i = 0; i < this.actionCards.size(); i++) {
            if (i != 0) {
                onHand.append("\t");
            }
            if (!this.actionCards.get(i).playable()) {
                onHand.append("\u001B[31m");
            }
            onHand.append("[")
                    .append(i + 1)
                    .append("] ")
                    .append(this.actionCards.get(i).getName())
                    .append("\u001B[0m");
        }
        return onHand.toString();
    }

    public void checkStatus(ArrayList<ActionCard> actionDeck) {
        if (this.active && this.ducks.isEmpty()) {
            System.out.println("\u001B[31m" + "\nPlayer " + this.name + " lost!\n" + "\u001B[0m");
            actionDeck.addAll(this.actionCards);
            this.active = false;
        }
    }

    public boolean isActive() {
        return this.active;
    }

    public void removeDuck(Duck duck) {
        this.ducks.remove(duck);
    }

    private boolean cardsAreUnplayable() {
        for (ActionCard actionCard : this.actionCards) {
            if (actionCard.playable()) {
                return false;
            }
        }
        return true;
    }

    private void putCardInActionDek(int index, ArrayList<ActionCard> actionDeck) {
        actionDeck.add(this.actionCards.get(index));
        this.actionCards.remove(index);
    }

    private boolean playActionCard(int index, Pond pond, int tile) {
        if (this.actionCards.get(index).play((tile == -1)? null : pond.getTile(tile))) {
            this.putCardInActionDek(index, pond.getActionDeck());
        } else {
            return false;
        }
        return true;
    }

    private int readIntWhileInBoundsFromOneTo(int max, String message) {
        int number;
        do {
            number = ZKlavesnice.readInt(message);
        } while (!(number >= 1 && number <= max));
        return number;
    }
}

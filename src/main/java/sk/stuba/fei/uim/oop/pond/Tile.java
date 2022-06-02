package sk.stuba.fei.uim.oop.pond;

import sk.stuba.fei.uim.oop.cards.pond.PondCard;

public class Tile {
    private PondCard card;
    private boolean aimed;

    public Tile() {
        this.card = null;
        this.aimed = false;
    }

    public PondCard getCard() {
        return this.card;
    }

    public void setCard(PondCard card) {
        this.card = card;
    }

    public boolean isAimed() {
        return this.aimed;
    }

    public void setAimed(boolean aimed) {
        this.aimed = aimed;
    }
}

package sk.stuba.fei.uim.oop.pond;

import sk.stuba.fei.uim.oop.cards.actions.ActionCard;
import sk.stuba.fei.uim.oop.cards.pond.PondCard;

import java.util.ArrayList;

public class Pond {
    private final Tile[] tiles;
    private final ArrayList<PondCard> pondDeck;
    private final ArrayList<ActionCard> actionDeck;

    public Pond(ArrayList<PondCard> pondDeck, ArrayList<ActionCard> actionDeck) {
        this.tiles = new Tile[6];
        this.pondDeck = pondDeck;
        this.actionDeck = actionDeck;
        for (int i = 0; i < this.tiles.length; i++) {
            this.tiles[i] = new Tile();
        }
    }

    public int getTilesLength() {
        return this.tiles.length;
    }

    public ArrayList<ActionCard> getActionDeck() {
        return this.actionDeck;
    }

    public ArrayList<PondCard> getPondDeck() {
        return this.pondDeck;
    }

    public Tile getTile(int tile) {
        return this.tiles[tile];
    }

    public void shiftAndFill(boolean putFirstToPondDeck) {
        if (!this.isEmpty()) {
            this.shift(putFirstToPondDeck);
        }
        this.fill();
    }

    public void fill() {
        for (Tile tile : this.tiles) {
            if (tile.getCard() == null) {
                tile.setCard(this.pondDeck.get(0));
                this.pondDeck.remove(0);
            }
        }
    }

    private void shift(boolean putFirstToPondDeck) {
        if (putFirstToPondDeck) {
            this.putFirstTileToPondDeck();
        }
        if (this.isEmpty()) {
            return;
        }
        for (int i = 0; i < this.tiles.length - 1; i++) {
            if (this.tiles[i].getCard() == null) {
                this.tiles[i].setCard(this.tiles[i + 1].getCard());
                this.tiles[i + 1].setCard(null);
                i--;
            }
        }
    }

    private void putFirstTileToPondDeck() {
        if (this.tiles[0].getCard() != null) {
            this.pondDeck.add(this.tiles[0].getCard());
            this.tiles[0].setCard(null);
        }
    }

    private boolean isEmpty() {
        for (Tile tile : this.tiles) {
            if (tile.getCard() != null) {
                return false;
            }
        }
        return true;
    }
}

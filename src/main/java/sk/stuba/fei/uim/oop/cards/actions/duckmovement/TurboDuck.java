package sk.stuba.fei.uim.oop.cards.actions.duckmovement;

import sk.stuba.fei.uim.oop.cards.pond.Duck;
import sk.stuba.fei.uim.oop.pond.Pond;
import sk.stuba.fei.uim.oop.pond.Tile;

public class TurboDuck extends DuckMovementCard {
    public TurboDuck(Pond pond) {
        super("Turbo duck", true, pond);
    }

    @Override
    public boolean play(Tile tile) {
        if (!(tile.getCard() instanceof Duck)) {
            return false;
        }
        int index = this.getTileIndex(tile);
        if (index == -1) {
            return false;
        }
        this.turboDuck(index);
        return true;
    }

    @Override
    public boolean playable() {
        for (int i = 0; i < this.getPond().getTilesLength(); i++) {
            if (this.getPond().getTile(i).getCard() instanceof Duck) {
                return true;
            }
        }
        return false;
    }

    private void turboDuck(int tile) {
        Duck temp = (Duck) this.getPond().getTile(tile).getCard();
        for (int i = 0; i < tile; i++) {
            this.getPond().getTile(tile - i).setCard(this.getPond().getTile(tile - i - 1).getCard());
        }
        this.getPond().getTile(0).setCard(temp);
    }

    private int getTileIndex(Tile tile) {
        for (int i = 0; i < this.getPond().getTilesLength(); i++) {
            if (tile.getCard().equals(this.getPond().getTile(i).getCard())) {
                return i;
            }
        }
        return -1;
    }
}

package sk.stuba.fei.uim.oop.cards.actions.duckmovement;

import sk.stuba.fei.uim.oop.cards.pond.PondCard;
import sk.stuba.fei.uim.oop.pond.Pond;
import sk.stuba.fei.uim.oop.pond.Tile;

import java.util.ArrayList;
import java.util.Collections;

public class Scatter extends DuckMovementCard {
    public Scatter(Pond pond) {
        super("Scatter", false, pond);
    }

    @Override
    public boolean play(Tile tile) {
        this.shuffleTiles();
        return true;
    }

    private void shuffleTiles() {
        ArrayList<PondCard> mix = new ArrayList<>(this.getPond().getTilesLength());
        for (int i = 0; i < this.getPond().getTilesLength(); i++) {
            mix.add(this.getPond().getTile(i).getCard());
        }
        Collections.shuffle(mix);
        for (int i = 0; i < this.getPond().getTilesLength(); i++) {
            this.getPond().getTile(i).setCard(mix.get(0));
            mix.remove(0);
        }
    }
}

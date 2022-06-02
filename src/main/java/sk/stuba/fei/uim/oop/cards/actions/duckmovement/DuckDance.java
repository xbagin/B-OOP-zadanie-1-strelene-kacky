package sk.stuba.fei.uim.oop.cards.actions.duckmovement;

import sk.stuba.fei.uim.oop.pond.Pond;
import sk.stuba.fei.uim.oop.pond.Tile;

import java.util.Collections;

public class DuckDance extends DuckMovementCard {
    public DuckDance(Pond pond) {
        super("Duck dance", false, pond);
    }

    @Override
    public boolean play(Tile tile) {
        this.putInPondDeck();
        this.shufflePondDeck();
        this.getPond().fill();
        return true;
    }

    private void putInPondDeck() {
        for (int i = 0; i < this.getPond().getTilesLength(); i++) {
            this.getPond().getPondDeck().add(this.getPond().getTile(i).getCard());
            this.getPond().getTile(i).setCard(null);
        }
    }

    private void shufflePondDeck() {
        Collections.shuffle(this.getPond().getPondDeck());
    }
}

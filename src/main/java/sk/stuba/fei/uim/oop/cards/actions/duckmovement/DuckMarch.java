package sk.stuba.fei.uim.oop.cards.actions.duckmovement;

import sk.stuba.fei.uim.oop.pond.Pond;
import sk.stuba.fei.uim.oop.pond.Tile;

public class DuckMarch extends DuckMovementCard {
    public DuckMarch(Pond pond) {
        super("Duck march", false, pond);
    }

    @Override
    public boolean play(Tile tile) {
        this.getPond().shiftAndFill(true);
        return true;
    }
}

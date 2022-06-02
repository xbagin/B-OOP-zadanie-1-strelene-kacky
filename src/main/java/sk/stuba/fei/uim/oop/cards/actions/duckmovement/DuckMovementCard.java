package sk.stuba.fei.uim.oop.cards.actions.duckmovement;

import sk.stuba.fei.uim.oop.cards.actions.ActionCard;
import sk.stuba.fei.uim.oop.pond.Pond;

public abstract class DuckMovementCard extends ActionCard {
    public DuckMovementCard(String name, boolean playedOnTile, Pond pond) {
        super(name, playedOnTile, pond);
    }
}

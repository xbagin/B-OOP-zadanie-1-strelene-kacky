package sk.stuba.fei.uim.oop.cards.actions;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.pond.Pond;

public abstract class ActionCard extends Card implements Play {
    private final boolean playedOnTile;
    private final Pond pond;

    public ActionCard(String name, boolean playedOnTile, Pond pond) {
        super(name);
        this.playedOnTile = playedOnTile;
        this.pond = pond;
    }

    public Pond getPond() {
        return this.pond;
    }

    public boolean isPlayedOnTile() {
        return this.playedOnTile;
    }

    public boolean playable() {
        return true;
    }
}

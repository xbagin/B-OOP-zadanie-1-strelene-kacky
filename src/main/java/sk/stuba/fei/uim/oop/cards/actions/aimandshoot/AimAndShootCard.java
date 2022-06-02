package sk.stuba.fei.uim.oop.cards.actions.aimandshoot;

import sk.stuba.fei.uim.oop.cards.actions.ActionCard;
import sk.stuba.fei.uim.oop.cards.pond.Duck;
import sk.stuba.fei.uim.oop.pond.Pond;
import sk.stuba.fei.uim.oop.pond.Tile;

public abstract class AimAndShootCard extends ActionCard {
    public AimAndShootCard(String name, boolean playedOnTile, Pond pond) {
        super(name, playedOnTile, pond);
    }

    protected void aim(Tile tile) {
        tile.setAimed(true);
    }

    protected void shoot(Tile tile) {
        if (tile.getCard() instanceof Duck) {
            Duck duck = (Duck) tile.getCard();
            duck.getPlayer().removeDuck(duck);
            tile.setCard(null);
        }
    }
}

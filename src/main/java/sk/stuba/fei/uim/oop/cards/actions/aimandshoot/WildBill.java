package sk.stuba.fei.uim.oop.cards.actions.aimandshoot;

import sk.stuba.fei.uim.oop.cards.actions.Play;
import sk.stuba.fei.uim.oop.pond.Pond;
import sk.stuba.fei.uim.oop.pond.Tile;

public class WildBill extends AimAndShootCard implements Play {
    public WildBill(Pond pond) {
        super("WildBill", true, pond);
    }

    @Override
    public boolean play(Tile tile) {
        if (!tile.isAimed()) {
            this.aim(tile);
        }
        this.shoot(tile);
        tile.setAimed(false);
        this.getPond().shiftAndFill(false);
        return true;
    }
}

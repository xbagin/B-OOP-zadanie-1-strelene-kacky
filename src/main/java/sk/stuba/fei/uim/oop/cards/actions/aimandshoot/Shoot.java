package sk.stuba.fei.uim.oop.cards.actions.aimandshoot;

import sk.stuba.fei.uim.oop.cards.actions.Play;
import sk.stuba.fei.uim.oop.pond.Pond;
import sk.stuba.fei.uim.oop.pond.Tile;

public class Shoot extends AimAndShootCard implements Play {
    public Shoot(Pond pond) {
        super("Shoot", true, pond);
    }

    @Override
    public boolean play(Tile tile) {
        if (!tile.isAimed()) {
            return false;
        }
        this.shoot(tile);
        tile.setAimed(false);
        this.getPond().shiftAndFill(false);
        return true;
    }

    @Override
    public boolean playable() {
        for (int i = 0; i < this.getPond().getTilesLength(); i++) {
            if (this.getPond().getTile(i).isAimed()) {
                return true;
            }
        }
        return false;
    }
}

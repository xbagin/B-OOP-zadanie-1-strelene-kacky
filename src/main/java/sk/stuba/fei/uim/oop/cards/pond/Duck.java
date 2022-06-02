package sk.stuba.fei.uim.oop.cards.pond;

import sk.stuba.fei.uim.oop.player.Player;

public class Duck extends PondCard {
    private final Player player;

    public Duck(Player player) {
        super("Duck");
        this.player = player;
    }

    @Override
    public String getName() {
        return super.getName() + " of player " + this.player.getName();
    }

    public Player getPlayer() {
        return player;
    }
}

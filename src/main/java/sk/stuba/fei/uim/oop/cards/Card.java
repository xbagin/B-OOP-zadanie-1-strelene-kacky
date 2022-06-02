package sk.stuba.fei.uim.oop.cards;

public abstract class Card {
    protected final String name;

    public Card(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}

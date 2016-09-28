package ua.mishkurov.pos.coins;

/**
 *
 * @author Anton_Mishkurov
 */
public class Coin implements Comparable<Coin> {

    private int value;

    public Coin(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Coin(" + value + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Coin) {
            return ((Coin) obj).value == this.value;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return value;
    }

    //to store Coins in reverse order
    @Override
    public int compareTo(Coin other) {
        return other.getValue() - this.value;
    }


}

package com.epam.mishkurov.pos;

/**
 * Created by Anton_Mishkurov on 9/26/2016.
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

    //to store Coins in reverse order
    @Override
    public int compareTo(Coin o) {
        return o.getValue() - this.value;
    }
}

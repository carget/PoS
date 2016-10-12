package ua.mishkurov.pos;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.*;
import org.junit.runner.RunWith;
import ua.mishkurov.pos.coins.Coin;
import ua.mishkurov.pos.products.ProductFactory;
import ua.mishkurov.pos.sales.Sale;

import java.util.Collection;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Theories.class)
public class posTest {

    private Pos pos;

    @DataPoints(value = "NegativeAndZeroCoins")
    public static Coin[] NegativeAndZeroCoinsValues() {
        return new Coin[]{new Coin(0), new Coin(-1), new Coin(-Integer.MIN_VALUE)};
    }

    @DataPoints(value = "ValidCoins")
    public static Coin[] ValidCoinsValues() {
        return new Coin[]{new Coin(1), new Coin(5), new Coin(10), new Coin(25), new Coin(50)};
    }

    @DataPoints(value = "InvalidCoins")
    public static Coin[] InvalidCoinsValues() {
        return new Coin[]{new Coin(2), new Coin(3), new Coin(15), new Coin(75), new Coin(100)};
    }

    @Before
    public void setUp() throws Exception {
        pos = new SimplePos();
    }

    @Theory
    public void insertCoin() {
        pos.insertCoin(new Coin(5));
        assertThat(pos.getDeposit(), is(5));
    }

    @Test(expected = IllegalArgumentException.class)
    @Theory
    public void negativeCoinTest(@FromDataPoints("NegativeAndZeroCoins") Coin coin) {
        pos.insertCoin(coin);
    }

    @Theory
    public void validCoins(@FromDataPoints("ValidCoins") Coin coin) {
        pos.insertCoin(coin);
        assertThat(pos.getDeposit(), is(coin.getValue()));
    }

    @Test(expected = IllegalArgumentException.class)
    @Theory
    public void invalidCoins(@FromDataPoints("InvalidCoins") Coin coin) {
        pos.insertCoin(coin);
    }

    @Theory
    public void sumCoins() {
        Coin coin = new Coin(1);
        pos.insertCoin(coin);
        pos.insertCoin(coin);
        pos.insertCoin(coin);
        assertThat(pos.getDeposit(), is(3));
    }

    @Theory
    public void addProductToBasket() {
        pos.addProductToBasket(ProductFactory.getProductById(1));
        assertThat(pos.getBasket().get(ProductFactory.getProductById(1)), is(1));
        pos.addProductToBasket(ProductFactory.getProductById(2));
        pos.addProductToBasket(ProductFactory.getProductById(2));
        assertThat(pos.getBasket().get(ProductFactory.getProductById(2)), is(2));
    }

    @Theory
    public void cancelAndGetChange() {
        pos.addProductToBasket(ProductFactory.getProductById(1));
        pos.insertCoin(new Coin(1));
        pos.insertCoin(new Coin(1));
        pos.insertCoin(new Coin(1));
        pos.insertCoin(new Coin(1));
        pos.insertCoin(new Coin(1));
        pos.insertCoin(new Coin(50));
        pos.insertCoin(new Coin(25));
        assertThat(pos.getDeposit(), is(80));
        Collection<Coin> change = pos.cancelAndGetChange();
        assertThat(calcChange(change), is(80));
        assertThat(pos.getDeposit(), is(0));
    }

    private int calcChange(Collection<Coin> coins) {
        int sum = 0;
        for (Coin coin : coins) {
            sum += coin.getValue();
        }
        return sum;
    }

    @Theory
    public void checkOutTest() {
        pos.addProductToBasket(ProductFactory.getProductById(1));
        pos.insertCoin(new Coin(1));
        pos.insertCoin(new Coin(1));
        pos.insertCoin(new Coin(1));
        pos.insertCoin(new Coin(1));
        pos.insertCoin(new Coin(1));
        pos.insertCoin(new Coin(50));
        pos.insertCoin(new Coin(25));
        Collection<Coin> change = pos.checkout();
        assertThat(calcChange(change), is(80 - ProductFactory.getProductById(1).getPrice()));
        assertThat(pos.getDeposit(), is(0));
    }

    @Theory
    public void salesListTest() {
        pos.insertCoin(new Coin(50));
        pos.insertCoin(new Coin(50));
        pos.addProductToBasket(ProductFactory.getProductById(1));
        pos.checkout();
        pos.insertCoin(new Coin(50));
        pos.addProductToBasket(ProductFactory.getProductById(2));
        pos.checkout();
        List<Sale> saleList = pos.getSalesList();
        assertThat(saleList.size(), is(2));
    }

}

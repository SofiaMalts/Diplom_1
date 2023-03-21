package praktikum;

import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;


public class BunTest {
    private final String testName = "Test bun";
    private final float testPrice = 100;

    @Test
    public void getNameReturnValidString() {
        Bun bun = new Bun(testName, testPrice);
        String actualName = bun.getName();
        Assert.assertEquals(testName, actualName);
    }

    @Test
    public void getPriceReturnValidFloat() {
        Bun bun = new Bun(testName, testPrice);
        float actualPrice = bun.getPrice();
        MatcherAssert.assertThat(actualPrice, is(testPrice));
    }

}
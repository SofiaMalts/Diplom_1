package praktikum;

import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    @Mock
    Bun bun;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void setBunsGetValidObject() {
        Burger burger = new Burger();
        Bun testBun = new Bun("Test bun", 100);
        burger.setBuns(testBun);
        boolean isObjectSet = Objects.equals(burger.bun, testBun);
        assertTrue("The bun is not set correctly. Expected bun: {" + testBun.name + ", " + testBun.price + "}. Actual bun: {" + burger.bun.name + ", " + burger.bun.price + "}", isObjectSet);
    }

    @Test
    public void addIngredientGetValidArrayList() {
        Burger burger = new Burger();
        Ingredient testIngredient = new Ingredient(SAUCE, "Терияки", 20);
        burger.addIngredient(testIngredient);
        //System.out.println(burger.ingredients.get(0).type);
        //System.out.println(burger.ingredients.get(0).name);
        //System.out.println(burger.ingredients.get(0).price);
        assertFalse("Burger ingredient list is empty", burger.ingredients.isEmpty());
        assertTrue("Added ingredient is not present in the list.", burger.ingredients.contains(testIngredient));
    }

    @Test
    public void removeIngredientGetValidArrayList() {
        Burger burger = new Burger();
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient(IngredientType.SAUCE, "hot sauce", 100));
        ingredients.add(new Ingredient(IngredientType.FILLING, "dinosaur", 200));
        ingredients.add(new Ingredient(IngredientType.FILLING, "sausage", 300));
        burger.addIngredient(ingredients.get(0));
        burger.addIngredient(ingredients.get(1));
        burger.addIngredient(ingredients.get(2));
        burger.removeIngredient(1);
        //System.out.println(ingredients.get(1).type);
        //System.out.println(ingredients.get(1).name);
        //System.out.println(ingredients.get(1).price);
        //System.out.println(burger.ingredients.get(1).type);
        //System.out.println(burger.ingredients.get(1).name);
        //System.out.println(burger.ingredients.get(1).price);
        assertFalse("Burger ingredient list is empty", burger.ingredients.isEmpty());
        assertFalse("Removed ingredient is present in the list.", burger.ingredients.contains(ingredients.get(1)));

    }

    @Test
    public void moveIngredientGetValidIndex() {
        Burger burger = new Burger();
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient(IngredientType.SAUCE, "hot sauce", 100));
        ingredients.add(new Ingredient(IngredientType.FILLING, "dinosaur", 200));
        ingredients.add(new Ingredient(IngredientType.FILLING, "sausage", 300));
        burger.addIngredient(ingredients.get(0));
        burger.addIngredient(ingredients.get(1));
        burger.addIngredient(ingredients.get(2));
        Ingredient testedIngredient = ingredients.get(0);
        int initialIndex = 0;
        int newIndex = 2;
        burger.moveIngredient(initialIndex, newIndex);
        int currentIndex = burger.ingredients.indexOf(testedIngredient);
        assertNotEquals("Ingredient wasn't moved", currentIndex, initialIndex);
        assertEquals("Ingredient wasn't moved to expected position. Expected index: " + newIndex + ". Actual index: " + currentIndex + "", currentIndex, newIndex);
    }

    @Test
    public void getPriceReturnValidFloat() {
        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(new Ingredient(IngredientType.SAUCE, "hot sauce", 100));
        burger.addIngredient(new Ingredient(IngredientType.FILLING, "dinosaur", 200));
        Mockito.when(bun.getPrice()).thenReturn(100F);
        System.out.println(burger.getPrice());
        MatcherAssert.assertThat(burger.getPrice(), is(500.0F));
    }

    @Test
    public void getReceiptReturnString() {
        Burger burger = new Burger();
        burger.setBuns(bun);
        String bunName = "Test bun";
        String expectedPriceAsString = "500.000000";
        burger.addIngredient(new Ingredient(IngredientType.SAUCE, "hot sauce", 100F));
        burger.addIngredient(new Ingredient(IngredientType.FILLING, "dinosaur", 200F));
        Mockito.when(bun.getPrice()).thenReturn(100F);
        Mockito.when(bun.getName()).thenReturn(bunName);
        String expectedReceipt = "(==== " + bunName + " ====)\r\n" + "= " + SAUCE.toString().toLowerCase() + " hot sauce =\r\n" + "= " + FILLING.toString().toLowerCase() + " dinosaur =\r\n" + "(==== " + bunName + " ====)\r\n" + "\r\nPrice: " + expectedPriceAsString + "\r\n";
        System.out.println(burger.getReceipt());
        MatcherAssert.assertThat(burger.getReceipt(), is(expectedReceipt));
    }
}
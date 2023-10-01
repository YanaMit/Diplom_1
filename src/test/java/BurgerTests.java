import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Mockito;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import java.util.ArrayList;
import java.util.List;

import static praktikum.IngredientType.*;


import org.junit.runners.Parameterized;
import praktikum.IngredientType;

@RunWith(Parameterized.class)

public class BurgerTests {

        private IngredientType ingredientType;
        private String ingredientName;
        private float ingredientPrice;
        private float bunPrice;
        private int numOfIngredients;
        private float priceOfBurger;


        public BurgerTests (IngredientType ingredientType, String ingredientName, float ingredientPrice,
                            float bunPrice, int numOfIngredients, float priceOfBurger) {
                this.ingredientType = ingredientType;
                this.ingredientName = ingredientName;
                this.ingredientPrice = ingredientPrice;
                this.bunPrice = bunPrice;
                this.numOfIngredients = numOfIngredients;
                this.priceOfBurger=priceOfBurger;
        }


        @Parameterized.Parameters
        public static Object[][] myIngredientTests() {
                return new Object[][]{
                        {SAUCE,"соус", 100f,500f,2,1200f},
                        {FILLING,"начинка",200f,600f,3,1800f},
                        {SAUCE,"", 0f,100f,0,200f}

                };
        }

        @Before
        public void init() {
                MockitoAnnotations.initMocks(this);
        }

        @Mock
        Bun bun;


        @Mock
        public List<Ingredient> ingredients = new ArrayList<>();


        @Mock
        Ingredient ingredient;


        @Test
        public  void setBunTest (){
                Burger burger = new Burger();
                burger.setBuns(bun);
                Assert.assertEquals(burger.bun, bun);
        }

        @Test
        public  void addIngredientTest (){
                Burger burger = new Burger();
                burger.ingredients = ingredients;
                burger.addIngredient(ingredient);
                Mockito.verify(burger.ingredients, Mockito.times(1)).add(ingredient);
        }

        @Test
        public  void removeIngredientTest (){
                Burger burger = new Burger();
                burger.ingredients = ingredients;
                burger.removeIngredient(150);
                Mockito.verify(burger.ingredients, Mockito.times(1)).remove(150);
        }

        @Test
        public  void moveIngredientTest (){
                Burger burger = new Burger();
                burger.ingredients = ingredients;
                burger.moveIngredient(0,1);
                Mockito.verify(burger.ingredients, Mockito.times(1)).remove(0);
                Mockito.verify(burger.ingredients, Mockito.times(1)).add(1, ingredients.remove(0));

        }

        private List<Ingredient> makeIngredientsList(int numOfIngredients, Ingredient ingredient) {
            List<Ingredient> newIngredients = new ArrayList<>();//asList(ingredient, ingredient);
            for(int i =0; i < numOfIngredients; i++)
            {
                newIngredients.add(ingredient);
            }
            return newIngredients;
        }

        @Test
        public void getPriceTest() {
                Burger burger = new Burger();
                burger.bun=bun;
                List<Ingredient> newIngredients = makeIngredientsList(numOfIngredients, ingredient);
                burger.ingredients = newIngredients;
                Mockito.when(bun.getPrice()).thenReturn(bunPrice);
                Mockito.when(ingredient.getPrice()).thenReturn(ingredientPrice);
                float sumPrice = burger.getPrice();
                Mockito.verify(burger.bun, Mockito.times(1)).getPrice();
                Mockito.verify(ingredient, Mockito.times(numOfIngredients)).getPrice();
                Assert.assertEquals(priceOfBurger,sumPrice,0);
        }


        @Test
        public void getReceiptTest() {
                Burger burger = new Burger();
                burger.bun=bun;
                List<Ingredient> newIngredients = makeIngredientsList(numOfIngredients, ingredient);
                burger.ingredients = newIngredients;
                Mockito.when(bun.getPrice()).thenReturn(bunPrice);
                Mockito.when(ingredient.getPrice()).thenReturn(ingredientPrice);
                Mockito.when(bun.getName()).thenReturn("булочка");
                Mockito.when(ingredient.getType()).thenReturn(ingredientType);
                Mockito.when(ingredient.getName()).thenReturn(ingredientName);
                float sumPrice = burger.getPrice();
                String expected = "(==== булочка ====)\n" + String.format("= %s %s =\n",
                        ingredientType.toString().toLowerCase(), ingredientName).repeat(numOfIngredients)
                        +"(==== булочка ====)\n"+
                        "\n"+ String.format("Price: %f", sumPrice)+"\n";
                Assert.assertEquals(burger.getReceipt(), expected);
        }

}




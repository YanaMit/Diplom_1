import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import praktikum.Ingredient;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.IngredientType;

import static praktikum.IngredientType.*;

@RunWith(Parameterized.class)

public class IngredientTests {

    private Ingredient ingredient;
    private IngredientType ingredientType;
    private String ingredientName;
    private float ingredientPrice;

    public IngredientTests (IngredientType ingredientType, String ingredientName, float ingredientPrice) {
       this.ingredientType = ingredientType;
       this.ingredientName = ingredientName;
     this.ingredientPrice = ingredientPrice;
    }


    @Parameterized.Parameters
    public static Object[][] myIngredientTests() {
        return new Object[][]{
                {SAUCE,"соус", 1.5f},
                {FILLING,"начинка",2.55f}
        };
        }

        @Before
    public void makeIngredient () {

     ingredient = new Ingredient(ingredientType,ingredientName, ingredientPrice);

        }

    @Test
    public void ingredientGetPriceTest () {

        Assert.assertEquals(ingredientPrice, ingredient.getPrice(), 0);
    }

    @Test
    public void ingredientGetNameTest (){

        Assert.assertEquals(ingredientName, ingredient.getName());
    }

    @Test
    public void ingredientGetTypeTest (){

        Assert.assertEquals(ingredientType, ingredient.getType());
    }


}

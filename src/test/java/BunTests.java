import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import praktikum.Bun;

public class BunTests {

    private Bun bun;
    @Before
    public void makeBun () {
        this.bun = new Bun("батон", 10.5f);
    }


    @Test
    public void bunGetNameTest (){
        //Bun bun = new Bun("батон", 10.5f);
        Assert.assertEquals("батон", bun.getName());
    }

    @Test
    public void bunGetPriceTest () {
        //Bun bun = new Bun("батон", 10.5f);
        Assert.assertEquals(10.5f, bun.getPrice(), 0);
    }
}

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Administrator on 04/09/2017.
 */
public class Basic {

    @Test
    public void myTestMethod(){
        System.out.println("Hello World");
    }

    @Before
    public void myBefore(){
        System.out.println("Before");
    }

    @After
    public void myAfter(){
        System.out.println("After");
    }

    @AfterClass
    public static void myAfterClass(){
        System.out.println("After Class");
    }

    @Test
    public void myTest(){
        System.out.println("Test");
    }
}

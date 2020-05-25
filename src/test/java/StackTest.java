import data_structures.Stack;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class StackTest {

    Stack<Integer> stack = new Stack<>();

    @Before
    public void initStack(){
        stack = new Stack<>();
    }

    @Test
    public void addTest(){
        Assert.assertEquals(stack.size(), 0);
        stack.add(1);
        Assert.assertEquals(stack.size(), 1);
        stack.add(2);
        Assert.assertEquals(stack.size(), 2);
    }

    @Test
    public void popTest(){
        stack.add(1);
        stack.add(2);
        stack.add(3);
        Assert.assertEquals((int)stack.pop(), 3);
        Assert.assertEquals(stack.size(), 2);
        Assert.assertEquals((int)stack.pop(), 2);
        Assert.assertEquals(stack.size(), 1);
        Assert.assertEquals((int)stack.pop(), 1);
        Assert.assertEquals(stack.size(), 0);
    }

    @Test
    public void peekTest(){
        stack.add(1);
        stack.add(2);
        stack.add(3);
        Assert.assertEquals((int)stack.peek(), 3);
        Assert.assertEquals(stack.size(), 3);
        Assert.assertEquals((int)stack.peek(), 3);
        Assert.assertEquals(stack.size(), 3);
    }

    @Test
    public void bigDataTest(){
        for(int i = 0; i < 1000000; i++){
            stack.add(i);
        }
        Assert.assertEquals(stack.size(), 1000000);
        for (int i = 1000000; i > 0; i--){
            Assert.assertEquals((int)stack.pop(), i - 1);
        }
        Assert.assertEquals(stack.size(), 0);
    }

    @Test
    public void popOnEmptyStack(){
        stack.pop();
        Assert.assertEquals(stack.size(), 0);
    }

    @Test
    public void addNull(){
        stack.add(null);
    }

}

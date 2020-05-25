import collections.MyMap;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MapTest {

    MyMap<Integer, Integer> map = new MyMap<>();

    @Before
    public void initMap(){
        map = new MyMap<>();
    }

    @Test
    public void putTest(){
        map.put(1, 9);
        map.put(2, 8);
        map.put(3, 7);
        Assert.assertEquals(map.size(), 3);
    }

    @Test
    public void getTest(){
        map.put(1, 9);
        map.put(2, 8);
        map.put(3, 7);
        Assert.assertEquals((int)map.get(1), 9);
        Assert.assertEquals((int)map.get(2), 8);
        Assert.assertEquals((int)map.get(3), 7);
    }

    @Test
    public void replaceTest(){
        map.put(1, 9);
        Assert.assertEquals((int)map.put(1, 8), 9);
        Assert.assertEquals((int)map.get(1), 8);
    }

    @Test
    public void containsTest(){
        map.put(1, 9);
        map.put(2, 8);
        map.put(3, 7);
        Assert.assertTrue(map.containsKey(2));
        Assert.assertFalse(map.containsKey(4));
        Assert.assertTrue(map.containsValue(9));
        Assert.assertFalse(map.containsValue(5));
    }

    @Test
    public void getNotExistingKey(){
        Assert.assertNull(map.get(0));
    }

    @Test
    public void removeTest(){
        map.put(1, 9);
        map.put(2, 8);
        map.put(3, 7);
        map.remove(3);
        Assert.assertEquals(map.size(), 2);
        Assert.assertNull(map.get(3));
        Assert.assertFalse(map.containsKey(3));
    }

    @Test
    public void clearTest(){
        map.put(1, 9);
        map.put(2, 8);
        map.put(3, 7);
        map.clear();
        Assert.assertEquals(map.size(), 0);
        Assert.assertTrue(map.isEmpty());
    }
}

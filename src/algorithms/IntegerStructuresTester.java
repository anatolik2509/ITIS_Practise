package algorithms;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.AbstractCollection;

public class IntegerStructuresTester {
    AbstractCollection<Integer> collection;
    String path;

    public IntegerStructuresTester(AbstractCollection<Integer> collection, String path){
        this.collection = collection;
        this.path = path;
    }

    public void test() throws IOException {
        PrintWriter wr = new PrintWriter(new File(path));

    }

}

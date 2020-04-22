package comparators;

import java.util.Comparator;

public class AppleRipenessComparator implements Comparator<Apple> {
    @Override
    public int compare(Apple o1, Apple o2) {
        return o1.getColor().getRed() - o2.getColor().getRed();
    }
}


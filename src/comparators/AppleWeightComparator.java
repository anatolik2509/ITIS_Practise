package comparators;

import java.util.Comparator;

public class AppleWeightComparator implements Comparator<Apple> {
    @Override
    public int compare(Apple o1, Apple o2) {
        return (int)(o1.getWeight() - o2.getWeight());
    }
}

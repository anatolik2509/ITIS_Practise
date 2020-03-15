package comparators;

import java.awt.*;
import java.util.Objects;

public class Apple implements Comparable<Apple> {

    private double weight;

    private Color color;

    public double getWeight() {
        return weight;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public int compareTo(Apple other) {
        return (int)(weight - other.getWeight());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Apple apple = (Apple) o;
        return Double.compare(apple.weight, weight) == 0 &&
                color.equals(apple.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight, color);
    }
}

package comparators;

import java.awt.*;
import java.util.Objects;

public class Car implements Comparable<Car> {

    private Color color;

    private String brand;

    private int horsepower;

    public Car(Color color, String brand, int horsepower) {
        this.color = color;
        this.brand = brand;
        this.horsepower = horsepower;
    }

    public Color getColor() {
        return color;
    }

    public String getBrand() {
        return brand;
    }

    public int getHorsepower() {
        return horsepower;
    }

    @Override
    public int compareTo(Car other) {
        return horsepower - other.getHorsepower();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return horsepower == car.horsepower &&
                brand.equals(car.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, horsepower);
    }
}

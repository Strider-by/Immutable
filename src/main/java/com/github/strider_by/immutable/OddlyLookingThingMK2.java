package com.github.strider_by.immutable;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class OddlyLookingThingMK2<T extends Cloneable, V extends Number> {

    private final String label;
    private final long id;
    private final int[][] anchorPoints;
    private final double area;
    private final T storedValue;
    private final V[] numbers;

    private OddlyLookingThingMK2(String label, long id, int[][] anchorPoints, double area, T storedValue, V... numbers) {
        this.label = label;
        this.id = id;
        this.anchorPoints = anchorPoints;
        this.area = area;
        this.storedValue = storedValue;
        this.numbers = numbers;
    }

    public static <T extends Cloneable, V extends Number> OddlyLookingThingMK2 valueOf(
            String label, long id, int[][] anchorPoints, double area, T storedValue, V... numbers) {

        int[][] anchorPointsCopy = Arrays.stream(anchorPoints)
                .map(point -> point.clone())
                .toArray(int[][]::new);

        V[] numbersCopy = numbers.clone();

        if(!isCloneable(storedValue)) {
            throw new IllegalArgumentException("Provided parameter is not a valid Cloneable object");
        }
        
        T storedValueCopy = clone(storedValue);

        return new OddlyLookingThingMK2(label, id, anchorPointsCopy, area, storedValueCopy, numbersCopy);
    }

    public String getLabel() {
        return label;
    }

    public long getId() {
        return id;
    }

    public int[][] getAnchorPoints() {
        return Arrays.stream(anchorPoints)
                .map(point -> point.clone())
                .toArray(int[][]::new);
    }

    public double getArea() {
        return area;
    }

    public T getStoredValue() {
        return clone(storedValue);
    }

    public V[] getNumbers() {
        return numbers.clone();
    }

    private static <T extends Cloneable> boolean isCloneable(T obj) {
        boolean result = false;

        try {
            obj.getClass().getMethod("clone").invoke(obj);
            result = true;
        } catch (NullPointerException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            // no, we cannot invoke clone method here
        }

        return result;
    }

    private static <T extends Cloneable> T clone(T obj) {
        try {
            return (T) obj.getClass().getMethod("clone").invoke(obj);
        } catch (NullPointerException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new IllegalArgumentException("This is not a valid Cloneable object");
        }
    }
}


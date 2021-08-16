package com.github.strider_by.immutable;

import java.util.Arrays;

public class OddlyLookingThing<T, V extends Number> {

    private final String label;
    private final long id;
    private final int[][] anchorPoints;
    private final double area;
    private final T storedValue;
    private final V[] numbers;

    private OddlyLookingThing(String label, long id, int[][] anchorPoints, double area, T storedValue, V... numbers) {
        this.label = label;
        this.id = id;
        this.anchorPoints = anchorPoints;
        this.area = area;
        this.storedValue = storedValue;
        this.numbers = numbers;
    }

    public static <T, V extends Number> OddlyLookingThing valueOf(
            String label, long id, int[][] anchorPoints, double area, T storedValue, V... numbers) {

        int[][] anchorPointsCopy = Arrays.stream(anchorPoints)
                .map(point -> point.clone())
                .toArray(int[][]::new);

        V[] numbersCopy = numbers.clone();

        return new OddlyLookingThing(label, id, anchorPointsCopy, area, storedValue, numbersCopy);
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
        return storedValue;
    }

    public V[] getNumbers() {
        return numbers.clone();
    }
}

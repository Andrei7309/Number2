package org.example;

import org.example.castomException.CustomException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;


public class SimpleListTest {
    private SimpleList<Integer> simpleList;

    @Before
    public void setUp() {
        simpleList = new SimpleList<>();
    }

    @Test
    public void add() {
        IntStream.range(0, 20).forEach(i -> simpleList.add(i));
        Integer expected = 200;
        simpleList.add(expected);
        Integer actual = simpleList.get(20).orElse(null);
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = CustomException.class)
    public void insert() throws CustomException {
        IntStream.range(0, 20).forEach(i -> simpleList.add(i));
        Integer expected = 200;
        int index = 10;
        try {
            simpleList.insert(index, expected);
        } catch (Exception e) {
            throw new CustomException("Такой ячейки в массиве нет");
        }
        Integer actual = simpleList.get(index).orElse(null);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void remove() {
        int size = 20;
        IntStream.range(0, size).forEach(i -> simpleList.add(i));
        int index = 5;
        try {
            simpleList.remove(index);
        } catch (Exception e) {
            throw new CustomException("Такой ячейки в массиве нет");
        }
        Integer actual = size - 1;
        Integer expected = simpleList.size();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void get() {
    }

    @Test
    public void addAll() {
    }

    @Test
    public void first() {
    }

    @Test
    public void last() {
    }

    @Test
    public void contains() {
    }

    @Test
    public void isEmpty() {
    }

    @Test
    public void shuffle() {
    }

    @Test
    public void sort() {
    }
}
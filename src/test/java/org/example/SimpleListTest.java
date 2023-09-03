package org.example;

import org.example.castomException.CustomException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;


public class SimpleListTest {
    private SimpleList<Integer> simpleList;

    @Before
    public void setUp() {
        simpleList = new SimpleList<>();
    }

    @Test
    public void testAdd() {
        IntStream.range(0, 20).forEach(i -> simpleList.add(i));
        Integer expected = 200;
        simpleList.add(expected);
        Integer actual = simpleList.get(20).orElse(null);
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = CustomException.class)
    public void testInsert() throws CustomException {
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
    public void testRemove() {
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
    public void testGet() {
        int size = 20;
        IntStream.range(0, size).forEach(i -> simpleList.add(i + (int) (Math.random() * 100)));
        int index = 8;
        Integer expected = simpleList.get(index).orElse(null);
        Optional<Integer> result = simpleList.get(index);
        Assert.assertTrue(result.isPresent());
        Assert.assertEquals(expected, simpleList.get(index).orElse(null));
    }

    @Test
    public void testSize() {
        int size = 10;
        IntStream.range(0, size).forEach(i -> simpleList.add(i + (int) (Math.random() * 100)));
        Integer expected = size;
        Integer actual = simpleList.size();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testAddAll() {
        int size = 20;
        SimpleList<Integer> list = new SimpleList<>();
        IntStream.range(0, size).forEach(list::add);

        IntStream.range(0, size).forEach(i -> {
            Integer expected = i;
            Assert.assertEquals(expected, list.get(i).orElse(null));
        });
    }

    @Test
    public void testFirst() {
        int item = 100;
        Integer expected = item;
        IntStream.range(0, 20).forEach(i -> simpleList.add(i));
        Integer actual = simpleList.first(item);
        if (simpleList.contains(item)) {
            Assert.assertEquals(expected, actual);
        } else {
            expected = -1;
            Assert.assertEquals(expected, actual);
        }
    }

    @Test
    public void testLast() {
        int item = 100;
        Integer expected = item;
        IntStream.range(0, 20).forEach(i -> simpleList.add(i));
        Integer actual = simpleList.last(item);
        if (simpleList.contains(item)) {
            Assert.assertEquals(expected, actual);
        } else {
            expected = -1;
            Assert.assertEquals(expected, actual);
        }
    }

    @Test
    public void testContains() {
        int item = 100;
        IntStream.range(0, 20).forEach(i -> simpleList.add(i));
        if (simpleList.contains(item)) {
            boolean condition = simpleList.contains(item);
            Assert.assertTrue(condition);
        } else {
            boolean condition = simpleList.contains(item);
            Assert.assertFalse(condition);
        }
    }

    @Test
    public void isEmpty() {
        int size = 10;
        boolean condition;
        while (size > 0) {
            simpleList.add(size);
            size--;
        }
        if (simpleList.isEmpty()) {
            condition = true;
            Assert.assertTrue(condition);
        } else {
            condition = false;
            Assert.assertFalse(condition);
        }
    }

    @Test
    public void shuffle() {
        int size = 10;
        IntStream.range(0, 10).forEach(i -> simpleList.add(i));
        SimpleList<Integer> shuffleList = simpleList.shuffle();
        Assert.assertEquals(size, shuffleList.size());
        int countDifferences = 0;
        for (int i = 0; i < simpleList.size(); i++) {
            if (!Objects.equals(simpleList.get(i).orElse(null), shuffleList.get(i).orElse(null))) countDifferences++;
        }
        Assert.assertTrue(countDifferences > size / 2);

//        Assert.assertNotEquals(simpleList.get(i).orElse(null), shuffleList.get(i).orElse(null));
    }

    @Test
    public void sort() {
        int size = 30;
        for (int i = size - 1; i >= 0; i--) {
            simpleList.add(i);
        }
        SimpleList<Integer> sortedList = simpleList.sort((o1, o2) -> {
            if (o1 == o2) {
                return 0;
            } else return o1 > o2 ? 1 : -1;
        });
        Assert.assertEquals(size, sortedList.size());
        IntStream.range(0, size)
                .forEach(i -> {
                    Integer expected = i;
                    Assert.assertEquals(expected, sortedList.get(i).orElse(null));
                });
    }
}
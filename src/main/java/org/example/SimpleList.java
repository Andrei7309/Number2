package org.example;

import org.example.castomException.CustomException;
import org.example.impl.SimpleListInterface;

import java.util.Comparator;
import java.util.Optional;
import java.util.Random;
import java.util.stream.IntStream;

public class SimpleList<T> implements SimpleListInterface<T> {

    private static final int INITIAL_SIZE = 20;
    private Object[] array;
    private int size;

    public SimpleList() {
        this.array = new Object[INITIAL_SIZE];
        this.size = 0;
    }

    @Override
    public void add(T item) {
        if (size == array.length) {
            expandArray();
        }
        array[size] = item;
        size++;
    }

    private void expandArray() {
        Object[] newArray = new Object[array.length * 2];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    @Override
    public void insert(int index, T item) throws Exception {
        checkIndex(index);
        if (index == size) {
            expandArray();
        }
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = item;
        size++;
    }

    @Override
    public void remove(int index) throws Exception {
        checkIndex(index);
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
    }

    @Override
    public Optional<T> get(int index) {
        try {
            checkIndex(index);
            return Optional.ofNullable((T) array[index]);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void addAll(SimpleList<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i).orElse(null));
        }

    }

    @Override
    public int first(T item) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(item)) return i;
        }
        return -1;
    }

    @Override
    public int last(T item) {
        for (int i = size - 1; i >= 0; i--) {
            if (array[i].equals(item)) return i;
        }
        return -1;
    }

    @Override
    public boolean contains(T item) {
        return IntStream.range(0, size).anyMatch(i -> array[i].equals(item));
    }

    @Override
    public boolean isEmpty() {
        return array.length == 0;
    }

    @Override
    public SimpleList<T> shuffle() {
        SimpleList<T> shuffleList = new SimpleList<>();
        shuffleList.addAll(this);

        Random random = new Random();

        for (int i = 0; i < size; i++) {
            int index = random.nextInt(i + 1);
            T temporary = (T) shuffleList.array[i];
            shuffleList.array[i] = shuffleList.array[index];
            shuffleList.array[index] = temporary;
        }
        return shuffleList;
    }

    @Override
    public SimpleList<T> sort(Comparator<T> comparator) {
        SimpleList<T> sortedList = new SimpleList<>();
        sortedList.addAll(this);

        int size = sortedList.size();
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                T element1 = sortedList.get(j).orElse(null);
                T element2 = sortedList.get(j + 1).orElse(null);
                if (element1 != null && element2 != null && comparator.compare(element1, element2) > 0) {
                    sortedList.swap(j, j + 1);
                }
            }
        }

        return sortedList;
    }

    private void swap(int index1, int index2) {
        T temp = (T) array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }



    private void checkIndex(int index) {
        if (index < 0 || index > size) {
            throw new CustomException("Такой ячейки в массиве нет"); // todo кастомная ошибка
        }
    }
}

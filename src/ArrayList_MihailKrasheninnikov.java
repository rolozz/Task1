import java.util.Arrays;
import java.util.Comparator;

public class ArrayList_MihailKrasheninnikov<E> implements IntensiveList<E>{

    private static final int INIT_CAPACITY = 10;
    private Object[] elements;
    private int listSize = 0;

    public ArrayList_MihailKrasheninnikov(){
        this(INIT_CAPACITY);
    }

    public ArrayList_MihailKrasheninnikov(int init){
        if (init <= 0) {
            throw new IllegalArgumentException("Объем должен быть положительным");
        }
        elements = new Object[init];
    }


    @Override
    public int size() {
        return listSize;
    }

    @Override
    public void add(E element) {
        if (listSize == elements.length) {
            elements = Arrays.copyOf(elements, elements.length * 2);
        }
        elements[listSize++] = element;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > listSize) {
            throw new IndexOutOfBoundsException("Неверный индекс: " + index);
        }
        if (listSize == elements.length) {
            elements = Arrays.copyOf(elements, elements.length * 2);
        }
        System.arraycopy(elements, index, elements, index + 1, listSize - index);
        elements[index] = element;
        listSize++;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E get(int index) {
        checkIndex(index);
        return (E) elements[index];
    }

    @SuppressWarnings("unchecked")
    @Override
    public E set(int index, E element) {
        checkIndex(index);
        final var oldValue = (E) elements[index];
        elements[index] = element;
        return oldValue;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E remove(int index) {
        checkIndex(index);
        final var removedValue = (E) elements[index];
        final var numMoved = listSize - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
        elements[--listSize] = null;
        return removedValue;
    }

    @Override
    public void clear() {
        elements = new Object[INIT_CAPACITY];
        listSize = 0;
    }

    @Override
    public void quickSort(Comparator<E> comparator) {
        if (comparator == null) {
            throw new IllegalArgumentException("не может быть Null");
        }
        quickSortRec(0, listSize - 1, comparator);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean isSorted() {
        for (int i = 1; i < listSize; i++) {
            final var current = (Comparable<E>) elements[i - 1];
            if (current.compareTo((E) elements[i]) > 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void split(int size) {
        if (size < 0 || size > listSize) {
            throw new IllegalArgumentException("Некорректный размер");
        }
        listSize = size;
        for (int i = size; i < elements.length; i++) {
            elements[i] = null;
        }
    }

    private void quickSortRec(int low, int high, Comparator<E> comparator) {
        if (low < high) {
            final var pi = partition(low, high, comparator);
            quickSortRec(low, pi - 1, comparator);
            quickSortRec(pi + 1, high, comparator);
        }
    }

    @SuppressWarnings("unchecked")
    private int partition(int low, int high, Comparator<E> comparator) {
        final var pivot = (E) elements[high];
        var i = low - 1;
        for (int j = low; j < high; j++) {
            if (comparator.compare((E) elements[j], pivot) <= 0) {
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, high);
        return i + 1;
    }

    private void swap(int i, int j) {
        final var temp = elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= listSize) {
            throw new IndexOutOfBoundsException("Неверный индекс: " + index);
        }
    }

    @Override
    public String toString() {
        return "ArrayList_MihailKrasheninnikov{" +
                "elements=" + Arrays.toString(elements) +
                ", listSize=" + listSize +
                '}';
    }
}

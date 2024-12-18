import java.util.Arrays;
import java.util.Comparator;

/**
 * Пользовательская реализация изменяемого списка.
 * Этот класс реализует интерфейс {@link IntensiveList} и предоставляет базовые операции,
 * такие как добавление, удаление и получение элементов, а также сортировка и разделение списка.
 *
 * @param <E> тип элементов в списке
 */
public class ArrayList_MihailKrasheninnikov<E> implements IntensiveList<E> {

    /**
     * Начальная ёмкость списка.
     */
    private static final int INIT_CAPACITY = 10;
    /**
     * Массив, содержащий элементы списка.
     */
    private Object[] elements;
    /**
     * Текущий размер списка.
     */
    private int listSize = 0;

    /**
     * Создаёт пустой список с начальной ёмкостью 10.
     */
    public ArrayList_MihailKrasheninnikov() {
        this(INIT_CAPACITY);
    }

    /**
     * Создаёт пустой список с заданной начальной ёмкостью.
     *
     * @param init начальная ёмкость списка
     * @throws IllegalArgumentException если начальная ёмкость меньше или равна 0
     */
    public ArrayList_MihailKrasheninnikov(int init) {
        if (init <= 0) {
            throw new IllegalArgumentException("Объем должен быть положительным");
        }
        elements = new Object[init];
    }

    /**
     * Возвращает количество элементов в списке.
     *
     * @return размер списка
     */
    @Override
    public int size() {
        return listSize;
    }

    /**
     * Добавляет указанный элемент в конец списка.
     *
     * @param element элемент для добавления
     */
    @Override
    public void add(E element) {
        if (listSize == elements.length) {
            elements = Arrays.copyOf(elements, elements.length * 2);
        }
        elements[listSize++] = element;
    }

    /**
     * Вставляет указанный элемент в указанную позицию списка.
     * Сдвигает элемент, который находится на этой позиции (если есть), и все последующие элементы на одну позицию вправо.
     *
     * @param index индекс, по которому вставить элемент
     * @param element элемент для добавления
     * @throws IndexOutOfBoundsException если индекс выходит за пределы диапазона
     */
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

    /**
     * Возвращает элемент по указанному индексу.
     *
     * @param index индекс элемента
     * @return элемент, находящийся по указанному индексу
     * @throws IndexOutOfBoundsException если индекс выходит за пределы диапазона
     */
    @SuppressWarnings("unchecked")
    @Override
    public E get(int index) {
        checkIndex(index);
        return (E) elements[index];
    }

    /**
     * Заменяет элемент на указанном индексе новым значением.
     *
     * @param index индекс элемента для замены
     * @param element новый элемент
     * @return старое значение элемента
     * @throws IndexOutOfBoundsException если индекс выходит за пределы диапазона
     */
    @SuppressWarnings("unchecked")
    @Override
    public E set(int index, E element) {
        checkIndex(index);
        final var oldValue = (E) elements[index];
        elements[index] = element;
        return oldValue;
    }

    /**
     * Удаляет элемент на указанном индексе.
     *
     * @param index индекс элемента для удаления
     * @return удалённый элемент
     * @throws IndexOutOfBoundsException если индекс выходит за пределы диапазона
     */
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

    /**
     * Очищает список, устанавливая его размер равным нулю.
     */
    @Override
    public void clear() {
        elements = new Object[INIT_CAPACITY];
        listSize = 0;
    }

    /**
     * Сортирует список с использованием алгоритма быстрой сортировки.Дефолиное решени,
     * решение из книги со стримами требует заимплиментить List<E>
     *
     * @param comparator компаратор для сравнения элементов
     * @throws IllegalArgumentException если компаратор равен null
     */
    @Override
    public void quickSort(Comparator<E> comparator) {
        if (comparator == null) {
            throw new IllegalArgumentException("не может быть Null");
        }
        quickSortRec(0, listSize - 1, comparator);
    }

    /**
     * Проверяет, отсортирован ли список.
     *
     * @return true, если список отсортирован, иначе false
     */
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

    /**
     * Разделяет список.
     *
     * @param size новый размер списка
     * @throws IllegalArgumentException если размер некорректен
     */
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

    /**
     * Рекурсивный метод для выполнения быстрой сортировки.
     *
     * @param low начальный индекс
     * @param high конечный индекс
     * @param comparator компаратор для сравнения элементов
     */
    private void quickSortRec(int low, int high, Comparator<E> comparator) {
        if (low < high) {
            final var pi = partition(low, high, comparator);
            quickSortRec(low, pi - 1, comparator);
            quickSortRec(pi + 1, high, comparator);
        }
    }

    /**
     * Разделяет список на две части и возвращает индекс разделителя.
     *
     * @param low начальный индекс
     * @param high конечный индекс
     * @param comparator компаратор для сравнения элементов
     * @return индекс разделителя
     */
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

    /**
     * Обмен элементов списка по указанным индексам.
     *
     * @param i первый индекс
     * @param j второй индекс
     */
    private void swap(int i, int j) {
        final var temp = elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }

    /**
     * Проверяет, что индекс находится в пределах допустимого диапазона.
     *
     * @param index индекс для проверки
     * @throws IndexOutOfBoundsException если индекс выходит за пределы диапазона
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= listSize) {
            throw new IndexOutOfBoundsException("Неверный индекс: " + index);
        }
    }

    /**
     * Возвращает строковое представление объекта.
     *
     * @return строковое представление объекта
     */
    @Override
    public String toString() {
        return "ArrayList_MihailKrasheninnikov{" +
                "elements=" + Arrays.toString(elements) +
                ", listSize=" + listSize +
                '}';
    }
}

import java.util.Comparator;

public interface IntensiveList<E> {

  /**
   * Возвращает количество элементов в списке.
   *
   * @return размер списка
   */
  int size();

  /**
   * Добавляет указанный элемент в конец списка.
   *
   * @param element элемент для добавления
   */
  void add(E element);

  /**
   * Вставляет указанный элемент в заданную позицию списка.
   *
   * @param index индекс, по которому вставить элемент
   * @param element элемент для добавления
   * @throws IndexOutOfBoundsException если индекс выходит за пределы допустимого диапазона
   */
  void add(int index, E element);

  /**
   * Возвращает элемент, находящийся по указанному индексу.
   *
   * @param index индекс элемента для получения
   * @return элемент по указанному индексу
   * @throws IndexOutOfBoundsException если индекс выходит за пределы допустимого диапазона
   */
  E get(int index);

  /**
   * Заменяет элемент в указанной позиции новым элементом.
   *
   * @param index индекс элемента для замены
   * @param element новый элемент для замены
   * @return старый элемент, который был заменён
   * @throws IndexOutOfBoundsException если индекс выходит за пределы допустимого диапазона
   */
  E set(int index, E element);

  /**
   * Удаляет элемент по указанному индексу.
   *
   * @param index индекс элемента для удаления
   * @return удалённый элемент
   * @throws IndexOutOfBoundsException если индекс выходит за пределы допустимого диапазона
   */
  E remove(int index);

  /** Очищает список, удаляя все элементы. */
  void clear();

  /**
   * Сортирует элементы списка в соответствии с заданным компаратором.
   *
   * @param comparator компаратор для определения порядка элементов
   * @throws IllegalArgumentException если компаратор равен {@code null}
   */
  void quickSort(Comparator<E> comparator);

  /**
   * Проверяет, отсортирован ли список.
   *
   * @return {@code true}, если список отсортирован, иначе {@code false}
   */
  boolean isSorted();

  /**
   * Обрезает список до указанного размера.
   *
   * @param size новый размер списка
   * @throws IllegalArgumentException если размер меньше 0 или больше текущего размера списка
   */
  void split(int size);
}

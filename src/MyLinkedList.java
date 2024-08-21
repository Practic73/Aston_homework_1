/**
 * Класс MyLinkedList предоставляет реализацию связанного списка.
 */
public class MyLinkedList<T> implements MyCollection<T>{

    int size = 0;
    Node<T> head;
    Node<T> tail;

    /**
     *  Конструктор создает пустой список.
     * */
    public MyLinkedList() {
        this.size = 0;
    }

    /**
     * Возвращает текущее количество элементов в коллекции.
     *
     * @return количество объектов в коллекции.
     * */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Этот метод вставляет указанный элемент в указанную позицию в этом списке.
     *
     * @param index индекс для вставки.
     * @param element вставляемый объект.
     */
    public void add(int index, T element) {
        checkCorrectIndex(index);
        Node nodeInsert = new Node(null, element, null);
        Node node = this.head;

        for (int i =0; i < index-1; i++){
            node = node.next;
        }
        nodeInsert.next = node.next;
        nodeInsert.prev = node.prev;
        node.next = nodeInsert;
    }

    /**
     * Этот метод вставляет указанный элемент в список.
     *
     * @param element вставляемый объект.
     */
    public void add(T element) {
        addLast(element);
    }

    /**
     * Этот метод вставляет указанный элемент в конец списка.
     *
     * @param element вставляемый объект.
     */
    public void addLast(T element) {
        Node<T> newNode = new Node<>(this.tail, element, null);

        if (this.size == 0) {
            this.tail = this.head;
        } else {
            this.tail.next = newNode;
            newNode.prev = this.tail;
        }
        this.tail = newNode;
        this.size ++;
    }

    /**
     * Этот метод вставляет указанный элемент в начало списка.
     *
     * @param element вставляемый объект.
     */
    public void addFirst(T element) {
        Node<T> currentNode = this.head;
        Node<T> newNode = new Node<>(null, element, currentNode);

        this.head = newNode;
        this.head.next = currentNode;

        if (this.size == 0) {
            this.tail = this.head;
        } else {
            currentNode.prev = this.head;
        }

        this.size ++;
    }

    /**
     * Возвращает элемент из списка по указанному индексу.
     *
     * @param index индекс возвращаемого объекта.
     * @return найденный объект.
     * */
    public T get(int index) {
        Node node = new Node(null, null, null);
        for (int i = 0; i < index -1; i++) {
            node = node.next;
        }
        return (T) node;
    }

    /**
     * Возвращает элемент из начала списка.
     *
     * @return найденный объект.
     * */
    public T getFirst() {
        return (T) this.head;
    }

    /**
     * Возвращает элемент из конца списка.
     *
     * @return найденный объект.
     * */
    public T getLast() {
        return (T) this.tail;
    }

    /**
     * Удаляет элемент списка по индексу.
     * */
    public void remove(int index) {
        Node node = this.head;
        for (int i = 0; i < index -1; i++) {
            node = node.next;
        }
        node.next = node.next.next;
        node.prev = node.prev.prev;
    }

    /**
     * Внутренний класс, представляющий узел списка.
     * Имеет ссылку на следующий объект и на предыдущий.
     * Хранит значение.
     * */
    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    /**
     * Проверяет верность передаваемого индекса.
     * Проверка на выход за пределы массива.
     *
     * @param index проверяемый индекс.
     * */
    public void checkCorrectIndex(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Индекс вышел за предел массива");
        }
    }

}

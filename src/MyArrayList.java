import java.util.Collection;
import java.util.List;

/**
 * Класс MyArrayList предоставляет реализацию динамического списка.
 */
class MyArrayList<T> implements MyListInterface<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private int size = 0;
    private Object[] elementData;

    /**
     *  Конструктор создает пустой список elementData
     *  с начальной емкостью 10.
     * */
    public MyArrayList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    /**
     *  Конструктор создает пустой список ArrayList с указанной начальной
     * емкостью. Начальная емкость представляет собой количество элементов,
     * которое список может содержать без изменения его размера.
     *
     * @param initialCapacity начальная емкость.
     * */
    public MyArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException();
        } else {
            this.elementData = new Object[initialCapacity];
        }
    }

    /**
     * Конструктор создает список ArrayList, содержащий
     * элементы из указанной коллекции, в том же порядке, в котором они возвращаются
     * итератором коллекции.
     *
     * @param collection передаваемая коллекция.
     * */
    public MyArrayList(Collection<? extends T> collection) {
        this.elementData = new Object[collection.size()];
        int startIndexForCurrentArray = 0;

        for (T elem : collection) {
            this.elementData[startIndexForCurrentArray] = elem;
            startIndexForCurrentArray++;
        }
        this.size = collection.size();
    }

    /**
     * Конструктор создает список ArrayList, содержащий элементы из
     * указанного списка, в том же порядке, в котором они
     * расположены в исходном списке.
     *
     * @param list передаваемый список.
     * */
    public MyArrayList(List<? extends T> list) {
        this.elementData = new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            this.elementData[i] = list.get(i);
        }
        this.size = list.size();
    }

    /**
     * Добавляет передаваемый объект в конец списка.
     *
     * @param element объект для вставки.
     * */
    public void add(Object element) {
        if (!checkFreeCells()) {
            createNewArray();
        }
        elementData[size] = element;
        size++;
    }

    /**
     * Вставляет указанный элемент в список по указанному индексу.
     * Существующие элементы сдвигаются вправо.
     *
     * @param element объект для вставки.
     * */
    @Override
    public void add(int index, T element) {
        checkCorrectIndex(index);
        try {
            if (!checkFreeCells()) {
                createNewArray();
            }
            for (int i = this.elementData.length - 1; i >= index; i--) {
                if (this.elementData[i] == null) {
                    continue;
                } else {
                    this.elementData[i + 1] = this.elementData[i];
                }
            }
            this.elementData[index] = element;
            this.size++;
        } catch (IndexOutOfBoundsException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Удаляет элемент из списка по указанному индексу и
     * возвращает удаленный элемент.
     *
     * @param index индекс удаляемого объекта.
     * @return удаляемый объект.
     * */
    @Override
    public T remove(int index) {
        checkCorrectIndex(index);
        Object removedElement = elementData[index];
        size--;
        for (int i = index; i < size - 1; i++) {
            this.elementData[i] = elementData[i + 1];
        }
        this.elementData[size] = null;
        return (T) removedElement;
    }

    /**
     * Возвращает элемент из списка по указанному индексу.
     *
     * @param index индекс возвращаемого объекта.
     * @return найденный объект.
     * */
    @Override
    public T get(int index) {
        Object element = null;
        try {
            element = elementData[index];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
        return (T) element;
    }

    /**
     * Заменяет элемент в списке по указанному индексу
     * новым элементом и возвращает старый элемент.
     *
     * @param index индекс изменяемого объекта.
     * @param element объект, который будет записан.
     * @return старый объект, который заменили.
     * */
    @Override
    public T set(int index, Object element) {
        checkCorrectIndex(index);
        Object oldElement = this.elementData[index];
        this.elementData[index] = element;
        return (T) oldElement;
    }

    /**
     * Возвращает представление списка, ограниченное указанными
     * индексами fromIndex (включительно) и toIndex (исключительно).
     *
     * @param fromIndex индекс начала представления.
     * @param toIndex индекс конца представления.
     * @return представление списка.
     * */
    @Override
    public MyArrayList<T> subList(int fromIndex, int toIndex) {
        if (fromIndex > toIndex) {
            throw new IllegalArgumentException();
        }
        if (toIndex >= size || fromIndex < 0) {
            throw new IndexOutOfBoundsException("Индекс вышел за предел массива");
        }
        MyArrayList<T> endList = new MyArrayList<T>(toIndex - fromIndex);
        for (int i = fromIndex; i < toIndex; i++) {
            endList.add(this.elementData[i]);
        }
        return endList;
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
     * Считает новую размерность подмассива.
     *
     * @param oldCapacity старый размер.
     * @return новый размер.
     * */
    public int getNewCapacity(int oldCapacity) {
        return (int) (oldCapacity * 1.5 + 1);
    }

    /**
     * Создает новый внутренний массив с увеличенной размерностью.
     * Копирует объекты из старого массива в новый и возвращает его.
     *  */
    public void createNewArray() {
        int newCapacity = getNewCapacity(this.elementData.length);
        Object[] tempArray = new Object[newCapacity];

        for (int i = 0; i < this.elementData.length; i++) {
            tempArray[i] = this.elementData[i];
        }
        this.elementData = new Object[newCapacity];
        this.elementData = tempArray;
    }

    /**
     * Проверяет наличие пустых ячеек в массиве.
     *
     * @return true, если есть ячейки, false - если нет.
     * */
    public boolean checkFreeCells() {
        return this.elementData.length > this.size;
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
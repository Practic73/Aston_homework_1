import java.util.Collection;
import java.util.List;



class MyArrayList<T> implements MyListInterface<T>{

    private static final int DEFAULT_CAPACITY = 10;
    private int size = 0;
    private Object[] elementData;

    public int sizeElementData(){
        return this.elementData.length;
    }

    public MyArrayList(){
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(int initialCapacity){
        if (initialCapacity < 0) {
            throw new IllegalArgumentException();
        }
        else {
            this.elementData = new Object[initialCapacity];
        }
    }

    public MyArrayList(Collection<? extends T> collection){
        this.elementData = new Object[collection.size()];
        int startIndexForCurrentArray = 0;

        for (T elem : collection) {
            this.elementData[startIndexForCurrentArray] = elem;
            startIndexForCurrentArray ++;
        }
        this.size = collection.size();
    }

    public MyArrayList(List <? extends T> list){
        this.elementData = new Object[list.size()];
        for (int i = 0; i < list.size(); i++) {
            this.elementData[i] = list.get(i);
        }
        this.size = list.size();
    }

    public void add(Object element) {
        if (!checkFreeCells()) {
            createNewArray();
        }
        elementData[size] = element;
        size++;
    }

    public void add(int index, T element){
        checkCorrectIndex(index);
        try{
            if (!checkFreeCells()) {
                createNewArray();
            }
            for (int i = this.elementData.length - 1; i >= index; i --) {
                if (this.elementData[i] == null) {
                    continue;
                }
                else {
                    this.elementData[i + 1] = this.elementData[i];
                }
            }
            this.elementData[index] = element;
            this.size ++;
        }
        catch(IndexOutOfBoundsException e) {
            System.err.println(e.getMessage());
        }
    }

    public T remove(int index){
        checkCorrectIndex(index);
        Object removedElement = elementData[index];
        size --;
        for (int i = index; i < size - 1; i ++) {
            this.elementData[i] = elementData[i + 1];
        }
        this.elementData[size] = null;
        return (T) removedElement;
    }

    public T get(int index){
        Object element = null;
        try {
            element = elementData[index];
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println(e.getMessage());
        }
        return (T) element;
    }

    public T set(int index, Object element){
        checkCorrectIndex(index);
        Object oldElement = this.elementData[index];
        this.elementData[index] = element;
        return  (T) oldElement;
    }

    public MyArrayList<T> subList(int fromIndex, int toIndex){
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
        return  endList;
    }

    public int size(){
        return this.size;
    }

    public int getNewCapacity(int oldCapacity) {
        return (int)(oldCapacity * 1.5 + 1);
    }

    public void createNewArray(){
        int newCapacity = getNewCapacity(this.elementData.length);
        Object[] tempArray = new Object[newCapacity];

        for (int i = 0; i < this.elementData.length; i++) {
            tempArray[i] = this.elementData[i];
        }
        this.elementData = new Object[newCapacity];
        this.elementData = tempArray;
    }

    public boolean checkFreeCells() {
        return this.elementData.length > this.size;
    }

    public void checkCorrectIndex(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Индекс вышел за предел массива");
        }
    }
}
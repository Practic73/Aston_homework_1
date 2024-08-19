import java.lang.reflect.Array;
import java.util.*;

public class Program {
    public static void main(String[] args) {
        MyArrayList <Integer> myList = new MyArrayList<Integer>(8);
        myList.add(0);
        myList.add(1);
        myList.add(2);
        myList.add(3);
        myList.add(4);
        myList.add(5);
        myList.add(6);
        myList.add(7);

        System.out.println(myList.sizeElemenData());
        System.out.println(myList.size());
        myList.add(8);
        myList.add(9);
        myList.add(11,11);
        myList.add(11,12);
        /*int count = (int) myList.get(1);
        for (int i = 0; i < 10; i++) {
            System.out.println(myList.get(i));
        }*/
    }
}

class MyArrayList<T>{

    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private Object[] elementData;

    public Object sizeElemenData(){
        return this.elementData.length;
    }

    public MyArrayList(){
        this.elementData = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public MyArrayList(int initialCapacity){
        if (initialCapacity < 0) {
            throw new IllegalArgumentException();
        }
        else {
            this.elementData = new Object[initialCapacity];
        }
        this.size = 0;
    }

    public MyArrayList(Collection<? extends Object> collection){

    }

    public MyArrayList(List <? extends Object> list){

    }

    public void add(Object element) {

        if (checkFreeCells()) {
            elementData[size] = element;
            size++;
        }
        else {
            System.out.println("Место закончилось, произошло создание нового подмассива.");
            createNewArray();
            elementData[size] = element;
            size++;
            System.out.print(Arrays.toString(this.elementData));
        }
    }

    public void add(int index, Object element){
        if (checkFreeCells()) {
            if (this.elementData[index] == null) {
                elementData[index] = element;
                size++;
            }
            else {
                for (int i = this.elementData.length - 1; i >= index; i --) {
                    if (this.elementData[i] == null) {
                        continue;
                    }
                    else {
                        this.elementData[i + 1] = this.elementData[i];
                    }
                }
                this.elementData[index] = element;
            }
        }
        System.out.println(Arrays.toString(this.elementData));

    }

    public Object remove(int index){
        return null;
    }

    public Object get(int index){
        Object element = null;
        try {
            element = elementData[index];
        }catch (ArrayIndexOutOfBoundsException e){
            System.exit(-1);
        }
        return element;
    }

    public Object set(int index, Object element){
        return null;
    }

    public List<Object> subList(int fromIndex, int toIndex){
        return null;
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
        this.elementData = null;
        this.elementData = new Object[newCapacity];
        this.elementData = tempArray;
    }

    public boolean checkFreeCells() {
        if (this.elementData.length > this.size) {
            return true;
        }
        else {
            return false;
        }
    }

}
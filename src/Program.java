import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class Program {
    public static void main(String[] args) {
    }
}

interface MyCollection{
    int size();
}

interface MyListInterface<T> extends MyCollection{
    void add(int index, T element);
    T remove(int index);
    T get(int index);
    T set(int index, T element);
    List<T> subList(int fromIndex, int toIndex);
}

class MyArrayList<T> implements MyListInterface{

    private int size;
    private T[] elementData;

    public MyArrayList(){
        this.size = 10;
    }

    public MyArrayList(int initialCapacity){
        if (initialCapacity < 0) {
            throw new IllegalArgumentException();
        }
        else {
            this.size = initialCapacity;
        }
    }

    public MyArrayList(Collection<? extends T> collection){

    }

    public MyArrayList(List <? extends T> list){

    }

    public void add(int index, Object element){
        System.out.println("True");
    }

    public T remove(int index){
        return null;
    }

    public T get(int index){
        T element = null;
        try {
            element = elementData[index];
        }catch (ArrayIndexOutOfBoundsException e){
            System.exit(-1);
        }
        return element;
    }

    public T set(int index, Object element){
        return null;
    }

    public List<T> subList(int fromIndex, int toIndex){
        return null;
    }

    public int size(){
        return 0;
    }
}
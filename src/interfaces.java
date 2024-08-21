interface MyCollection<T>{
    int size();
}

interface MyListInterface<T> extends MyCollection{
    void add(int index, T element);
    T remove(int index);
    T get(int index);
    T set(int index, T element);
    MyArrayList<T> subList(int fromIndex, int toIndex);
}

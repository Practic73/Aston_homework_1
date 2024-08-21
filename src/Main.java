public class Main {
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
        for (int i = 0; i < myList.sizeElementData(); i++) {
            System.out.print(myList.get(i) + "\t");
        }
        System.out.println();
        myList.add(8);
        for (int i = 0; i < myList.sizeElementData(); i++) {
            System.out.print(myList.get(i) + "\t");
        }
        System.out.println();
        myList.add(9);
        for (int i = 0; i < myList.sizeElementData(); i++) {
            System.out.print(myList.get(i) + "\t");
        }
        System.out.println();
        myList.add(9,11);
        for (int i = 0; i < myList.sizeElementData(); i++) {
            System.out.print(myList.get(i) + "\t");
        }
        System.out.println();
        myList.remove(4);
        for (int i = 0; i < myList.sizeElementData(); i++) {
            System.out.print(myList.get(i) + "\t");
        }
        System.out.println();
        myList.set(3,111);

        for (int i = 0; i < myList.sizeElementData(); i++) {
            System.out.print(myList.get(i) + "\t");
        }
        System.out.println();

        myList.add(1);
        myList.add(2);
        myList.add(3);
        for (int i = 0; i < myList.sizeElementData(); i++) {
            System.out.print(myList.get(i) + "\t");
        }
        System.out.println();

        System.out.println(myList.size());
        System.out.println(myList.sizeElementData());
        myList.remove(11);
        for (int i = 0; i < myList.sizeElementData(); i++) {
            System.out.print(myList.get(i) + "\t");
        }
        System.out.println();
    }
}
public class ArrayDeque<T> {
    T[] arr;
    private int size;
    int first = 0;
    int last = 3;

    public ArrayDeque() {
        arr = (T[]) new Object[8];
        size = 0;
    }

    public ArrayDeque(ArrayDeque other) {
        arr = (T[]) new Object[other.arr.length];
        size = other.size;
        int index = other.first;
        while (index != other.last) {
            arr[index] = (T) other.arr[index];
            index++;
            if (index > arr.length - 1)
                index = 0;
        }
    }

    public void addFirst(T item) {
        arr[first] = item;
        first--;
        if (first < 0)
            first = arr.length - 1;
        if (first == last) // only expand when the array is full
            expand();
    }

    public void addLast(T item) {
        arr[last] = item;
        last++;
        if (last > arr.length - 1)
            last = 0;
        if (last == first) // only expand when the array is full
            expand();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int index = first;
        while (index != last) {
            index++;
            if (index > arr.length - 1)
                index = 0;
            System.out.print(arr[index] + " ");
        }
        System.out.println();
    }

    public T removeFirst() {
        arr[first] = null;
        first++;
        if (first > arr.length - 1)
            first = 0;
        if ((double) size / arr.length < 0.3) // make sure the load factor is larger than or equal to 0.3
            shrink();
    }

    public T removeLast() {
        arr[last] = null;
        last--;
        if (last < 0)
            last = arr.length - 1;
        if ((double) size / arr.length < 0.3) // make sure the load factor is larger than or equal to 0.3
            shrink();
    }

    public T get(int index) {
        int getIndex = first + index;
        if (getIndex > arr.length - 1)
            getIndex -= arr.length;
        return arr[getIndex];
    }

    private void expand() {
        T[] largeArr = (T[]) new Object[arr.length * 2];
        int index = first;
        for (int i = 0; i < arr.length; i++) {
            largeArr[i] = arr[index];
            index++;
            if (index > arr.length - 1)
                index = 0;
        }
        first = 0;
        last = size - 1;
        arr = largeArr;
    }

    private void shrink() {
        // Don't shrink when the array's length is smaller than 8
        if (arr.length <= 8)
            return;
        T[] smallArr = (T[]) new Object[arr.length / 2];
        int index = first;
        for (int i = 0; i < arr.length; i++) {
            smallArr[i] = arr[index];
            index++;
            if (index > arr.length - 1)
                index = 0;
        }
        first = 0;
        last = size - 1;
        arr = smallArr;
    }
}

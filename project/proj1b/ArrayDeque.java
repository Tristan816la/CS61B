public class ArrayDeque<T> implements Deque<T>{
    T[] arr;
    private int size;
    int nextFirst = 0;
    int nextLast = 3;

    public ArrayDeque() {
        arr = (T[]) new Object[8];
        size = 0;
    }

    public ArrayDeque(ArrayDeque other) {
        arr = (T[]) new Object[other.arr.length];
        size = other.size;
        int index = other.nextFirst;
        while (index != other.nextLast) {
            arr[index] = (T) other.arr[index];
            index++;
            if (index > arr.length - 1)
                index = 0;
        }
    }

    @Override
    public void addFirst(T item) {
        arr[nextFirst] = item;
        nextFirst--;
        if (nextFirst < 0)
            nextFirst = arr.length - 1;
        if (nextFirst == nextLast) // only expand when the array is full
            expand();
    }

    @Override
    public void addLast(T item) {
        arr[nextLast] = item;
        nextLast++;
        if (nextLast > arr.length - 1)
            nextLast = 0;
        if (nextLast == nextFirst) // only expand when the array is full
            expand();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        int index = nextFirst;
        while (index != nextLast) {
            index++;
            if (index > arr.length - 1)
                index = 0;
            System.out.print(arr[index] + " ");
        }
        System.out.println();
    }
    @Override
    public T removeFirst() {
        T result = arr[nextFirst];
        arr[nextFirst] = null;
        nextFirst++;
        if (nextFirst > arr.length - 1)
            nextFirst = 0;
        if ((double) size / arr.length < 0.3) // make sure the load factor is larger than or equal to 0.3
            shrink();
        return result;
    }
    @Override
    public T removeLast() {
        T result = arr[nextLast];
        arr[nextLast] = null;
        nextLast--;
        if (nextLast < 0)
            nextLast = arr.length - 1;
        if ((double) size / arr.length < 0.3) // make sure the load factor is larger than or equal to 0.3
            shrink();
        return result;
    }
    @Override
    public T get(int index) {
        int getIndex = nextFirst + index;
        if (getIndex > arr.length - 1)
            getIndex -= arr.length;
        return arr[getIndex];
    }

    private void expand() {
        T[] largeArr = (T[]) new Object[arr.length * 2];
        int index = nextFirst;
        for (int i = 0; i < arr.length; i++) {
            largeArr[i + 1] = arr[index];
            index++;
            if (index > arr.length - 1)
                index = 0;
        }
        nextFirst = 0;
        nextLast = size;
        arr = largeArr;
    }

    private void shrink() {
        // Don't shrink when the array's length is smaller than 8
        if (arr.length <= 8)
            return;
        T[] smallArr = (T[]) new Object[arr.length / 2];
        int index = nextFirst;
        for (int i = 0; i < arr.length; i++) {
            smallArr[i + 1] = arr[index];
            index++;
            if (index > arr.length - 1)
                index = 0;
        }
        nextFirst = 0;
        nextLast = size;
        arr = smallArr;
    }
}

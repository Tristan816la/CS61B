import edu.princeton.cs.algs4.Queue;

import org.junit.Test;

public class TestSortAlgs {

    @Test
    public void testQuickSort() {
        Queue<String> tas = new Queue<String>();
        tas.enqueue("d");
        tas.enqueue("a");
        tas.enqueue("c");
        tas.enqueue("b");
        tas.enqueue("e");
        tas.enqueue("i");
        tas.enqueue("f");
        tas = QuickSort.quickSort(tas);
        assert (isSorted(tas));
    }

    @Test
    public void testMergeSort() {
        Queue<String> tas = new Queue<String>();
        tas.enqueue("d");
        tas.enqueue("a");
        tas.enqueue("c");
        tas.enqueue("b");
        tas.enqueue("e");
        tas.enqueue("i");
        tas.enqueue("f");
        tas = MergeSort.mergeSort(tas);
        assert (isSorted(tas));
    }

    /**
     * Returns whether a Queue is sorted or not.
     *
     * @param items A Queue of items
     * @return true/false - whether "items" is sorted
     */
    private <Item extends Comparable> boolean isSorted(Queue<Item> items) {
        if (items.size() <= 1) {
            return true;
        }
        Item curr = items.dequeue();
        Item prev = curr;
        while (!items.isEmpty()) {
            prev = curr;
            curr = items.dequeue();
            if (curr.compareTo(prev) < 0) {
                return false;
            }
        }
        return true;
    }
}

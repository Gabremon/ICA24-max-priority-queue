import java.util.Vector;

// Class definition for the min-priority queue
class MinPriorityQueue {
    Vector<Integer> heap;

    // Constructor to initialize the heap
    public MinPriorityQueue() {
        heap = new Vector<>();
    }

    // Returns the minimum element from the min heap
    public int heapMinimum() {
        if (heap.size() > 0) {
            return heap.get(0);
        } else {
            throw new IllegalStateException("Heap is empty");
        }
    }

    // Extracts the minimum element from the min heap
    public int heapExtractMin() {
        if (heap.size() > 0) {
            int min = heap.get(0);
            heap.set(0, heap.get(heap.size() - 1));
            heap.remove(heap.size() - 1);
            minHeapify(0);
            return min;
        } else {
            throw new IllegalStateException("Heap is empty");
        }
    }

    // Decreases the key value of a heap element
    public void heapDecreaseKey(int index, int key) {
        if (key > heap.get(index)) {
            throw new IllegalArgumentException("New key is larger than current key");
        }
        heap.set(index, key);
        while (index > 0 && heap.get(parent(index)) > heap.get(index)) {
            int temp = heap.get(index);
            heap.set(index, heap.get(parent(index)));
            heap.set(parent(index), temp);
            index = parent(index);
        }
    }

    // Inserts a new key into the min heap
    public void minHeapInsert(int key) {
        heap.add(Integer.MAX_VALUE);
        heapDecreaseKey(heap.size() - 1, key);
    }

    // Helper function to maintain the min heap property
    private void minHeapify(int i) {
        int left = left(i);
        int right = right(i);
        int smallest = i;

        if (left < heap.size() && heap.get(left) < heap.get(i)) {
            smallest = left;
        }
        if (right < heap.size() && heap.get(right) < heap.get(smallest)) {
            smallest = right;
        }
        if (smallest != i) {
            int temp = heap.get(i);
            heap.set(i, heap.get(smallest));
            heap.set(smallest, temp);
            minHeapify(smallest);
        }
    }

    // Helper functions to get parent, left, and right indices
    private int parent(int i) { return (i - 1) / 2; }
    private int left(int i) { return 2 * i + 1; }
    private int right(int i) { return 2 * i + 2; }
}

public class Main {
    public static void main(String[] args) {
        // Initialize the min-priority queue
        MinPriorityQueue pq = new MinPriorityQueue();

        // Insert elements into the priority queue
        System.out.println("Inserting elements: 5, 3, 17, 10, 84, 19, 6, 22, 9");
        pq.minHeapInsert(5);
        pq.minHeapInsert(3);
        pq.minHeapInsert(17);
        pq.minHeapInsert(10);
        pq.minHeapInsert(84);
        pq.minHeapInsert(19);
        pq.minHeapInsert(6);
        pq.minHeapInsert(22);
        pq.minHeapInsert(9);

        // Get and print the minimum element
        System.out.println("The minimum element is: " + pq.heapMinimum());

        // Extract and print the minimum element
        System.out.println("Extracting the minimum element: " + pq.heapExtractMin());
        System.out.println("The new minimum after extraction is: " + pq.heapMinimum());

        // Decrease the key of an element (assuming we want to decrease the key at index 2)
        System.out.println("Decreasing the key of the element at index 2 to 1");
        pq.heapDecreaseKey(2, 1);
        System.out.println("The minimum element after decreasing the key: " + pq.heapMinimum());

        // Extract and print all elements to empty the priority queue
        System.out.println("Extracting all elements from the queue:");
        while (true) {
            try {
                System.out.print(pq.heapExtractMin() + " ");
            } catch (IllegalStateException e) {
                System.out.println("\nThe priority queue is now empty.");
                break;
            }
        }
    }
}
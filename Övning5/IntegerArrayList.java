/**
 * A simple implementation of a dynamic array for integers.
 * Mimics the behavior of a basic ArrayList for integers.
 */
public class IntegerArrayList {

    // Initial capacity of the list
    private static final int INITIAL_CAPACITY = 10;
    // Internal array to store elements
    private int[] array;
    // The current size of the list (number of elements added)
    private int size;

    /**
     * Constructs an empty list with an initial capacity of 10.
     */
    public IntegerArrayList() {
        this.array = new int[INITIAL_CAPACITY];
        this.size = 0;
    }

    /**
     * Adds an integer element to the end of the list.
     * @param element the integer to be added
     */
    public void add(int element) {
        if (size == array.length) {
            // Resize the array within the add method itself
            int newCapacity = array.length * 2;
            int[] newArray = new int[newCapacity];
            // Copy the elements to the new array
            for (int i = 0; i < size; i++) {
                newArray[i] = array[i];
            }
            array = newArray; // Set the field array to the newly created one
        }
        array[size] = element;
        size++;
    }
    

    /**
     * Retrieves the element at the specified index.
     * @param index the position of the element to return
     * @return the integer at the specified position in the list
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return array[index];
    }

    /**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left.
     * @param index the index of the element to be removed
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        // Shift elements to the left
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
    }

    /**
     * Returns the number of elements in the list.
     * @return the number of elements in the list
     */
    public int size() {
        return size;
    }

    /**
     * Returns true if this list contains the specified element.
     * @param element the integer to check for
     * @return true if the element is in the list, false otherwise
     */
    public boolean contains(int element) {
        for (int i = 0; i < size; i++) {
            if (array[i] == element) {
                return true;
            }
        }
        return false;
    }
    /**
     * Returns a string representation of the list.
     * @return a string containing the elements of the list
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(array[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    // Example of using IntegerArrayList
    public static void main(String[] args) {
        IntegerArrayList list = new IntegerArrayList();
        list.add(10);
        list.add(20);
        list.add(30);

        System.out.println("List: " + list); // Output: [10, 20, 30]
        System.out.println("Size: " + list.size()); // Output: 3
        System.out.println("Get element at index 1: " + list.get(1)); // Output: 20

        list.remove(1); // Remove element at index 1
        System.out.println("After removing index 1: " + list); // Output: [10, 30]

        System.out.println("Contains 30? " + list.contains(30)); // Output: true
    }
}

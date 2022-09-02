/**
 * Written by Nicole Vu - vu000166 and Gina Yi - yi000058
 * ArrayList class creates ArrayList instances and functions
 */

public class ArrayList<T extends Comparable<T>> implements List<T> {
    // Constructor
    public ArrayList() {
        array = (T[]) new Comparable[2];
        isSorted = true;
    }

    // Override methods
    @Override
    public boolean add(T element) {
        if (element == null) {
            return false;
        }
        // check and resize if the array is full
        if (size+1 >= array.length) {
            T[] temp = (T[]) new Comparable[array.length*2 + 1];
            for (int i = 0; i < array.length; i++) {
                temp[i] = array[i];
            }
            array = temp;
        }
        array[size] = element;

        // check if the added element breaks sorted order
        if (size > 0 && element.compareTo(array[size-1]) < 0)
            isSorted = false;
        size++;
        return true;
    }

    @Override
    public boolean add(int index, T element) {
        if (element == null || index < 0  || index >= size) {
            return false;
        }
        // check and resize if the array is full
        if (size+1 >= array.length) {
            T[] temp = (T[]) new Comparable[array.length*2 + 1];
            for (int i = 0; i < array.length; i++) {
                temp[i] = array[i];
            }
            array = temp;
        }

        // shift the elements after index in the array 1 spot to the right
        int count = array.length - 1;
        while (count >= index){
            if (array[count] != null) {
                array[count+1] = array[count];
            }
            count--;
        }
        array[index] = element;

        // check if the added element breaks sorted order
        if ((index > 0 && (element.compareTo(array[index-1]) < 0)) ||
                (index < (array.length-1) && element.compareTo(array[index+1]) > 0)) {
            isSorted = false;
        }
        size++;
        return true;
    }

    @Override
    public void clear() {
        array = (T[]) new Comparable[2]; // change the length of the array back to 2
        size = 0; // change the number of elements in the array to 0
        isSorted = true;
    }

    @Override
    public T get(int index) {
        if (index <= size && index >= 0) // check if index is out of bound
            return array[index];
        return null;
    }

    @Override
    public int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            // if the array is sorted and the current index in the array is greater than the element
            // stop the loop to increase efficiency
            if (isSorted == true && array[i].compareTo(element) > 0) {
                break;
            }
            else if (array[i].compareTo(element) == 0) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void sort() {
        // only run if isSorted is false to increase efficiency
        if (isSorted == false) {
            int i, j, minIndex;
            T temp;

            // selection sort
            for (i = 0; i < size - 1; i++) {
                minIndex = i;
                for (j = i+1; j < size; j++) {
                    if (array[j].compareTo(array[minIndex]) < 0)
                        minIndex = j;
                }
                temp = array[minIndex];
                array[minIndex] = array[i];
                array[i] = temp;
            }
            isSorted = true;
        }
    }

    @Override
    public T remove(int index) {
        //check if index is out of bound
        if (index >= array.length || index < 0) {
            return null;
        }
        else {
            T removedNode = array[index];
            int count = index;
            while (array[count] != null) {
                array[count] = array[count+1];
                count++;
            }
            size--;

            // check if isSorted is still true
            isSorted = true;
            for (int i = 1; i < size; i++){
                if (array[i].compareTo(array[i-1]) < 0) {
                    isSorted = false;
                    break;
                }
            }
            return removedNode;
        }
    }

    @Override
    public void equalTo(T element) {
        if (element != null) {
            int next = 0;
            int notEqual = 0; // number of elements that do not equal to given element
            for (int i = 0; i < size; i++) {
                // if the array is sorted and the current index in the array is greater than the element
                // stop the loop to increase efficiency
                if (isSorted == true && array[i].compareTo(element) > 0) {
                    break;
                }
                else if (array[i].compareTo(element) == 0) {
                    array[next] = array[i];
                    next++;
                }
                else {
                    notEqual++;
                }
            }
            size -= notEqual;
            for (int i = size; i < array.length; i++){
                array[i] = null;
            }
            isSorted = true;
        }

    }

    @Override
    public void reverse() {
        if (size != 0) {
            int head = 0;
            int tail = size - 1;
            T temp;
            while (head < tail) { //switch elements from head and tail indices
                temp = array[head];
                array[head] = array[tail];
                array[tail] = temp;
                head++;
                tail--;
            }

            // check if isSorted is still true
            isSorted = true;
            for (int i = 1; i < size; i++){
                if (array[i].compareTo(array[i-1]) < 0) {
                    isSorted = false;
                    break;
                }
            }
        }
    }

    @Override
    public void merge(List<T> otherList) {
        if (otherList != null) {
            ArrayList<T> other = (ArrayList<T>)otherList;
            sort();
            other.sort();

            // merge
            T[] temp = (T[]) new Comparable[this.size + other.size()];
            int tempCount = 0, thisCount = 0, otherCount = 0;
            while (tempCount < this.size + other.size()) { // run until the new array copy all the elements from both arrays
                // if all elements in this array are copied, continue with the rest of other array
                if (thisCount == size) {
                    temp[tempCount] = other.array[otherCount];
                    otherCount++;
                }
                // if all elements in other array are copied, continue with the rest of this array
                // or if the current element of this array is smaller than current element of other array
                else if (otherCount == other.size() ||
                        array[thisCount].compareTo(other.array[otherCount])<=0) {
                    temp[tempCount] = array[thisCount];
                    thisCount++;
                }
                // if the current element of other array is smaller than current element of this array
                else {
                    temp[tempCount] = other.array[otherCount];
                    otherCount++;
                }
                tempCount++;
            }
            array = temp;
            size += other.size();
            isSorted = true;
        }
    }

    @Override
    public boolean rotate(int n) {
        int rotate = 0; //variable to keep track of how many rotations will actually be made
        T temp;
        //determines how many times the array should actually be rotated based on size of array and n value
        if (n <= 0 || size <= 1 )
            return false;
        else if (n < size){
            rotate = n;
        }
        else if (n > size){
            rotate = n % size;
        }
        //loop to move all values to the right one index and move the last value to the front
        while (rotate > 0){
            int tail = size-1;
            temp = array[size-1];
            while(tail > 0) {
                array[tail] = array[tail - 1];
                tail--;
            }
            array[0] = temp;
            rotate--;
        }

        // check if isSorted is still true
        isSorted = true;
        for (int i = 1; i < size; i++){
            if (array[i].compareTo(array[i-1]) < 0) {
                isSorted = false;
                break;
            }
        }
        return true;
    }

    public String toString() {
        String str = "";
        int count = 0;
        while (count!= size && array[count] != null) {
            str += array[count] +"\n";
            count++;
        }
        return str;
    }

    @Override
    public boolean isSorted() {
        return isSorted;
    }

    private boolean isSorted;
    private T[] array;
    private int size = 0; // number of elements in the list

    // main
    public static void main(String[] args) {
    }
}

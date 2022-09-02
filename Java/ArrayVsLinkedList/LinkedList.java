/**
 * Written by Nicole Vu - vu000166 and Gina Yi - yi000058
 * LinkedList class creates LinkedList instances and functions
 */
public class LinkedList<T extends Comparable<T>> implements List<T> {
    // Constructor, create empty but headed list
    public LinkedList() {
        isSorted = true;
        size = 0;
    }

    // Implemented methods
    // add() adds elements to the end of the list
    @Override
    public boolean add(T element) {
        if (element == null) {
            return false;
        }
        // Case 1: List is empty, start and tail point to new node
        if (size == 0) {
            start = new Node<T>(element, start);
            tail = start;
        }
        // Case 2: List is not empty, the current node and tail point to the new node
        else {
            tail.setNext(new Node<T>(element, null));
            if (tail.getData().compareTo(element) > 0) { // if the new item is smaller than the current item
                isSorted = false;
            }
            tail = tail.getNext();
        }
        size++;
        return true;
    }

    @Override
    public boolean add(int index, T element) {
        if (element == null || index < 0 || index >= size) {
            return false;
        }
        // add to start
        if (index == 0) {
            // test sorted
            if (start.getData().compareTo(element) < 0) {
                isSorted = false;
            }
            start = new Node<T>(element, start);
        }
        // add to array of 2 and more
        else {
            ptr = start.getNext();
            trailer = start;

            for (int i = 0; i < index; i++) {
                if (i == (index-1)) {
                    trailer.setNext(new Node<T>(element, ptr));
                    //test sorted
                    if (trailer.getData().compareTo(element) > 0 ||
                            ptr.getData().compareTo(element) < 0) {
                        isSorted = false;
                    }
                }
                else {
                    ptr = ptr.getNext();
                    trailer = trailer.getNext();
                }
            }
        }
        size++;
        return true;
    }

    @Override
    public void clear() {
        start = null; // change the pointer of header to null
        size = 0;
        isSorted = true;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > (size -1)){
            return null;
        }
        else{
            ptr = start;
            int count = 0;
            while (count < index) {
                ptr = ptr.getNext();
                count++;
            }
            return ptr.getData();
        }
    }

    @Override
    public int indexOf(T element) {
        ptr = start;
        for (int i = 0; i < size; i++) {
            // if the list is sorted
            if (isSorted == true) {
                if (element.compareTo(ptr.getData()) == 0) {
                    return i;
                }
                // terminate if the current node is greater than element
                else if (element.compareTo(ptr.getData()) < 0) {
                    return -1;
                }
                else {
                    ptr = ptr.getNext();
                }
            }
            else {
                if (element.compareTo(ptr.getData()) == 0) {
                    return i;
                }
                else {
                    ptr = ptr.getNext();
                }
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
        // only sort when isSorted is false to increase efficiency
        if (isSorted == false && size > 1) {

            trailer = start; // trailer will be pointer to the position being checked
            ptr = start.getNext();

            T temp;
            for (int i = 0; i < size-1; i++) {
                for (int j = i; j < (size-1); j++) {
                    if (ptr.getData().compareTo(trailer.getData()) < 0) {
                        temp = trailer.getData();
                        trailer.setData(ptr.getData());
                        ptr.setData(temp);
                    }
                    ptr = ptr.getNext();
                }
                trailer = trailer.getNext();
                ptr = trailer.getNext();
            }
        }
        isSorted = true;
    }

    @Override

    public T remove(int index) {
        Node<T> temp = null;
        if (index < 0 || index >= size || size == 0) {
            return null;
        }
        // remove from list of 1 node
        if (size == 1) {
            temp = start;
            start = null;
        }
        //remove from start
        else if (index == 0) {
            temp = start;
            start = start.getNext();
        }
        // remove from list of 2 and more, not from start
        else {
            ptr = start.getNext();
            trailer = start;
            // remove from end
            if (index == size-1) {
                while (ptr.getNext() != null) {
                    ptr = ptr.getNext();
                    trailer = trailer.getNext();
                }
                temp = ptr;
                trailer.setNext(null);
            }
            else {
                int count = 0;
                while (count < (index-1)) {
                    ptr = ptr.getNext();
                    trailer = trailer.getNext();
                    count++;
                }
                temp = ptr;
                ptr = ptr.getNext();
                trailer.setNext(ptr);
            }
        }
        size--;
        //check sorted
        if (size == 1)
            isSorted = true;
        if (size > 1) {
            Node<T> ptr2 = start.getNext();
            trailer = start;
            isSorted = true;
            while (ptr2 != null) {
                if (trailer.getData().compareTo(ptr2.getData()) > 0) {
                    isSorted = false;
                    break;
                }
                trailer = trailer.getNext();
                ptr2 = ptr2.getNext();
            }
        }
        return temp.getData();
    }

    @Override
    public void equalTo(T element) {
        if (element != null) {
            Node<T> point = start;
            //Loop to find first occurrence of element in list
            while(!point.getData().equals(element) && point.getNext() != null){
                size--;
                point = point.getNext();
                if (point.getData().equals(element)){
                    start = point;
                }
            }
            point = start.getNext();
            trailer = start;
            int sortSize = 1; //variable used to keep track of size if list is already sorted
            //Loop to find the element in the list
            while (point != null){
                if (point.getData().equals(element)){
                    trailer.setNext(point);
                    trailer = trailer.getNext();
                    sortSize++;

                }
                //if statement to break the loop if the list is sorted and the next value is not equal to the element
                else if (!point.getData().equals(element) && isSorted == true) {
                    trailer.setNext(null);
                    size = sortSize;
                    break;
                }
                else if (!point.getData().equals(element)){
                    trailer.setNext(point.getNext());
                    size--;
                }
                point = point.getNext();

            }
            isSorted = true;
        }
    }

    @Override
    public void reverse() {
        if (size > 1) {
            Node<T> newTail = null;
            Node<T> temp;
            ptr = start;
            tail = start;
            while (ptr != null) {
                temp = ptr;
                ptr = ptr.getNext();
                temp.setNext(newTail);
                newTail = temp;
            }
            start = newTail;
        }
        //check sorted
        Node<T> ptr2 = start.getNext();
        trailer = start;
        isSorted = true;
        while (ptr2 != null) {
            if (trailer.getData().compareTo(ptr2.getData()) > 0) {
                isSorted = false;
                break;
            }
            trailer = trailer.getNext();
            ptr2 = ptr2.getNext();
        }
    }

    @Override
    public void merge(List<T> otherList) {
        LinkedList<T> other = (LinkedList<T>)otherList;
        sort();
        other.sort();
        Node<T> header = new Node<T>(null,null);
        ptr = header;
        while (true) {
            if (start == null) {
                ptr.setNext(other.start);
                break;
            }
            else if (other.start == null) {
                ptr.setNext(start);
                break;
            }
            else if (start.getData().compareTo(other.start.getData()) < 0) {
                ptr.setNext(start);
                start = start.getNext();
            }
            else {
                ptr.setNext(other.start);
                other.start = other.start.getNext();
            }
            ptr = ptr.getNext();
        }
        size += other.size();
        // skip header node
        start = header.getNext();
        tail = ptr.getNext();
    }

    @Override
    public boolean rotate(int n) {
        if (n <= 0 || size <= 1){
            return false;
        }

        else{
            trailer = start;
            Node<T> end = start.getNext();
            int rotation = 0;
            //loop to point the tail to start and make tail the new start
            while (rotation < n){
                //loop to find the last value in the list (tail value)
                while (end!= null){
                    if (end.getNext() == null) {
                        tail = end;
                        trailer.setNext(null);
                    }
                    end = end.getNext();
                    trailer = trailer.getNext();
                }
                tail.setNext(start);
                start = tail;
                end = start.getNext();
                trailer = start;
                rotation++;
            }

            //check sorted
            Node<T> ptr2 = start.getNext();
            trailer = start;
            isSorted = true;
            while (ptr2 != null) {
                if (trailer.getData().compareTo(ptr2.getData()) > 0) {
                    isSorted = false;
                    break;
                }
                trailer = trailer.getNext();
                ptr2 = ptr2.getNext();
            }
            return true;
        }
    }


    public String toString() {
        String str = "";
        ptr = start;
        while (ptr != null) {
            str += ptr.getData() + "\n";
            ptr = ptr.getNext();
        }
        return str;
    }

    @Override
    // isSorted() function returns isSorted
    public boolean isSorted() {
        return isSorted;
    }

    // Initialize member variables
    private boolean isSorted = false;
    private Node<T> start = null;
    private Node<T> tail;
    private Node<T> ptr;
    private Node<T> trailer;

    private int size; // number of elements in the list, header excluded

    // main
    public static void main(String[] args) {

    }
}

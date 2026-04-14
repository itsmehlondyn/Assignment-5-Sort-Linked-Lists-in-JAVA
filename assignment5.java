
/** ***********************************************************************
 * Assignment 5 - Generic Linked List
 *
 * Author: Londyn Pepperdine
 * Course: CSCI 271 Spring 2026
 * Date: April 13 2026
 *
 * Purpose:
 * This program implements a generic linked list in Java. It allows
 * users to insert, delete, access, split, and sort elements using
 * different methods.
 *
 * Method:
 * The program uses a singly linked list structure with nodes that
 * store data and references to the next node. Sorting is done using
 * a recursive insertion sort approach.
 *
 ************************************************************************ */
/** *****************************************************************
 * I declare and confirm the following:
 * - I have not discussed this program code with anyone other than my
 * instructor or teaching assistants.
 * - I have not used unauthorized code from the Internet or others.
 * - Any outside sources used are properly cited in comments.
 * - This program does not interfere with grading systems.
 *
 * Londyn Pepperdine
 ******************************************************************* */

// Java Program to Implement Generic Linked List
// Importing all input output classes
import java.util.Scanner;

// Node Class: (Generic Node class for LinkedList)
class Node<T> {

    private T element; // Data element to sore an item
    private Node<T> next; // a reference to the next Node

    // Parameterized constructor to assign value
    Node(T item) {
        this.element = item; // "this" refers to current object itself
        this.next = null;
    }

    void setNext(Node<T> n) { // set the private member
        this.next = n;
    }

    Node<T> getNext() { //get the private member
        return this.next;
    }

    void setElement(T item) { // set the private member
        this.element = item;
    }

    T getElement() { //get the private member
        return this.element;
    }

    public void displayRecursively() { // display Node recursively
        System.out.print(this.getElement());
        if (this.next != null) {
            System.out.print(" , ");
            this.next.displayRecursively();
        } else {
            System.out.println("] ");
        }
    }
}

// List Class: ( Generic LinkedList class)
class List<T extends Comparable<T>> {

    // Generic Node instance
    private Node<T> head;
    private int size; // store the size of the list

    List() { // Default constructor
        this.head = null; // a reference ot the head node
        this.size = 0;    // maintain the size of the list
    }

    // ********************************** isEmpty() **********************************
    boolean isEmpty() { // a method to check if the List is empty
        return (this.head == null);
    }

    // ********************************** addLast() **********************************
    void addLast(T item) { // add a Node containing item at the end of List
        Node<T> newNode = new Node<>(item); // create a new Node with the given item

        if (this.head == null) { // Check if the head
            head = newNode;
        } else {
            Node<T> temp = this.head; // create a temporary reference to the first node in the list

            while (temp.getNext() != null) { // move the reference to the last node
                temp = temp.getNext();
            }
            temp.setNext(newNode); // link the last node to the new node referenced by newNode 
        }
        this.size++; // update the size of the list
    }

    // ********************************** addFront() **********************************
    void addFront(T item) { //a method to add an item at the front of the list
        Node<T> newNode = new Node<>(item); // create a new Node with the given item
        newNode.setNext(this.head);
        this.head = newNode;
        this.size++;
        System.out.println("new node added at front !");
    }

    // ********************************** addAt() **********************************
    void addAt(int index, T item) { // add item in a new Node at index position

        // Checking if position is valid
        if (index < 0 || index >= this.size) {
            System.out.println("index is out of bound !");
        } else {
            Node<T> newNode = new Node<>(item); // create a new Node with the given item

            int count = 0;  // start a counter
            Node<T> temp = head; // create a temporary Node reference
            while (temp != null && count < index - 1) { // move to index position
                count++;
                temp = temp.getNext();
            }
            if (index > 0) {
                newNode.setNext(temp.getNext());
                temp.setNext(newNode);
            } else if (index == 0) {
                newNode.setNext(this.head);
                this.head = newNode;
            }
            System.out.println("new node added at index " + index);
        }
    }

    // ********************************** size() **********************************
    public int size() { // a method to return the number of elements in the list
        return this.size;
    }

    // ********************************** displayAll() **********************************
    public void displayAll() { // Method to display the LinkedList

        System.out.println("displayAll():");
        System.out.println("____________List of " + this.size + " items________________");

        if (this.isEmpty()) {
            System.out.println("The list is empty ...! ");
        } else {
            Node<T> temp = this.head;
            System.out.print("[ ");
            while (temp != null) {
                System.out.print(temp.getElement());
                if (temp.getNext() == null) {
                    System.out.println("] ");
                } else {
                    System.out.print(" , ");
                }
                temp = temp.getNext();
            }
        }
        System.out.println();
    }

    // ********************************** displayRecursively() **********************************
    public void displayRecursively() {
        System.out.println("displayRecursively():");
        System.out.println("____________List of " + this.size + " items________________");
        if (this.isEmpty()) {
            System.out.println("The list is empty ...! ");
        } else {
            System.out.print("[ ");
            this.head.displayRecursively();
        }
        System.out.println();
    }

    // ********************************** removeLast() **********************************
    public void removeLast() {
        if (this.isEmpty()) {
            System.out.println("The list is empty !");
        } else if (this.head.getNext() == null) {
            this.head = null;
            this.size--;    // update the size
            System.out.println("last item removed");
        } else {
            Node<T> temp = this.head;

            while ((temp.getNext() != null) && (temp.getNext().getNext() != null)) {
                temp = temp.getNext();
            }
            temp.setNext(null);
            temp = null;
            this.size--;        // update teh size
            System.out.println("last item removed");
        }
    }

    // ********************************** removeAt() **********************************
    public void removeAt(int index) {
        if (this.isEmpty()) { // check the empty list
            System.out.println("The list is empty !");
        } else if (index >= this.size || index < 0) { // check index is valid
            System.out.println("index is out of bound !");
        } else if (index == 0) { // remove the first Node
            this.head = this.head.getNext();
            this.size--;
            System.out.println("item is removed at index " + index);
        } else { // remove other than the first node
            int count = 0;
            Node<T> temp = this.head;

            while (count < index - 1) { // search for the node at position index
                count++;
                temp = temp.getNext();
            }

            Node<T> pNode = temp.getNext(); // hold on to that node temporarily 
            temp.setNext(pNode.getNext());  // hold on to that node temporarily 
            pNode.setNext(null);            // disconnect that node form the list
            this.size--;                    // update the size
            System.out.println("item is removed at index " + index);
        }
    }

    // ********************************** removeFront() **********************************
    public void removeFront() {
        if (this.isEmpty()) {
            System.out.println("The list is empty !");
        } else {
            Node<T> temp = this.head;
            this.head = this.head.getNext();
            temp.setNext(null);
            System.out.println("front item removed");
        }
    }

    // ********************************** getAt() **********************************
    T getAt(int index) {
        T res = null;
        if (this.isEmpty()) {
            System.out.println("The list is empty !");
        } else if (index >= this.size || index < 0) {
            System.out.println("index is out of bound !");
        } else if (index == 0) {
            res = this.head.getElement();
        } else {
            int count = 0;
            Node<T> temp = this.head;

            while (count++ != index) { // search for the node at position index
                temp = temp.getNext();
            }
            res = temp.getElement();
        }
        return res;
    }

    
    /****************************splitAt()****************************
     * Description: Splits the list at the given index into two halves. The left
     * half (indices 0 to index-1) remains in 'this' list. The right half
     * (indices index to size-1) is returned as a new list.
     *
     * Parameters: int index - the position at which to split the list (input)
     *
     * Pre: The list must be non-empty and index must satisfy 0 <= index < size.
     * If the list is empty or index is out of bounds, an error message is
     * printed and an empty list is returned.
     *
     * Post: 'this' list contains elements from index 0 up to (but not
     * including) index. The returned list contains elements from index onward
     * to the end. The size fields of both lists are updated accordingly.
     *
     * Returns: A new List<T> containing the right half of the original list.
     *
     * Called by: insertionSort() Calls: displayAll()
     ***********************************************************************/

    public List<T> splitAt(int index) {

        List<T> Right = new List<>();

        if (this.isEmpty()) {
            System.out.println("The list is empty !");
        } else if (index >= this.size || index < 0) {
            System.out.println("index is out of bound !");
        } else if (index == 0) {
            this.head = null;
        } else {

            System.out.println("Original list before split:");
            this.displayAll();

            Node<T> temp = this.head;
            for (int i = 0; i < index - 1; i++) {   //get to position before index
                temp = temp.getNext();
            }

            Right.head = temp.getNext();        // right list starts at index
            Right.size = this.size - index;     // right gets everything from index onward
            temp.setNext(null);                 // sever: left list ends here
            this.size = index;                  // left list now has 'index' elements

            System.out.println("Left list after split:");
            this.displayAll();
            System.out.println("Right list after split:");
            Right.displayAll();
        }

        return Right;

    }

    
    /****************************insertSorted()****************************
     * Description: Inserts the given item into the list in sorted (ascending)
     * order. Assumes the list is already sorted before the call.
     *
     * Parameters: T item - the element to be inserted (input)
     *
     * Pre: The list must already be sorted in ascending order, or be empty. T
     * must implement Comparable<T>.
     *
     * Post: The item is inserted at the correct sorted position. The size of
     * the list is incremented by 1. The sorted order of the list is maintained.
     *
     * Returns: void
     *
     * Called by: insertionSort() Calls: none
     ************************************************************************/

    public void insertSorted(T item) {

        Node<T> newNode = new Node<>(item); // create a new Node with the given item

        // Case 1: list is empty, or item belongs at the front
        if (this.isEmpty() || item.compareTo(this.head.getElement()) < 0) {
            newNode.setNext(this.head);
            this.head = newNode;
            this.size++;
            return;
        }

        // Case 2: walk until we find the right spot
        Node<T> temp = this.head;
        while (temp.getNext() != null
                && item.compareTo(temp.getNext().getElement()) >= 0) { // walk until we find the right spot
            temp = temp.getNext();
        }

        // Insert newNode between temp and temp.getNext()
        newNode.setNext(temp.getNext());
        temp.setNext(newNode);
        this.size++;
    }

    /******************************insertionSorted()****************************
     * Description: Sorts the list in ascending order using a recursive
     * merge-sort-style algorithm. The list is split at the midpoint, each half
     * is sorted recursively, and then the right half is merged back into the
     * left half using insertSorted().
     *
     * Parameters: none
     *
     * Pre: The list may be in any order (sorted or unsorted). T must implement
     * Comparable<T>.
     *
     * Post: All elements in 'this' list are arranged in ascending sorted order.
     * The size of the list remains unchanged.
     *
     * Returns: void
     *
     * Called by: main() (via menu option 99) Calls: splitAt(), insertSorted(),
     * insertionSort() (recursively)
     ************************************************************************/
    public void insertionSort() {

        // Base case: a list of 0 or 1 element is already sorted
        if (this.size <= 1) {
            return;
        }

        // Check for empty list and warn the user
        if (this.isEmpty()) {
            System.out.println("insertionSort(): an empty list cannot be sorted!");
            return;
        }

        // Step 1 — split at the midpoint; L keeps [0 .. N/2-1], L2 gets [N/2 .. N-1]
        int mid = this.size / 2;   // integer division; for odd N this gives (N-1)/2
        List<T> L2 = this.splitAt(mid);  // 'this' is now L1

        // Step 2 — recursively sort each half
        this.insertionSort();   // sort L1 (the left half, now stored in 'this')
        L2.insertionSort();     // sort L2 (the right half)

        // Step 3 — merge: insert every element of L2 into L1 (this) using insertSorted()
        Node<T> cur = L2.head;
        while (cur != null) {
            this.insertSorted(cur.getElement());
            cur = cur.getNext();
        }
        // 'this' now contains the fully sorted, merged list
    }
}

// The class for the Main Program
public class assignment5 {

    public static void main(String[] args) { // The main() method

        List<Integer> list = new List<>();
        Integer ch, item, index;
        boolean quit = false;

        Scanner sc = new Scanner(System.in);

        do {

            System.out.println("____________Main Menu_____________________");
            System.out.println("select option :");
            System.out.println("1: insert back");
            System.out.println("2: insert front");
            System.out.println("3: insert at index");
            System.out.println("4: display items");
            System.out.println("5: get item at index");
            System.out.println("6: delete back");
            System.out.println("7: delete front");
            System.out.println("8: delete at index");
            System.out.println("88: split list at index");
            System.out.println("11: insert item in sorted list");
            System.out.println("99: sort list using insertion sort");
            System.out.println("9: exit");
            ch = sc.nextInt();

            switch (ch) {
                case 1:
                    System.out.println("enter item to insert:");
                    item = sc.nextInt(); // read in an integer
                    list.addLast(item);
                    break;
                case 2:
                    System.out.println("enter item to insert:");
                    item = sc.nextInt(); // read in an integer
                    list.addFront(item);
                    break;
                case 3:
                    System.out.println("enter item to insert:");
                    item = sc.nextInt(); // read in an integer
                    System.out.println("enter index:");
                    index = sc.nextInt(); // read in an integer
                    list.addAt(index, item);
                    break;
                case 4:
                    list.displayAll();
                    list.displayRecursively();
                    break;
                case 5:
                    System.out.println("enter index:");
                    index = sc.nextInt();
                    System.out.println("item at index:" + index + " is: " + list.getAt(index));
                    break;
                case 6:
                    list.removeLast();
                    break;
                case 7:
                    list.removeFront();
                    break;
                case 8:
                    System.out.println("enter index:");
                    index = sc.nextInt();
                    list.removeAt(index);
                    break;
                case 9:
                    quit = true;
                    break;
                case 88:
                    System.out.println("enter index of where to spilt list:");
                    index = sc.nextInt();
                    list.splitAt(index);
                    break;
                case 11:
                    System.out.println("enter item to be inserted:");
                    item = sc.nextInt();
                    list.insertSorted(item);
                    break;
                case 99:
                    list.insertionSort();
                    break;
                default:
                    System.out.println("invalid selection");
            }
        } while (!quit);
        sc.close();
    }
}

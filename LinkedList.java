import java.util.ArrayList;
import java.util.List;

//doubly linked list.
public class LinkedList {
    //node class with prev and next linking
    class Node{
        int data;
        Node next;
        Node prev;
        Node(int data){
            this.data = data;
        }
    }

    Node head; //head node
    Node tail; //tail node
    int size; //size of linkedlist -> no os nodes
    //constructor
    public LinkedList(){
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    //add new node at tail
    public void addNodeLast(int data){
        Node newNode = new Node(data);
        if(head==null){
            head = newNode;
            tail = newNode;
            head.prev = null;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        tail.next = null;
        this.size++;
    }
    //add new node at head
    public void addNodeFront(int data){
        Node newNode = new Node(data);
        if(head==null){
            head = newNode;
            tail = newNode;
            head.prev = null;
            tail.next = null;
        } else {
            head.prev = newNode;
            newNode.next = head;
            head = newNode;
            head.prev = null;
        }
        this.size++;
    }
    //remove tail node
    public int popNodeLast(){
        if(this.size<1){
            return -1;
        }
        if(this.size==1){
            Node onlyNode = head;
            head = head.prev = head.next = null;
            tail = tail.prev = tail.next = null;
            this.size--;
            return onlyNode.data;
        }
        Node resultNode = tail;
        tail = tail.prev;
        tail.next = null;
        this.size--;
        return resultNode.data;
    }
    //remove head node
    public int popNodeFirst(){
        if(this.size<1){
            return -1;
        }
        if(this.size==1){
            Node onlyNode = head;
            head = head.prev = head.next = null;
            tail = tail.prev = tail.next = null;
            this.size--;
            return onlyNode.data;
        }
        Node resultNode = head;
        head = head.next;
        head.prev = null;
        this.size--;
        return resultNode.data;
    }
    //method to check node with data exists
    public boolean contains(int data){
        if(this.size<1) return false;
        Node iter = head;
        while(iter!=null){
            if(iter.data == data){
                return true;
            }
            iter = iter.next;
        }
        return false;
    }
    //add node at specific index
    public void addNodeAtIndex(int index, int data) throws Exception {
        if(index>this.size) throw new Exception("Invalid index");
        if(index == 0) addNodeFront(data);
        else if(index==this.size) addNodeLast(data);
        else {
            this.size++;
            Node iter = head;
            for (int i = 0; i < index-1; i++) {
                iter = iter.next;
            }
            Node newNode = new Node(data);
            Node afterNode = iter.next;
            iter.next = newNode;
            newNode.prev = iter;
            newNode.next = afterNode;
            if(afterNode!=null) afterNode.prev = newNode;
        }
    }
    //remove node from specific index
    public int popNodeAtIndex(int index) throws Exception {
        if(index>this.size) throw new Exception("Invalid Index");
        if(index==0) popNodeFirst();
        else if (index==this.size-1) popNodeLast();
        else {
            this.size--;
            Node iter = head;
            for(int i=0;i<index;i++){
                iter = iter.next;
            }
            iter.prev.next = iter.next;
            iter.next.prev = iter.prev;
            return iter.data;
        }
        return -1;
    }
    //delete node with data from linkedlist
    public void popNode(int data) throws Exception {
        if(contains(data)){
            Node iter = head;
            int index = 0;
            while(iter!=null){
                if(iter.data == data){
                    break;
                }
                iter = iter.next;
                index++;
            }
            popNodeAtIndex(index);
        }
    }
    //convert linkedlist to list for display
    public List<Integer> toList(){
        List<Integer> result = new ArrayList<>();
        Node iter = head;
        while(iter!=null){
            result.add(iter.data);
            iter = iter.next;
        }
        return result;
    }
    //return linkedlist size
    public int size(){
        return this.size();
    }
}

import java.util.HashMap;

//least recently used cache
public class LRUCache {
    class Node{
        int key;
        int value;
        Node prev;
        Node next;
        Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.next = null;
            this.prev = null;
        }
    }
    Node head;
    Node tail;
    int size;
    HashMap<Integer, Node> map;
    public LRUCache(int size) {
        this.size = size;
        map = new HashMap<>();
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        head.next = tail;
        tail.prev = head;
    }
    //add node at front
    private void addNode(Node newNode){
        Node temp = head.next;
        head.next = newNode;
        newNode.next = temp;
        newNode.prev = head;
        temp.prev = newNode;
    }
    //delete node
    private void deleteNode(Node node){
        Node prevNode = node.prev;
        Node nextNode = node.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }
    //return value of given key
    public int get(int key) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            int value = node.value;
            map.remove(key);
            deleteNode(node);
            addNode(node);
            map.put(key, head.next);
            return value;
        }
        return -1;
    }
    //add key - value, if size exceeds => remove least recently used key - value
    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            map.remove(key);
            deleteNode(node);
        }
        if(map.size() == this.size){
            map.remove(tail.prev.key);
            deleteNode(tail.prev);
        }
        addNode(new Node(key, value));
        map.put(key, head.next);
    }
}
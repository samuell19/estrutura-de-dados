package vetor;

public class VetorLL {

    private Node head;
    private int size;
    private Node tail;

    public class Node{
        Object data;
        Node prev;
        Node next;
        public Node(Object d){
            data = d;
            prev = next = null;
        }
    }

    public VetorLL(){
        head=new Node(null);
        tail=new Node(null);
        head.next=tail;
        tail.prev=head;
        size=0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Node getNode(int r){
        if (r < 0 || r >= size) {
            throw new IndexOutOfBoundsException("Rank inválido");
        }
        Node current = head.next;
        for (int i = 0; i < r; i++) {
            current = current.next;
        }
        return current;
    }

    public Object elementAtRank(int r){
        if (r < 0 || r >= size) {
            throw new IndexOutOfBoundsException("Rank inválido");
        }
        Node node = getNode(r);
        return node.data;
    }

    public Object replaceAtRank(int r, Object o){
        if (r < 0 || r >= size) {
            throw new IndexOutOfBoundsException("Rank inválido");
        }
        Node node = getNode(r);
        Object oldData = node.data;
        node.data= o;
        return oldData;
    }

    public Object insertAtRank(int r, Object o){
        if (r<0 || r>size) {
            throw new IndexOutOfBoundsException("Rank inválido");
        }
        Node newNode = new Node(o);
        Node current= getNode(r);
        Node previous = current.prev;
        newNode.next=current;
        newNode.prev=previous;
        previous.next=newNode;
        current.prev=newNode;
        size++;
        return o;
    }

    public Object removeAtRank(int r){
        if (r < 0 || r >= size) {
            throw new IndexOutOfBoundsException("Rank inválido");
        }
        Node current = getNode(r);
        Node previous = current.prev;
        Node next = current.next;
        previous.next=next;
        next.prev=previous;
        size--;
        return current.data;
    }


}

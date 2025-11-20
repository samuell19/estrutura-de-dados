package squencia;

public class Sequencia {
    private Node head;
    private Node tail;
    private int size;

    public class Node {
        public Object data;
        Node next;
        Node prev;

        Node(Object data) {
            next=prev=null;
            this.data = data;
        }

    }
    public Sequencia(){
            head= new Node(null);
            tail= new Node(null);
            head.next= tail;
            tail.prev= head;
            size=0;
    }

    public int size(){
        return size;
    }
    public boolean isEmpty(){
        return size==0;
    }

    public Object elementAtRank(int r){
        if (r<0 || r>=size){
            throw new IndexOutOfBoundsException("Rank (índice) inválido: " + r);
        }
        Node current=head.next;
        for (int i=0; i<r; i++){
            current=current.next;
        }
        return current.data;
    }

    public Object replaceAtRank(int r, Object o){
        if (r<0 || r>=size){
            throw new IndexOutOfBoundsException("Rank (índice) inválido: " + r);
        }
        Node current=head.next;
        for (int i=0; i<r; i++){
            current=current.next;
        }
        Object oldData=current.data;
        current.data=o;
        return oldData;
    }

    public void insertAtRank(int r, Object o){
        if (r<0 || r>size){
            throw new IndexOutOfBoundsException("Rank (índice) inválido: " + r);
        }
        Node newNode=new Node(o);
        if (r==0){
            newNode.next=head.next;
            newNode.prev=head;
            head.next.prev=newNode;
            head.next=newNode;
        } else if (r==size){
            newNode.prev=tail.prev;
            newNode.next=tail;
            tail.prev.next=newNode;
            tail.prev=newNode;
        } else {
            Node current=head.next;
            for (int i=0; i<r; i++){
                current=current.next;
            }
            newNode.prev=current.prev;
            newNode.next=current;
            current.prev.next=newNode;
            current.prev=newNode;
        }
        size++;
    }

    public Object removeAtRank(int r){
        if (r<0 || r>=size){
            throw new IndexOutOfBoundsException("Rank (índice) inválido: " + r);
        }
        Node current=head.next;
        for (int i=0; i<r; i++){
            current=current.next;
        }
        Object removedData=current.data;
        current.prev.next=current.next;
        current.next.prev=current.prev;
        size--;
        return removedData;
    }

    public Node first(){
        if (isEmpty()){
            return null;
        }
        return head.next;
    }
    public Node last(){
        if (isEmpty()){
            return null;
        }
        return tail.prev;
    }

    public Node before (Node p){
        if (p==head.next){
            return null;
        }
        return p.prev;
    }

    public Node after (Node p){
        if (p==tail.prev){
            return null;
        }
        return p.next;
    }

    public void replaceElements (Node n, Node o){
        if (n==null){
            throw new IllegalArgumentException("Nó inválido");
        }
        n.data=o.data;
    }

    public void swapElements (Node n, Node o){
        if (n== null || o==null){
            throw new IllegalArgumentException("Nó inválido");
        }
        Object temp= n.data;
        n.data=o.data;
        o.data=temp;
    }

    public void insertBefore (Node p, Object o){
        if (p==null){
            throw new IllegalArgumentException("Nó inválido");
        }
        Node novo= new Node (o);
        novo.next=p;
        novo.prev=p.prev;
        p.prev.next=novo; 
        p.prev=novo;
        size++;
    }

    public void insertAfter (Node p, Object o){
        if (p==null){
            throw new IllegalArgumentException("Nó inválido");
        }
        Node novo= new Node(o);
        novo.prev=p;
        novo.next=p.next;
        p.next.prev=novo; 
        p.next=novo;
        size++;
    }
    public void insertFirst (Object o){
        insertAfter(head,o);
    }
    public void insertLast (Object o){
        insertBefore(tail,o); 
    }

    public Node atRank(int r){
        if (r < 0 || r >= size) {
            throw new IndexOutOfBoundsException("rank inválido");
}
        
        Node atual = head.next;
    
        for ( int i=0 ; i<r; i++){
            atual = atual.next;
        }
        return atual;
    }

    public int rankOf(Node p){
        if (p == null) {
            throw new IllegalArgumentException("Nó inválido");
        }
        Node atual = head.next;
        int rank = 0;
        while (atual != tail) {
            if (atual == p) {
                return rank;
            }
            atual = atual.next;
            rank++;
        }
        throw new IllegalArgumentException("Nó não pertence à lista");
    }
    



}

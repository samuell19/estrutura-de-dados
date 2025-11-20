package Tadlista;

public class Lista {

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
    public Lista(){
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
    public Node isFirst(){
        if (isEmpty()){
            return null;
        }
        return head.next;
    }
    public Node isLast(){
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

    //insiro antes do p, preciso me preocupar com a inserção do no novo, e preciso atualizar o ponteiro next do predecessor de p para o novo nó
    public void insertBefore (Node p, Object o){
        if (p==null){
            throw new IllegalArgumentException("Nó inválido");
        }
        Node novo= new Node (o);
        novo.next=p;
        novo.prev=p.prev;
        p.prev.next=novo; // atualiza o next do predecessor
        p.prev=novo;
        size++;
    }

    //insiro depois do p, preciso me preocupar com a inserção do no novo, e preciso atualizar o ponteiro prev do sucessor de p para o novo nó
    public void insertAfter (Node p, Object o){
        if (p==null){
            throw new IllegalArgumentException("Nó inválido");
        }
        Node novo= new Node(o);
        novo.prev=p;
        novo.next=p.next;
        p.next.prev=novo; // atualiza o prev do sucessor
        p.next=novo;
        size++;
    }
    public void insertFirst (Object o){
        insertAfter(head,o); 
    }
    public void insertLast (Object o){
        insertBefore(tail,o); 
    }

    public Object remove(Node p){
        if (p==null || p==head || p==tail){
            throw new IllegalArgumentException("Nó inválido");
        }
        Object removedData= p.data;
        p.prev.next=p.next; //atualizo o next do predecessor para o sucessor
        p.next.prev=p.prev; //atualizo o prev do sucessor para o predecessor
        size--;
        return removedData;
        
    }
    

}

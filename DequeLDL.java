public class DequeLDL {
    class Node{
        int data;
        Node next, prev;

        public Node(int data){
            this.data=data;
            this.next=null;
            this.prev=null;
        }
    }

    Node head;
    Node tail;
    int size;

    public void enqueue(int data){ //O(1)
        Node newNode= new Node(data);
        if (head==null){
            head=tail=newNode;
            }else{
                tail.next=newNode; 
                newNode.prev=tail; 
                tail=newNode; 
            }
        size++;
        }

    public void enqueue_behind(int data){ //O(1)
        Node newNode= new Node(data);
        if (head==null){
            head=tail=newNode;
        }else{
            head.prev=newNode; 
            newNode.next=head; 
            head=newNode;
        }
        size++;
    }

    public Object dequeue(){ //O(1)
        if (head == null) {
            return null;
        }
        if (head == tail) {
            Object data = head.data;
            head = null;
            tail = null;
            size--;
            return data;
        }
        Object data = head.data;
        head = head.next;
        head.prev = null;
        size--;

        return data;
    }
    public Object dequeue_right(){ //O(1)
    if(head == null){
        return null;
    }
    
    if (head == tail) {
        Object data = tail.data;
        head = null;
        tail = null;
        size--;
        return data;
    }
    Object data = tail.data;
    tail = tail.prev; 
    tail.next = null;      
    size--;

    return data;
}

    
        public void display() {  
        Node current = head;  
        if(head == null) {  
            System.out.println("Lista vazia");  
            return;  
        }  
        System.out.println("Nós da fila ");  
        while(current != null) {    
            System.out.print(current.data + " ");  
            current = current.next;  
        }  
        
         }

    public static void main(String[] args) {
        DequeLDL list = new DequeLDL();
          
        

        list.enqueue(9);
        list.enqueue(5);
        list.enqueue_behind(3);
        list.display();
        list.dequeue();
        System.out.println("prev do head: " + list.head.prev);
        System.out.println("next do tail: " + list.tail.next);
        
        
        System.out.println("Tamanho da fila: " + list.size);
        
        list.display();
        list.dequeue_right();
        list.display();
    }
    }



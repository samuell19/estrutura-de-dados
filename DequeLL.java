public class DequeLL {
    class Node{
        int data;
        Node next;

        public Node(int data){
            this.data=data;
            this.next=null;
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
                tail=newNode; 
                
            }
        size++;
        }

    public void enqueue_behind(int data){ //O(1)
        Node newNode= new Node (data);
        if (head==null){
            head=tail=newNode;
            }else{
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
        size--;

        return data;
    }

    public Object dequeue_right(){ //O(n)
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
    
    Object data = tail.data;  
    Node current = head;
    
    while (current.next != tail) {
        current = current.next;
    }
    
    current.next = null;  
    tail = current;
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
        DequeLL list = new DequeLL();
          
        list.display();
        
        list.enqueue(9);
        list.enqueue(5);
        list.enqueue(3);
        list.enqueue(4);
        list.enqueue_behind(8);
        
        list.display();
        
       
        Object removido = list.dequeue();
        System.out.println("Elemento removido da esquerda: " + removido);
        System.out.println("Tamanho da fila: " + list.size);
        System.out.println("a cauda eh:" + list.tail.data);
        list.dequeue_right();
        list.display();
    }

}

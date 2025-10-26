public class Filal {
    class Node{
        int data;
        Node next;

        public Node(int data){
            this.data=data;
            this.next=null;
        }
    }

    public Node head;
    Node tail=null;
    int size;

    public void enqueue(int data){
        Node newNode= new Node(data);
        if (head==null){
            head=tail=newNode;
            tail.next=null;
            }else{
                tail.next=newNode; //o next do antigo tail agora eh o newNode, ou seja vou apontar o next do antigo tail para o newNode
                tail=newNode; //o tail eh o ultimo elemento, por isso ele vira o newNode
                
            }
        size++;
        }

    public void enqueue_behind(int data){
        Node newNode= new Node (data);
        if (head==null){
            head=tail=newNode;
            tail.next=null;
            }else{
                newNode.next=head; //pra adicionar um nó na esquerda, só preciso apontar o novo nó pra head
                head=newNode; //e transformar esse novo nó na head
        }
        size++;
    }

    public Object dequeue(){ 
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

    public Object dequeue_right(){
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
        Filal list = new Filal();
          
        list.display();
        
        list.enqueue(9);
        list.enqueue(5);
        list.enqueue(3);
        list.enqueue(4);
        
        list.display();
        
       
        Object removido = list.dequeue();
        System.out.println("Elemento removido da esquerda: " + removido);
        System.out.println("Tamanho da fila: " + list.size);
        System.out.println("a cauda eh:" + list.tail.data);
        
        list.display();
    }

}

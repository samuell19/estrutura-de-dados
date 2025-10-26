public class Filall {
    class Node{
        int data;
        Node next, prev;

        public Node(int data){
            this.data=data;
            this.next=null;
            this.prev=null;
        }
    }

    public Node head;
    public Node tail;
    int size;

    public void enqueue(int data){
        Node newNode= new Node(data);
        if (head==null){
            head=tail=newNode;
                head.prev=null;
                tail.next=null;
            }else{
                tail.next=newNode; //o next do antigo tail agora eh o newNode, ou seja vou apontar o next do antigo tail para o newNode
                newNode.prev=tail; //o prev do newNode eh o antigo tail, pq o newNode ta sendo adicionado depois do antigo tail
                tail=newNode; //o tail eh o ultimo elemento, por isso ele vira o newNode
                tail.next=null;
            }
        size++;
        }

    public void enqueue_behind(int data){ //o fura fila
        Node newNode= new Node(data);
        if (head==null){
            head=tail=newNode;
            head.prev=null;
            head.next=null;
        }else{
            head.prev=newNode; //o meu head.prev que antes era null, agora vai apontar pro novo nó
            newNode.next=head; //meu novo nó.next vai apontar para a head atual
            head=newNode;//atualizo minha head que vai ser o novo no
            newNode.prev=null;//meu prev da head vai ser null
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
        head.prev = null;
        size--;

        return data;
    }
    public Object dequeue_right(){
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
        Filall list = new Filall();
          
        

        list.enqueue(9);
        list.enqueue(5);
        list.enqueue_behind(3);
        list.display();
        list.dequeue();

        
        
        System.out.println("Tamanho da fila: " + list.size);
        
        list.display();
        list.dequeue_right();
        list.display();
    }
    }



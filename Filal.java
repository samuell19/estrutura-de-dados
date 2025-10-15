public class Filal {
    class Node{
        int data;
        Node next;

        public Node(int data){
            this.data=data;
            this.next=null;
        }
    }

    private Node head;
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
                tail.next=null;
            }
        size++;
        }

    public Object dequeue(){
        if (head == null) {
            return null;
        }
        Node newStart = head.next;
        head.next = newStart.next;
        head = newStart;
        size--;


        return newStart.data;
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
        list.dequeue();
        System.out.println(list.tail.data);
        System.out.println(list.head.data);
        System.out.println(list.head.next.data);
        System.out.println("Tamanho da fila: " + list.size);
        
        list.display();
    }

}

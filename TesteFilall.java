public class TesteFilall {
    public static void main(String[] args) {
        
        Filall fila = new Filall();
        
        
        //teste enqueue
        System.out.println("2. Adicionando elementos (enqueue):");
        fila.enqueue(10);
        fila.enqueue(20);
        fila.enqueue(30);
        fila.display();
        System.out.println("Tamanho: " + fila.size);
        System.out.println("Head: " + fila.head.data + ", Tail: " + fila.tail.data + "\n");
        
       //teste enqueue behind
        System.out.println("3. Adicionando na frente (enqueue_behind):");
        fila.enqueue_behind(5);
        fila.enqueue_behind(1);
        fila.display();
        System.out.println("Tamanho: " + fila.size);
        System.out.println("Head: " + fila.head.data + ", Tail: " + fila.tail.data + "\n");
        
        // teste dequeue
        System.out.println("4. Removendo elementos (dequeue):");
        fila.display();
        Object removido1 = fila.dequeue();
        fila.display();
        System.out.println("Tamanho: " + fila.size);
        System.out.println("Head: " + fila.head.data + ", Tail: " + fila.tail.data + "\n");
        
        Object removido2 = fila.dequeue();
        System.out.println("mais uma remoção");
        fila.display();
        System.out.println("Tamanho: " + fila.size + "\n");
        
    }
}

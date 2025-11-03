public class TesteDequeLL {
    public static void main(String[] args) {

        
        DequeLL fila = new DequeLL();
        
        
        // enqueue normal
        System.out.println("2. Adicionando elementos (enqueue):");
        fila.enqueue(100);
        fila.enqueue(200);
        fila.enqueue(300);
        fila.enqueue(400);
        fila.display();
        System.out.println("Tamanho: " + fila.size);
        System.out.println("Head: " + fila.head.data + ", Tail: " + fila.tail.data + "\n");
        
        // enqueue_behind (fura fila)
        System.out.println("3. Adicionando na frente (enqueue_behind):");
        fila.enqueue_behind(50);
        fila.enqueue_behind(25);
        fila.display();
        System.out.println("Tamanho: " + fila.size);
        System.out.println("Head: " + fila.head.data + ", Tail: " + fila.tail.data + "\n");
        
        // dequeue (remover da esquerda)
        System.out.println("4. Removendo da esquerda (dequeue):");
        Object removidoEsq1 = fila.dequeue();
        System.out.println("fila depois");
        fila.display();
        System.out.println("Tamanho: " + fila.size + "\n");
        
        Object removidoEsq2 = fila.dequeue();
        System.out.println("fila depois");
        fila.display();
        System.out.println("Tamanho: " + fila.size + "\n");
        
        // teste dequeue na direita
        System.out.println("5. Removendo da direita (dequeue_right):");
        Object removidoDir1 = fila.dequeue_right();
        System.out.println("fila depois");
        fila.display();
        System.out.println("Tamanho: " + fila.size + "\n");
        
        Object removidoDir2 = fila.dequeue_right();
        System.out.println("fila depois");
        fila.display();
        System.out.println("Tamanho: " + fila.size + "\n");
        
    }
}


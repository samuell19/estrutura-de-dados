package vetor;

public class TesteVetor {
    public static void main(String[] args) {
        System.out.println("--- Criando Vetor ---");
        Vetor meuVetor = new Vetor(5);

        System.out.println("Está vazio? " + meuVetor.isEmpty());
        System.out.println("Tamanho: " + meuVetor.size());

        System.out.println("\n--- Testando insertAtRank ---");
        meuVetor.insertAtRank(0, "A");
        meuVetor.insertAtRank(1, "B");
        meuVetor.insertAtRank(2, "C");
        System.out.println("Tamanho: " + meuVetor.size());

        System.out.println("\n--- Testando elemAtRank ---");
        System.out.println("Elemento no rank 0: " + meuVetor.elemAtRank(0));
        System.out.println("Elemento no rank 1: " + meuVetor.elemAtRank(1));
        System.out.println("Elemento no rank 2: " + meuVetor.elemAtRank(2));

        System.out.println("\n--- Testando replaceAtRank ---");
        Object antigo = meuVetor.replaceAtRank(1, "X");
        System.out.println("Item substituído: " + antigo);
        System.out.println("Novo item no rank 1: " + meuVetor.elemAtRank(1));

        System.out.println("\n--- Testando insertAtRank (no meio) ---");
        meuVetor.insertAtRank(1, "Y");
        System.out.println("Elemento no rank 0: " + meuVetor.elemAtRank(0));
        System.out.println("Elemento no rank 1: " + meuVetor.elemAtRank(1));
        System.out.println("Elemento no rank 2: " + meuVetor.elemAtRank(2));
        System.out.println("Elemento no rank 3: " + meuVetor.elemAtRank(3)); 
        System.out.println("Tamanho: " + meuVetor.size());

        System.out.println("\n--- Testando removeAtRank ---");
        Object removido = meuVetor.removeAtRank(2);
        System.out.println("Item removido: " + removido);
        System.out.println("Tamanho: " + meuVetor.size());
        System.out.println("Elemento no rank 2: " + meuVetor.elemAtRank(2));
    }
}

import squencia.Sequencia;

public class TesteSequencia {

    public static void main(String[] args) {

        Sequencia seq = new Sequencia();

        System.out.println("===== TESTANDO TAD SEQUÊNCIA =====\n");

        // teste de inserção por rank
        System.out.println("Inserindo com insertAtRank...");
        seq.insertAtRank(0, "A");  
        seq.insertAtRank(1, "C");  
        seq.insertAtRank(1, "B");  
        seq.insertAtRank(3, "D");  

        // Testando elementAtRank
        System.out.println("Elemento no rank 2: " + seq.elementAtRank(2));

        // replaceAtRank
        System.out.println("Substituindo C por X (rank 2)");
        seq.replaceAtRank(2, "X");

        // removeAtRank
        System.out.println("Removendo rank 1 (B)");
        seq.removeAtRank(1);

        // first() e last()
        System.out.println("Primeiro: " + seq.first().data);
        System.out.println("Último: " + seq.last().data);

        // insertBefore / insertAfter
        System.out.println("Inserindo antes e depois de nós...");
        seq.insertBefore(seq.first(), "Z");
        seq.insertAfter(seq.last(), "Y");

        // insertFirst e insertLast
        System.out.println("insertFirst(W) e insertLast(K)");
        seq.insertFirst("W");
        seq.insertLast("K");

        // atRank
        System.out.println("Node no rank 3: " + seq.atRank(3).data);

        // rankOf
        System.out.println("Rank do último (K): " + seq.rankOf(seq.last()));
        
        // swapElements
        System.out.println("Swap do first com o last...");
        seq.swapElements(seq.first(), seq.last());

        // replaceElements
        System.out.println("replaceElements: substituindo first pelo rank 2");
        seq.replaceElements(seq.first(), seq.atRank(2));

        System.out.println("=== FIM DOS TESTES ===");
    }
}

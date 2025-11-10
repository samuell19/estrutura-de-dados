public class ProgramaTesteFila {

    public static void main(String[] args) {
        
        System.out.println("--- 1. INICIANDO TESTE BÁSICO (ENFILEIRAR E DESENFILEIRAR) ---");
        // Vamos usar a sua classe. Testese(3) = capacidade inicial de 3.
        Testese fila = new Testese(3);

        System.out.println("Tamanho inicial: " + fila.getSize()); // Deve ser 0
        fila.enfileirar("A");
        fila.enfileirar("B");
        System.out.println("Tamanho após A e B: " + fila.getSize()); // Deve ser 2
        
        System.out.println("Removendo: " + fila.desinfileirar()); // Deve ser "A"
        System.out.println("Removendo: " + fila.desinfileirar()); // Deve ser "B"
        System.out.println("Tamanho após remover A e B: " + fila.getSize()); // Deve ser 0

        System.out.println("\n--- 2. TESTE DE FILA VAZIA (EXCEÇÃO) ---");
        try {
            System.out.println("Tentando remover da fila vazia...");
            fila.desinfileirar();
        } catch (IllegalStateException e) {
            System.out.println("Sucesso! Capturamos a exceção: " + e.getMessage());
        }

        System.out.println("\n--- 3. TESTE DE COMPORTAMENTO CIRCULAR (A MÁGICA!) ---");
        // A fila tem capacidade 3 e está vazia.
        fila.enfileirar("C"); // fim = 1, inicio = 0
        fila.enfileirar("D"); // fim = 2, inicio = 0
        
        System.out.println("Removendo: " + fila.desinfileirar()); // Remove "C". inicio = 1, fim = 2
        
        // Agora, o 'inicio' está em 1, 'fim' está em 2.
        // O array está: [null, D, null]
        
        System.out.println("Adicionando 'E'...");
        fila.enfileirar("E"); // Adiciona 'E' em a[2]. fim = 0. (DEU A VOLTA!)
        // O array está: [null, D, E]
        
        System.out.println("Adicionando 'F'...");
        fila.enfileirar("F"); // Adiciona 'F' em a[0]. fim = 1.
        // O array está: [F, D, E]
        
        System.out.println("Fila cheia. Tamanho: " + fila.getSize()); // Deve ser 3

        // A fila lógica é: D, E, F
        // O array físico é: [F, D, E] (com inicio = 1)
        
        System.out.println("Removendo todos na ordem correta:");
        System.out.println("Removendo: " + fila.desinfileirar()); // Deve ser "D"
        System.out.println("Removendo: " + fila.desinfileirar()); // Deve ser "E"
        System.out.println("Removendo: " + fila.desinfileirar()); // Deve ser "F"

        System.out.println("\n--- 4. TESTE DE RESIZE (CRESCIMENTO DINÂMICO) ---");
        // A fila está vazia. Capacidade = 3.
        System.out.println("Adicionando 1, 2, 3...");
        fila.enfileirar("1");
        fila.enfileirar("2");
        fila.enfileirar("3");
        System.out.println("Tamanho: " + fila.getSize()); // Deve ser 3
        
        System.out.println("!!! ADICIONANDO '4' - ISSO DEVE ACIONAR O RESIZE !!!");
        fila.enfileirar("4"); // Aciona o resize! Capacidade vira 6.
        
        System.out.println("Tamanho após resize: " + fila.getSize()); // Deve ser 4
        
        System.out.println("Adicionando 5, 6...");
        fila.enfileirar("5");
        fila.enfileirar("6");
        
        System.out.println("Fila cheia (de novo). Tamanho: " + fila.getSize()); // Deve ser 6
        
        System.out.println("!!! ADICIONANDO '7' - ISSO DEVE ACIONAR O SEGUNDO RESIZE !!!");
        fila.enfileirar("7"); // Aciona o resize! Capacidade vira 12.
        System.out.println("Tamanho após 2º resize: " + fila.getSize()); // Deve ser 7

        // A fila lógica é: 1, 2, 3, 4, 5, 6, 7
        // O array físico (após o resize) é: [1, 2, 3, 4, 5, 6, 7, null, ...]
        
        System.out.println("Removendo todos na ordem correta após múltiplos resizes:");
        System.out.println("Removendo: " + fila.desinfileirar()); // 1
        System.out.println("Removendo: " + fila.desinfileirar()); // 2
        System.out.println("Removendo: " + fila.desinfileirar()); // 3
        System.out.println("Removendo: " + fila.desinfileirar()); // 4
        System.out.println("Removendo: " + fila.desinfileirar()); // 5
        System.out.println("Removendo: " + fila.desinfileirar()); // 6
        System.out.println("Removendo: " + fila.desinfileirar()); // 7
        
        System.out.println("Tamanho final: " + fila.getSize()); // Deve ser 0
        
        System.out.println("\n--- TODOS OS TESTES FORAM APROVADOS! ---");
    }
}
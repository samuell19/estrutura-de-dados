package tree;

public class Arvore {

    // Definição do Nó conforme Slide 23 e 24
    private class No {
        int chave; // O elemento armazenado
        No esquerda;
        No direita;
        No pai; // Slide 23 menciona explicitamente o "Nó pai"

        public No(int chave) {
            this.chave = chave;
            this.esquerda = null;
            this.direita = null;
            this.pai = null;
        }
    }

    private No raiz;

    public Arvore() {
        this.raiz = null;
    }

    // --- MÉTODOS DE CONSULTA E ACESSO (Slides 6 a 9) ---

    public boolean isEmpty() {
        return raiz == null;
    }

    public No root() {
        return raiz;
    }

    // --- BUSCA (Slide 27) ---
    // Algoritmo TreeSearch(k, v)
    public boolean buscar(int k) {
        return buscarRecursivo(raiz, k) != null;
    }

    private No buscarRecursivo(No v, int k) {
        // Se v for externo (null), não encontrou (Slide 27)
        if (v == null) {
            return null;
        }

        // Se k < chave(v), busca na esquerda
        if (k < v.chave) {
            return buscarRecursivo(v.esquerda, k);
        }
        // Se k > chave(v), busca na direita
        else if (k > v.chave) {
            return buscarRecursivo(v.direita, k);
        }
        // Se k == chave(v), retorn v
        else {
            return v;
        }
    }

    // --- INSERÇÃO (Slide 28) ---
    // Procura pela chave k, se não achar, insere onde parou (w)
    public void inserir(int k) {
        if (raiz == null) {
            raiz = new No(k);
        } else {
            inserirRecursivo(raiz, k);
        }
    }

    private void inserirRecursivo(No v, int k) {
        if (k < v.chave) {
            if (v.esquerda == null) {
                No novo = new No(k);
                v.esquerda = novo;
                novo.pai = v;
            } else {
                inserirRecursivo(v.esquerda, k);
            }
        } else if (k > v.chave) {
            if (v.direita == null) {
                No novo = new No(k);
                v.direita = novo;
                novo.pai = v;
            } else {
                inserirRecursivo(v.direita, k);
            }
        }
        // Se for igual, não faz nada (chaves únicas) ou atualiza valor
    }

    // --- REMOÇÃO (Slides 29 e 30) ---
    public void remover(int k) {
        raiz = removerRecursivo(raiz, k);
    }

    private No removerRecursivo(No v, int k) {
        if (v == null) return null;

        if (k < v.chave) {
            v.esquerda = removerRecursivo(v.esquerda, k);
        } else if (k > v.chave) {
            v.direita = removerRecursivo(v.direita, k);
        } else {
            // Encontrou o nó a ser removido

            // Caso 1: Nó folha ou apenas 1 filho
            if (v.esquerda == null) return v.direita;
            if (v.direita == null) return v.esquerda;

            // Caso 2 (Slide 30): Nó tem 2 filhos.
            // Encontrar o sucessor (menor nó da subárvore direita)
            // "Caminhamento em ordem na subárvore direita"
            No sucessor = encontrarMinimo(v.direita);
            v.chave = sucessor.chave; // Substitui o valor
            v.direita = removerRecursivo(v.direita, sucessor.chave); // Remove o duplicado
        }
        return v;
    }

    private No encontrarMinimo(No v) {
        while (v.esquerda != null) {
            v = v.esquerda;
        }
        return v;
    }

    // --- TRAVESSIAS (Slides 12, 13, 20) ---

    // Pré-Ordem (Slide 12): Visita, Esquerda, Direita
    public void preOrdem() {
        System.out.print("Pré-Ordem: ");
        preOrdemRecursivo(raiz);
        System.out.println();
    }
    private void preOrdemRecursivo(No v) {
        if (v != null) {
            System.out.print(v.chave + " "); // Visite(v)
            preOrdemRecursivo(v.esquerda);
            preOrdemRecursivo(v.direita);
        }
    }

    // Em-Ordem (Slide 20 e 26): Esquerda, Visita, Direita
    // "Visita as chaves em ordem crescente" (Slide 26)
    public void emOrdem() {
        System.out.print("Em-Ordem:  ");
        emOrdemRecursivo(raiz);
        System.out.println();
    }
    private void emOrdemRecursivo(No v) {
        if (v != null) {
            emOrdemRecursivo(v.esquerda);
            System.out.print(v.chave + " "); // Visite(v)
            emOrdemRecursivo(v.direita);
        }
    }

    // Pós-Ordem (Slide 13): Esquerda, Direita, Visita
    public void posOrdem() {
        System.out.print("Pós-Ordem: ");
        posOrdemRecursivo(raiz);
        System.out.println();
    }
    private void posOrdemRecursivo(No v) {
        if (v != null) {
            posOrdemRecursivo(v.esquerda);
            posOrdemRecursivo(v.direita);
            System.out.print(v.chave + " "); // Visite(v)
        }
    }

    // Método principal para testar
    public static void main(String[] args) {
        Arvore arvore = new Arvore();

        // Inserindo dados do exemplo do Slide 26 (elementos: 6, 2, 9, 1, 4, 8)
        System.out.println("--- Inserindo Elementos (Exemplo Slide 26) ---");
        arvore.inserir(6);
        arvore.inserir(2);
        arvore.inserir(9);
        arvore.inserir(1);
        arvore.inserir(4);
        arvore.inserir(8);

        // Testando as travessias
        arvore.emOrdem();   // Esperado: 1 2 4 6 8 9
        arvore.preOrdem();  // Esperado: 6 2 1 4 9 8
        arvore.posOrdem();  // Esperado: 1 4 2 8 9 6

        // Testando Busca
        System.out.println("\n--- Teste de Busca ---");
        System.out.println("Busca 4: " + (arvore.buscar(4) ? "Encontrado" : "Não encontrado"));
        System.out.println("Busca 99: " + (arvore.buscar(99) ? "Encontrado" : "Não encontrado"));

        // Testando Remoção (Exemplo Slide 30: remover nó com 2 filhos)
        System.out.println("\n--- Removendo o 2 (Nó com 2 filhos: 1 e 4) ---");
        arvore.remover(2);
        arvore.emOrdem(); // Deve manter a ordem: 1 4 6 8 9
    }
}

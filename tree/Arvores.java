package tree;

public class Arvores {

    private class No {
        int chave;
        int altura;
        No esquerda;
        No direita;
        No pai;

        public No(int chave) {
            this.chave = chave;
            this.altura = 1;
            this.esquerda = null;
            this.direita = null;
            this.pai = null;
        }
    }

    private No raiz;

    public Arvores() {
        this.raiz = null;
    }

    public boolean isEmpty() {
        return raiz == null;
    }

    public No root() {
        return raiz;
    }

    public boolean buscar(int k) {
        return buscarRecursivo(raiz, k) != null;
    }

    private No buscarRecursivo(No v, int k) {
        if (v == null) {
            return null;
        }

        if (k < v.chave) {
            return buscarRecursivo(v.esquerda, k);
        } else if (k > v.chave) {
            return buscarRecursivo(v.direita, k);
        } else {
            return v;
        }
    }

    // =========================
    // MÉTODOS AUXILIARES AVL
    // =========================

    private int altura(No v) {
        if (v == null) {
            return 0;
        }
        return v.altura;
    }

    private void atualizarAltura(No v) {
        if (v != null) {
            v.altura = 1 + Math.max(altura(v.esquerda), altura(v.direita));
        }
    }

    // FB(v) = altura(esquerda) - altura(direita)
    private int fatorBalanceamento(No v) {
        if (v == null) {
            return 0;
        }
        return altura(v.esquerda) - altura(v.direita);
    }

    // =========================
    // ROTAÇÕES
    // =========================

    // Rotação Simples à Direita (RSD)
    private No rotacaoDireita(No y) {
        No x = y.esquerda;
        No T2 = x.direita;

        x.direita = y;
        y.esquerda = T2;

        if (T2 != null) {
            T2.pai = y;
        }

        x.pai = y.pai;
        y.pai = x;

        atualizarAltura(y);
        atualizarAltura(x);

        return x;
    }

    // Rotação Simples à Esquerda (RSE)
    private No rotacaoEsquerda(No x) {
        No y = x.direita;
        No T2 = y.esquerda;

        y.esquerda = x;
        x.direita = T2;

        if (T2 != null) {
            T2.pai = x;
        }

        y.pai = x.pai;
        x.pai = y;

        atualizarAltura(x);
        atualizarAltura(y);

        return y;
    }

    // =========================
    // INSERÇÃO AVL
    // =========================

    public void inserir(int k) {
        raiz = inserirRecursivo(raiz, k);
        if (raiz != null) {
            raiz.pai = null;
        }
    }

    private No inserirRecursivo(No v, int k) {
        // Inserção igual ABB
        if (v == null) {
            return new No(k);
        }

        if (k < v.chave) {
            No novoEsquerda = inserirRecursivo(v.esquerda, k);
            v.esquerda = novoEsquerda;
            if (novoEsquerda != null) {
                novoEsquerda.pai = v;
            }
        } else if (k > v.chave) {
            No novoDireita = inserirRecursivo(v.direita, k);
            v.direita = novoDireita;
            if (novoDireita != null) {
                novoDireita.pai = v;
            }
        } else {
            // chave duplicada: não insere
            return v;
        }

        // Atualiza altura
        atualizarAltura(v);

        // Calcula FB
        int fb = fatorBalanceamento(v);

        // Caso 1: Esquerda-Esquerda (LL) -> RSD
        if (fb > 1 && k < v.esquerda.chave) {
            return rotacaoDireita(v);
        }

        // Caso 2: Direita-Direita (RR) -> RSE
        if (fb < -1 && k > v.direita.chave) {
            return rotacaoEsquerda(v);
        }

        // Caso 3: Esquerda-Direita (LR) -> rotação esquerda no filho + direita no nó
        if (fb > 1 && k > v.esquerda.chave) {
            v.esquerda = rotacaoEsquerda(v.esquerda);
            if (v.esquerda != null) {
                v.esquerda.pai = v;
            }
            return rotacaoDireita(v);
        }

        // Caso 4: Direita-Esquerda (RL) -> rotação direita no filho + esquerda no nó
        if (fb < -1 && k < v.direita.chave) {
            v.direita = rotacaoDireita(v.direita);
            if (v.direita != null) {
                v.direita.pai = v;
            }
            return rotacaoEsquerda(v);
        }

        return v;
    }

    // =========================
    // REMOÇÃO AVL
    // =========================

    public void remover(int k) {
        raiz = removerRecursivo(raiz, k);
        if (raiz != null) {
            raiz.pai = null;
        }
    }

    private No removerRecursivo(No v, int k) {
        if (v == null) {
            return null;
        }

        // Remoção igual ABB
        if (k < v.chave) {
            v.esquerda = removerRecursivo(v.esquerda, k);
            if (v.esquerda != null) {
                v.esquerda.pai = v;
            }
        } else if (k > v.chave) {
            v.direita = removerRecursivo(v.direita, k);
            if (v.direita != null) {
                v.direita.pai = v;
            }
        } else {
            // nó com 0 ou 1 filho
            if (v.esquerda == null || v.direita == null) {
                No temp;

                if (v.esquerda != null) {
                    temp = v.esquerda;
                } else {
                    temp = v.direita;
                }

                if (temp == null) {
                    // sem filhos
                    v = null;
                } else {
                    // um filho
                    temp.pai = v.pai;
                    v = temp;
                }
            } else {
                // nó com 2 filhos: usa sucessor
                No sucessor = encontrarMinimo(v.direita);
                v.chave = sucessor.chave;
                v.direita = removerRecursivo(v.direita, sucessor.chave);
                if (v.direita != null) {
                    v.direita.pai = v;
                }
            }
        }

        // se a árvore ficou vazia
        if (v == null) {
            return null;
        }

        // Atualiza altura
        atualizarAltura(v);

        // Calcula FB
        int fb = fatorBalanceamento(v);

        // Caso 1: Esquerda-Esquerda (LL)
        if (fb > 1 && fatorBalanceamento(v.esquerda) >= 0) {
            return rotacaoDireita(v);
        }

        // Caso 2: Esquerda-Direita (LR)
        if (fb > 1 && fatorBalanceamento(v.esquerda) < 0) {
            v.esquerda = rotacaoEsquerda(v.esquerda);
            if (v.esquerda != null) {
                v.esquerda.pai = v;
            }
            return rotacaoDireita(v);
        }

        // Caso 3: Direita-Direita (RR)
        if (fb < -1 && fatorBalanceamento(v.direita) <= 0) {
            return rotacaoEsquerda(v);
        }

        // Caso 4: Direita-Esquerda (RL)
        if (fb < -1 && fatorBalanceamento(v.direita) > 0) {
            v.direita = rotacaoDireita(v.direita);
            if (v.direita != null) {
                v.direita.pai = v;
            }
            return rotacaoEsquerda(v);
        }

        return v;
    }

    private No encontrarMinimo(No v) {
        while (v.esquerda != null) {
            v = v.esquerda;
        }
        return v;
    }

    // =========================
    // MOSTRAR ÁRVORE
    // =========================

    public void mostrar() {
        if (raiz == null) {
            System.out.println("Árvore vazia.");
        } else {
            mostrarRecursivo(raiz, 0);
        }
    }

    // print lateral com chave[FB]
    private void mostrarRecursivo(No v, int nivel) {
        if (v != null) {
            mostrarRecursivo(v.direita, nivel + 1);

            for (int i = 0; i < nivel; i++) {
                System.out.print("    ");
            }

            System.out.println(v.chave + "[" + fatorBalanceamento(v) + "]");

            mostrarRecursivo(v.esquerda, nivel + 1);
        }
    }

    // =========================
    // PERCURSOS
    // =========================

    public void emOrdem() {
        System.out.print("Em-Ordem: ");
        emOrdemRecursivo(raiz);
        System.out.println();
    }

    private void emOrdemRecursivo(No v) {
        if (v != null) {
            emOrdemRecursivo(v.esquerda);
            System.out.print(v.chave + " ");
            emOrdemRecursivo(v.direita);
        }
    }

    // =========================
    // MAIN
    // =========================

    public static void main(String[] args) {
        Arvores arvore = new Arvores();

        System.out.println("--- Inserções AVL ---");
        arvore.inserir(10);
        arvore.inserir(5);
        arvore.inserir(15);
        arvore.inserir(2);
        arvore.inserir(8);
        arvore.inserir(22);

        arvore.mostrar();
        arvore.emOrdem();

        System.out.println("\n--- Inserindo 25 ---");
        arvore.inserir(25);
        arvore.mostrar();
        arvore.emOrdem();

        System.out.println("\n--- Inserindo 30 ---");
        arvore.inserir(30);
        arvore.mostrar();
        arvore.emOrdem();

        System.out.println("\n--- Removendo 5 ---");
        arvore.remover(5);
        arvore.mostrar();
        arvore.emOrdem();

        System.out.println("\n--- Removendo 10 ---");
        arvore.remover(10);
        arvore.mostrar();
        arvore.emOrdem();
    }
}
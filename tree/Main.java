package tree;
import java.util.Scanner;

class ABP {

    protected class No {
        int chave;
        int fb;
        No esquerda;
        No direita;
        No pai;

        public No(int chave) {
            this.chave = chave;
            this.fb = 0;
        }
    }

    protected No raiz;

    protected No criarNo(int chave) {
        return new No(chave);
    }

    public boolean buscar(int chave) {
        return buscarNo(raiz, chave) != null;
    }

    protected No buscarNo(No atual, int chave) {
        while (atual != null) {
            if (chave < atual.chave) {
                atual = atual.esquerda;
            } else if (chave > atual.chave) {
                atual = atual.direita;
            } else {
                return atual;
            }
        }
        return null;
    }

    protected No minimo(No no) {
        while (no != null && no.esquerda != null) {
            no = no.esquerda;
        }
        return no;
    }

    protected void ligarFilho(No pai, No filho, boolean esquerda) {
        if (esquerda) {
            pai.esquerda = filho;
        } else {
            pai.direita = filho;
        }

        if (filho != null) {
            filho.pai = pai;
        }
    }

    public void inserir(int chave) {
        if (raiz == null) {
            raiz = criarNo(chave);
            return;
        }

        No atual = raiz;
        No pai = null;

        while (atual != null) {
            pai = atual;

            if (chave < atual.chave) {
                atual = atual.esquerda;
            } else if (chave > atual.chave) {
                atual = atual.direita;
            } else {
                return;
            }
        }

        No novo = criarNo(chave);

        if (chave < pai.chave) {
            ligarFilho(pai, novo, true);
        } else {
            ligarFilho(pai, novo, false);
        }
    }

    public void remover(int chave) {
        No alvo = buscarNo(raiz, chave);
        if (alvo == null) {
            return;
        }

        removerNoABP(alvo);

        if (raiz != null) {
            raiz.pai = null;
        }
    }

    protected void removerNoABP(No alvo) {
        if (alvo.esquerda != null && alvo.direita != null) {
            No sucessor = minimo(alvo.direita);
            alvo.chave = sucessor.chave;
            alvo = sucessor;
        }

        No filho = (alvo.esquerda != null) ? alvo.esquerda : alvo.direita;
        No pai = alvo.pai;

        if (pai == null) {
            raiz = filho;
            if (raiz != null) {
                raiz.pai = null;
            }
        } else if (alvo == pai.esquerda) {
            ligarFilho(pai, filho, true);
        } else {
            ligarFilho(pai, filho, false);
        }
    }

    public void mostrar() {
    if (raiz == null) {
        System.out.println("Arvore vazia.");
        return;
    }

    int altura = alturaExibicao(raiz);
    int largura = (int) Math.pow(2, altura + 1) * 4;
    int linhas = altura * 2;

    String[][] matriz = new String[linhas][largura];

    for (int i = 0; i < linhas; i++) {
        for (int j = 0; j < largura; j++) {
            matriz[i][j] = " ";
        }
    }

    preencherMatriz(raiz, 0, 0, largura - 1, matriz);

    System.out.println();
    for (int i = 0; i < linhas; i++) {
        StringBuilder sb = new StringBuilder();
        boolean vazia = true;

        for (int j = 0; j < largura; j++) {
            sb.append(matriz[i][j]);
            if (!matriz[i][j].equals(" ")) {
                vazia = false;
            }
        }

        if (!vazia) {
            System.out.println(sb.toString());
        }
    }
    System.out.println();
}

private int alturaExibicao(No no) {
    if (no == null) {
        return 0;
    }
    return 1 + Math.max(alturaExibicao(no.esquerda), alturaExibicao(no.direita));
}

private void preencherMatriz(No no, int linha, int esq, int dir, String[][] matriz) {
    if (no == null || linha >= matriz.length) {
        return;
    }

    int meio = (esq + dir) / 2;
    String texto = no.chave + "[" + no.fb + "]";
    int inicio = meio - (texto.length() / 2);

    for (int i = 0; i < texto.length(); i++) {
        int pos = inicio + i;
        if (pos >= 0 && pos < matriz[0].length) {
            matriz[linha][pos] = String.valueOf(texto.charAt(i));
        }
    }

    preencherMatriz(no.esquerda, linha + 2, esq, meio - 1, matriz);
    preencherMatriz(no.direita, linha + 2, meio + 1, dir, matriz);
}
}

class AVL extends ABP {

    @Override
    public void inserir(int chave) {
        if (raiz == null) {
            raiz = criarNo(chave);
            return;
        }

        No inserido = inserirAVL(chave);

        if (inserido != null) {
            atualizarInsercao(inserido);
        }

        if (raiz != null) {
            raiz.pai = null;
        }
    }

    private No inserirAVL(int chave) {
        No atual = raiz;
        No pai = null;

        while (atual != null) {
            pai = atual;

            if (chave < atual.chave) {
                atual = atual.esquerda;
            } else if (chave > atual.chave) {
                atual = atual.direita;
            } else {
                return null;
            }
        }

        No novo = criarNo(chave);

        if (chave < pai.chave) {
            ligarFilho(pai, novo, true);
        } else {
            ligarFilho(pai, novo, false);
        }

        return novo;
    }

    private void atualizarInsercao(No no) {
        No atual = no;
        No pai = atual.pai;

        while (pai != null) {
            if (atual == pai.esquerda) {
                pai.fb++;
            } else {
                pai.fb--;
            }

            if (pai.fb == 0) {
                return;
            }

            if (pai.fb == 2 || pai.fb == -2) {
                rebalancearInsercao(pai);
                return;
            }

            atual = pai;
            pai = pai.pai;
        }
    }

    @Override
    public void remover(int chave) {
        No alvo = buscarNo(raiz, chave);
        if (alvo == null) {
            return;
        }

        removerAVL(alvo);

        if (raiz != null) {
            raiz.pai = null;
        }
    }

    private void removerAVL(No alvo) {
        No removido = alvo;

        if (alvo.esquerda != null && alvo.direita != null) {
            No sucessor = minimo(alvo.direita);
            alvo.chave = sucessor.chave;
            removido = sucessor;
        }

        No filho = (removido.esquerda != null) ? removido.esquerda : removido.direita;
        No pai = removido.pai;
        boolean veioDaEsquerda = false;

        if (pai != null) {
            veioDaEsquerda = (removido == pai.esquerda);
        }

        if (pai == null) {
            raiz = filho;
            if (raiz != null) {
                raiz.pai = null;
            }
        } else if (veioDaEsquerda) {
            ligarFilho(pai, filho, true);
        } else {
            ligarFilho(pai, filho, false);
        }

        atualizarRemocao(pai, veioDaEsquerda);
    }

    private void atualizarRemocao(No no, boolean veioDaEsquerda) {
        No atual = no;
        boolean ladoEsquerdo = veioDaEsquerda;

        while (atual != null) {
            if (ladoEsquerdo) {
                atual.fb--;
            } else {
                atual.fb++;
            }

            if (atual.fb == 1 || atual.fb == -1) {
                return;
            }

            if (atual.fb == 2 || atual.fb == -2) {
                No paiAnterior = atual.pai;
                boolean atualEraEsquerda = false;

                if (paiAnterior != null) {
                    atualEraEsquerda = (atual == paiAnterior.esquerda);
                }

                No novaRaiz = rebalancearRemocao(atual);

                if (novaRaiz.fb != 0) {
                    return;
                }

                atual = paiAnterior;
                ladoEsquerdo = atualEraEsquerda;
            } else {
                No filho = atual;
                atual = atual.pai;

                if (atual != null) {
                    ladoEsquerdo = (filho == atual.esquerda);
                }
            }
        }
    }

    private void rebalancearInsercao(No no) {
        if (no.fb == 2) {
            if (no.esquerda.fb >= 0) {
                rotacaoDireita(no);
            } else {
                rotacaoEsquerda(no.esquerda);
                rotacaoDireita(no);
            }
        } else {
            if (no.direita.fb <= 0) {
                rotacaoEsquerda(no);
            } else {
                rotacaoDireita(no.direita);
                rotacaoEsquerda(no);
            }
        }
    }

    private No rebalancearRemocao(No no) {
        if (no.fb == 2) {
            if (no.esquerda.fb >= 0) {
                return rotacaoDireita(no);
            } else {
                rotacaoEsquerda(no.esquerda);
                return rotacaoDireita(no);
            }
        } else {
            if (no.direita.fb <= 0) {
                return rotacaoEsquerda(no);
            } else {
                rotacaoDireita(no.direita);
                return rotacaoEsquerda(no);
            }
        }
    }
    private No rotacaoEsquerda(No a) {
        No b = a.direita;
        No t2 = b.esquerda;
        No pai = a.pai;

        b.esquerda = a;
        a.direita = t2;

        if (t2 != null) {
            t2.pai = a;
        }

        a.pai = b;
        b.pai = pai;

        if (pai == null) {
            raiz = b;
        } else if (pai.esquerda == a) {
            pai.esquerda = b;
        } else {
            pai.direita = b;
        }

        int fbA = a.fb;
        int fbB = b.fb;

        int fbBNovo = fbB + 1 - Math.min(fbA, 0);
        int fbANovo = fbA + 1 + Math.max(fbBNovo, 0);

        b.fb = fbBNovo;
        a.fb = fbANovo;

        return b;
    }
    private No rotacaoDireita(No a) {
        No b = a.esquerda;
        No t2 = b.direita;
        No pai = a.pai;

        b.direita = a;
        a.esquerda = t2;

        if (t2 != null) {
            t2.pai = a;
        }

        a.pai = b;
        b.pai = pai;

        if (pai == null) {
            raiz = b;
        } else if (pai.esquerda == a) {
            pai.esquerda = b;
        } else {
            pai.direita = b;
        }

        int fbA = a.fb;
        int fbB = b.fb;

        int fbBNovo = fbB - 1 - Math.max(fbA, 0);
        int fbANovo = fbA - 1 + Math.min(fbBNovo, 0);

        b.fb = fbBNovo;
        a.fb = fbANovo;

        return b;
    }
}

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AVL arvore = new AVL();
        int opcao;
        int valor;

        do {
            System.out.println("\n====================");
            System.out.println("     ARVORE AVL");
            System.out.println("====================");
            System.out.println("1 - Inserir");
            System.out.println("2 - Remover");
            System.out.println("3 - Buscar");
            System.out.println("4 - Mostrar");
            System.out.println("0 - Sair");
            System.out.print("Opcao: ");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Valor: ");
                    valor = sc.nextInt();
                    arvore.inserir(valor);
                    System.out.println("Inserido.");
                    break;

                case 2:
                    System.out.print("Valor: ");
                    valor = sc.nextInt();
                    arvore.remover(valor);
                    System.out.println("Removido.");
                    break;

                case 3:
                    System.out.print("Valor: ");
                    valor = sc.nextInt();
                    if (arvore.buscar(valor)) {
                        System.out.println("Encontrado.");
                    } else {
                        System.out.println("Nao encontrado.");
                    }
                    break;

                case 4:
                    arvore.mostrar();
                    break;

                case 0:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opcao invalida.");
            }

        } while (opcao != 0);

        sc.close();
    }
}
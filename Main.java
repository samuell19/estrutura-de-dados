import java.util.Scanner;

class ArvoreRubroNegra {

    private static final boolean RUBRO = true;
    private static final boolean NEGRO = false;

    private class No {
        int chave;
        boolean cor;
        No esquerda;
        No direita;
        No pai;

        No(int chave, boolean cor) {
            this.chave = chave;
            this.cor = cor;
        }
    }

    private final No NIL;
    private No raiz;

    public ArvoreRubroNegra() {
        NIL = new No(0, NEGRO);
        NIL.esquerda = NIL;
        NIL.direita = NIL;
        NIL.pai = NIL;
        raiz = NIL;
    }

    public boolean buscar(int chave) {
        return buscarNo(chave) != NIL;
    }

    private No buscarNo(int chave) {
        No atual = raiz;

        while (atual != NIL) {
            if (chave < atual.chave) {
                atual = atual.esquerda;
            } else if (chave > atual.chave) {
                atual = atual.direita;
            } else {
                return atual;
            }
        }

        return NIL;
    }

    public void inserir(int chave) {
        if (buscar(chave)) {
            return;
        }

        No novo = new No(chave, RUBRO);
        novo.esquerda = NIL;
        novo.direita = NIL;

        No pai = NIL;
        No atual = raiz;

        while (atual != NIL) {
            pai = atual;

            if (novo.chave < atual.chave) {
                atual = atual.esquerda;
            } else {
                atual = atual.direita;
            }
        }

        novo.pai = pai;

        if (pai == NIL) {
            raiz = novo;
        } else if (novo.chave < pai.chave) {
            pai.esquerda = novo;
        } else {
            pai.direita = novo;
        }

        corrigirInsercao(novo);
    }

    private void corrigirInsercao(No no) {
        while (no.pai.cor == RUBRO) {
            if (no.pai == no.pai.pai.esquerda) {
                No tio = no.pai.pai.direita;

                if (tio.cor == RUBRO) {
                    no.pai.cor = NEGRO;
                    tio.cor = NEGRO;
                    no.pai.pai.cor = RUBRO;
                    no = no.pai.pai;
                } else {
                    if (no == no.pai.direita) {
                        no = no.pai;
                        rotacaoEsquerda(no);
                    }

                    no.pai.cor = NEGRO;
                    no.pai.pai.cor = RUBRO;
                    rotacaoDireita(no.pai.pai);
                }
            } else {
                No tio = no.pai.pai.esquerda;

                if (tio.cor == RUBRO) {
                    no.pai.cor = NEGRO;
                    tio.cor = NEGRO;
                    no.pai.pai.cor = RUBRO;
                    no = no.pai.pai;
                } else {
                    if (no == no.pai.esquerda) {
                        no = no.pai;
                        rotacaoDireita(no);
                    }

                    no.pai.cor = NEGRO;
                    no.pai.pai.cor = RUBRO;
                    rotacaoEsquerda(no.pai.pai);
                }
            }
        }

        raiz.cor = NEGRO;
    }

    public void remover(int chave) {
        No z = buscarNo(chave);

        if (z == NIL) {
            return;
        }

        No y = z;
        No x;
        boolean corOriginalY = y.cor;

        if (z.esquerda == NIL) {
            x = z.direita;
            transplantar(z, z.direita);
        } else if (z.direita == NIL) {
            x = z.esquerda;
            transplantar(z, z.esquerda);
        } else {
            y = minimo(z.direita);
            corOriginalY = y.cor;
            x = y.direita;

            if (y.pai == z) {
                x.pai = y;
            } else {
                transplantar(y, y.direita);
                y.direita = z.direita;
                y.direita.pai = y;
            }

            transplantar(z, y);
            y.esquerda = z.esquerda;
            y.esquerda.pai = y;
            y.cor = z.cor;
        }

        if (corOriginalY == NEGRO) {
            corrigirRemocao(x);
        }
    }

    private void corrigirRemocao(No x) {
        while (x != raiz && x.cor == NEGRO) {
            if (x == x.pai.esquerda) {
                No irmao = x.pai.direita;

                if (irmao.cor == RUBRO) {
                    irmao.cor = NEGRO;
                    x.pai.cor = RUBRO;
                    rotacaoEsquerda(x.pai);
                    irmao = x.pai.direita;
                }

                if (irmao.esquerda.cor == NEGRO && irmao.direita.cor == NEGRO) {
                    irmao.cor = RUBRO;
                    x = x.pai;
                } else {
                    if (irmao.direita.cor == NEGRO) {
                        irmao.esquerda.cor = NEGRO;
                        irmao.cor = RUBRO;
                        rotacaoDireita(irmao);
                        irmao = x.pai.direita;
                    }

                    irmao.cor = x.pai.cor;
                    x.pai.cor = NEGRO;
                    irmao.direita.cor = NEGRO;
                    rotacaoEsquerda(x.pai);
                    x = raiz;
                }
            } else {
                No irmao = x.pai.esquerda;

                if (irmao.cor == RUBRO) {
                    irmao.cor = NEGRO;
                    x.pai.cor = RUBRO;
                    rotacaoDireita(x.pai);
                    irmao = x.pai.esquerda;
                }

                if (irmao.direita.cor == NEGRO && irmao.esquerda.cor == NEGRO) {
                    irmao.cor = RUBRO;
                    x = x.pai;
                } else {
                    if (irmao.esquerda.cor == NEGRO) {
                        irmao.direita.cor = NEGRO;
                        irmao.cor = RUBRO;
                        rotacaoEsquerda(irmao);
                        irmao = x.pai.esquerda;
                    }

                    irmao.cor = x.pai.cor;
                    x.pai.cor = NEGRO;
                    irmao.esquerda.cor = NEGRO;
                    rotacaoDireita(x.pai);
                    x = raiz;
                }
            }
        }

        x.cor = NEGRO;
    }

    private void transplantar(No antigo, No novo) {
        if (antigo.pai == NIL) {
            raiz = novo;
        } else if (antigo == antigo.pai.esquerda) {
            antigo.pai.esquerda = novo;
        } else {
            antigo.pai.direita = novo;
        }

        novo.pai = antigo.pai;
    }

    private No minimo(No no) {
        while (no.esquerda != NIL) {
            no = no.esquerda;
        }

        return no;
    }

    private void rotacaoEsquerda(No x) {
        No y = x.direita;
        x.direita = y.esquerda;

        if (y.esquerda != NIL) {
            y.esquerda.pai = x;
        }

        y.pai = x.pai;

        if (x.pai == NIL) {
            raiz = y;
        } else if (x == x.pai.esquerda) {
            x.pai.esquerda = y;
        } else {
            x.pai.direita = y;
        }

        y.esquerda = x;
        x.pai = y;
    }

    private void rotacaoDireita(No y) {
        No x = y.esquerda;
        y.esquerda = x.direita;

        if (x.direita != NIL) {
            x.direita.pai = y;
        }

        x.pai = y.pai;

        if (y.pai == NIL) {
            raiz = x;
        } else if (y == y.pai.direita) {
            y.pai.direita = x;
        } else {
            y.pai.esquerda = x;
        }

        x.direita = y;
        y.pai = x;
    }

    public void mostrar() {
        if (raiz == NIL) {
            System.out.println("Arvore vazia.");
            return;
        }

        int altura = altura(raiz);
        int largura = (int) Math.pow(2, altura + 1) * 5;
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
            StringBuilder linha = new StringBuilder();
            boolean vazia = true;

            for (int j = 0; j < largura; j++) {
                linha.append(matriz[i][j]);

                if (!matriz[i][j].equals(" ")) {
                    vazia = false;
                }
            }

            if (!vazia) {
                System.out.println(linha.toString());
            }
        }
        System.out.println();
    }

    private void preencherMatriz(No no, int linha, int inicio, int fim, String[][] matriz) {
        if (no == NIL || linha >= matriz.length) {
            return;
        }

        int meio = (inicio + fim) / 2;
        String texto = formatarNo(no);
        int posicaoInicial = meio - (texto.length() / 2);

        for (int i = 0; i < texto.length(); i++) {
            int pos = posicaoInicial + i;

            if (pos >= 0 && pos < matriz[0].length) {
                matriz[linha][pos] = String.valueOf(texto.charAt(i));
            }
        }

        preencherMatriz(no.esquerda, linha + 2, inicio, meio - 1, matriz);
        preencherMatriz(no.direita, linha + 2, meio + 1, fim, matriz);
    }

    private String formatarNo(No no) {
        if (no.cor == RUBRO) {
            return no.chave + "[R]";
        }

        return no.chave + "[N]";
    }

    private int altura(No no) {
        if (no == NIL) {
            return 0;
        }

        return 1 + Math.max(altura(no.esquerda), altura(no.direita));
    }
}

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArvoreRubroNegra arvore = new ArvoreRubroNegra();

        int opcao;

        do {
            System.out.println("\n==============================");
            System.out.println("      ARVORE RUBRO-NEGRA");
            System.out.println("==============================");
            System.out.println("1 - Inserir");
            System.out.println("2 - Remover");
            System.out.println("3 - Buscar");
            System.out.println("4 - Mostrar arvore");
            System.out.println("0 - Sair");
            System.out.print("Opcao: ");

            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o valor para inserir: ");
                    int inserir = scanner.nextInt();
                    arvore.inserir(inserir);
                    System.out.println("Valor inserido.");
                    break;

                case 2:
                    System.out.print("Digite o valor para remover: ");
                    int remover = scanner.nextInt();
                    arvore.remover(remover);
                    System.out.println("Remocao concluida.");
                    break;

                case 3:
                    System.out.print("Digite o valor para buscar: ");
                    int buscar = scanner.nextInt();

                    if (arvore.buscar(buscar)) {
                        System.out.println("Valor encontrado.");
                    } else {
                        System.out.println("Valor nao encontrado.");
                    }
                    break;

                case 4:
                    arvore.mostrar();
                    break;

                case 0:
                    System.out.println("Encerrando...");
                    break;

                default:
                    System.out.println("Opcao invalida.");
            }

        } while (opcao != 0);

        scanner.close();
    }
}
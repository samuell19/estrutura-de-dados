public class Testese {
    private int size;
    private Object [] a;
    private int inicio;
    private int fim;
    private int capacidade;

    public Testese(int capacidade){
        this.size=0;
        this.a= new Object[capacidade];
        this.inicio = 0;
        this.capacidade = capacidade;
        this.fim = 0;
    }

    public int getSize(){
        return size;
    }

    public void enfileirar(Object obj) {
        if (size == capacidade) {
            int novaCapacidade = capacidade * 2; //dobro a capacidade
            Object[] b = new Object[novaCapacidade]; //crio um array novo com o dobro da capacidade
            //copio os elementos do array antigo para o novo
            for (int f = 0; f < size; f++) {
                b[f] = a[(inicio + f) % capacidade];
            }
            a = b;
            inicio = 0;
            fim = size;
            capacidade = novaCapacidade;
        }
        a[fim] = obj; //adiciono o objeto na posição do fim
        fim = (fim + 1) % capacidade;//formula magica de coleguinha
        size++;//aumento o tamanhop
    }

    public Object desinfileirar(){
        if (size==0){
            throw new IllegalStateException("fila vazia");
        }
        Object removido= a[inicio]; //guardo o valor que vou remover
        a[inicio]=null; //apago ele
        inicio = (inicio + 1) % capacidade; //formula magica de coleguinha
        size --; //reduzo o tamanho
        return removido;
    }
    


 }



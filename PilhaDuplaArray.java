public class PilhaDuplaArray {

    private int capacidade;
    private Object[] a;
    private int tVermelho; 
    private int tPreto; 
    private int FC;
    private static final String err = "pilha vazia"; 
    public PilhaDuplaArray(int capacidadeTotal, int crescimento) {
        this.capacidade = capacidadeTotal;
        this.a = new Object[capacidade];
        //começa -1 porque vou somar
        this.tVermelho = -1; 
        //começa na capacidade, porque vou subtrair
        this.tPreto = capacidade; 
        this.FC = crescimento;
        if (crescimento <= 0) {
            this.FC = 0;
        }
    }
    //método para duplicar a pilha quando chegar na capacidade máxima
    public void crescer() {
        int capacidade_velha=capacidade;
        if (FC==0){
            capacidade*=2;
        }else{
            capacidade+=FC;
        }
        //copio a pilha vermelha para a nova capacidade
        Object[] b =new Object[capacidade]; //copio a pilha vermelha 
        for (int f =0; f<=tVermelho; f++){
            b[f]=a[f];
            
        }
        //copio a pilha preta para a nova capacidade
        int j=capacidade-1;
        for (int g = capacidade_velha - 1; g >= tPreto; g--){ 
            b[j]=a[g];
            j--;
        }
        //atualizo o tPreto
        tPreto = capacidade - (capacidade_velha - tPreto);
        a = b;
    }
    //método de para reduzir o tamanho do array 
    private void popList(){
        int totalCheio=sizeVermelho()+sizePreto();
        if(totalCheio>0 && totalCheio<=capacidade/3) {
            int capacidade_old=capacidade;
            capacidade/=2; //reduzo pela metade
            //faço a cópia da pilha vermelha
            Object []b= new Object[capacidade]; 
            for(int f=0;f<=tVermelho;f++){
                b[f]=a[f];                      
                
            }
            //faço a cópia da pilha preta
            int j=capacidade-1;
            for(int g=capacidade_old-1;g>=tPreto;g--){ 
                b[j]=a[g];
                j--;
            }
            //atualizo o tPreto para não dar out of bounds
            tPreto = capacidade - (capacidade_old - tPreto);//mesmo se tirar vai apontar certo pq isso so acontece no pop
            a=b;
        }
    }
    public int getCapacidade() {
        return this.capacidade;
    }
    //pilha vermelha 
    public void pushVermelho(Object o) {
        //verifico se tem espaço disponível
        if (tVermelho + 1 >= tPreto) { 
            crescer();
        
    }
        a[++tVermelho] = o;
    }

    public int sizeVermelho() {
        return tVermelho + 1; //mais um pq quero os elementos nao o indice
    }

    public boolean isEmptyVermelho() {
        return tVermelho == -1;
    }

    public Object topVermelho() throws PilhaVaziaExcecao {
        if (isEmptyVermelho()) {
            throw new PilhaVaziaExcecao(err);
        }
        return a[tVermelho];
    }

    public Object popVermelho() throws PilhaVaziaExcecao {
        if (isEmptyVermelho()) {
            throw new PilhaVaziaExcecao(err);
        }
        //vai ser t-- porque a pilha vermelha vai do inicio até o final
        Object r = a[tVermelho--];
        //caso, tenha espaço de sobra, o array vai ser cortado
        popList(); 
        return r;
    }

    // métodos pilha preta (vai percorrer de tras pra frente)
    public void pushPreto(Object o) {
        //verifico se tem espaço disponível
        if (tPreto - 1 <= tVermelho) { 
            crescer();
        }
        //percorro do final até o início
        a[--tPreto] = o; 
    }

    public boolean isEmptyPreto() {
        return tPreto == capacidade;
    }

    public int sizePreto() {
        return capacidade - tPreto;
    }

    public Object topPreto() throws PilhaVaziaExcecao {
        if (isEmptyPreto()) {
            throw new PilhaVaziaExcecao(err);
        }
        return a[tPreto];
    }

    public Object popPreto() throws PilhaVaziaExcecao {
        if (isEmptyPreto()) {
            throw new PilhaVaziaExcecao(err);
        }
        //vai ser tPreto++ porque a pilha preta é de trás para frente
        Object r = a[tPreto++]; 
        //caso, tenha espaço de sobra, o array vai ser cortado
        popList(); 
        return r;
    }
}


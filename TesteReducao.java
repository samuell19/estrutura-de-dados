public class TesteReducao {
    public static void main(String[] args) {
        PilhaRubro pilhas = new PilhaRubro(60);
        System.out.println("Capacidade Inicial: " + pilhas.getCapacidade());

        
        

        pilhas.pushVermelho("vermelho1");
        pilhas.pushPreto("preto1");
        pilhas.pushPreto("preto2");
        pilhas.pushPreto("preto3");
        pilhas.pushPreto("preto4");
        pilhas.pushPreto("preto5");
        pilhas.pushVermelho("vermelho1");
        pilhas.pushVermelho("vermelho2");
        pilhas.pushVermelho("vermelho3");
        System.out.println("Itens na Pilha 1: " + pilhas.sizeVermelho());
        System.out.println("Itens na Pilha 2: " + pilhas.sizePreto());
        System.out.println("Total de itens: " + (pilhas.sizeVermelho() + pilhas.sizePreto()));
        System.out.println("--------------------------------------------");
        System.out.println("top preto: "+ pilhas.topPreto());
        System.out.println("top vermelho: "+ pilhas.topVermelho());


        System.out.println("\n--- Forçando a redução ---");
        System.out.println("Removendo um item da Pilha 1...");
        pilhas.popVermelho(); 
        pilhas.popVermelho();
        
       
        System.out.println("\nCapacidade Final: " + pilhas.getCapacidade());
        System.out.println("Itens na Pilha 1 agora: " + pilhas.sizeVermelho()); 
        System.out.println("Itens na Pilha 2 : " + pilhas.sizePreto()); 
        System.out.println("topo da preta: " + pilhas.topPreto());
    }
}
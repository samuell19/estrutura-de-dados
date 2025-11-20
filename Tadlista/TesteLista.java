package Tadlista;

public class TesteLista {
    public static void main(String[] args) {

        System.out.println("========== TESTANDO ARRAYLISTA ==========");

        ArrayLista arr = new ArrayLista(20);

        arr.insertFirst("B");
        arr.insertLast("C");
        arr.insertFirst("A");
        arr.insertAfter(1, "X");
        arr.insertBefore(2, "Y");

        System.out.println("Tamanho: " + arr.size());
        System.out.println("Primeiro: " + arr.isFirst());
        System.out.println("Último: " + arr.isLast());

        System.out.println("Replaceando rank 2 com Z");
        arr.replaceElements(2, "Z");

        System.out.println("Swap (0,3)");
        arr.swapElements(0, 3);

        System.out.println("Removendo rank 1: " + arr.remove(1));

        System.out.println("========== TESTANDO LISTA ENCADEADA ==========");

        Lista lista = new Lista();

        lista.insertFirst("B");
        lista.insertLast("C");
        lista.insertFirst("A"); 

        Lista.Node nodeB = lista.after(lista.isFirst());
        lista.insertAfter(nodeB, "X");
        lista.insertBefore(nodeB, "Y");

        System.out.println("Tamanho: " + lista.size());
        System.out.println("Primeiro: " + lista.isFirst().data);
        System.out.println("Último: " + lista.isLast().data);

        System.out.println("Swap de primeiro com último");
        lista.swapElements(lista.isFirst(), lista.isLast());

        System.out.println("Removendo o primeiro: " + lista.remove(lista.isFirst()));

        System.out.println("Fim dos testes!");
    }
}

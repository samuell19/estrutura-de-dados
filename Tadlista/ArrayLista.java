package Tadlista;

public class ArrayLista {

    private Object[] elementos;
    private int tamanho;
    private int size;

    public ArrayLista(int tamanho) {
        this.elementos = new Object[tamanho];
        this.tamanho = tamanho;
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Object isFirst() {
        if (isEmpty()) return null;
        return elementos[0];
    }

    public Object isLast() {
        if (isEmpty()) return null;
        return elementos[size - 1];
    }

    public Object before(int r) {
        if (r <= 0 || r >= size) return null;
        return elementos[r - 1];
    }

    public Object after(int r) {
        if (r < 0 || r >= size - 1) return null;
        return elementos[r + 1];
    }

    public Object replaceElements(int r, Object novoElemento) {
        if (r < 0 || r >= size) {
            throw new IndexOutOfBoundsException("Rank inválido: " + r);
        }
        Object antigo = elementos[r];
        elementos[r] = novoElemento;
        return antigo;
    }

    public Object swapElements(int r1, int r2) {
        if (r1 < 0 || r1 >= size || r2 < 0 || r2 >= size) {
            throw new IndexOutOfBoundsException();
        }
        Object temp = elementos[r1];
        elementos[r1] = elementos[r2];
        elementos[r2] = temp;
        return temp;
    }

    public Object insertAfter(int r, Object novoElemento) {
        if (r < 0 || r >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (size >= tamanho) {
            throw new IndexOutOfBoundsException("Lista cheia");
        }

        Object temp = elementos[r];

        for (int i = size - 1; i > r; i--) {
            elementos[i + 1] = elementos[i];
        }

        elementos[r + 1] = novoElemento;
        size++;
        return temp;
    }

    public Object insertBefore(int r, Object novoElemento) {
        if (r < 0 || r >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (size >= tamanho) {
            throw new IndexOutOfBoundsException("Lista cheia");
        }

        Object temp = elementos[r];

        for (int i = size - 1; i >= r; i--) {
            elementos[i + 1] = elementos[i];
        }

        elementos[r] = novoElemento;
        size++;
        return temp;
    }

    public Object insertFirst(Object novoElemento) {
        if (size >= tamanho) {
            throw new IndexOutOfBoundsException("Lista cheia");
        }

        for (int i = size - 1; i >= 0; i--) {
            elementos[i + 1] = elementos[i];
        }

        elementos[0] = novoElemento;
        size++;
        return novoElemento;
    }

    public Object insertLast(Object novoElemento) {
        if (size >= tamanho) {
            throw new IndexOutOfBoundsException("Cheio");
        }

        elementos[size] = novoElemento;
        size++;
        return novoElemento;
    }

    public Object remove(int r){
        if (r < 0 || r >= size) {
            throw new IndexOutOfBoundsException("Rank inválido: " + r);
        }
        Object removido = elementos[r];
        for (int i = r; i < size - 1; i++) {
            elementos[i] = elementos[i + 1];
        }
        elementos[size - 1] = null; 
        size--;
        return removido;
    }

    public Object elementAt(int r) {
        if (r < 0 || r >= size) {
            throw new IndexOutOfBoundsException("Rank inválido: " + r);
        }
        return elementos[r];
    }
}

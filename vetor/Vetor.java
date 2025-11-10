package vetor;

public class Vetor {
    private Object[] a;
    private int tamanho;

    public Vetor(int capacidade) {
        a = new Object[capacidade];
        tamanho = 0;
    }

    public int size() {
        return tamanho;
    }

    public boolean isEmpty() {
        return tamanho == 0;
    }

    public Object elemAtRank(int r) {
        if (r < 0 || r >= tamanho) {
            throw new IndexOutOfBoundsException("Rank inválido");
        }
        return a[r];
    }

    public Object replaceAtRank(int r, Object o) {
        if (r < 0 || r >= tamanho) {
            throw new IndexOutOfBoundsException("Rank inválido");
        }
        Object replace = a[r];
        a[r] = o;
        return replace;
    }

    public Object insertAtRank(int r, Object o) {
        if (r < 0 || r > tamanho) {
            throw new IndexOutOfBoundsException("Rank inválido");
        }
        if (tamanho == a.length) {
            throw new IllegalStateException("Vetor cheio");
        }
        for (int i = tamanho - 1; i >= r; i--) {
            a[i + 1] = a[i];
        }
        a[r] = o;
        tamanho++;
        return o;
    }

    public Object removeAtRank(int r) {
        if (r < 0 || r >= tamanho) {
            throw new IndexOutOfBoundsException("Rank inválido");
        }
        Object removido = a[r];
        for (int i = r; i < tamanho - 1; i++) {
            a[i] = a[i + 1];
        }
        tamanho--;
        a[tamanho] = null;
        return removido;
    }
}

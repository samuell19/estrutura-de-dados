public void heapsort(int arr[]) {
    int n = arr.length;

    // 1. Constrói o Max-Heap (Pai sempre maior que os filhos)
    for (int i = n / 2 - 1; i >= 0; i--) {
        downheap(arr, n, i);
    }

    // 2. A "Mágica": Extração e Ordenação
    for (int i = n - 1; i > 0; i--) {
        // Troca a raiz (o MAIOR de todos) com o ÚLTIMO do array
        int temp = arr[0];
        arr[0] = arr[i];
        arr[i] = temp;

        // O maior "morreu" no fim do array. 
        // Agora conserta o resto (tamanho reduzido para 'i')
        downheap(arr, i, 0);
    }
}

void downheap(int arr[], int n, int i) {
    int maior = i;
    int esq = 2 * i + 1;
    int dir = 2 * i + 2;

    if (esq < n && arr[esq] > arr[maior]) maior = esq;
    if (dir < n && arr[dir] > arr[maior]) maior = dir;

    if (maior != i) {
        int troca = arr[i];
        arr[i] = arr[maior];
        arr[maior] = troca;
        downheap(arr, n, maior);
    }
}
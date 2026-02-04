//merge dee arvores
public Arvbin uniaoArv(arvbin A, arvbin B){
    percorrerEInserir(B.getRaiz(), A);
    return A
}
private void percorrerEInserir(No node, arvbin destino){
    if (no!=null){
        percorrerEInserir(no.getEsq(), destino);
        destino.inserir(no.getChave();
        percorrerEInserir(no.getDir(), destino))
    }
}


public fodasee uniaoArv(arvbin A, arv bin b){
    percorrerEInserir(b.getRaiz, A)
    return a
}

private percorrerEInserir(No node, arvbin destino){
    if no!=null{
        percorrerEInserir(no.getEsq(), destino)
        destino.inserir(no.getChave)
        percorrerEInserir(no.getDireita, destino)
    }
}

//impar sem contador
public No achaNóDoMeio() {
    // Começamos no primeiro nó real (após o sentinela de início)
    No lento = inicio.getProximo();
    No rapido = inicio.getProximo();

    // Enquanto o rápido não atingir o sentinela de fim
    // Verificamos o próximo e o próximo do próximo
    while (rapido != fim && rapido.getProximo() != fim) {
        lento = lento.getProximo();           // Anda 1 casa
        rapido = rapido.getProximo().getProximo(); // Anda 2 casas
    }

    // Quando o loop acaba, o lento está no meio certinho
    return lento;
}


private Integer ultimoValorVisitado = null;

public boolean éÁrvoreBináriaDePesquisa(ÁrvBin A) {
    ultimoValorVisitado = null; // Reinicia para cada nova busca
    return validaRecursivo(A.getRaiz());
}




//teste pra arvore de pesquisa
public boolean éÁrvoreBináriaDePesquisa(ÁrvBin A) {
    // Começa a verificação da raiz com limites infinitos
    return valida(A.getRaiz(), Integer.MIN_VALUE, Integer.MAX_VALUE);
}

private boolean valida(No no, int min, int max) {
    // 1. Se o nó é null, ele é válido (chegou ao fim do galho)
    if (no == null) {
        return true;
    }

    // 2. O valor do nó atual está fora do "intervalo de segurança"?
    // Se for menor que o mínimo permitido ou maior que o máximo, está errado!
    if (no.getChave() < min || no.getChave() > max) {
        return false;
    }

    // 3. Verifica os filhos passando os novos limites:
    // Para a ESQUERDA: o novo máximo é o valor do pai (no.getChave())
    // Para a DIREITA: o novo mínimo é o valor do pai (no.getChave())
    return valida(no.getEsq(), min, no.getChave() - 1) && 
           valida(no.getDir(), no.getChave() + 1, max);
}
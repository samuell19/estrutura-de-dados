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

private


private Integer ultimoValorVisitado = null;

public boolean éÁrvoreBináriaDePesquisa(ÁrvBin A) {
    ultimoValorVisitado = null; // Reinicia para cada nova busca
    return validaRecursivo(A.getRaiz());
}

private boolean validaRecursivo(No no) {
    if (no == null) return true; // Nó vazio é sempre válido

    // 1. Vai tudo para a esquerda
    if (!validaRecursivo(no.getEsq())) return false;

    // 2. Verifica a Raiz atual (Lógica do Dicionário)
    // Se o valor atual não for maior que o anterior, quebrou a regra
    if (ultimoValorVisitado != null && no.getChave() <= ultimoValorVisitado) {
        return false;
    }
    ultimoValorVisitado = no.getChave(); // Atualiza o "ponteiro" de comparação

    // 3. Vai para a direita
    return validaRecursivo(no.getDir());
}

private fodase fodase(arv a, arv b){
    percorrerEInseri(b.getRaiz, a)
    return A
}
private percorrerEInseri(Node no, destino){
    if no!=null{
        percorrerEInseri(no.getDir(), destino)
        destino.inserir(no.getChave())
        percorrerEInseri(no.getEsq(), destino)
    }
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
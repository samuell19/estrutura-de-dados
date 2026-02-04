public arvbin arvbinMerge(arv a, arv b){
    percorrerEinserir(b.getRaiz(), a)
    return a 
}

private percorrerEinserir(No no, destino){
    if (no!=null){
        percorrerEInserir(no.getEsq(), destino);
        destino.inserir(no.getChave);
        percorrerEinserir(no.getDir(,direita))
    }
}

public boolean (arvbin A){
    return validaRecursivo(a.getRaiz, integer min, integer max)

private validaRecursivo(Node no, max, min){
    if no==null{
        return true
    }
    if no<min or no>max{
        false
    }
    return validaRecursivo(min, no.getEsq, no.getChave) && ()
}
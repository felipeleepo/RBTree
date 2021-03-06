public class ArvoreRN {

    No root;

    public ArvoreRN() {
        this.root = null;
    }

    public void insert(No n, int v, No pai, No avo, boolean isRight) {

        if (root == null) {
            root = new No(v, null);
            root.c = false; // NEGRO
            System.out.println("RAIZ");
        } else {
            if(n == null){
                if(pai != null && !pai.c){
                    System.out.println("CASO 1");
                    n = Novo(v, n, pai, isRight);
                }
                // PAI e AVO NAO NULO
                else if (pai != null && avo != null) {
                    
                    No tio;
                    
                    if(pai == avo.esq)
                        tio = avo.dir;
                    else
                        tio = avo.esq;

                    if(pai.c && tio != null && tio.c){
                        System.out.println("CASO 2");
                        n = Novo(v, n, pai, isRight);
                        Caso2(pai, avo, tio);
                    }else if(pai.c && (tio == null || !tio.c)){
                        if(!isRight && pai == avo.esq){
                            System.out.println("CASO 3a");
                            n = Novo(v, n, pai, isRight);
                            pai.pai = avo.pai;
                            if(pai.pai != null){
                                if(pai.pai.dir == avo)
                                    pai.pai.dir = pai;
                                else
                                    pai.pai.esq = pai;
                            }
                                
                            avo.esq = pai.dir;
                            pai.dir = avo;
                            avo.pai = pai;
                            avo.c = true; // RUBRO
                            pai.c = false; // NEGRO
                            if(pai.pai == null)
                                root = pai;
                            
                        }
                        else if(isRight && pai == avo.dir){
                            System.out.println("CASO 3b");
                            n = Novo(v, n, pai, isRight);
                            pai.pai = avo.pai;
                            if(pai.pai != null){
                                if(pai.pai.dir == avo)
                                    pai.pai.dir = pai;
                                else
                                    pai.pai.esq = pai;
                            }
                            avo.dir = pai.esq;
                            pai.esq = avo;
                            avo.pai = pai;
                            avo.c = true; // RUBRO
                            pai.c = false; // NEGRO
                            if(pai.pai == null)
                                root = pai;
                            
                        }
                        else if(!isRight && pai == avo.dir){
                            System.out.println("CASO 3c");
                            n = Novo(v, n, pai, isRight);
                            n.dir = pai;
                            n.esq = avo;
                            n.pai = avo.pai;
                            if(avo.pai == null)
                                root = n;
                            avo.pai = n;
                            pai.pai = n;
                            pai.esq = null;
                            avo.dir = null;
                            n.c = false;
                            avo.c = pai.c = true;
                        }else{
                            System.out.println("CASO 3d");
                            n = Novo(v, n, pai, isRight);
                            n.esq = pai;
                            n.dir = avo;
                            n.pai = avo.pai;
                            if(avo.pai == null)
                                root = n;
                            avo.pai = n;
                            pai.pai = n;
                            pai.dir = null;
                            avo.esq = null;
                            n.c = false;
                            avo.c = pai.c = true;
                        }
                    }   

                    if(root.c)
                        root.c = false;
                }else{
                    System.out.println("Nenhum Caso.");
                }                     
            } else if (v > n.e) {
                insert(n.dir, v, n, pai, true);
            } else {
                insert(n.esq, v, n, pai, false);
            }
        }
    }

    public No Novo(int v, No n, No pai, boolean isRight){
        n = new No(v, pai);
        if (isRight)
            pai.dir = n;
        else
            pai.esq = n;
        return n;
    }
    
    public No Tio(No n){
        if(n.pai != null && n.pai.pai != null){
            if(n.pai == n.pai.pai)
                return n.pai.pai.dir;
            else
                return n.pai.pai.esq;
        }
        return null;
    }
    
    public No Irmao(No n){
        if(n.pai != null){
            if(n == n.pai.dir)
                return n.pai.esq;
            else
                return n.pai.dir;
        }
        return null;
    }

    private void Caso2(No pai, No avo, No tio){
        if(pai != null && tio != null){
            pai.c = tio.c = false;
            if(!pai.c){
                if(pai.esq != null)
                    pai.esq.c = true;
                if(pai.dir != null)
                    pai.dir.c = true;
            }
            if(avo != null){
                avo.c = true;
            
                if(avo.pai != null && avo.pai.c){
                    No aux_tio;
                    if(avo.pai == avo.pai.pai)
                        aux_tio = avo.pai.dir;
                    else
                        aux_tio = avo.pai.esq;
                    No aux_avo = null;
                    if( avo.pai.pai != null)
                        aux_avo = avo.pai.pai;
                    Caso2(avo.pai, aux_avo, aux_tio);
                }
            }
            
            if(pai == root){
                pai.esq.c = pai.dir.c = false;
                pai.c = true;
            }
        }        
    }

    public No Sucessor(No n){
        No aux = n;
        if(n.dir != null){
            aux = aux.dir;
            while(aux.esq != null){
                aux = aux.esq;
            }
        }else{
            while(aux.pai != null && aux != aux.pai.esq){
                aux = aux.pai;
            }
            aux = aux.pai;
        }
        System.out.print("\nSucessor: " + aux.e);
        return aux;            
        
    }
    
    public void remove(int o, No n, No pai, boolean isRight) {

        if (root == null) {
            System.out.println("Arvore Vazia");
        }

        if (n.e == o) {
            
            No aux;
            if(n.dir == null && n.esq == null){
                if(isRight)
                    n.pai.dir = null;
                else
                    n.pai.esq = null;
                System.out.println("\nRemovido o Último");
            }else{

                boolean removidoCor = n.c;
                boolean auxCor;
                if(n.dir == null){
                    aux = n.esq;
                    System.out.print("\nSubÁrvore Esquerda subiu");
                }else if(n.esq == null){
                    aux = n.dir;
                    System.out.print("\nSubÁrvore Direita subiu");
                }else{
                    aux = Sucessor(n);
                    auxCor = aux.c;
                    aux.pai = n.pai;
                    aux.esq = n.esq;
                    aux.esq.pai = aux;
                    if(aux.pai != null){
                        if(aux.pai.dir == aux)
                            aux.pai.dir = null;
                        else
                            aux.pai.esq = null;
                    }else
                        root = aux;
                }

                auxCor = aux.c;
                aux.pai = n.pai;
                if(aux.pai != null){
                    if(isRight)
                        n.pai.dir = aux;
                    else
                        n.pai.esq = aux;
                }

                if(removidoCor && auxCor){
                    System.out.println("\nCASO 1");                    
                }else if(!removidoCor && auxCor){
                    System.out.println("\nCASO 2");
                    aux.c = false;
                    if(aux != root)
                        aux.d = true;
                }else if(!removidoCor && !auxCor){
                    System.out.print("\nCASO 3");
                    if(aux != root)
                        aux.d = true;
                    Situacao3(aux, isRight);
                }else{
                    System.out.println("\nCASO 4");
                }
            }
                
        } else if (n.e > o) {
            remove(o, n.esq, n, false);
        } else {
            remove(o, n.dir, n, true);
        }
    }
    
    public void Situacao3(No n, boolean isRight){
        No w = Irmao(n);
         Mostrar(n);
        boolean FilhoWEsqNegro = false;
        boolean FilhoWDirNegro = false;
        // PAREI NO CASO .2B
        if(w != null && (w.esq == null || !w.esq.c)){
            FilhoWEsqNegro = true;
        }
        if(w != null && (w.dir == null || !w.dir.c)){
            FilhoWDirNegro = true;
        }System.out.println(!n.c + " " +"" + " " +FilhoWEsqNegro+ " "+FilhoWDirNegro + " " + (n.pai != null && n.pai.c));
        if(!n.c && (w != null && w.c) && (n.pai != null && !n.pai.c)){
            System.out.println(".1");
            if(isRight)
                RotacaoEsquerda(n.pai);
            else
                RotacaoDireita(n.pai);
            w.c = false;
            n.pai.c = true;
        }else if(!n.c && (w == null || !w.c) && FilhoWEsqNegro && FilhoWDirNegro && (n.pai != null && !n.pai.c)){
            System.out.println(".2a");
            // irmao rubro
        }else if(!n.c && (w == null || !w.c) && FilhoWEsqNegro && FilhoWDirNegro && (n.pai != null && n.pai.c)){
            System.out.println(".2b");
            //irmao rubro
            // pai negro
        }else if(!n.c && (w == null || !w.c) && n.pai != null && !FilhoWEsqNegro && FilhoWDirNegro){
            System.out.println(".3");
            // Rotação direita em W
            // Cor de W = New Filho Esq cor
        }else if(!n.c && (w == null || !w.c) && n.pai != null && !FilhoWDirNegro){
            System.out.println(".4");
            // rotacao esq
            // w cor = pai cor
            // pai negro
            // w dir negro
        }else{
            System.out.println(".bugou");
        }
        
    }

    private void printNodeValue(No n) {
        String cor;
        String duplo = "";
        if(n.c)
            cor = "R";
        else
            cor = "N";
        if(n.d)
            duplo = "=";
        Mostrar(n);
        System.out.print("[" + cor + "]" + duplo );
        System.out.print('\n');
    }

    public void printTree(No n, boolean isRight, String indent) {
        if (n.dir != null) {
            printTree(n.dir, true, indent + (isRight ? "        " : " |      "));
        }
        System.out.print(indent);
        if (isRight) {
            System.out.print(" /");
        } else {
            System.out.print(" \\");
        }
        System.out.print("----- ");
        printNodeValue(n);
        if (n.esq != null) {
            printTree(n.esq, false, indent + (isRight ? " |      " : "        "));
        }
    }
    
    private void Mostrar(No n){
        String ene = "";
        if(n != null){
            ene += n.e;
            if(n.esq != null)
                ene += "["+n.esq.e;
            else
                ene += "[";
            ene += ",";
            if(n.dir != null)
                ene += n.dir.e+"]";
            else
                ene += "]";
            if(n.pai != null)
                ene += "["+n.pai.e+"]";
            else
                ene+= "[]";
        }
        else
            ene += "null";
        System.out.print(ene);
    }

    private void RotacaoDireita(No n){
        No subD = n.dir;
        n.dir = subD.esq;
        if (n.dir != null) {
            n.dir.pai = n;
        }

        subD.esq = n;
        subD.pai = n.pai;
        n.pai = subD;

        if (subD.pai != null) {
            subD.pai.dir = subD;
        } else {
            root = subD;
        }
    }

    private void RotacaoEsquerda(No n){
        No subE = n.esq;
        n.esq = subE.esq;
        if (n.esq != null) {
            n.esq.pai = n;
        }

        subE.dir = n;
        subE.pai = n.pai;
        n.pai = subE;

        if (subE.pai != null) {
            subE.pai.esq = subE;
        } else {
            root = subE;
        }
    }
}

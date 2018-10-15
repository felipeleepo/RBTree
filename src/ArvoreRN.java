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
                // PAI e AVO NAO NULO
                if (pai != null && avo != null) {
                    No tio;
                    if(pai == avo.esq)
                        tio = avo.dir;
                    else
                        tio = avo.esq;

                    if(pai.c && tio != null && tio.c){
                        System.out.println("CASO 2");
                        n = Novo(v, n, pai, isRight);
                        Caso2(n, pai, avo, tio);
                    }else if(pai.c && (tio == null || !tio.c)){
                        if(!isRight && pai == avo.esq){
                            System.out.println("CASO 3a");
                            n = Novo(v, n, pai, isRight);
                            pai.pai = avo.pai;
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
                            pai.esq = null;
                            avo.dir = null;
                            n.c = false;
                            avo.c = pai.c = true;
                        }
                        else{
                            System.out.println("CASO 3d");
                            n = Novo(v, n, pai, isRight);
                            n.esq = pai;
                            n.dir = avo;
                            n.pai = avo.pai;
                            if(avo.pai == null)
                                root = n;
                            avo.pai = n;
                            pai.dir = null;
                            avo.esq = null;
                            n.c = false;
                            avo.c = pai.c = true;
                        }
                    }   

                }else if(pai != null && !pai.c){
                    System.out.println("CASO 1");
                    n = Novo(v, n, pai, isRight);
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

    private void Caso2(No n, No pai, No avo, No tio){
        if(pai != null && tio != null && avo != null){
            pai.c = tio.c = false;
            avo.c = true;
        }
        if(avo.pai != null && avo.pai.pai != null){
            No aux_tio;
            if(avo.pai == avo.pai.pai)
                aux_tio = avo.dir;
            else
                aux_tio = avo.esq;
            
            Caso2(avo, avo.pai, avo.pai.pai, aux_tio);
        }
    }

    /*public void Caso3(No n, boolean isRight){
        if(!isRight){
            // 3A
            if(n.pai != null && n.pai == )
        }
    }*/

    /*public String search(No n, int v, boolean externa) {

        if (n == null) {
            if (externa) {
                if (size == 0) {
                    return "Árvore Vazia";
                } else {
                    return "Valor " + v + " não encontrado";
                }
            } else {
                return "";
            }
        } else if (n.e == v) {
            if (externa) {
                return "Valor encontrato " + n.e;
            } else {
                return "";
            }
        } else if (v > n.e) {
            return "" + search(n.dir, v, externa);
        } else {
            return "" + search(n.esq, v, externa);
        }
    }

    public void remove(int o, No n, No pai, boolean isRight) {

        if (size == 0) {
            System.out.println("Arvore Vazia");
        }

        if (n.e == o) {
            System.out.println("Valor removido " + n.e);
            // Não possui Filhos
            if (n.dir == n.esq) {
                if (isRight) {
                    pai.dir = null;
                } else {
                    pai.esq = null;
                }
                updateNew(pai, isRight, false);
            // Possui Filho direito
            } else if (n.esq == null) {
                if (isRight) {
                    pai.dir = n.dir;
                } else {
                    pai.esq = n.dir;
                }
                n.dir = n.pai;
                updateNew(pai, isRight, false);
            // Possui Filho esquerdo
            } else if (n.dir == null) {
                if (isRight) {
                    pai.dir = n.esq;
                } else {
                    pai.esq = n.esq;
                }
                n.esq = n.pai;
                updateNew(pai, isRight, false);
            // Sucessor
            } else {
                if (pai != null) {
                    pai.dir = nextNode(n, pai);
                } else {
                    root = nextNode(n, null);
                }
                updateNew(n, isRight, false);
            }
            size--;
        } else if (n.e > o) {
            remove(o, n.esq, n, false);
        } else {
            remove(o, n.dir, n, true);
        }
    }
*/
    private void printNodeValue(No n) {
        String cor;
        if(n.c)
            cor = "R";
        else
            cor = "N";
        System.out.print(n.e + "[" + cor + "]" );
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
}
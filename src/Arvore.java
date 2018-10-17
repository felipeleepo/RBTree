public class Arvore{
    ArvoreRN a;
    
    public Arvore(){
        a = new ArvoreRN();
    }
    
    public void Inserir(int valor) {
        System.out.print("INSERÇÃO(" + valor + ") ");
        a.insert(a.root, valor, null, null, true);
        Imprimir();
        System.out.println("______________________________________");
    }

    public void Remover(int valor) {
        System.out.print("REMOÇÃO("+valor+")");
        a.remove(valor, a.root, a.root.pai, true);
        Imprimir();
        System.out.println("______________________________________");
    }

    /*public void Buscar(int valor) {
        System.out.println("BUSCA: " + a.search(a.root, valor, true));
        System.out.println("______________________________________");
    }*/

    public void Imprimir() {
        a.printTree(a.root, true, " ");
    }

    /*public void ImprimirEmOrdem() {
        a.printInOrder(a.root);
    }
    public void Altura() {
    }*/
    
}

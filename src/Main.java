class Main {
  public static void main(String[] args) {
    Arvore rb = new Arvore();
    rb.Inserir(3);
    rb.Imprimir();
    rb.Inserir(0);
    rb.Imprimir();
    rb.Inserir(1);
    rb.Imprimir();
    rb.Inserir(4);
    rb.Imprimir();
    
    rb.Remover(3);
  }
}

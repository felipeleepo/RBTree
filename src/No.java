public class No {
    int e;
    No pai, dir, esq;
    boolean c; // RUBRO = t, NEGRO = f
    boolean d = false;
    
    public No (int s){
        e = s;
        dir = null;
        esq = null;
        c = true;
    }
    public No (int s, No pai){
        this.pai = pai;
        e = s;
        dir = null;
        esq = null;
        c = true;
    } 
}
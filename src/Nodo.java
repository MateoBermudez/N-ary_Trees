public class Nodo {
    private Nodo ls, liga;
    private char dato;

    public Nodo(char dato) {
        this.ls = null;
        this.liga = null;
        this.dato = dato;
    }

    public Nodo getLs() {
        return ls;
    }

    public void setLs(Nodo ls) {
        this.ls = ls;
    }

    public Nodo getLiga() {
        return liga;
    }

    public void setLiga(Nodo liga) {
        this.liga = liga;
    }

    public char getDato() {
        return dato;
    }

    public void setDato(char dato) {
        this.dato = dato;
    }
}

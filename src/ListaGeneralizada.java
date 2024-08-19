public class ListaGeneralizada {
    Nodo raiz;

    public ListaGeneralizada(char raiz, String arbol) {
        this.raiz = new Nodo(raiz);
        if (arbol.length() > 1) {
            crearArbol(arbol.substring(1, arbol.length()-1), this.raiz);
        }
    }

    //Validar para inputs invalidos.
    private void crearArbol(String arbol, Nodo padre) {
        int contador = 1, contadorHijo = 0;
        char[] array = arbol.toCharArray();
        StringBuilder subString = new StringBuilder();
        Nodo sigPadre = null;
        int i = arbol.indexOf('(') + 1;
        if (i <= 0) {
            for (char c : array) {
                insertarFinal(c, padre);
            }
        }
        else {
            while (contador > 0) {
                if (i >= array.length) {
                    crearArbol(subString.toString(), sigPadre);
                    break;
                }
                if (contador == 1 && Character.isLetter(array[i])) {
                    insertarFinal(array[i], padre);
                }
                else if (array[i] == '(') {
                    if (contadorHijo == 0) {
                        sigPadre = new Nodo(array[i-1]);
                        insertarLS(padre, sigPadre);
                    }
                    contador++;
                    contadorHijo++;
                }
                else if (array[i] == ')') {
                    contador --;
                    contadorHijo--;
                }
                if (contadorHijo > 0 || array[i] == ')') {
                    subString.append(array[i]);
                }
                if (contadorHijo == 0 && !subString.isEmpty()) {
                    crearArbol(subString.toString(), sigPadre);
                    subString = new StringBuilder();
                }
                i++;
            }
        }

    }

    private void insertarLS(Nodo padre, Nodo hijo) {
        Nodo aux = padre;
        while (aux.getDato() != hijo.getDato() && aux.getLiga() != null) {
            aux = aux.getLiga();
        }
        aux.setLs(hijo);
    }

    private void insertarFinal(char c, Nodo padre) {
        Nodo aux = padre;
        while (aux.getLiga() != null) {
            aux = aux.getLiga();
        }
        Nodo next = new Nodo(c);
        aux.setLiga(next);
    }

    private void insertarFinal(Nodo hijo, Nodo padre) {
        Nodo aux = padre;
        while (aux.getLiga() != null) {
            aux = aux.getLiga();
        }
        aux.setLiga(hijo);
    }

    public void recorrido(Nodo R) {
        Nodo p = R;
        while (p != null) {
            if (p.getLs() == null) {
                System.out.print(p.getDato() + " ");
            }
            else {
                recorrido(p.getLs());
            }
            p = p.getLiga();
        }
    }

    public void imprimirListas(Nodo R) {
        Nodo p = R;
        System.out.print("\n");
        while (p != null) {
            System.out.print(p.getDato() + ((p.getLiga() != null) ? " --> " : ""));
            p = p.getLiga();
        }
        p = R;
        while (p != null) {
            if (p.getLs() != null) {
                imprimirListas(p.getLs());
            }
            p = p.getLiga();
        }
    }

    public boolean insertarDato(char padre, char hijo, Nodo raiz){
        boolean encontrado = false;
        while (raiz != null && !encontrado){
            if (raiz.getDato() == padre){
                Nodo insertar = new Nodo(hijo);
                if(this.raiz == raiz){
                    insertarFinal(hijo, raiz);
                }
                else if (raiz.getLs() == null){
                    Nodo aux = new Nodo(raiz.getDato());
                    raiz.setLs(aux);
                    aux.setLiga(insertar);
                }
                else {
                    insertarFinal(hijo, raiz.getLs());
                }
                encontrado = true;
            }
            else {
                if (raiz.getLs() != null){
                    encontrado = insertarDato(padre, hijo, raiz.getLs());
                }
                raiz = raiz.getLiga();
            }
        }
        return encontrado;
    }

    public boolean eliminarDato(char dato, Nodo raiz) {
        if (dato == this.raiz.getDato()) {
            this.raiz = this.raiz.getLiga();
            if (this.raiz.getLs() != null) {
                if (this.raiz.getLiga() != null) {
                    Nodo sig = this.raiz.getLiga();
                    sig.setLs(this.raiz.getLs());
                    sig.getLs().setDato(sig.getDato());
                    this.raiz.setLs(null);
                }
                else {
                    Nodo sig = this.raiz.getLs();
                    this.raiz.setLiga(sig);
                    this.raiz.setLs(null);
                    this.raiz.setLiga(sig.getLiga());
                }
            }
            return true;
        }
        boolean eliminado = false;
        while (raiz.getLiga() != null && !eliminado) {
            Nodo sig = raiz.getLiga();
            if (sig.getDato() == dato) {
                if (sig.getLs() != null) {
                    if (raiz.getLs() != null) {
                        insertarFinal(raiz.getLs(), sig.getLs());
                    }
                    else {
                        raiz.setLs(sig.getLs());
                        raiz.getLs().setDato(raiz.getDato());
                    }
                }
                raiz.setLiga(sig.getLiga());
                eliminado = true;
            }
            else {
                if (sig.getLs() != null) {
                    eliminado = eliminarDato(dato, sig.getLs());
                }
                if (eliminado) {
                    if (sig.getLs().getLiga() == null) {
                        sig.setLs(null);
                    }
                }
                raiz = raiz.getLiga();
            }

        }
        return eliminado;
    }
}

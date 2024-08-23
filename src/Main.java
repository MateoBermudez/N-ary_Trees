import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);

    //Realizar el menu
    public static void main(String[] args) {
        String arbol;
        ListaGeneralizada lista;

        System.out.println("Ingrese el arbol");
        arbol = sc.next();
        arbol = arbol.trim();
        arbol = arbol.replaceAll(",", "");
        arbol = arbol.toUpperCase();
        arbol = ValidarArbol(arbol);
        arbol = ValidarInputArbol(arbol);
        lista = new ListaGeneralizada(arbol.charAt(0), arbol);
        lista.recorrido(lista.raiz);
        System.out.print("\n");
        lista.imprimirListas(lista.raiz);
        System.out.print("\n");
        System.out.println("Ingrese un padre y un hijo para insertar");
        System.out.println("Ingrese el padre");
        char padre = sc.next().toUpperCase().charAt(0);
        System.out.println("Ingrese el hijo");
        char hijo = sc.next().toUpperCase().charAt(0);
        System.out.println((lista.insertarDato(padre, hijo, lista.raiz)) ? "Se inserto correctamente" : "No se encontró el padre");
        lista.recorrido(lista.raiz);
        System.out.print("\n");
        lista.imprimirListas(lista.raiz);
        System.out.print("\n");
        System.out.println("Ingrese un dato para eliminar");
        char dato = sc.next().toUpperCase().charAt(0);
        System.out.println((lista.eliminarDato(dato, lista.raiz)) ? "Se eliminó correctamente" : "No se encontró el dato");
        lista.recorrido(lista.raiz);
        System.out.print("\n");
        lista.imprimirListas(lista.raiz);
        System.out.print("\n");

    }

    //Este metodo unicamente valida que tenga letras mayusculas y parentesis
    private static String ValidarArbol(String arbol) {
        String regex = "^[A-Z()]+$";
        boolean validado = arbol.matches(regex);
        while (!validado) {
            System.out.println("Ingrese el arbol");
            arbol = sc.next();
            arbol = arbol.trim();
            arbol = arbol.replaceAll(",", "");
            arbol = arbol.toUpperCase();
            validado = arbol.matches(regex);
        }
        return arbol;
    }

    //Parece que este metodo valida correctamente, realizar pruebas
    private static String ValidarInputArbol(String arbol) {
        int cont = 0;
        //Suponemos que el arbol es valido
        boolean valido = true;
        while (valido) {
            if (arbol == null || arbol.isEmpty() || !Character.isLetter(arbol.charAt(0))) {
                valido = false;
            }
            for (int i = 1; valido && i < arbol.length(); i++) {
                if (Character.isLetter(arbol.charAt(1))) {
                    valido = false;
                }
                if (arbol.charAt(i) == '(') {
                    if (arbol.charAt(i - 1) == ')' || arbol.charAt(i - 1) == '('){
                        valido = false;
                    }
                    cont++;
                } else if (arbol.charAt(i) == ')') {
                    if (arbol.charAt(i - 1) == '(') {
                        valido = false;
                    }
                    cont--;
                }
                if (cont == 0 && i != arbol.length() - 1) {
                    valido = false;
                }
                if (cont < 0) {
                    valido = false;
                }
                if (cont > 0 && i == arbol.length() - 1) {
                    valido = false;
                }
            }

            if (!valido) {
                System.out.println("Ingrese el arbol");
                arbol = sc.next();
                arbol = arbol.trim();
                arbol = arbol.replaceAll(",", "");
                arbol = arbol.toUpperCase();
                arbol = ValidarArbol(arbol);
                cont = 0;
                valido = true;
            }
            else {
                valido = false;
            }
        }
        return arbol;
    }
}
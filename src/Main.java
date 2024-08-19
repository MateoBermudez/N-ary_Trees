import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        String arbol;
        ListaGeneralizada lista;

        System.out.println("Ingrese el arbol");
        arbol = sc.next();
        arbol = arbol.trim();
        arbol = arbol.toUpperCase();
        arbol = ValidarArbol(arbol);
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

    //Validar Errores para inputs invalidos.
    private static String ValidarArbol(String arbol) {
        String regex = "^[A-Z()]+$";
        boolean validado = arbol.matches(regex);
        while (!validado) {
            System.out.println("Ingrese el arbol");
            arbol = sc.next();
            arbol = arbol.trim();
            arbol = arbol.toUpperCase();
            validado = arbol.matches(regex);
        }
        return arbol;
    }
}

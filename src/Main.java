import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static Scanner sc = new Scanner(System.in);

    //Realizar el menu
    public static void main(String[] args) {
        String arbol;
        ListaGeneralizada lista = null;
        int opcion;
        do {
            String menu = "\nIngrese las opción que desea realizar:\n" +
                    "1. Construir árbol\n" +
                    "2. Insertar dato\n"+
                    "3. Eliminar dato\n"+
                    "4. Buscar dato\n"+
                    "5. Mostrar raíces\n"+
                    "6. Mostrar hojas\n"+
                    "7. Mostrar la altura de un dato\n"+
                    "8. Mostrar el nivel de un dato\n"+
                    "9. Imprimir Datos\n"+
                    "10. Mostrar Arbol\n"+
                    "11. Reto 1\n"+
                    "12. Mostrar Primos\n"+
                    "13. Salir\n";

            System.out.print(menu);
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    lista= construirArbol(lista);
                    break;
                case 2:
                    System.out.println("Ingrese el padre");
                    char padre = sc.next().toUpperCase().charAt(0);
                    System.out.println("Ingrese el hijo");
                    char hijo = sc.next().toUpperCase().charAt(0);
                    System.out.println((lista.insertarDato(padre, hijo , lista.raiz)) ? "Dato Insertado" : "No se pudo insertar el dato");
                    break;
                case 3:
                    System.out.println("Ingrese el dato a eliminar");
                    char datoEliminar = sc.next().toUpperCase().charAt(0);
                    System.out.println((lista.eliminarDato(datoEliminar, lista.raiz)) ? "Se eliminó correctamente" : "No se encontró el dato");
                    break;
                case 4:
                    System.out.println("Ingrese el dato a buscar");
                    char datoBuscar = sc.next().toUpperCase().charAt(0);
                    System.out.println((lista.buscarDato(datoBuscar, lista.raiz)) ? "Dato encontrado" : "No se encontró el dato");
                    break;
                case 5:
                    lista.MostrarPadres(lista.raiz);
                    break;
                case 6:
                    if (lista.raiz.getLiga() == null) {
                        System.out.println(lista.raiz.getDato());
                    }
                    else {
                        lista.MostrarHojas(lista.raiz.getLiga());
                    }
                    break;
                case 7:
                    System.out.println("Ingrese el dato");
                    char dato = sc.next().toUpperCase().charAt(0);
                    Nodo aux = lista.buscarNodo(dato, lista.raiz);
                    if (aux != lista.raiz) {
                        aux = aux.getLs();
                    }
                    int altura = lista.alturaDato(aux, 0);
                    System.out.println("La altura del dato " + dato + " es: " + altura);
                    break;
                case 8:
                    System.out.println("Ingrese el dato");
                    char datoNivel = sc.next().toUpperCase().charAt(0);
                    lista.NivelDato(lista.raiz, datoNivel, 1);
                    break;
                case 9:
                    lista.recorrido(lista.raiz);
                    break;
                case 10:
                    lista.imprimirListas(lista.raiz);
                    break;
                case 11:
                    ArrayList<Character> listaDato = new ArrayList<>();
                    listaDato = lista.reto1(lista.raiz, listaDato);
                    for (int i = 0; i < listaDato.size() && i+3 <= listaDato.size() ; i=i+3) {
                        System.out.println();
                        System.out.print(listaDato.get(i));
                        System.out.print(listaDato.get(i+1));
                        System.out.print(listaDato.get(i+2));
                    }
                    break;
                case 12:
                    System.out.println("Ingrese el dato");
                    char datoPrimo = sc.next().toUpperCase().charAt(0);
                    int nivelPrimos = lista.getNivel(lista.raiz, datoPrimo, 1)+1;
                    Nodo aux1 = lista.buscarPadre(datoPrimo, lista.raiz);
                    aux1 = aux1.getLiga();
                    String hermanos = "";
                    while (aux1 != null) {
                        hermanos = hermanos + aux1.getDato();
                        aux1 = aux1.getLiga();
                    }
                    String primos = "";
                    if (nivelPrimos == 2 || nivelPrimos == 1) {
                        System.out.println("No tiene primos");
                    }
                    else {
                        lista.imprimirPrimos(lista.raiz, hermanos, nivelPrimos, primos);
                    }
                    break;
                case 13:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        } while (opcion != 13);
    }

    private static ListaGeneralizada construirArbol(ListaGeneralizada lista) {
        System.out.println("Ingrese el arbol");
        String arbol = sc.next();
        arbol = arbol.trim().replaceAll(",", "").toUpperCase();
        arbol = ValidarArbol(arbol);
        arbol = ValidarInputArbol(arbol);
        lista = new ListaGeneralizada(arbol.charAt(0), arbol);
        return lista;
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
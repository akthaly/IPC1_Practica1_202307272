
package práctica.pkg1;

import  java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Aleja
 */
public class Práctica1 {
    public static String[][] tablero = new String[8][8];
    public static String[][] penalizaciones = new String[8][8];
    //Scanner global para numeros
    public static Scanner inputData = new Scanner(System.in);
    //Scanner global para letras
    public static Scanner inputString = new Scanner(System.in);
    //Jugador
    public static String simbolo = " @";
    //Posicion del jugador
    public static int posicion = 1;
    
    // NivelPenalizacion
    public static int nivelPenalizacion = -99;
    
    public static void main(String[] args) {
        int opcion;
         
        String[] nombres = new String [5];

        do {
            System.out.println("**************BIENVENIDOS**************");
            System.out.println("1. Iniciar Juego");
            System.out.println("2. Retomar Juego");
            System.out.println("3. Mostrar informacion del estudiante");
            System.out.println("4. Salir");
            System.out.println("***************************************");
            System.out.print("Elige una opcion: ");
            opcion = inputData.nextInt();
            
            if (opcion == 1) {
                System.out.println(" Iniciando Partida");
                llenarMatriz();
                casillasConPenalizaciones();

                elegirOpcion();

                break;    
            }
            if (opcion == 2) {
                System.out.println("Remando juego");
            }
            if (opcion == 3) {
                System.out.println("+++++++++++++++++++++++++++++++++++++++");
                System.out.println("Nombre: Bryan Alejandro Anona Paredes");
                System.out.println("Carnet: 202307272");
                System.out.println("Seccion: E");
                System.out.println("+++++++++++++++++++++++++++++++++++++++");
                break;
            }
            if (opcion == 4) {
                System.out.println("Gracias por usar el juego");
                break;
            }
           } while (opcion != 4);

    }
    
        public static void llenarMatriz() {
        int contador = 1;
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                tablero[i][j] = String.valueOf(contador);
                contador++; //le suma +1 a la variable contador
            }
        }

        for (int i = 0; i < penalizaciones.length; i++) {
            for (int j = 0; j < penalizaciones[i].length; j++) {
                penalizaciones[i][j] = "";
            }
        }

    }
        
        public static void casillasConPenalizaciones() {
        Random random = new Random();

        int cantidadPenalizaciones;
        int posicionAleatoria;
        for (int i = 0; i < penalizaciones.length; i++) {

            cantidadPenalizaciones = random.nextInt(3) + 2;

            while (cantidadPenalizaciones != 0) {

                while (true) {
                    posicionAleatoria = random.nextInt(penalizaciones[i].length);
                    if (!penalizaciones[i][posicionAleatoria].contains("#")) {
                        break;
                    }
                }
                penalizaciones[i][posicionAleatoria] = "#" + penalizaciones[i][posicionAleatoria];
                cantidadPenalizaciones--;
            }
        }

    }
        
         public static void imprimirTablero() {
        System.out.println("-------------------------------------------------");
        boolean bandera = false;
        String casilla;
        for (int i = tablero.length - 1; i >= 0; i--) {
            for (int j = 0; j < tablero.length; j++) {
                System.out.print("+------");
            }

            System.out.println("+");


            for (int j = tablero.length - 1; j >= 0; j--) {

                if (bandera == true) {
                    casilla = penalizaciones[i][j] + tablero[i][j];
                    // ""+64 = ""64

                    System.out.printf("| %4s ", casilla);
                } else {
                    casilla = penalizaciones[i][(tablero.length - 1) - j] + tablero[i][(tablero.length - 1) - j];
                    //8-1= 7 
                    System.out.printf("| %4s ", casilla);
                }

            }
            System.out.println("|");

            for (int j = tablero.length - 1; j >= 0; j--) {
                if (bandera == true) {
                    if (posicion == Integer.parseInt(tablero[i][j])) {
                        System.out.printf("| %4s ", simbolo);

                        if (penalizaciones[i][j].contains("#")) {
                            nivelPenalizacion = i + 1;
                        }

                    } else {
                        System.out.printf("| %4s ", " ");
                    }
                } else {

                    if (posicion == Integer.parseInt(tablero[i][(tablero.length - 1) - j])) {
                        System.out.printf("| %4s ", simbolo);
                        if (penalizaciones[i][(tablero.length - 1) - j].contains("#")) {
                            nivelPenalizacion = i + 1;
                        }
                    } else {
                        System.out.printf("| %4s ", " ");
                    }

                }
            }

            System.out.println("|");
            bandera = !bandera;

        }
        for (int j = 0; j < 8; j++) {

            System.out.print("+------");
        }
        System.out.println("+");

    }

    public static void elegirOpcion() {
    String elegir;
    Random random = new Random();
    int dado;
    do {
        if (posicion > 64) {
            System.out.println(" Ganaste el juego");
            break;
        }
        imprimirTablero();
        niveles_Penalizacion();
        System.out.println(" Lanzar dado (d)");
        System.out.println(" Pausar Juego (p)");


        elegir = inputString.nextLine();

        if (elegir.equals("d")) {
            dado = random.nextInt(6) + 1;
            System.out.println("El dado cayo en: " + dado);
            posicion += dado;
        } else if (elegir.equals("p")) {
            System.out.println("Juego pausado");
        } else {
            System.out.println("Opción no válida");
        }
    } while (!elegir.equals("p"));
}

    public static void niveles_Penalizacion() {

        if (nivelPenalizacion == 1 || nivelPenalizacion == 2) {
            double respuesta = nivelFacil();
            System.out.println("¡Has caido en una penalizacion!");
            System.out.println("Calcule el Lado B del triangulo y coloque su respuesta aproximada a 2 decimales: ");
            double dato = inputData.nextDouble();
            if (dato == respuesta) {
                System.out.println("¡Has logrado vencer la penalizacion, continua tu camino!");
            }else{
                System.out.println("¡No lograste vencer la penalizacion, FIN DEL JUEGO! La respuesta era: " + respuesta);
                return;
            }
        } else if (nivelPenalizacion == 3 || nivelPenalizacion == 4 || nivelPenalizacion == 5) {
            System.out.println("¡Has caido en una penalizacion!");
        } else if (nivelPenalizacion == 6 || nivelPenalizacion == 7 || nivelPenalizacion == 8) {
            System.out.println("¡Has caido en una penalizacion!");
        }

        nivelPenalizacion = -99;
    }
    
    public static double nivelFacil(){

        double ladoA = 19.0;

        double ladoC = 16.0;

        double anguloCGrados = 29.0;

        // Convertir el ángulo de grados a radianes
        double anguloCRadianes = Math.toRadians(anguloCGrados);

        // Aplicar la fórmula de leyes de cosenos
        double ladoB = Math.sqrt(Math.pow(ladoA, 2) + Math.pow(ladoC, 2) - 2 * ladoA * ladoC * Math.cos(anguloCRadianes));
        
        // Redondear el resultado a dos decimales antes de devolverlo
        ladoB = Math.round(ladoB * 100.0) / 100.0;
        
        return ladoB;
    }
   
}
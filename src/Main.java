import java.util.Random;
import java.util.Scanner;
//============Clase almacenamientoPila==============
class AlmacenamientoPila{
	 private char[] datos;
	    private int tope;
	    
	    //Crear la pila 
	    public AlmacenamientoPila() {
	    	datos=new char[27];// Se inicializa en 27 por que , ahi guardaremos las letras del alfabeto
	    	tope = -1;
	    	
	    }
	    //Agregar elemento a la pila 
	    public void agregarLetra(char letra) {

	        if (tope < datos.length - 1) { //si tope es menor al tamaño de datos menos una posicion
	            tope++; // tope incrementa 
	            datos[tope] = letra;// y en la posicion donde este tope, se guardara la letra ingresada 
	        }
	    }
	    
	    public char Eliminarletra() {
	    	if(!verificarPilaVacia()) {
	    		 char letra = datos[tope];
	             tope--;
	             return letra;
	    	}
			return '\0'; //\0 se utiliza para devolver un null pero en caracter
	    	
	    }
	    
	    //metodo para verificar si la pila esta vacia
	    public boolean verificarPilaVacia() {
	    	 return tope == -1;
	    }
	    
	    //metodo para buscar una letra dentro de la pila
	    public boolean BuscarLetra (char letra) {

	    	
	        for (int i = 0; i <= tope; i++) {

	            if (datos[i] == letra) {
	                return true;
	            }
	        }

	        return false;
	    }
	    
	    //metodo para regresar las letras ingresadas 
	    public char[] obtenerLetras() {

	        char[] letras = new char[tope + 1];

	        for (int i = 0; i <= tope; i++) {
	            letras[i] = datos[i];
	        }

	        return letras;
	    }
	    //Cantidad de letras 
	    public int cantidad() {
	        return tope + 1;
	    }
	
}
//====================CLASE JUEGO AHORCADO=================
class JuegoAhorcado{
	 private String palabraSecreta;
	 private AlmacenamientoPila letrasIngresadas;
	 private Oportunidades oportunidades;
	   
	 public JuegoAhorcado() {

	        letrasIngresadas = new AlmacenamientoPila();
	        oportunidades = new Oportunidades();
	 }
	 
	public String [] cargarPalabras (String cadena ){
		return null;
	}
	
	public String elegirPalabras (String [] palabras) {//En este metodo escogemos de manera aleatoria 
		if (palabras.length == 0) {//una palabra , con un objeto del tipo random
            return null;
        }

        Random random = new Random();

        int posicion = random.nextInt(palabras.length);

        return palabras[posicion];
		
	}
	
	public String obtenerPalabraEncontrada(String palabraSecreta, char[] letrasIngresadas) {

        String resultado = "";

        for (int i = 0; i < palabraSecreta.length(); i++) {

            char letra = palabraSecreta.charAt(i);

            boolean encontrada = false;

            for (int j = 0; j < letrasIngresadas.length; j++) {

                if (letrasIngresadas[j] == letra) {
                    encontrada = true;
                }
            }

            if (encontrada) {
                resultado += letra + " ";
            } else {
                resultado += "_ ";
            }
        }

        return resultado;
	}
	
	 // Verificar si se adivino la palabra
    public boolean seAdivinoLaPalabra(
            String palabraSecreta, char[] letrasIngresadas) {

        for (int i = 0; i < palabraSecreta.length(); i++) {

            char letra = palabraSecreta.charAt(i);

            boolean encontrada = false;

            for (int j = 0; j < letrasIngresadas.length; j++) {

                if (letrasIngresadas[j] == letra) {
                    encontrada = true;
                }
            }

            if (!encontrada) {
                return false;
            }
        }

        return true;
    }
    
    // Obtener letras disponibles
    public String obtenerLetrasDisponibles(char[] letrasIngresadas) {

        String abecedario = "abcdefghijklmnñopqrstuvwxyz";
        String disponibles = "";

        for (int i = 0; i < abecedario.length(); i++) {

            char letra = abecedario.charAt(i);

            boolean usada = false;

            for (int j = 0; j < letrasIngresadas.length; j++) {

                if (letrasIngresadas[j] == letra) {
                    usada = true;
                }
            }

            if (!usada) {
                disponibles += letra;
            }
        }

        return disponibles;
    }
    
    public void inicioAhorcado(String palabra, Scanner sc) {

        palabraSecreta = palabra.toUpperCase();

        // Reiniciar estructuras para una nueva partida
        letrasIngresadas = new AlmacenamientoPila();
        oportunidades = new Oportunidades();

        System.out.println();
        System.out.println("================================");
        System.out.println("      JUEGO DEL AHORCADO");
        System.out.println("================================");

        System.out.println("Estoy pensando en una palabra de "
                + palabraSecreta.length() + " letras.");

        System.out.println();

        // Mientras queden oportunidades
        while (oportunidades.quedanOportunidades()) {

            char[] letras = letrasIngresadas.obtenerLetras();

            System.out.println("--------------------------------");

            System.out.println("Te quedan "
                    + oportunidades.getIntentos()
                    + " oportunidades.");

            System.out.println("Palabra: "
                    + obtenerPalabraEncontrada(palabraSecreta, letras));

            System.out.println("Letras disponibles: "
                    + obtenerLetrasDisponibles(letras));

            System.out.print("Ingresa una letra: ");

            String entrada = sc.nextLine().trim().toLowerCase();

            // Verificar que solo se ingrese una letra
            if (entrada.length() != 1) {

                System.out.println("Debes ingresar solamente una letra.");
                continue;
            }

            char letra = entrada.charAt(0);

            // Verificar que sea una letra del alfabeto español
            String abecedario = "abcdefghijklmnñopqrstuvwxyz";

            if (abecedario.indexOf(letra) == -1) {

                System.out.println("Caracter no valido. Ingresa una letra.");
                continue;
            }

            // Verificar si la letra ya fue ingresada
            if (letrasIngresadas.BuscarLetra(letra)) {

                System.out.println("Ya habias ingresado esa letra.");
                continue;
            }

            // Guardar letra en la pila
            letrasIngresadas.agregarLetra(letra);

            // Verificar si la letra existe
            if (palabraSecreta.indexOf(letra) >= 0) {

                System.out.println("Bien hecho, la letra si esta.");

            } else {

                System.out.println("Esa letra no esta en la palabra.");

                oportunidades.descontarIntento();
            }

            // Mostrar progreso
            letras = letrasIngresadas.obtenerLetras();

            System.out.println("Palabra: "
                    + obtenerPalabraEncontrada(palabraSecreta, letras));

            // Verificar si gano
            if (seAdivinoLaPalabra(palabraSecreta, letras)) {

                System.out.println();
                System.out.println("Felicidades, GANASTE!");
                System.out.println("La palabra era: " + palabraSecreta);
                return;
            }
        }

        // Si se terminan las oportunidades
        System.out.println();
        System.out.println("Te quedaste sin oportunidades.");
        System.out.println("NO ADIVINASTE LA PALABRA.");
        System.out.println("La palabra secreta era: " + palabraSecreta);
    }
}


//=================CLASE PRUEBA JUEGO AHORCADO==========
class PruebaJuegoAhorcado{
	public void menuOpciones() {
		Scanner sc = new Scanner (System.in);

		String opcion;
		do {
			
			System.out.println("======MENU DEL JUEGO====");
			System.out.println("1) Verificar Archivo");
			System.out.println("2) Llenar Archivo con Palabras");
			System.out.println("3) Borrar Archivo");
			System.out.println("4) Jugar");
			System.out.println("----------------------------------");
			System.out.println("5) Salir");
			System.out.println("Ingrese opcion:");
		    opcion = sc.next();
			switch(opcion) {
			case "1":
				
				break;
			case "2":
				
				break;
			case "3":
				
				break;
			case "4":
			
				

			break;
			case "5":
				System.out.println("Saliendo del menu.....");
				return; 
				default:
					System.out.println("Ese caracter no es valido");
					break;
					
			}
			
		}while (opcion != "5");
	
	}
}


//=========CLASE OPORTUNIDADES==========
class Oportunidades{
	private byte intentos = 8;
	
    public void descontarIntento() {

        intentos--;
    }

    public byte getIntentos() {

        return intentos;
    }
	
}
	

public class Main {

	public static void main(String[] args) {
		PruebaJuegoAhorcado  pja = new PruebaJuegoAhorcado();
		pja.menuOpciones();

	}

}

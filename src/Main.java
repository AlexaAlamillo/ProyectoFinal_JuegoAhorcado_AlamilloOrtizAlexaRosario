import java.util.Random;
import java.io.*;
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

        palabraSecreta = palabra.toLowerCase();

        // Reiniciar estructuras para una nueva partida
        letrasIngresadas = new AlmacenamientoPila();
        oportunidades = new Oportunidades();

        System.out.println();
        System.out.println("================================");
        System.out.println("      JUEGO DEL AHORCADO");
        System.out.println("================================");

        System.out.println("Estoy pensando en una palabra de "+ palabraSecreta.length() + " letras");

        System.out.println();

        // Mientras queden oportunidades
        while (oportunidades.quedanOportunidades()) {

            char[] letras = letrasIngresadas.obtenerLetras();

            System.out.println("--------------------------------");

            System.out.println("Te quedan " + oportunidades.getIntentos()+ " oportunidades.");

            System.out.println("Palabra: " + obtenerPalabraEncontrada(palabraSecreta, letras));

            System.out.println("Letras disponibles: " + obtenerLetrasDisponibles(letras));

            System.out.print("Ingresa una letra: ");

            String entrada = sc.nextLine().toLowerCase();

            // Verificar que el usuario solo ingrese  letras
            if (entrada.length() != 1) {

                System.out.println("Debes ingresar solamente una letra");
                continue;// para que siga ejecutando el codigo sin parar 
            }

            char letra = entrada.charAt(0);

            // Verificar que sea una letra del alfabeto español
            String abecedario = "abcdefghijklmnñopqrstuvwxyz";

            if (abecedario.indexOf(letra) == -1) {//Con esto nos podemos asegurar que no ocurra un error por agregar un numero

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

            System.out.println("Palabra: " + obtenerPalabraEncontrada(palabraSecreta, letras));

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
        System.out.println("===========Te quedaste sin oportunidades ===========");
        System.out.println("NO ADIVINASTE LA PALABRA.");
        System.out.println("La palabra secreta era: " + palabraSecreta);
    }
}


//=================CLASE PRUEBA JUEGO AHORCADO==========
class PruebaJuegoAhorcado{
	
	    private ArchivoPalabras archivo;
	    private JuegoAhorcado juego;

	    
	   Scanner sc = new Scanner(System.in);
	    // Constructor
	    public PruebaJuegoAhorcado() {

	        archivo = new ArchivoPalabras();
	        juego = new JuegoAhorcado();
	    }

	    // Menu principal
	    public void menuOpciones() {

	        String opcion;

	        do {

	            System.out.println();
	            System.out.println("======= MENU DEL AHORCADO =======");
	            System.out.println("1) Verificar archivo");
	            System.out.println("2) Llenar archivo con palabras");
	            System.out.println("3) Borrar archivo");
	            System.out.println("4) Jugar");
	            System.out.println("5) Salir");
	            System.out.println("================================");
	            System.out.print("Ingrese opcion: ");

	            opcion = sc.nextLine();

	            switch (opcion) {

	                case "1":

	                    verificarArchivo();
	                    break;

	                case "2":

	                    llenarArchivo();
	                    break;

	                case "3":

	                    borrarArchivo();
	                    break;

	                case "4":

	                    jugar();
	                    break;

	                case "5":

	                    System.out.println("Saliendo del juego...");
	                    break;

	                default:

	                    System.out.println("Opcion no valida.");
	                    break;
	            }

	        } while (!opcion.equals("5"));
	    }
	    
	    
	    public void verificarArchivo() {

	        archivo.verificarArchivo();

	        String[] palabras = archivo.leerPalabras();

	        System.out.println("Cantidad de palabras: " + palabras.length);

	        if (palabras.length == 0) {
	            System.out.println("El archivo esta vacio.");
	        } else {

	            System.out.println("Palabras registradas:");

	            for (int i = 0; i < palabras.length; i++) {
	                System.out.println((i + 1) + ". " + palabras[i]);
	            }
	        }
	    }

	    // Opcion 2: llenar archivo
	    public void llenarArchivo() {

	        archivo.verificarArchivo();

	        System.out.print("Cuantas palabras deseas agregar? ");

	        String entrada = sc.nextLine();

	        int cantidad;

	        try {
	            cantidad = Integer.parseInt(entrada);
	        } catch (NumberFormatException e) {
	            System.out.println("Debes ingresar un numero.");
	            return;
	        }

	        if (cantidad <= 0) {
	            System.out.println("La cantidad debe ser mayor que cero.");
	            return;
	        }

	        for (int i = 0; i < cantidad; i++) {

	            System.out.print("Ingresa la palabra " + (i + 1) + ": ");

	            String palabra = sc.nextLine().trim();

	            // Validar que la palabra solo tenga letras
	            if (palabra.isEmpty()) {
	                System.out.println("La palabra no puede estar vacia.");
	                i--;
	                continue;
	            }

	            boolean valida = true;

	            for (int j = 0; j < palabra.length(); j++) {

	                char letra = Character.toLowerCase(palabra.charAt(j));

	                String abecedario = "abcdefghijklmnñopqrstuvwxyz";

	                if (abecedario.indexOf(letra) == -1) {
	                    valida = false;
	                }
	            }

	            if (!valida) {
	                System.out.println("Solo puedes ingresar palabras con letras.");
	                i--;
	                continue;
	            }

	            archivo.cargarPalabras(palabra);
	        }

	        System.out.println("Carga de palabras finalizada.");
	    }

	    // Opcion 3: vaciar archivo
	    public void borrarArchivo() {

	        System.out.print("Estas seguro de vaciar el archivo? (S/N): ");

	        String respuesta = sc.nextLine();

	        if (respuesta.equalsIgnoreCase("S")) {

	            archivo.verificarArchivo();
	            archivo.eliminarArchivo();

	        } else {

	            System.out.println("Operacion cancelada.");
	        }
	    }

	    // Opcion 4: jugar
	    public void jugar() {

	        archivo.verificarArchivo();

	        // Comprobar si existen palabras
	        if (archivo.estaVacio()) {

	            System.out.println("El archivo de palabras esta vacio.");
	            System.out.println("Primero debes agregar palabras.");

	            llenarArchivo();

	            // Comprobar nuevamente
	            if (archivo.estaVacio()) {
	                System.out.println("No hay palabras para jugar.");
	                return;
	            }
	        }

	        // Leer palabras del archivo
	        String[] palabras = archivo.leerPalabras();

	        // Elegir palabra aleatoria
	        String palabra = juego.elegirPalabras(palabras);

	        if (palabra != null) {
	            juego.inicioAhorcado(palabra, sc);
	        }
	    }

	    // Metodo main
	    public static void main(String[] args) {

	        PruebaJuegoAhorcado prueba = new PruebaJuegoAhorcado();

	        prueba.archivo.verificarArchivo();

	        System.out.println("=================================");
	        System.out.println("   JUEGO DEL AHORCADO EN JAVA");
	        System.out.println("=================================");

	       
	        Jugador jugador = new Jugador();

	        jugador.bienvenida();

	        prueba.menuOpciones();
	    }
}


//=========CLASE OPORTUNIDADES==========
class Oportunidades{
	
	private int intentos;
	private int errores;

	// Constructor
	public Oportunidades() {
	   intentos = 10;
	   errores = 0;
	}

	    // Descontar una oportunidad
	    public void descontarIntento() {
	        intentos--;
	        errores++;
	    }

	    // Obtener los intentos restantes
	    public int getIntentos() {
	        return intentos;
	    }

	    // Obtener errores cometidos
	    public int getErrores() {
	        return errores;
	    }

	    // Verificar si quedan intentos
	    public boolean quedanOportunidades() {
	        return intentos > 0;
	    }
	
}



class ArchivoPalabras {

    private String ruta = "./archivo/palabras.txt";

    // Verificar y crear archivo
    public void verificarArchivo() {

        File carpeta = new File("./archivo");

        if (!carpeta.exists()) {
            carpeta.mkdir();
        }

        File archivo = new File(ruta);

        try {

            if (!archivo.exists()) {
                archivo.createNewFile();
            }

            System.out.println("Archivo verificado correctamente");

        } catch (IOException e) {
            System.out.println("Error al crear el archivo");
        }
    }

    // Leer las palabras del archivo
    public String[] leerPalabras() {

        int contador = 0;

        // Contar palabras
        try {

            BufferedReader br = new BufferedReader(new FileReader(ruta));

            String linea;

            while ((linea = br.readLine()) != null) {

                if (!linea.trim().isEmpty()) {
                    contador++;
                }
            }

            br.close();

        } catch (IOException e) {
            System.out.println("Error al leer el archivo.");
        }

        // Crear arreglo del tamaño exacto
        String[] palabras = new String[contador];

        int posicion = 0;

        // Leer y guardar las palabras
        try {

            BufferedReader br = new BufferedReader(
                    new FileReader(ruta));

            String linea;

            while ((linea = br.readLine()) != null) {

                linea = linea.trim().toUpperCase();

                if (!linea.isEmpty()) {
                    palabras[posicion] = linea;
                    posicion++;
                }
            }

            br.close();

        } catch (IOException e) {
            System.out.println("Error al cargar las palabras.");
        }

        return palabras;
    }

    // Buscar palabra mediante busqueda secuencial
    public boolean buscarPalabra(String palabra) {

        String[] palabras = leerPalabras();

        palabra = palabra.trim().toUpperCase();

        for (int i = 0; i < palabras.length; i++) {

            if (palabras[i].equals(palabra)) {
                return true;
            }
        }

        return false;
    }

    // Agregar una palabra al archivo
    public void cargarPalabras(String palabra) {

        palabra = palabra.trim().toUpperCase();

        if (palabra.isEmpty()) {
            System.out.println("La palabra no puede estar vacia.");
            return;
        }

        if (buscarPalabra(palabra)) {
            System.out.println("La palabra ya existe.");
            return;
        }

        try {

            FileWriter fw = new FileWriter(ruta, true);
            PrintWriter pw = new PrintWriter(fw);

            pw.println(palabra);

            pw.close();
            fw.close();

            // Ordenar despues de agregar
            guardarOrdenadas();

            System.out.println("Palabra guardada correctamente.");

        } catch (IOException e) {
            System.out.println("Error al guardar la palabra.");
        }
    }

    // Ordenar palabras con burbuja
    public void ordenarPalabras(String[] palabras) {

        String auxiliar;

        for (int i = 0; i < palabras.length - 1; i++) {

            for (int j = 0; j < palabras.length - 1 - i; j++) {

                if (palabras[j].compareTo(palabras[j + 1]) > 0) {

                    auxiliar = palabras[j];
                    palabras[j] = palabras[j + 1];
                    palabras[j + 1] = auxiliar;
                }
            }
        }
    }

    // Guardar palabras ordenadas
    public void guardarOrdenadas() {

        String[] palabras = leerPalabras();

        ordenarPalabras(palabras);

        try {

            FileWriter fw = new FileWriter(ruta);
            PrintWriter pw = new PrintWriter(fw);

            for (int i = 0; i < palabras.length; i++) {
                pw.println(palabras[i]);
            }

            pw.close();
            fw.close();

        } catch (IOException e) {
            System.out.println("Error al ordenar el archivo.");
        }
    }

    // Mostrar cantidad de palabras
    public void verificarCantidad() {

        String[] palabras = leerPalabras();

        System.out.println("Palabras cargadas: " + palabras.length);
    }

    // Verificar si el archivo esta vacio
    public boolean estaVacio() {

        String[] palabras = leerPalabras();

        return palabras.length == 0;
    }

    // Vaciar archivo
    public void eliminarArchivo() {

        try {

            FileWriter fw = new FileWriter(ruta);
            PrintWriter pw = new PrintWriter(fw);

            pw.close();
            fw.close();

            System.out.println("Archivo vaciado correctamente");

        } catch (IOException e) {
            System.out.println("Error al vaciar el archivo");
        }
    }
}


//========================Clase Jugador=========================
class Jugador{
	public void bienvenida() {
		System.out.println("=======REGLAS DEL JUEGO========");
		System.out.println("1.Tienes solo 8 oportunidades para adivinar la palabra, pierdes\r\n"
				+ "una oportunidad de adivinar solo cuando ha ingresado una letra\r\n"
				+ "que NO está en la palabra secreta. ");
		System.out.println();
		System.out.println("2.Solo se puede ingresar una letra a la vez (A-Z). ");
		System.out.println("Tendras que adivinar una palabra aleatoria ");
		System.out.println();
		System.out.println("3.El juego terminara cuando adivines la palabra completa\r\n"
				+ "en el número de oportunidades indicado (o menos).");
		System.out.println();
		System.out.println("4.Si el número de oportunidades de adivinar se termina,\r\n"
				+ "PIERDES y se  te deberá revelar la palabra secreta.");
		System.out.println();
	}
}
	

public class Main {

	public static void main(String[] args) {
		Jugador j = new Jugador();
		j.bienvenida();
		PruebaJuegoAhorcado  pja = new PruebaJuegoAhorcado();
		pja.menuOpciones();

	}

}

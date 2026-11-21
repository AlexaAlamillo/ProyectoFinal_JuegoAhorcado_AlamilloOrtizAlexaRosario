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
	
	public void inicioAhorcado() {
		
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
		// TODO Auto-generated method stub

	}

}

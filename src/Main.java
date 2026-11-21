import java.util.Scanner;
//============Clase almacenamientoPila==============
class AlmacenamientoPila{
	 private char[] datos;
	    private int tope;
	    
	
}
//====================CLASE JUEGO AHORCADO=================
class JuegoAhorcado{
	public String [] cargarPalabras (String cadena ){
		return null;
	}
	
	public void elegirPalabras (String [] palabras) {
		
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
class oportunidades{
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

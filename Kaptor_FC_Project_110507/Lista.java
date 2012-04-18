/************************************************************************
				TECNOLÓGICO DE MONTERREY
				CAMPUS ZACATECAS
				
				CLASE: Lista doblemente encadenada
				PROGRMADORES: 
						Víctor Eduardo Cardoso Nungaray			363579
						Agustín Fernando Rumayor Barraza		363592
						Alejandro Padilla Barrios				366712
				EQUIPO: Inteligencia Digital
				PERIODO: Enero-Mayo del 2007
************************************************************************/
public class Lista{
	//Declaracion de objetos
	private Nodo primero, ultimo;
	
	//Constructores
	public Lista(){
		primero = null;
		ultimo = null;
	}
	//Funciones
	public boolean vacia(){//Funcion que regresa "True" si la lista esta vacía
		return primero == null;
	}
	public void insertarInicio(Object elemento){//Inserta un nodo al frente de la lista
		if(vacia())
			primero = ultimo = new Nodo(elemento);
		else{
			primero.setPrev(new Nodo(elemento,primero, true));
			primero = primero.getPrev();
		}
	}
	public void insertarFinal(Object elemento){//Inserta un nodo al final de la lista
		if(vacia())
			primero = ultimo = new Nodo(elemento);
		else{
			ultimo.setNext(new Nodo(elemento, ultimo, false));
			ultimo = ultimo.getNext();
		}			
	}
	public Object eliminarInicio(){//Elimina el primer nodo y devuelve el objeto
		if(vacia()){
			return null;
		}else{
			Object elemento = primero.getItem();
			if(primero == ultimo)
				primero = ultimo = null;
			else
				primero = primero.getNext();
				
			return elemento;
		}
	}
	public Object eliminarFinal(){//Elimina el ultimo nodo y devuelve el objeto
		if(vacia())
			return null;
		else{
			Object elemento = ultimo.getItem();
			if(primero == ultimo)
				primero = ultimo = null;
			else{
				Nodo actual = primero;
				while(actual.getNext()!=ultimo)
					actual = actual.getNext();
				
				ultimo = actual;
				actual.setNext(null);
			}
			return elemento;
		}
	}
	public int length(){//Regresa la longitud de la lista
		int contador = 0;
		if(!vacia()){
			Nodo actual = primero;
			while(actual!=null){
				contador++;
				actual = actual.getNext();
			}
		}
		return contador;
	}
	public Object getIndex(int n){//Devuelve el objeto con el indice indicado
		if(vacia())
			return null;
		else if(n>length())
			return null;
		else{
			Object elemento = null;
			int tmp = 0;
			Nodo actual = primero;
			while(tmp < n+1){
				elemento = actual.getItem();
				tmp++;
				actual = actual.getNext();
			}
			return elemento;
		}
	}
	public void imprimir(){//Imprime la lista
		if(vacia())
			System.out.println("Lista Vacía");
		else{
			System.out.println("Lista: ");
			Nodo actual = primero;
			while(actual!=null){
				System.out.println(actual.getItem().toString()+"-");
				actual = actual.getNext();
			}
			System.out.println("\n");
		}
	}
	public void imprimirInv(){//Imprime la lista alrevés
		if(vacia())
			System.out.println("Lista Vacía");
		else{
			System.out.println("Lista: ");
			Nodo actual = ultimo;
			while(actual!=null){
				System.out.println(actual.getItem().toString()+"-");
				actual = actual.getPrev();
			}
			System.out.println("\n");
		}
	}

}
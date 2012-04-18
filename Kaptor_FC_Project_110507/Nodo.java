/************************************************************************
				TECNOLÓGICO DE MONTERREY
				CAMPUS ZACATECAS
				
				CLASE: Nodo
				PROGRMADORES: 
						Víctor Eduardo Cardoso Nungaray			363579
						Agustín Fernando Rumayor Barraza		363592
						Alejandro Padilla Barrios				366712
				EQUIPO: Inteligencia Digital
				PERIODO: Enero-Mayo del 2007
************************************************************************/
public class Nodo{
	//Se declaran las variable y los objetos
	private Object dato;
	private Nodo sig, ant;
	
	//Constructores
	public Nodo(){
		dato = null;
		sig=null;
		ant=null;
	}
	public Nodo(Object objeto){
		dato = objeto;
	}
	public Nodo(Object objeto, Nodo node, boolean b){
		dato = objeto;
		if(b)
			sig = node;//sig apunta al Nodo si 'b' es 'true'
		else
			ant = node;//ant apunta al Nodo si 'b' es 'false'
	}
	//funciones
	public Object getItem(){//devuelve el objeto
		return dato;
	}
	public Nodo getNext(){//Devuelve el apuntador siguiente
		return sig;
	}
	public Nodo getPrev(){//Devuelve el apuntador anterior
		return ant;
	}
	public void setNext(Nodo x){//Inserta un nuevo nodo adelante
		sig=x;
	}
	public void setPrev(Nodo x){//Inserta un nuevo nodo atrás
		ant=x;
	}
	public void setItem(Object objeto){//Inserta un objeto nuevo
		dato = objeto;
	}
}//Fin del la clase Nodo
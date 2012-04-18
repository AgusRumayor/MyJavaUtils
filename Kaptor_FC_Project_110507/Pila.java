/************************************************************************
				TECNOLÓGICO DE MONTERREY
				CAMPUS ZACATECAS
				
				CLASE: Pila
				PROGRMADORES: 
						Víctor Eduardo Cardoso Nungaray			363579
						Agustín Fernando Rumayor Barraza		363592
						Alejandro Padilla Barrios				366712
				EQUIPO: Inteligencia Digital
				PERIODO: Enero-Mayo del 2007
************************************************************************/
public class Pila extends Lista{//Hereda de la clase Lista
	//Constructor
	public Pila(){}
	//Funciones
	public void push(Object objeto){//Agrega un objeto a la pila
		insertarInicio(objeto);
	}
	public Object pop(){//Sacar y eliminar objeto de la pila
		return eliminarInicio();
	}
}//Fin de la clase Pila
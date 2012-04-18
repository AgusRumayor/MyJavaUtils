/************************************************************************
				TECNOL�GICO DE MONTERREY
				CAMPUS ZACATECAS
				
				CLASE: Graficas
				PROGRMADORES: 
						V�ctor Eduardo Cardoso Nungaray			363579
						Agust�n Fernando Rumayor Barraza		363592
						Alejandro Padilla Barrios				366712
				EQUIPO: Inteligencia Digital
				PERIODO: Enero-Mayo del 2007
************************************************************************/
//Librer�as
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

//Clase
public class Graficas extends JComponent{
	private int espacioMem, X,Y,W,H, numDatos, operacion, grafica;
	private float espacio, numMayor, grueso, numMenor;
	private double temporal1, temporal2;
	private Lista Memoria;
	private String titulo;
	//Constructor
	public Graficas(){
		/**    TIPO      GRAFICA
		* 			1			Barras
		* 			2			Poligonal
		* 			3			Pastel
		* 			4			Datos			*/
		Memoria = new Lista();	
		X = 130;
		Y = 50;
		W = 540;
		H = 300;
		grafica = 1;
		titulo = "";
	}
	/*************FUNCIONES**************************************/
	//Funci�n que dibuja la Pantalla de Gr�ficas
	public void setTipo(int n){
		grafica = n;
	}
	public void setTitle(String s){
		titulo = s;
	}
	
	//Entrada: Ninguna
	/*Proceso: Dibuja un rectangulo Negro, luego dibuja un rectangulo un poco m�s peque�o color blanco
	 		  centrado dentro del negro, y abajo escribe el nombre del programador en Negro
			  y una advertencia en gris. Todo se hace seg�n los parametros X(coordenada c),
			  Y(coordenada y), W(ancho) y H(alto)*/
	public void dibujarPantalla(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.black);
		g2.fillRect(X-3,Y-3,W+6,H+6);
		g2.setColor(Color.white);
		g2.fillRect(X,Y,W,H);
		g2.setColor(Color.black);
		g2.drawString("Kaptor Flow Chart",X+230,Y+315);
		g2.setColor(Color.gray);
		g2.drawString("Programado por Inteligencia Digital", X+230, Y+328);
	}//Termina Funci�n de dibujar pantalla

	//Funci�n que mete a la memoria un n�mero(mutador)
	//Entrada: El n�mero de pantalla
	/*Proceso: pasa el n�mero que estaba en la pantalla al espacio de memoria 
				  correspondiente y aumenta el �ndice de memoria.*/
	//Salida: Ninguna
	public void MS(double numPant){
		double x = numPant;
		Memoria.insertarFinal(""+x);
	}//Termina funci�n MS
	
	//Funcion que limpia la memoria borrando todo (mutador)
	//Entrada: Ninguna
	//Proceso: Pone un String vac�o en todos los espacios de memoria y deja el �ndice en cero
	//Salida: Nunguna
	public void MC(){
		for(int i=0;i<Memoria.length();i++){
			Memoria.eliminarFinal();
		}
		espacioMem = 0;
	}//Termina funci�n MC
	
	//Funci�n que regresa el ultimo n�mero almacenado dejandolo en memoria
	//Entrada: Ninguna
	//Proceso: Convierte a String el �ltimo n�mero almacenado en memoria
	//Salida: El String
	public String MT(){
		String s = ""+Memoria.getIndex(espacioMem-1);
		return s;
	}//Fin de funci�n MT
	
	//Funci�n que regresa el �ltimo n�mero almacenado eliminandolo de la memoria
	//Entrada: Ninguna
	/*Proceso: Convierte a String el �ltimo n�mero almacenado en memoria, lo borra del vector
		 		  y resta 1 al �ndice de memoria*/
	//Salida: El string
	public String MR(){
		String s = ""+Memoria.getIndex(espacioMem-1);
		espacioMem--;
		Memoria.eliminarFinal();
		return s;
	}//Fin de metodo MR

	//Funci�n que suma los n�meros almacenados en memoria
	//Entrada: Ninguna
	//Proceso: Suma todos los valores almacenados en memoria
	//Salida: El resultado de la suma
	public double mSuma(){
		double y = 0;
		for(int i=0;i<Memoria.length();i++){
			double x = Double.parseDouble((String)Memoria.getIndex(i));
			y += x;
		}
		return y;
	}//Fin de funci�n mSuma
	
	//Funci�n que saca el Promedio de los n�meros almacenados en Memoria
	//Entrada: Ninguna
	//Proceso: Realiza la suma de los valores en memoria y los divide entre los que son
	//Salida: El resultado de la divisi�n
	public double mPromedio(){
		double x = mSuma()/Memoria.length();
		return x;
	}//Fin de funci�n mPromedio
	
	//Funci�n que obtiene datos de la gr�fica
	//Entrada: Ninguna
	//Proceso: Calcula el total de datos en memoria, el n�mero mayor y el n�mero menor
	//Salida: Ninguna
	public void datosGrf(){
		numDatos = Memoria.length(); //Calculo el numero de datos a graficar
		numMayor = Float.parseFloat((String)Memoria.getIndex(0));//Calcula el numero mayor de Memoria
		numMenor = Float.parseFloat((String)Memoria.getIndex(0));//Calcula el numero menor de Memoria
		for(int i=0;i<numDatos;i++){
			float nx = Float.parseFloat((String)Memoria.getIndex(i));
			if(nx > numMayor){
				numMayor = nx;
			}
			float ny = Float.parseFloat((String)Memoria.getIndex(i));
			if(ny < numMenor){
				numMenor = ny;
			}
		}		
	}//Fin de funci�n datosGrf
	
	//Funci�n que calcula el porcentaje X respecto a Y
	//Entrada: el n�mero que queremos en porcentaje (x) y el n�mero que ser� 100% (y)
	//Proceso: Calcula el porcentaje de "x" respecto a "y" [x*100/y]
	//Salida: El resultado de la operaci�n
	//NOTA: Esta funci�n la desarrolle para calcular posiciones de la gr�fica
	public float getPorcentaje(float x, float y){
		float result = (x*100)/y;
		return result;
	}//Fin de funci�n getPorcentaje
	
	//Funci�n que ingresa un porcentaje (x) y te devuelve el valor respecto a y
	//Entrada: Un porcentaje (x) y un 100% en n�mero (y)
	//Proceso: Te devuelve el n�mero correspondiente al porcentaje (x) respecto a "y"
	//NOTA: Esta funci�n fue creada para calcular posiciones de gr�ficas
	public float getNumPorcent(float x, float y){
		float result = (y/100)*x;
		return result;
	}//Fin de funci�n getNumPorcent
	
	//Funci�n que deja un n�mero de alta presici�n con s�lo dos decimales
	//Entrada: Un n�mero
	/*Proceso: Primero convierte el n�mero a String, despu�s busca el punto en ese String, 
				  lo recorta dejandolo solamente con dos decimales
		 		  */
	//Salida: Un String
	/*NOTA: Esta funci�n fue creada para evitar el desbordamiento del String en la
		 	  pantalla al ser dibujado*/
	public String numSimple(double x){
		String s = ""+x;
		int numLetras = s.length();
		String num = "";
		int decimales = 0;
		String punto = "";
		for(int f=0;f<numLetras;f++){
			String st = ""+s.charAt(f);
			num += st;
			if(st.equals("."))
				punto = ".";
			if(punto.equals("."))
				decimales++;
			if(decimales > 2)
				break;
		}
		return num;
	}//Fin de funci�n numSimple
	
	//Funci�n que crea una gr�fica de barras
	//Entrada: El objeto graphics
	/*Proceso: Dibuja una gr�fica de barras, seg�n su porcentaje, tomando como 100% el n�mero mayor,
				  divide el espacio disponible entre el n�mero de datos para acomodar. E imprimiendo 
		 		  el numero mayor, el menor y la media.*/
	//Salida: Ninguna
	public void graficaBarras(Graphics g){
		datosGrf();
		g.setColor(Color.gray);//Comienzo a dibujar interfaz de gr�fica de barras
		g.drawLine(X+40,Y+20,X+40,Y+280);
		g.drawLine(X+40,Y+280,X+530,Y+280);
		g.drawString("0%",X+13,Y+280);
		g.drawString("10%",X+9,Y+254);
		g.drawString("20%",X+9,Y+228);
		g.drawString("30%",X+9,Y+202);
		g.drawString("40%",X+9,Y+176);
		g.drawString("50%",X+9,Y+150);
		g.drawString("60%",X+9,Y+124);
		g.drawString("70%",X+9,Y+98);
		g.drawString("80%",X+9,Y+72);
		g.drawString("90%",X+9,Y+46);
		g.drawString("100%",X+4,Y+20);//Termino de dibujar interfaz de gr�fica de barras
		
		espacio =(60/numDatos);//Comienzo a dibujar la gr�fica
		grueso =(490/numDatos)-(espacio);	
		for(int i=0; i<numDatos; i++){
			float porcent = getPorcentaje(Float.parseFloat((String)Memoria.getIndex(i)),numMayor);
			float num = getNumPorcent(porcent,260);
			float xf = X+40+(espacio)*i+i*grueso+(espacio/2);
			float yf = Y+280-num;
			int x1 = (int) xf;
			int y1 = (int) yf;
			int x2 = (int) grueso;
			int y2 = (int) num;
			g.setColor(Color.pink);
			g.fillRect(x1,y1,x2,y2);
			g.setColor(Color.gray);
			g.drawString(""+numSimple(Double.parseDouble((String)Memoria.getIndex(i))),x1+((int)grueso/4), Y+270);
		}
		g.drawString("100% = "+numSimple(numMayor),X+400,Y+30);
		g.drawString(numSimple(getPorcentaje(numMenor,numMayor))+"% = "+numSimple(numMenor),X+400,Y+45);
		g.drawString("Media: "+numSimple(mPromedio()),X+400,Y+60);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Courier New",Font.BOLD+Font.ITALIC,20));
		g.drawString(""+titulo,X+50,Y+25);
	}//Termina Funci�n de dibujar gr�fica de barras
	
	//Funci�n que dibuja una gr�fica de pastel
	//Entrada: El objeto graphics
	/*Proceso: Dibuja una gr�fica de pastel, seg�n su porcentaje, tomando como 100% la suma de todos
				  los datos del vector Memoria, que a su vez corresponde a 360�, saca el porcentaje 
				  correspondiente de cada dato, lo convierte a su proporci�n en grados y lo grafica,
				  imprimiendo el n�mero mayor, el menor y la media.*/
	//Salida: Ninguna
	public void graficaPastel(Graphics g){
		datosGrf();
		int radio = 270;
		int centroX = X+(W/2)-(radio/2);
		int centroY = Y+(H/2)-(radio/2);
		float tetaI = 0;
		float tetaF = 0;
		float porcent;
		for(int i=0;i<numDatos;i++){
			if(i%3 == 0)
				g.setColor(Color.yellow);
			else if(i%3 == 1)
				g.setColor(Color.gray);
			else if(i%3 == 2)
				g.setColor(Color.orange);			
			float porcentP = getPorcentaje(Float.parseFloat((String)Memoria.getIndex(i)),(float)mSuma());
			tetaF = getNumPorcent(porcentP,360);
			int af = (int) tetaF;
			int ai = (int) tetaI;
			g.fillArc(centroX,centroY,radio,radio,ai,af);
			g.setColor(Color.gray);
			g.drawArc(centroX,centroY,radio,radio,ai,af);
			g.drawLine(centroX+(radio/2),centroY+(radio/2),centroX+radio,centroY+(radio/2));
			tetaI += tetaF;
			if(numDatos<14)
				g.drawString("N�m"+(i+1)+" : "+numSimple(Double.parseDouble((String)Memoria.getIndex(i))),X+50,Y+30+20*i);
		}
		g.drawString(""+numSimple(getPorcentaje(numMayor,Float.parseFloat(""+mSuma())))+"% = "+numSimple(numMayor),X+400,Y+30);
		g.drawString(numSimple(getPorcentaje(numMenor,Float.parseFloat(""+mSuma())))+"% = "+numSimple(numMenor),X+400,Y+45);
		g.drawString("Media: "+numSimple(mPromedio()),X+400,Y+60);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Courier New",Font.BOLD+Font.ITALIC,20));
		g.drawString(""+titulo,X+35,Y+25);
	}//Termina Funci�n de dibujar grafica de pastel
	
	//Funci�n que dibuja gr�fica poligonal
	//Entrada: El objeto graphics
	/*Proceso: Dibuja una gr�fica poligonal, seg�n su porcentaje, tomando como 100% el n�mero mayor,
				  divide el espacio disponible entre el n�mero de datos para acomodar. E imprime 
		 		  el numero mayor, el menor y la media.*/
	//Salida: Ninguna
	public void graficaPoligonal(Graphics g){
		datosGrf();
		g.setColor(Color.gray);//Comienzo a dibujar interfaz de gr�fica poligonal
		g.drawLine(X+40,Y+20,X+40,Y+280);
		g.drawLine(X+40,Y+280,X+530,Y+280);
		g.drawString("0%",X+13,Y+280);
		g.drawString("10%",X+9,Y+254);
		g.drawString("20%",X+9,Y+228);
		g.drawString("30%",X+9,Y+202);
		g.drawString("40%",X+9,Y+176);
		g.drawString("50%",X+9,Y+150);
		g.drawString("60%",X+9,Y+124);
		g.drawString("70%",X+9,Y+98);
		g.drawString("80%",X+9,Y+72);
		g.drawString("90%",X+9,Y+46);
		g.drawString("100%",X+4,Y+20);//Termino de dibujar interfaz de gr�fica poligonal
		float separacion = 490/numDatos;
		int Xpol1 = X+40;
		int Ypol1 = Y+280;
		if(numMayor != 0){
			for(int i=0;i<numDatos;i++){
				float porcentPol = getPorcentaje(Float.parseFloat((String)Memoria.getIndex(i)),numMayor);
				float numPol = getNumPorcent(porcentPol,260);
				float Xpol = X+40+separacion+separacion*i;
				float Ypol = Y+280-numPol;
				int Xpol2 = (int)Xpol;
				int Ypol2 = (int)Ypol;
				g.setColor(Color.green);
				g.drawLine(Xpol1,Ypol1,Xpol2,Ypol2);
				Xpol1 = Xpol2;
				Ypol1 = Ypol2;
				g.setColor(Color.gray);
				g.drawString(""+numSimple(Double.parseDouble((String)Memoria.getIndex(i))),Xpol2-15,Ypol2-1);
			}
		}
		g.drawString("100% = "+numSimple(numMayor),X+50,Y+295);
		g.drawString(numSimple(getPorcentaje(numMenor,numMayor))+"% = "+numSimple(numMenor),X+210,Y+295);
		g.drawString("Media: "+numSimple(mPromedio()),X+360,Y+295);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Courier New",Font.BOLD+Font.ITALIC,20));
		g.drawString(""+titulo,X+50,Y+25);
	}//Termina Funci�n de dibujar grafica poligonal
	
	//Funci�n que crea despliega los datos
	//Entrada: El objeto graphics
	//Salida: Ninguna
	public void graficaDatos(Graphics g){
		datosGrf();
		g.setColor(Color.GRAY);
		g.setFont(new Font("Arial",Font.PLAIN,16));
		g.drawString("N�mero de datos: "+numDatos,X+40,Y+60);
		g.drawString("N�mero mayor: "+numDatos,X+40,Y+80);
		g.drawString("N�mero menor: "+numDatos,X+40,Y+100);
		g.drawString("Promedio: "+mPromedio(),X+40,Y+120);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Courier New",Font.BOLD+Font.ITALIC,20));
		g.drawString(""+titulo,X+50,Y+25);
	}//Termina Funci�n de dibujar gr�fica de barras


	public void graficar(int tipo){
		grafica = tipo;
		repaint();
	}

	public void paint(Graphics g){
		dibujarPantalla(g);
		if(!Memoria.vacia()){
			switch(grafica){
				case 1: graficaBarras(g);
				break;
				case 2: graficaPoligonal(g);
				break;
				case 3: graficaPastel(g);
				break;
				case 4: graficaDatos(g);
			}
		}
	}
}//Fin de la clase Graficas
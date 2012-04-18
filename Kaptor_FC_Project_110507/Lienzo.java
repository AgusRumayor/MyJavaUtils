/************************************************************************
				TECNOLÓGICO DE MONTERREY
				CAMPUS ZACATECAS
				
				CLASE: Lienzo
				PROGRMADORES: 
						Víctor Eduardo Cardoso Nungaray			363579
						Agustín Fernando Rumayor Barraza		363592
						Alejandro Padilla Barrios				366712
				EQUIPO: Inteligencia Digital
				PERIODO: Enero-Mayo del 2007
************************************************************************/
//Librerías
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class Lienzo extends Canvas{
	//Declaración de variables y objetos
	public Vector Tipo;
	private int widthDesign;/*************************new*****************************/
	private int heightDesign;/***************************new**************************/
	//Constructor
	public Lienzo() {
		Tipo = new Vector();
		setSize(5000,5000);
		setBackground(new Color(245,250,255));
		/****************NOTA*************
			Cada espacio del vector Tipo
			contiene otro vector con las 
			8 posibles propiedades de
			cada figura
		**********************************/
 	}//Fin del constructor
	
	//Funcion que guarda el vector de dibujo 15/03/07  Agus
	public void GuardaVec(Vector aux){
    	PrintWriter arch;
	   DataOutputStream arch1;
			try{
				arch= new PrintWriter(new FileWriter("ArchivosGuardados/"+"Dibujo"+".txt"));
				arch1= new DataOutputStream(new FileOutputStream("ArchivosGuardados/"+"Dibujo"+".bin"));
				for(int i=0;i<aux.size();i++)
				{
					arch.println(aux.get(i));
					arch1.writeUTF(""+i);
				}
				arch.close();
				arch1.close();
			}
			catch(Exception a){
				return ;
			}
	}
	public void G(){
		GuardaVec(Tipo);
	}
	
	//Funcion para leer los archivos Agus 15/03/07
	
	public Vector Leer(){
		BufferedReader arch;
		DataInputStream arch1;
		Vector aux;
		aux= new Vector();
		try{
			int i=0;
			arch= new BufferedReader(new FileReader("ArchivosGuardados/"+"Dibujo"+".txt"));
			//arch1= new DataInputStream(new FileInputStream("ArchivosGuardados/"+"Dibujo"+".bin"));
			do{
				aux.addElement(arch.readLine());
				System.out.println(aux.get(i));
				i++;
			}
			while(arch.readLine()!=null);
			arch.close();
			return aux;
		}
		catch(NullPointerException npe)
			{
			}
			catch(FileNotFoundException fnfe){
			return null;
			}
			catch(IOException ie)
			{
			}
			return aux;
	}
	
	//Metodo que dibuja un proceso del diagrama de flujo y lo almacena en memoria
	public void setDesign(int tipo, int x, int y, String s, Color c1, Color c2,int grueso, Font f){/*********************new******************/
		Vector v = new Vector();
		v.addElement(new Integer(tipo));
		v.addElement(new Integer(x));
		v.addElement(new Integer(y));
		v.addElement(s);
		v.addElement(c1);
		v.addElement(c2);
		v.addElement(new Integer(grueso));
		v.addElement(f);
		Tipo.addElement(v);	
		repaint();
	}
	public void setCiclo(int tipo, int x, int y, String s1, String s2, String s3, Color c1, Color c2,int grueso, Font f){
		Vector v = new Vector();
		v.addElement(new Integer(tipo));
		v.addElement(new Integer(x));
		v.addElement(new Integer(y));
		v.addElement(s1);
		v.addElement(s2);
		v.addElement(s3);
		v.addElement(c1);
		v.addElement(c2);
		v.addElement(new Integer(grueso));
		v.addElement(f);
		Tipo.addElement(v);	
		repaint();
	}
		
	public void setLine(int x, int y, int xlast, int ylast, Color c2, int grueso){
		Vector v2 = new Vector();
		v2.addElement(new Integer(9));
		v2.addElement(new Integer(xlast));
		v2.addElement(new Integer(ylast));
		v2.addElement(new Integer(x));
		v2.addElement(new Integer(y));
		v2.addElement(c2);
		v2.addElement(new Integer(grueso));
		Tipo.addElement(v2);
		repaint();
	}
	//Función que dibuja un proceso
	public void proceso(int x, int y,String s, Graphics g, Color c1, Color c2,int grueso,Font f){
		Graphics2D g2 = (Graphics2D)g;
		int w,h,sizeX, sizeY;
		sizeX = (f.getSize()*100)/150;//Tamaño de la letra en X*********
		sizeY = f.getSize();//Tamaño de la letra en Y
		h=(sizeY*150)/100;
		w= (s.length()*sizeX*115)/100;
		if(w<50)w=50;
		x = x-w/2;
		g2.setStroke(new BasicStroke(grueso,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
		g2.setFont(f);
		g2.setColor(c1);
		g2.fill(new Rectangle2D.Double(x,y,w,h));
		g2.setColor(c2);
		g2.draw(new Rectangle2D.Double(x,y,w,h));
		g2.drawString(s,x+w/2-(s.length()*sizeX)/2,y+3*h/4);
		widthDesign = w;/*************************new*****************************/
		heightDesign = h;/***************************new**************************/
	}//Fin de función proceso
		//Función que dibuja una desición
	public void decision(int x, int y,String s, Graphics g, Color c1, Color c2,int grueso,Font f){
		Graphics2D g2 = (Graphics2D)g;
		int w,h,sizeX, sizeY;
		sizeX = (f.getSize()*100)/160;//Tamaño de la letra en X
		sizeY = f.getSize();//Tamaño de la letra en Y
		w= (s.length()*sizeX*115)/100;
		if(w<50)w=50;
		int slength = s.length();
		if(slength<3)slength=3;
		h=(sizeY*150)*slength/600;
		x = x;
		g2.setStroke(new BasicStroke(grueso,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
		g2.setFont(f);
		g2.setColor(c1);
		GeneralPath rombo = new GeneralPath();
		rombo.moveTo(x,y);
		rombo.lineTo(x-w/2,y+h);
		rombo.lineTo(x,y+2*h);
		rombo.lineTo(x+w/2,y+h);
		rombo.closePath();
		g2.fill(rombo);
		g2.setColor(c2);
		g2.drawLine(x,y,x-w/2,y+h);
		g2.drawLine(x-w/2,y+h,x,y+2*h);
		g2.drawLine(x,y+2*h,x+w/2,y+h);
		g2.drawLine(x+w/2,y+h,x,y);
		g2.setColor(c2);
		g2.drawString(s,x-(s.length()*sizeX)/2,y+h+sizeY/2);
		widthDesign = w;/*************************new*****************************/
		heightDesign = h;/***************************new**************************/
	}//Fin de función desición
	
	//Función que dibuja una Entrada_salida de datos
	public void in_out(int x, int y,String s, Graphics g, Color c1, Color c2,int grueso,Font f){
		Graphics2D g2 = (Graphics2D)g;
		int w,h,sizeX, sizeY;
		sizeX = (f.getSize()*100)/160;//Tamaño de la letra en X
		sizeY = f.getSize();//Tamaño de la letra en Y
		w= (s.length()*sizeX*115)/100;
		if(w<50)w=50;
		h=(sizeY*150)/100;
		g2.setStroke(new BasicStroke(grueso,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
		g2.setFont(f);
		g2.setColor(c1);
		GeneralPath rombo = new GeneralPath();
		int sesgo = 8;
		rombo.moveTo(x+w/2+sesgo,y);
		rombo.lineTo(x-w/2,y);
		rombo.lineTo(x-w/2-sesgo,y+h);
		rombo.lineTo(x+w/2,y+h);
		rombo.closePath();
		g2.fill(rombo);
		g2.setColor(Color.BLACK);
		g2.drawLine(x+w/2+sesgo,y,x-w/2,y);
		g2.drawLine(x-w/2,y,x-w/2-sesgo,y+h);
		g2.drawLine(x-w/2-sesgo,y+h,x+w/2,y+h);
		g2.drawLine(x+w/2,y+h,x+w/2+sesgo,y);
		g2.setColor(c2);
		g2.drawString(s,x-(s.length()*sizeX)/2,y+3*h/4);
		widthDesign = w;/*************************new*****************************/
		heightDesign = h;/***************************new**************************/
	}//Fin de función Entrada_Salida

	
	//Función que dibuja Inicio
	public void inicio_fin(int x, int y,String s, Graphics g, Color c1, Color c2,int grueso,Font f){
		Graphics2D g2 = (Graphics2D)g;
		int w,h,sizeX, sizeY;
		sizeX = (f.getSize()*100)/160;//Tamaño de la letra en X
		sizeY = f.getSize();//Tamaño de la letra en Y
		w= (s.length()*sizeX*115)/100;
		if(w<50)w=50;
		h=(sizeY*150)/100;
		x = x-w/2;
		g2.setStroke(new BasicStroke(grueso,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
		g2.setFont(f);
		g2.setColor(c1);
		g2.fill(new RoundRectangle2D.Double(x,y,w,h,50,50));
		g2.setColor(c2);
		g2.draw(new RoundRectangle2D.Double(x,y,w,h,50,50));
		g2.drawString(s,x+w/2-(s.length()*sizeX)/2,y+3*h/4);
		widthDesign = w;/*************************new*****************************/
		heightDesign = h;/***************************new**************************/
	}//Fin de función Inicio
	
	//Función que dibuja imprimir
	public void imprimir(int x, int y,String s, Graphics g, Color c1, Color c2,int grueso,Font f){
		Graphics2D g2 = (Graphics2D)g;
		int w,h,sizeX, sizeY;
		sizeX = (f.getSize()*100)/160;//Tamaño de la letra en X
		sizeY = f.getSize();//Tamaño de la letra en Y
		w= (s.length()*sizeX*115)/100;
		if(w<50)w=50;
		h=(sizeY*240)/100;
		x = x-w/2;
		g2.setStroke(new BasicStroke(grueso,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
		g2.setFont(f);
		g2.setColor(c1);
		GeneralPath forma = new GeneralPath();
		forma.moveTo(x,y);
		forma.lineTo(x,y+h);
		forma.curveTo(x+w/4,y+h+30,x+w*3/4,y+h-20,x+w,y+h);
		forma.lineTo(x+w,y);
		forma.closePath();
		g2.fill(forma);
		g2.setColor(Color.BLACK);
		g2.drawLine(x,y,x,y+h);
		g2.draw(new CubicCurve2D.Double(x,y+h,x+w/4,y+h+30,x+w*3/4,y+h-20,x+w,y+h));
		g2.drawLine(x+w,y+h,x+w,y);
		g2.drawLine(x+w,y,x,y);
		g2.setColor(c2);
		g2.drawString(s,x+w/2-(s.length()*sizeX)/2,y+sizeY*3/2);
		widthDesign = w;/*************************new*****************************/
		heightDesign = h;/***************************new**************************/
	}//Fin de función imprimir
	//Función que dibuja un ciclo
	public void ciclo(int x, int y,String s1,String s2, String s3, Graphics g, Color c1, Color c2,int grueso,Font f){
		int largo = s1.length();
		if(largo < s2.length())largo = s2.length();
		if(largo < s3.length())largo = s3.length();
		Graphics2D g2 = (Graphics2D)g;
		int w,h,sizeX, sizeY;
		sizeX = (f.getSize()*100)/160;//Tamaño de la letra en X
		sizeY = f.getSize();//Tamaño de la letra en Y
		w= (largo*sizeX*250)/100;
		if(w<50)w=50;
		h=(sizeY*550)/100;
		x = x-w/4;
		g2.setStroke(new BasicStroke(grueso,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
		g2.setFont(f);
		g2.setColor(c1);
		g2.fill(new Rectangle2D.Double(x,y,w,h));
		g2.setColor(c2);
		g2.draw(new Rectangle2D.Double(x,y,w,h));
		g2.draw(new Line2D.Double(x+w,y,x,y+h/2));
		g2.draw(new Line2D.Double(x,y+h/2,x+w,y+h));
		g2.drawString(s1,x+(s2.length()*sizeX)/4,y+h/3-sizeY/3);
		g2.drawString(s2,x+w-(s2.length()*sizeX),y+2*h/3-sizeY/3);
		g2.drawString(s3,x+(s2.length()*sizeX)/4,y+h-sizeY/3);
		widthDesign = w;/*************************new*****************************/
		heightDesign = h;/***************************new**************************/
	}//Fin de función ciclo
	
	/*****************************NEW CODE BLOCK***************************************/
	//Función que dibuja una linea de union
	public void linea(int x, int y, int x2, int y2, Color c1, int grueso, Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(c1);
		g2.setStroke(new BasicStroke(grueso,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
		g2.drawLine(x2,y2,x,y);
	}//Fin de función una linea de union
	//Metodo que devuelve la variable width de la ultima figura
	public int getWidth(){
		return widthDesign;
	}//Fin del metodo getWidth();
	
	//Metodo que devuelve la variable height de la ultima figura
	public int getHeight(){
		return heightDesign;
	}//Fin del metodo getHeight
	/*******************************END NEW CODE BLOCK*******************************/
	
	//Metodo paint
	public void paint(Graphics g){//Se puede utilizar el metodo paintComponent
		g.setColor(new Color(246,246,255));
		for(int i=0;i<Tipo.size();i++){
			Vector v2 = new Vector();
			v2 = (Vector)Tipo.elementAt(i);
			if(((Integer)v2.elementAt(0)).intValue()==0){//TIPO=0 Es Inicio		
				inicio_fin(((Integer)v2.elementAt(1)).intValue(),//Posicion en X
						((Integer)v2.elementAt(2)).intValue(),//Posicion en Y
						(String)v2.elementAt(3),g,//String y Graphics
						(Color)v2.elementAt(4),//Color1
						(Color)v2.elementAt(5),//Color 2
						((Integer)v2.elementAt(6)).intValue(),//Grueso de bordes
						(Font)v2.elementAt(7)//Fuente
				);
			}else if(((Integer)v2.elementAt(0)).intValue()==1){//TIPO=1 es una entrada
				in_out(((Integer)v2.elementAt(1)).intValue(),//Posicion en X
						((Integer)v2.elementAt(2)).intValue(),//Posicion en Y
						(String)v2.elementAt(3),g,//String y Graphics
						(Color)v2.elementAt(4),//Color1
						(Color)v2.elementAt(5),//Color 2
						((Integer)v2.elementAt(6)).intValue(),//Grueso de bordes
						(Font)v2.elementAt(7)//Fuente
				);
			}else if(((Integer)v2.elementAt(0)).intValue()==2){//TIPO=2 es un proceso
				proceso(((Integer)v2.elementAt(1)).intValue(),//Posicion en X
						((Integer)v2.elementAt(2)).intValue(),//Posicion en Y
						(String)v2.elementAt(3),g,//String y Graphics
						(Color)v2.elementAt(4),//Color1
						(Color)v2.elementAt(5),//Color 2
						((Integer)v2.elementAt(6)).intValue(),//Grueso de bordes
						(Font)v2.elementAt(7)//Fuente
				);
			}else if(((Integer)v2.elementAt(0)).intValue()==3){//TIPO=3 es una decisión
				decision(((Integer)v2.elementAt(1)).intValue(),//Posicion en X
						((Integer)v2.elementAt(2)).intValue(),//Posicion en Y
						(String)v2.elementAt(3),g,//String y Graphics
						(Color)v2.elementAt(4),//Color1
						(Color)v2.elementAt(5),//Color 2
						((Integer)v2.elementAt(6)).intValue(),//Grueso de bordes
						(Font)v2.elementAt(7)//Fuente
				);
			}else if(((Integer)v2.elementAt(0)).intValue()==4){//TIPO=4 es una desicion de dos caminos
				decision(((Integer)v2.elementAt(1)).intValue(),//Posicion en X
						((Integer)v2.elementAt(2)).intValue(),//Posicion en Y
						(String)v2.elementAt(3),g,//String y Graphics
						(Color)v2.elementAt(4),//Color1
						(Color)v2.elementAt(5),//Color 2
						((Integer)v2.elementAt(6)).intValue(),//Grueso de bordes
						(Font)v2.elementAt(7)//Fuente
				);
			}else if(((Integer)v2.elementAt(0)).intValue()==5){//TIPO=5 es un ciclo
				/*Este almacena dos valores más en el vector, porque a diferencia de los
			  demás, este cuenta con tres String*/
			  ciclo(((Integer)v2.elementAt(1)).intValue(),//Posicion en X
						((Integer)v2.elementAt(2)).intValue(),//Posicion en Y
						(String)v2.elementAt(3),//Primer String
						(String)v2.elementAt(4),//Segundo String
						(String)v2.elementAt(5),g,//Tercer String y Graphics
						(Color)v2.elementAt(6),//Color1
						(Color)v2.elementAt(7),//Color 2
						((Integer)v2.elementAt(8)).intValue(),//Grueso de bordes
						(Font)v2.elementAt(9)//Fuente
				);
			}else if(((Integer)v2.elementAt(0)).intValue()==6){//TIPO=6 es un do...while
				decision(((Integer)v2.elementAt(1)).intValue(),//Posicion en X
						((Integer)v2.elementAt(2)).intValue(),//Posicion en Y
						(String)v2.elementAt(3),g,//String y Graphics
						(Color)v2.elementAt(4),//Color1
						(Color)v2.elementAt(5),//Color 2
						((Integer)v2.elementAt(6)).intValue(),//Grueso de bordes
						(Font)v2.elementAt(7)//Fuente
				);
			}else if(((Integer)v2.elementAt(0)).intValue()==7){//TIPO=7 es un while
				decision(((Integer)v2.elementAt(1)).intValue(),//Posicion en X
						((Integer)v2.elementAt(2)).intValue(),//Posicion en Y
						(String)v2.elementAt(3),g,//String y Graphics
						(Color)v2.elementAt(4),//Color1
						(Color)v2.elementAt(5),//Color 2
						((Integer)v2.elementAt(6)).intValue(),//Grueso de bordes
						(Font)v2.elementAt(7)//Fuente
				);
			}else if(((Integer)v2.elementAt(0)).intValue()==8){//TIPO=8 es imprimir
				imprimir(((Integer)v2.elementAt(1)).intValue(),//Posicion en X
						((Integer)v2.elementAt(2)).intValue(),//Posicion en Y
						(String)v2.elementAt(3),g,//String y Graphics
						(Color)v2.elementAt(4),//Color1
						(Color)v2.elementAt(5),//Color 2
						((Integer)v2.elementAt(6)).intValue(),//Grueso de bordes
						(Font)v2.elementAt(7)//Fuente
				);
			/*************************NEW CODE BLOCK**************************************/
			}else if(((Integer)v2.elementAt(0)).intValue()==9){//TIPO=9 es una linea de unión
				linea(((Integer)v2.elementAt(1)).intValue(),//primer Posicion en X
						((Integer)v2.elementAt(2)).intValue(),//primer Posicion en Y
						((Integer)v2.elementAt(3)).intValue(),//segunda Posicion en X
						((Integer)v2.elementAt(4)).intValue(),//segunda Posicion en Y
						(Color)v2.elementAt(5),//Color
						((Integer)v2.elementAt(6)).intValue(),g//Grueso de bordes
				);
			}
			/**************************************END NEW CODE BLOCK*************************/
		}
	}//Fin del metodo paint
}
/***************************************************************************
RESTRICCIONES:
	
		  	> El espacio que se muestra en la pantalla es de 390(eje x)
		por 415 (eje y).
****************************************************************************/
/**********************************NEW COMENTS**************************************/
/*__________________________________________________________________________

					TABLA DE TIPO DE SIMBOLOS
____________________________________________________________________________
	   TIPO				   	FIGURA
____________________________________________________________________________	   
		0					Inicio
		1					Entrada
		2					Proceso
		3					Desición
		4					Desición de dos caminos
		5					Ciclo
		6					Do while
		7					While
		8					Imprimir
		9					Linea
___________________________________________________________________________*/
/*****************************************END NEW COMENTS******************************/
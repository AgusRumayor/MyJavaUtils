/************************************************************************
				TECNOLÓGICO DE MONTERREY
				CAMPUS ZACATECAS
				
				CLASE: Menu
				PROGRMADORES: 
						Víctor Eduardo Cardoso Nungaray			363579
						Agustín Fernando Rumayor Barraza		363592
						Alejandro Padilla Barrios				366712
				EQUIPO: Inteligencia Digital
				PERIODO: Enero-Mayo del 2007
************************************************************************/
//Librerías
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import javax.swing.border.*;

public class Menu extends JApplet{
	//Declaración de variables y objetos
	public JMenuBar mbBarra;
	public JMenu mArchivo, mProblemas, mEdicion, mComentarios, mAyuda;
	public JMenuItem miNuevo, miGuardar, miSalir, miAbrir, miEditar, miQuitar, miResolver, miDeshacer;
	public JMenuItem miAcerca;
	public JCheckBoxMenuItem chbmiJava, chbmiC, chbmiCmasmas;
	
	public Menu(){
		//Creación de la barra de menú
		mArchivo = new JMenu("Archivo");
		mArchivo.setMnemonic('A');//Al pulsar la letra 'A' el menu Archivo se despliega
		miNuevo = new JMenuItem("Nuevo");
		miNuevo.setMnemonic('N');
		mArchivo.add(miNuevo);
		miAbrir = new JMenuItem("Abrir...");
		miAbrir.setMnemonic('b');
		mArchivo.add(miAbrir);
		miGuardar = new JMenuItem("Guardar");
		miGuardar.setMnemonic('G');
		mArchivo.add(miGuardar);
		miSalir = new JMenuItem("Salir");
		miSalir.setMnemonic('S');
		mArchivo.add(miSalir);
		mEdicion = new JMenu("Edición");
		mEdicion.setMnemonic('E');
		miDeshacer = new JMenuItem("Deshacer");
		miDeshacer.setMnemonic('h');
		mEdicion.add(miDeshacer);
		mComentarios = new JMenu("Ver comentarios");
		mComentarios.setMnemonic('m');
		mEdicion.add(mComentarios);
		chbmiJava = new JCheckBoxMenuItem("Java");
		mComentarios.add(chbmiJava);
		chbmiCmasmas = new JCheckBoxMenuItem("C++");
		mComentarios.add(chbmiCmasmas);
		chbmiC = new JCheckBoxMenuItem("C");
		mComentarios.add(chbmiC);
		mProblemas = new JMenu("Problemas");
		mProblemas.setMnemonic('P');
		miEditar = new JMenuItem("Editar");
		miEditar.setMnemonic('d');
		mProblemas.add(miEditar);
		miQuitar = new JMenuItem("Quitar");
		miQuitar.setMnemonic('Q');
		mProblemas.add(miQuitar);
		miResolver = new JMenuItem("Resolver");
		miResolver.setMnemonic('R');
		mProblemas.add(miResolver);
		mAyuda = new JMenu("Ayuda");
		mAyuda.setMnemonic('y');
		miAcerca = new JMenuItem("Acerca de Kaptor FC");
		miAcerca.setMnemonic('e');
		mAyuda.add(miAcerca);
		mbBarra = new JMenuBar();
		mbBarra.add(mArchivo);
		mbBarra.add(mEdicion);
		mbBarra.add(mProblemas);
		mbBarra.add(mAyuda);
		//Termina creación de la barra de menú
		
		setJMenuBar(mbBarra);//Inserta la barra de menu en pantalla.
	}
}
/************************************************************************
				TECNOLÓGICO DE MONTERREY
				CAMPUS ZACATECAS
				
				CLASE: Codigos
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
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import javax.swing.border.*;

public class Codigos extends JApplet{
	//Declaración de variables y objetos
	public JPanel pCodigos, pCodJava, pCodC, pCodCmasmas, pCodTools1, pCodTools2, pCodTools3;
	public JTextArea taJava, taC, taCmasmas;
	public JScrollPane spJava, spCmasmas, spC;
	public JButton bCompilarJava, bCompilarC, bCompilarCmasmas;
	public JLabel lJava, lC, lCmasmas;
	public Font fCodigo, fTitulo1;
	
	public Codigos(){
		pCodigos = new JPanel(new GridLayout(3,1,2,2));
		pCodJava = new JPanel(new BorderLayout(2,2));
		pCodC = new JPanel(new BorderLayout(2,2));
		pCodCmasmas = new JPanel(new BorderLayout(2,2));
		pCodTools1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pCodTools2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pCodTools3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		bCompilarJava = new JButton("Compilar");
		bCompilarCmasmas = new JButton("Compilar");
		bCompilarC = new JButton("Compilar");
		
		fTitulo1 = new Font("Times New Roman",Font.BOLD+Font.ITALIC,20);
		fCodigo = new Font("Courier New",Font.PLAIN,14);
		
		lJava = new JLabel("          Java          ");
		lJava.setFont(fTitulo1);
		lC = new JLabel("          C              ");
		lC.setFont(fTitulo1);
		lCmasmas = new JLabel("          C++           ");
		lCmasmas.setFont(fTitulo1);

		
		taJava = new JTextArea(8,50);
		taC = new JTextArea(8,50);
		taCmasmas = new JTextArea(8,50);
		taJava.setFont(fCodigo);
		taCmasmas.setFont(fCodigo);
		taC.setFont(fCodigo);
		
		spJava = new JScrollPane(taJava);
		spCmasmas = new JScrollPane(taCmasmas);
		spC = new JScrollPane(taC);
		
		pCodTools1.add(bCompilarJava);
		pCodTools1.add(lJava);
		
		pCodTools2.add(bCompilarCmasmas);
		pCodTools2.add(lCmasmas);
		
		pCodTools3.add(bCompilarC);
		pCodTools3.add(lC);
		
		pCodJava.add(pCodTools1,BorderLayout.NORTH);
		pCodJava.add(spJava,BorderLayout.SOUTH);
		pCodCmasmas.add(pCodTools2,BorderLayout.NORTH);
		pCodCmasmas.add(spCmasmas,BorderLayout.SOUTH);
		pCodC.add(pCodTools3,BorderLayout.NORTH);
		pCodC.add(spC,BorderLayout.SOUTH);
		
		pCodigos.add(pCodJava);
		pCodigos.add(pCodCmasmas);
		pCodigos.add(pCodC);
		getContentPane().add(pCodigos);
	}
}

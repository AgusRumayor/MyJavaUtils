/************************************************************************
				TECNOLÓGICO DE MONTERREY
				CAMPUS ZACATECAS
				
				CLASE: Editor_Problemas
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

public class Editor_Problemas extends JApplet{
	//Declaración de variables y objetos
	public JPanel main, pabrir, flow, flow2;
	public JScrollPane sp1, sp2;
	public JButton guardar, abrir, nuevo, borrar;
	public JLabel titulo, numero;
	public JTextField tit, open;
	public JTextArea pantalla, problemas;
	public Font f1, f2, f3;
	public String cadena;
	public Vector integrado;
	public int count;
	public Formato format;
	//Constructor
	public Editor_Problemas(boolean usuario){
		//Si usuario es Verdadero : Profesor
		//Si usuario es Falso	  : Alumno
		count = 0;
		integrado = new Vector();
		format = new Formato();
		if(integrado.isEmpty())
			cadena = "No hay problemas";
		f1 = new Font("Arial",Font.BOLD,16);
		f2 = new Font("Times New Roman",Font.PLAIN,12);
		f3 = new Font("Verdana",Font.PLAIN,14);
		titulo = new JLabel("Titulo ");
		numero = new JLabel("");
		tit = new JTextField(20);
		tit.setFont(f1);
		open = new JTextField(2);
		pantalla = new JTextArea(1,45);
		pantalla.setFont(f3);
		pantalla.setLineWrap(true);
		problemas = new JTextArea(cadena,1,20);
		problemas.setEditable(false);
		problemas.setFont(f2);
		sp1 = new JScrollPane(pantalla);
		sp1.setBorder(new TitledBorder("Descripción"));
		sp2 = new JScrollPane(problemas);
		sp2.setBorder(new TitledBorder("Problemas"));
		abrir = new JButton("Abrir");
		abrir.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent evento){
					int tmp = Integer.parseInt(open.getText());
					if(tmp<1)
						tmp = 1;
					if(tmp>integrado.size())
						tmp = integrado.size();
					open.setText(""+tmp);
					abrir(tmp-1);
				}
			}
		);
		guardar = new JButton("Guardar");
		guardar.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent evento){
					guardar();
				}
			}
		);
		nuevo = new JButton("Nuevo");
		nuevo.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent evento){
					pantalla.setText("");
					tit.setText("");
					numero.setText("");
				}
			}
		);
		borrar = new JButton("Borrar");
		borrar.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent evento){
					borrar();
				}
			}
		);
		pabrir = new JPanel(new FlowLayout());
		flow = new JPanel(new FlowLayout(FlowLayout.LEFT));
		flow2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		main = new JPanel(new BorderLayout(5,5));
		
		flow.add(titulo);
		flow.add(tit);
		main.add(flow,BorderLayout.NORTH);
		main.add(sp1,BorderLayout.WEST);
		main.add(sp2,BorderLayout.EAST);
		pabrir.add(open);
		pabrir.add(abrir);
		pabrir.setBorder(new TitledBorder(""));
		if(usuario){
			flow2.add(nuevo);
			flow2.add(guardar);
			flow2.add(borrar);
			flow.add(format);
		}else{
			pantalla.setEditable(false);
			tit.setEditable(false);
		}
		flow2.add(pabrir);
		flow2.add(numero);
		main.add(flow2,BorderLayout.SOUTH);
		getContentPane().add(main);
		
	}
	public void guardar(){
		Vector v = new Vector();
		v.addElement(tit.getText());
		v.addElement(pantalla.getText());
		integrado.addElement(v);
		count = integrado.size()-1;
		cadena = "";
		for(int i=0;i<integrado.size();i++){
			cadena += ""+(i+1)+": "+(String)((Vector)integrado.elementAt(i)).elementAt(0)+"\n";
		} 
		problemas.setText(cadena);
		numero.setText("Problema "+count+1+": "+(String)((Vector)integrado.lastElement()).elementAt(0));
	}
	public void abrir(int n){
		if(!integrado.isEmpty()){
			count = n;
			pantalla.setText((String)((Vector)integrado.elementAt(count)).elementAt(1));
			tit.setText((String)((Vector)integrado.elementAt(count)).elementAt(0));
			numero.setText("Problema "+(count+1)+": "+(String)((Vector)integrado.elementAt(count)).elementAt(0));
		}else{
			cadena = "No hay problemas";
			problemas.setText(cadena);
		}

	}
	public void borrar(){
		cadena="";
		if(integrado.size()>0)
			integrado.remove(count);
		if(integrado.size()>0){
			for(int i=0;i<integrado.size();i++){
				cadena += ""+(i+1)+": "+(String)((Vector)integrado.elementAt(i)).elementAt(0)+"\n";
			} 
			pantalla.setText((String)((Vector)integrado.elementAt(count)).elementAt(1));
			tit.setText((String)((Vector)integrado.elementAt(count)).elementAt(0));
			numero.setText("Problema "+(count+1)+": "+(String)((Vector)integrado.elementAt(count)).elementAt(0));
		}else{
			pantalla.setText("");
			tit.setText("");
			numero.setText("");
			cadena = "No hay problemas";
		}
		count = 0;
		problemas.setText(cadena);
	}
}

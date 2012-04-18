/************************************************************************
				TECNOLÓGICO DE MONTERREY
				CAMPUS ZACATECAS
				
				CLASE: Estadisticas
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
import java.awt.geom.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import javax.swing.border.*;

public class Estadisticas extends JApplet{
	//Declaración de variables y objetos
	public JPanel main, grid, flow;
	public Graficas grf;
	public JButton bAceptar;
	public CheckboxGroup cbgGraficas, cbgProfesor;
	public Checkbox cbGrafica1, cbGrafica2, cbGrafica3, cbGrafica4;
	public Checkbox cbprf1, cbprf2, cbprf3;
	public JComboBox materias, alumnos;
	//Constructor
	public Estadisticas(boolean usuario,Vector v, Vector v2){
		//Si usuario es Verdadero : Profesor
		//Si usuario es Falso	  : Alumno
		//Los vectores v(materias) y v2(alumnos) se utilizan solo en el interfaz del profesor
		grf = new Graficas();
		bAceptar = new JButton("Aceptar");
		main = new JPanel(new BorderLayout(7,7));
		grid = new JPanel(new FlowLayout(FlowLayout.LEFT));
		grid.setBorder(new TitledBorder("Gráficas"));
		flow = new JPanel(new FlowLayout(FlowLayout.LEFT));
		flow.setBorder(new TitledBorder("Estadísticas"));
		//Creo los CheckBox
		cbgGraficas = new CheckboxGroup();//Botones para graficar valores de memoria
		cbGrafica1 = new Checkbox("Gràfica de barras", cbgGraficas, true);
		cbGrafica2 = new Checkbox("Gráfica Poligonal", cbgGraficas, false);
		cbGrafica3 = new Checkbox("Gráfica de Pastel", cbgGraficas, false);
		cbGrafica4 = new Checkbox("Graficar datos", cbgGraficas, false);
		
		cbgProfesor = new CheckboxGroup();//Botones para graficar valores de memoria
		cbprf1 = new Checkbox("Materia: ", cbgProfesor, true);
		cbprf2 = new Checkbox("Alumnos: ", cbgProfesor, false);
		cbprf3 = new Checkbox("Materias", cbgProfesor, false);
		
		materias = new JComboBox(v);
		alumnos = new JComboBox(v2);
		
		bAceptar.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent evento){
					int tmp = 1;
					String s1 = new String("");
					if(cbGrafica1.getState())
						tmp = 1;
					else if(cbGrafica2.getState())
						tmp = 2;
					else if(cbGrafica3.getState())
						tmp = 3;
					else if(cbGrafica4.getState())
						tmp = 4;
					if(cbprf1.getState())
						s1 = "Materia";
					else if(cbprf2.getState())
						s1 = "Alumno";
					else if(cbprf3.getState())
						s1 = "Materias";
					grf.setTitle(s1);
					grf.setTipo(tmp);
					grf.repaint();
				}
			}
		);
		
		flow.add(cbprf1);
		flow.add(materias);
		flow.add(cbprf2);
		flow.add(alumnos);
		flow.add(cbprf3);
		grid.add(cbGrafica1);
		grid.add(cbGrafica2);
		grid.add(cbGrafica3);
		grid.add(cbGrafica4);
		grid.add(bAceptar);
		if(usuario)
			main.add(flow,BorderLayout.NORTH);
		else{
			grf.setTitle("Calificaciones");
		}
		main.add(grf,BorderLayout.CENTER);
		main.add(grid,BorderLayout.SOUTH);
		getContentPane().add(main);
	}
}

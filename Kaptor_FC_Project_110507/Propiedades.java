/************************************************************************
				TECNOLÓGICO DE MONTERREY
				CAMPUS ZACATECAS
				
				CLASE: Propiedades
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

public class Propiedades extends JApplet{
	//Declaración de variables y objetos
	public JPanel pProp2;
	public JLabel lColor1, lColor2, lBorde;
	public JButton bColor1, bColor2;
	public JTextField tfTestColor1, tfTestColor2;
	public JComboBox cbColores1, cbColores2, cbBorde;
	public int strokeSize;
	public String tamañosBorde[]={"1","2","3","4","5","6"};
	public Color color1,color2;
	public Color colores[]={Color.BLACK,Color.BLUE,Color.CYAN,Color.DARK_GRAY,
				Color.GRAY,Color.GREEN,Color.LIGHT_GRAY,Color.MAGENTA,Color.ORANGE,
				Color.PINK,Color.RED,Color.WHITE,Color.YELLOW};
	public String nombreColores[]={"Negro","Azul","Cyan","Gris Oscuro","Gris","Verde",
				"Gris Claro","Magenta","Naranja","Rosa","Rojo","Blanco","Amarillo","..."};
	public Propiedades(){
		pProp2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lColor2 = new JLabel("Líneas:");
		lColor1 = new JLabel("Rellenos:");
		lBorde = new JLabel("Borde:");

		bColor1 = new JButton("más...");
		bColor1.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent evento){
					color1 = JColorChooser.showDialog(Propiedades.this,"Seleccione color de relleno",color1);
					cbColores1.setSelectedIndex(13);
					tfTestColor1.setBackground(color1);
				}
			}
		);
		bColor2 = new JButton("más...");
		bColor2.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent evento){
					color2 = JColorChooser.showDialog(Propiedades.this,"Seleccione color de letra y bordes",color2);
					cbColores2.setSelectedIndex(13);
					tfTestColor2.setBackground(color2);
				}
			}
		);
		cbColores1 = new JComboBox(nombreColores);
		cbColores1.setMaximumRowCount(4);
		cbColores1.setSelectedIndex(8);
		cbColores1.addItemListener(
			new ItemListener(){
				public void itemStateChanged(ItemEvent evento){
					if(cbColores1.getSelectedIndex() < 13){
						color1 = colores[cbColores1.getSelectedIndex()];
						tfTestColor1.setBackground(color1);
					}
				}
			}
		);
		cbColores2 = new JComboBox(nombreColores);
		cbColores2.setMaximumRowCount(4);
		cbColores2.addItemListener(
			new ItemListener(){
				public void itemStateChanged(ItemEvent evento){
					if(cbColores2.getSelectedIndex() < 13){
						color2 = colores[cbColores2.getSelectedIndex()];
						tfTestColor2.setBackground(color2);
					}
				}
			}
		);
		cbBorde = new JComboBox(tamañosBorde);
		cbBorde.setMaximumRowCount(6);
		cbBorde.addItemListener(
			new ItemListener(){
				public void itemStateChanged(ItemEvent evento){
					strokeSize = Integer.parseInt(tamañosBorde[cbBorde.getSelectedIndex()]);
				}
			}
		);
		strokeSize = Integer.parseInt(tamañosBorde[cbBorde.getSelectedIndex()]);
		tfTestColor1 = new JTextField("",2);
		tfTestColor1.setEditable(false);
		tfTestColor2 = new JTextField("",2);
		tfTestColor2.setEditable(false);
		//Colores
		color1 = colores[cbColores1.getSelectedIndex()];//Rellenos
		color2 = colores[cbColores2.getSelectedIndex()];//Bordes y Letra
		tfTestColor1.setBackground(color1);
		tfTestColor2.setBackground(color2);
		
		pProp2.add(lColor1);
		pProp2.add(tfTestColor1);
		pProp2.add(cbColores1);
		pProp2.add(bColor1);
		pProp2.add(lBorde);
		pProp2.add(cbBorde);
		pProp2.add(lColor2);
		pProp2.add(tfTestColor2);
		pProp2.add(cbColores2);
		pProp2.add(bColor2); 
		
		getContentPane().add(pProp2);
	}
	public Color getColor1(){
		return color1;
	}
	public Color getColor2(){
		return color2;
	}
	public int getStrokeSize(){
		return strokeSize;
	}
}

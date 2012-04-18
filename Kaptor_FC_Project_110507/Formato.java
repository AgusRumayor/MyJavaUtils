/************************************************************************
				TECNOLÓGICO DE MONTERREY
				CAMPUS ZACATECAS
				
				CLASE: Formato
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

public class Formato extends JApplet{
	//Declaración de variables y objetos
	public JPanel pProp1;
	public JToggleButton bNegrita, bCursiva;
	public JComboBox cbLetra, cbFuente;
	public String sFuente, sAllFonts[];
	public GraphicsEnvironment graphicsEvn;
	public int fontSize, cursiva, negrita, estilo;
	public String tamañosLetra[]={"8","10","12","14","16","18","20","22"};
	
	public Formato(){
		pProp1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		graphicsEvn = GraphicsEnvironment.getLocalGraphicsEnvironment();
		sAllFonts = graphicsEvn.getAvailableFontFamilyNames();
		
		bNegrita = new JToggleButton("N");
		bNegrita.setFont(new Font("Arial Black",Font.BOLD,12));
		bCursiva = new JToggleButton("K");
		bCursiva.setFont(new Font("Arial",Font.ITALIC+Font.BOLD,12));
		bNegrita.addItemListener(
			new ItemListener(){
				public void itemStateChanged(ItemEvent evento){
					if(evento.getSource()==bNegrita)
						negrita = bNegrita.isSelected()?Font.BOLD:Font.PLAIN;
						estilo = negrita+cursiva;
				}
			}
		);
		bCursiva.addItemListener(
			new ItemListener(){
				public void itemStateChanged(ItemEvent evento){
					if(evento.getSource()==bCursiva)
						cursiva = bCursiva.isSelected()?Font.ITALIC:Font.PLAIN;
						estilo = negrita+cursiva;
				}
			}
		);
		cbLetra = new JComboBox(tamañosLetra);
		cbLetra.setSelectedIndex(2);
		cbLetra.setMaximumRowCount(5);
		cbLetra.addItemListener(
			new ItemListener(){
				public void itemStateChanged(ItemEvent evento){
					fontSize = Integer.parseInt(tamañosLetra[cbLetra.getSelectedIndex()]);
				}
			}
		);
		cbFuente = new JComboBox(sAllFonts);
		cbFuente.setMaximumRowCount(8);
		cbFuente.setSelectedItem("Verdana");
		cbFuente.addItemListener(
			new ItemListener(){
				public void itemStateChanged(ItemEvent evento){
					sFuente = sAllFonts[cbFuente.getSelectedIndex()];
				}
			}
		);
		
		sFuente = sAllFonts[cbFuente.getSelectedIndex()];
		fontSize = Integer.parseInt(tamañosLetra[cbLetra.getSelectedIndex()]);
		cursiva = bCursiva.isSelected()?Font.ITALIC:Font.PLAIN;
		negrita = bNegrita.isSelected()?Font.BOLD:Font.PLAIN;
		estilo = negrita+cursiva;

		pProp1.add(cbFuente);
		pProp1.add(cbLetra);
		pProp1.add(bNegrita);
		pProp1.add(bCursiva);
		
		getContentPane().add(pProp1);
	}
	public String getFuente(){
		return sFuente;
	}
	public int getEstilo(){
		return estilo;
	}
	public int getFontSize(){
		return fontSize;
	}
}

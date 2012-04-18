/************************************************************************
				TECNOLÓGICO DE MONTERREY
				CAMPUS ZACATECAS
				
				PROGRAMA: Kaptor Flow Chart (Diagramador de Programas)
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
import java.applet.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import java.net.*;
import java.util.*;

public class Kaptor extends JApplet{
	//Declaración de objetos
	public Lienzo escenario;
	public JPanel pDesigns, blMain, pPropiedades, pTools, pFlow, PANELPRINCIPAL;
	public JPanel pParametros, pInicio, pEntrada, pProceso, pDecision, pCiclo, pImprimir;
	public JPanel pEntradas, pWhile, pDoWhile, pFor;
	public JRadioButton rbEntrada, rbProceso,rbDecision,rbInicio,rbImprimir,rbCiclo;
	public ButtonGroup bgTools;
	public JButton bCodigo, bInsertar6;
	public JButton bInsertar, bInsertar2, bInsertar3, bInsertar4, bInsertar5;
	public JButton bEntradas, bInsertar7, bInsertar8;
	public JLabel lFuente, lProblma;
	public JLabel lDecision, lImprimir, lEntrada, lCiclo, lInicio, lProceso, lEntradas;
	public JTextField tfDatos, tfDatos2, tfDatos3, tfDatos4, tfDatos5;
	public JTextField tfEntradas, tfWhile, tfDoWhile, tfDoWhile2, tfFor, tfFor2;
	public JTabbedPane tpFichas;
	public JComboBox cbFor;
	public JComboBox cbInicio, cbDecision, cbCiclo, cbEntradas;
	public String variablesFor[]={"i","k","v","h","j","r"};
	public String vecInicio[]={"nada (void)","entero","long","double","float","cadena","caracter","boleano"};
	public String vecDecision[]={"un camino (if)","dos caminos (if-else)"};
	public String vecCiclo[]={"Elija una","bucle","mientras (while)","hacer...mientras"};
	public String vecEntradas[]={"entero","long","float","double","cadena","caracter","boleano"};
	public int tipo, tool, entradas, xPos, yPos;/**********new*************/
	public JScrollPane spProblem;
	public JTextArea taProblem;
	public ScrollPane spLienzo;/**************************************new***************************************/
	public ImageIcon iaInicio, iaProceso, iaDecision, iaImprimir, iaEntrada, iaCiclo;
	public Pila botones;
	/********Objetos para pruebas, no deben aparecer en la compilacion final***/
	public JButton b1,b5,b2;
	public Graficas grf;
	public Estadisticas est;
	public Editor_Problemas edit;
	public Menu menu;
	public Codigos cod;
	public Formato format;
	public Propiedades prop;
	public Vector alumnos, materias;/*************************************new*******************************/
	//Método para inicializar el programa
	public void start(){
		/**Objetos de Prueba, se quitan al final***/
		b1 = new JButton("Aqui van más botones y otras opciones...");
		b2 = new JButton("12%  loading...");
		b5 = new JButton("the panel is under construction  ( 4% )");
		edit = new Editor_Problemas(true);
		alumnos = new Vector();
		materias = new Vector();
		menu = new Menu();
		cod = new Codigos();
		format = new Formato();
		prop = new Propiedades();
		est = new Estadisticas(true, materias, alumnos);
		/***************FIn de objetos de prueba*********/
		xPos = 200;/**************************new*************************/
		yPos = 0;/**************************new*************************/
		//Estructuras de datos
		botones = new Pila();
		//Creación de contenedores
		PANELPRINCIPAL = new JPanel(new BorderLayout(7,7));
		blMain = new JPanel(new BorderLayout(7,7));
		pTools = new JPanel(new BorderLayout(2,2));
		pDesigns = new JPanel(new GridLayout(6,2,2,2));
		pDesigns.setBorder(new TitledBorder("Herramientas"));
		pPropiedades = new JPanel(new BorderLayout(2,2));
		pFlow = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pInicio = new JPanel(new GridLayout(5,1,1,1));
		pEntrada = new JPanel(new BorderLayout(1,1));
		pProceso = new JPanel(new GridLayout(3,1,1,1));
		pDecision = new JPanel(new GridLayout(5,1,1,1));
		pCiclo = new JPanel(new GridLayout(2,1,1,1));
		pImprimir = new JPanel(new GridLayout(3,1,1,1));
		pEntradas = new JPanel(new GridLayout(5,1,1,1));
		pWhile = new JPanel(new GridLayout(3,1,1,1));
		pDoWhile = new JPanel(new GridLayout(5,1,1,1));
		pFor = new JPanel(new GridLayout(4,2,1,1));
		pParametros = new JPanel(new FlowLayout(1));
		pParametros.setBorder(new TitledBorder("Parámetros"));
		tpFichas = new JTabbedPane();
		
		//Lienzo
		escenario = new Lienzo();
		/****************************************/grf = new Graficas();
		spLienzo = new ScrollPane();
		spLienzo.add(escenario);/////////////////////////////////////////escenario o grf
		//Image Icon's
		try{
			iaInicio = new ImageIcon(new URL(getCodeBase(),"inicio.gif"));
			iaDecision = new ImageIcon(new URL(getCodeBase(),"decision.gif"));
			iaProceso = new ImageIcon(new URL(getCodeBase(),"proceso.gif"));
			iaImprimir = new ImageIcon(new URL(getCodeBase(),"imprimir.gif"));
			iaEntrada = new ImageIcon(new URL(getCodeBase(),"entrada.gif"));
			iaCiclo = new ImageIcon(new URL(getCodeBase(),"ciclo.gif"));
		}catch(MalformedURLException e){
			JOptionPane.showMessageDialog(null,"No se cargaron correctamente las imagenes",
				null,JOptionPane.ERROR_MESSAGE);
		}

		//Etiquetas
		lFuente = new JLabel("FUENTE  ");
		lInicio = new JLabel(iaInicio);
		lDecision = new JLabel(iaDecision);
		lProceso = new JLabel(iaProceso);
		lImprimir = new JLabel(iaImprimir);
		lEntrada = new JLabel(iaEntrada);
		lCiclo = new JLabel(iaCiclo);
		lEntradas = new JLabel("Entrada ");
		//Text Area
		taProblem = new JTextArea();
		taProblem.setColumns(15);
		spProblem = new JScrollPane(taProblem);
		//Botones
		bInsertar = new JButton("Insertar");//de Panel Parametros Inicio
		bInsertar2 = new JButton("Aceptar");//de Panel Parametros Entrada
		bInsertar3 = new JButton("Insertar");//de Panel Parametros Proceso
		bInsertar4 = new JButton("Insertar");//de Panel Parametros Decisión
		bInsertar5 = new JButton("Aceptar");//de Panel Parametros Ciclo
		bInsertar6 = new JButton("Insertar");//de Panel Parametros Imprimir
		bEntradas = new JButton("Insertar");//Para meter entradas
		bInsertar7 = new JButton("Insertar");//Para meter While's
		bInsertar8 = new JButton("Insertar");//Para meter Do While's
		bCodigo = new JButton("Codificar");
		bCodigo.addActionListener(/******************NEW************/
			new ActionListener(){
				public void actionPerformed(ActionEvent evento){
					//Acciones Codificar
					cod.taJava.setText("Usted ha codificado en JAVA");/******prueba******/
					cod.taCmasmas.setText("Usted ha codificado en C++");/******prueba******/
					cod.taC.setText("Usted ha codificado en C");/******prueba******/
				}
			}
		);/****************************NEW******************************/
		//Campos de texto
		tfDatos = new JTextField("",8);
		tfDatos2 = new JTextField("1",3);
		tfDatos3 = new JTextField("",8);
		tfDatos4 = new JTextField("",8);
		tfDatos5 = new JTextField("",8);
		tfEntradas = new JTextField("",8);
		tfWhile = new JTextField("",8);
		tfDoWhile = new JTextField("",8);
		tfDoWhile2 = new JTextField("",8);
		tfFor = new JTextField("",3);
		tfFor2 = new JTextField("",3);
		//Combo Box
		cbInicio = new JComboBox(vecInicio);
		cbInicio.setMaximumRowCount(4);
		cbInicio.addItemListener(
			new ItemListener(){
				public void itemStateChanged(ItemEvent evento){
					//Acciones***************************************
				}
			}
		);
		cbEntradas = new JComboBox(vecEntradas);
		cbEntradas.setMaximumRowCount(4);
		cbEntradas.addItemListener(
			new ItemListener(){
				public void itemStateChanged(ItemEvent evento){
					//Acciones***************************************
				}
			}
		);
		cbDecision = new JComboBox(vecDecision);
		cbDecision.addItemListener(
			new ItemListener(){
				public void itemStateChanged(ItemEvent evento){
					//Acciones***************************************
				}
			}
		);
		cbCiclo = new JComboBox(vecCiclo);
		cbCiclo.addItemListener(
			new ItemListener(){
				public void itemStateChanged(ItemEvent evento){
					if(cbCiclo.getSelectedIndex() == 1){
						tool = 9;
						refreshInterfaz();
					}else if(cbCiclo.getSelectedIndex() == 2){
						tool = 7;
						refreshInterfaz();
					}else if(cbCiclo.getSelectedIndex() == 3){
						tool = 8;
						refreshInterfaz();
					}
				}
			}
		);
		cbFor = new JComboBox(variablesFor);
		cbFor.addItemListener(
			new ItemListener(){
				public void itemStateChanged(ItemEvent evento){
					//Acciones***************************************
				}
			}
		);
		//RadioButtons
		rbInicio = new JRadioButton("Inicio",true);
		rbInicio.addItemListener(
			new ItemListener(){
				public void itemStateChanged(ItemEvent evento){
					tool = 0;
					refreshInterfaz();
				}
			}
		);
		rbProceso = new JRadioButton("Proceso",false);
		rbProceso.addItemListener(
			new ItemListener(){
				public void itemStateChanged(ItemEvent evento){
					tool = 2;
					refreshInterfaz();
				}
			}
		);
		rbDecision = new JRadioButton("Decisión",false);
		rbDecision.addItemListener(
			new ItemListener(){
				public void itemStateChanged(ItemEvent evento){
					tool = 3;
					refreshInterfaz();
				}
			}
		);
		rbImprimir = new JRadioButton("Imprimir",false);
		rbImprimir.addItemListener(
			new ItemListener(){
				public void itemStateChanged(ItemEvent evento){
					tool = 5;
					refreshInterfaz();
				}
			}
		);
		rbCiclo = new JRadioButton("Ciclo",false);
		rbCiclo.addItemListener(
			new ItemListener(){
				public void itemStateChanged(ItemEvent evento){
					tool = 4;
					refreshInterfaz();
				}
			}
		);
		rbEntrada = new JRadioButton("Entrada",false);
		rbEntrada.addItemListener(
			new ItemListener(){
				public void itemStateChanged(ItemEvent evento){
					tool = 1;
					refreshInterfaz();
				}
			}
		);
		bgTools = new ButtonGroup();//Se crea un grupo de radio buttons
		bgTools.add(rbInicio);
		bgTools.add(rbProceso);
		bgTools.add(rbDecision);
		bgTools.add(rbImprimir);
		bgTools.add(rbCiclo);
		bgTools.add(rbEntrada);
		//Inicialización de Valores
		tipo = 0;
		tool = 0;
		entradas = 1;
		//Metodos de botones
		/** NOTA: La variable tipo indica el tipo de figura que es, por ejemplo:
					 el tipo 0 representa inicio. Las demás las puedes verificar en
					 la clase Lienzo casi al ultimo, de esta manera se puede
					 saber de que es cada botón        ***************************/
		bInsertar.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent evento){
					tipo = 0;
					yPos += escenario.getHeight()+20;
					escenario.setDesign(tipo,xPos,yPos+20,tfDatos.getText(), prop.getColor1(), prop.getColor2(),prop.getStrokeSize(),new Font(format.getFuente(), format.getEstilo(), format.getFontSize()));/**************new*****************/
					escenario.setLine(xPos,yPos+20,xPos,yPos, prop.getColor2(),prop.getStrokeSize());
					JButton bFin = new JButton("Fin");//Botón que cierra toda la función
					bFin.addActionListener(
						new ActionListener(){
							public void actionPerformed(ActionEvent evn){
								botones.pop();
								JButton bUltimo = (JButton)botones.pop();
								bUltimo.setEnabled(true);
								botones.push(bUltimo);
								refreshInterfaz();
							}
						}
					);
					JButton bAnt = (JButton)botones.pop();
					bAnt.setEnabled(false);
					botones.push(bAnt);
					botones.push(bFin);
					refreshInterfaz();
				}
			}
		);
		bInsertar2.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent evento){
					tool = 6;
					entradas = Integer.parseInt(tfDatos2.getText());
					lEntradas.setText("Entrada 1");
					refreshInterfaz();
				}
			}
		);
		bInsertar3.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent evento){
					tipo = 2;
					escenario.setDesign(tipo,xPos,yPos+20,tfDatos3.getText(), prop.getColor1(), prop.getColor2(),prop.getStrokeSize(),new Font(format.getFuente(), format.getEstilo(), format.getFontSize()));
				}
			}
		);
		bInsertar4.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent evento){
					if(cbDecision.getSelectedIndex()==0){
						tipo = 3;
						yPos += escenario.getHeight()+20;
						escenario.setDesign(tipo,xPos,yPos+20,tfDatos4.getText(), prop.getColor1(), prop.getColor2(),prop.getStrokeSize(),new Font(format.getFuente(), format.getEstilo(), format.getFontSize()));
						escenario.setLine(xPos,yPos+20,xPos,yPos, prop.getColor2(),prop.getStrokeSize());
						JButton bIf = new JButton("Cerrar if");//Botón que cierra toda la función
						bIf.addActionListener(
							new ActionListener(){
								public void actionPerformed(ActionEvent evn){
									botones.pop();
									JButton bUltimo = (JButton)botones.pop();
									bUltimo.setEnabled(true);
									botones.push(bUltimo);
									refreshInterfaz();
								}
							}
						);
						JButton bAnt = (JButton)botones.pop();
						bAnt.setEnabled(false);
						botones.push(bAnt);
						botones.push(bIf);
						refreshInterfaz();
					}else if(cbDecision.getSelectedIndex()==1){
						tipo = 4;
						yPos += escenario.getHeight()+20;
						escenario.setDesign(tipo,xPos,yPos+20,tfDatos4.getText(), prop.getColor1(), prop.getColor2(),prop.getStrokeSize(),new Font(format.getFuente(), format.getEstilo(), format.getFontSize()));
						escenario.setLine(xPos,yPos+20,xPos,yPos, prop.getColor2(),prop.getStrokeSize());
						xPos += escenario.getWidth()/2;
						yPos += escenario.getHeight()+20;
						escenario.setLine(xPos+20,yPos,xPos,yPos, prop.getColor2(),prop.getStrokeSize());
						xPos += 20;
						JButton bElse = new JButton("Cerrar else");//Botón que cierra toda la función
						bElse.addActionListener(
							new ActionListener(){
								public void actionPerformed(ActionEvent evn){
									botones.pop();
									JButton bUltimo = (JButton)botones.pop();
									bUltimo.setEnabled(true);
									botones.push(bUltimo);
									refreshInterfaz();
								}
							}
						);
						JButton bAnt = (JButton)botones.pop();
						bAnt.setEnabled(false);
						botones.push(bAnt);
						botones.push(bElse);
						refreshInterfaz();
						JButton bIf = new JButton("Cerrar if");//Botón que cierra toda la función
						bIf.addActionListener(
							new ActionListener(){
								public void actionPerformed(ActionEvent evn){
									botones.pop();
									JButton bUltimo = (JButton)botones.pop();
									bUltimo.setEnabled(true);
									botones.push(bUltimo);
									refreshInterfaz();
								}
							}
						);
						JButton bAnt2 = (JButton)botones.pop();
						bAnt2.setEnabled(false);
						botones.push(bAnt2);
						botones.push(bIf);
						refreshInterfaz();
					}
				}
			}
		);
		bInsertar5.addActionListener(//Insertar un bucle(For)
			new ActionListener(){
				public void actionPerformed(ActionEvent evento){
					tipo = 5;
					//escenario.setDesign(tipo,xPos,yPos+20,tfDatos2.getText(), prop.getColor1(), prop.getColor2(),prop.getStrokeSize(),new Font(format.getFuente(), format.getEstilo(), format.getFontSize()));
				}
			}
		);
		bInsertar6.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent evento){
					tipo = 8;
					//escenario.setDesign(tipo,xPos,yPos+20,xPos,yPos,tfDatos5.getText(), prop.getColor1(), prop.getColor2(),prop.getStrokeSize(),new Font(format.getFuente(), format.getEstilo(), format.getFontSize()));
				}
			}
		);
		bEntradas.addActionListener(/******************int Entradas, ciclar***/
			new ActionListener(){
				public void actionPerformed(ActionEvent evento){
					tipo = 1;
					//escenario.setDesign(tipo,xPos,yPos+20,xPos,yPos,tfEntradas.getText(), prop.getColor1(), prop.getColor2(),prop.getStrokeSize(),new Font(format.getFuente(), format.getEstilo(), format.getFontSize()));
				}
			}
		);
		bInsertar7.addActionListener(//Insertar un while
			new ActionListener(){
				public void actionPerformed(ActionEvent evento){
					tipo = 6;
					//escenario.setDesign(tipo,xPos,yPos+20,xPos,yPos,tfDatos5.getText(), prop.getColor1(), prop.getColor2(),prop.getStrokeSize(),new Font(format.getFuente(), format.getEstilo(), format.getFontSize()));
				}
			}
		);
		bInsertar8.addActionListener(//Insertar un Do While
			new ActionListener(){
				public void actionPerformed(ActionEvent evento){
					tipo = 7;
					//escenario.setDesign(tipo,xPos,yPos+20,xPos,yPos,tfDatos5.getText(), prop.getColor1(), prop.getColor2(),prop.getStrokeSize(),new Font(format.getFuente(), format.getEstilo(), format.getFontSize()));
				}
			}
		);
		
		//Comienza a agregarse los objetos a los contenedores
		
		/****************PRIMER TABBED PANE************************/
		botones.push(bCodigo);
		//Se comienzan a crear paneles de parametros de cada diseño
		pInicio.add(new JLabel("Nombre: "));
		pInicio.add(tfDatos);
		pInicio.add(new JLabel("Tipo: "));
		pInicio.add(cbInicio);
		pInicio.add(bInsertar);
		
		pEntrada.add(new JLabel("Variables: "),"West");
		pEntrada.add(tfDatos2,"East");
		pEntrada.add(bInsertar2,"South");
		
		pEntradas.add(new JLabel("Tipo: "));
		pEntradas.add(cbEntradas);
		pEntradas.add(new JLabel("Nombre: "));
		pEntradas.add(tfEntradas);
		pEntradas.add(lEntradas);
		pEntradas.add(bEntradas);
		
		pWhile.add(new JLabel("Condición:"));
		pWhile.add(tfWhile);
		pWhile.add(bInsertar7);
		
		pDoWhile.add(new JLabel("Repetir:"));
		pDoWhile.add(tfDoWhile);
		pDoWhile.add(new JLabel("Mientras:"));
		pDoWhile.add(tfDoWhile2);
		pDoWhile.add(bInsertar8);
		
		pFor.add(new JLabel("Ciclo"));
		pFor.add(bInsertar5);
		pFor.add(new JLabel("valor inicial:"));
		pFor.add(tfFor);
		pFor.add(new JLabel("valor final:"));
		pFor.add(tfFor2);
		pFor.add(new JLabel("variable:"));
		pFor.add(cbFor);
		
		pProceso.add(new JLabel("Proceso: "));
		pProceso.add(tfDatos3);
		pProceso.add(bInsertar3);
		
		pDecision.add(new JLabel("Decisión: "));
		pDecision.add(cbDecision);
		pDecision.add(new JLabel("Condición: "));
		pDecision.add(tfDatos4);
		pDecision.add(bInsertar4);
		
		pCiclo.add(new JLabel("Ciclo: "));
		pCiclo.add(cbCiclo);
		
		pImprimir.add(new JLabel("Imprimir: "));
		pImprimir.add(tfDatos5);
		pImprimir.add(bInsertar6);
		//Se terminan de crear paneles con parametros
		refreshInterfaz();
	}//fin de la función init
	
	
	public void refreshInterfaz(){		
		pFlow.removeAll();/********************new**********/
		for(int i=0;i<botones.length();i++)
			pFlow.add((JButton)botones.getIndex(i));
		pPropiedades.add(pFlow,BorderLayout.NORTH);
		pParametros.removeAll();
		switch(tool){
			case 0:	pParametros.add(pInicio);
			break;
			case 1: 	pParametros.add(pEntrada);
			break;
			case 2:	pParametros.add(pProceso);
			break;
			case 3: 	pParametros.add(pDecision);
			break;
			case 4: 	pParametros.add(pCiclo);
			break;
			case 5: 	pParametros.add(pImprimir);
			break;	
			case 6: 	pParametros.add(pEntradas);
			break;	
			case 7: 	pParametros.add(pWhile);
			break;
			case 8: 	pParametros.add(pDoWhile);
			break;	
			case 9: 	pParametros.add(pFor);
			break;			  
		}

		pTools.add(pParametros,BorderLayout.SOUTH);
		
		blMain.add(pTools,BorderLayout.WEST);
		blMain.add(pPropiedades,BorderLayout.SOUTH);		
		tpFichas.addTab("Diseños",null,blMain);
		tpFichas.addTab("Código",null,cod);
		tpFichas.addTab("Resultados",null,est);
		tpFichas.addTab("Problemas", null, edit);
		PANELPRINCIPAL.add(tpFichas,BorderLayout.CENTER);
		PANELPRINCIPAL.add(menu,BorderLayout.NORTH);
		getContentPane().add(PANELPRINCIPAL);
		pDesigns.add(lInicio);
		pDesigns.add(lEntrada);
		pDesigns.add(rbInicio);
		pDesigns.add(rbEntrada);
		pDesigns.add(lProceso);
		pDesigns.add(lDecision);
		pDesigns.add(rbProceso);
		pDesigns.add(rbDecision);
		pDesigns.add(lCiclo);
		pDesigns.add(lImprimir);
		pDesigns.add(rbCiclo);
		pDesigns.add(rbImprimir);
		
		pTools.add(pDesigns,BorderLayout.NORTH);
		
		pPropiedades.add(prop,BorderLayout.SOUTH);
		
		blMain.add(format,BorderLayout.NORTH);
		blMain.add(spLienzo,"Center");//"Center" equivale a BorderLayout.CENTER
		blMain.add(spProblem,BorderLayout.EAST);/***********/
		
		/********************NEW**********************Se cambio el bloque del segundo Tabbed Pane para arriba*******/
		
	}//Fin de función refreshInterfaz
	
}//Fin de la clase Kaptor



/***************************************************************************
RESTRICCIONES:
		> Ningun String dentro del diagrama de flujo debera ser mayor a 20
		  caracteres.
		  
****************************************************************************/
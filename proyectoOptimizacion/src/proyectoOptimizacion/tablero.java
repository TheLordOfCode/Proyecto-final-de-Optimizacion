package proyectoOptimizacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import lpsolve.LpSolve;
import lpsolve.LpSolveException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.UIManager;

public class tablero extends JFrame {


	//    private String[][] matrizLogica;  
	//    private String[][] matrizLogicaAuxiliar;
	private String[] nombreCiudades;
	private String[] coordenadasCiudades;
	private Boton[][] botones;
	private String[][] ciudades;
	private String [][] modelo;
	private int n;
	private int m;
	private JPanel contentPane;
	private boolean detalles = true;
	JPanel panel;
	JPanel panel_1;
	JPanel panel_2;
	JButton btnNewButton;
	JLabel labelW;
	JLabel labelZ;
	int contador = 0;
	boolean cargo = false;
	double[] solucion = new double[2];
	String funcionObjetivo = "0 0";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tablero frame = new tablero();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public tablero() {
		modelo modelito = new modelo();
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Ubicación de Hogwarts");
		setBounds(100, 100, 756, 612);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 736, 21);
		contentPane.add(menuBar);

		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);

		JMenuItem mntmAbrir = new JMenuItem("Abrir");
		mntmAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				abrirArchivo();
				obtenerCoordenadas(coordenadasCiudades);
				mostrarCiudadesEnPanel(false);
				crearTablero();				
				imprimirmatriz();
				actualizarTablero();
				panel.repaint();
				cargo = true;	
			}
		});
		mntmAbrir.setIcon(new ImageIcon("carpeta.png"));
		mnArchivo.add(mntmAbrir);

		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salir();
			}
		});
		mntmSalir.setIcon(new ImageIcon("salir.png"));
		mnArchivo.add(mntmSalir);

		JMenu mnNewMenu = new JMenu("Acerca de");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmEquipo = new JMenuItem("Equipo");
		mntmEquipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Desarrollado por: \nDiego Fernando Rosero Giraldo 1663797\nDanilo Roman Tapia 1613015\nJuan Guillermo Quevedo 1667432\nVersion -1.0");
			}
		});
		mnNewMenu.add(mntmEquipo);

		panel = new JPanel();
		panel.setBounds(10, 28, 533, 533);
		contentPane.add(panel);
		panel.setLayout(null);

		panel_1 = new JPanel();
		panel_1.setBackground(new Color(119, 136, 153));
		panel_1.setBounds(548, 22, 189, 472);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		btnNewButton = new JButton("Solucionar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				if(detalles){
//					mostrarCiudadesEnPanel();
					if(cargo){
						modelito.resolver(modelo);
						solucion = modelito.getSolucion();
						System.out.println("x: " +solucion[0]);
						System.out.println("y: " +solucion[1]);
						Double x0 = solucion[0];
						int w = x0.intValue();
						Double y0 = solucion[1];
						int z = y0.intValue();
						ciudades[w][z]= "5";	
						labelW.setText(""+w);
						labelZ.setText(""+z);
						actualizarTablero();
						btnNewButton.setText("Detalles");
						detalles= false;
						imprimirmatriz();						
					}else{
						JOptionPane.showMessageDialog(null, "Por favor primero cargue el archivo \n archivo -> abrir", "No se puede solucionar",JOptionPane.WARNING_MESSAGE);
					}
				}else{
					String detalles = detalles();
					JTextArea textArea = new JTextArea(detalles);
					JScrollPane scrollPane = new JScrollPane(textArea);  
					textArea.setLineWrap(true);  
					textArea.setWrapStyleWord(true); 
					scrollPane.setPreferredSize( new Dimension( 500, 500 ) );
					JOptionPane.showMessageDialog(null, scrollPane, "Hogwarts",  
					                                       JOptionPane.INFORMATION_MESSAGE);
					//JOptionPane.showMessageDialog(null, detalles);
				}
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));

		btnNewButton.setBounds(10, 11, 169, 60);
		btnNewButton.setEnabled(false);
		panel_1.add(btnNewButton);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.GRAY);
		panel_2.setBounds(547, 503, 189, 56);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblW = new JLabel("W: ");
		lblW.setForeground(Color.WHITE);
		lblW.setBounds(54, 33, 18, 14);
		panel_2.add(lblW);
		
		JLabel lblHogwart = new JLabel("Hogwarts");
		lblHogwart.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblHogwart.setForeground(Color.WHITE);
		lblHogwart.setBounds(54, 5, 73, 27);
		panel_2.add(lblHogwart);
		
		JLabel lblZ = new JLabel("Z:");
		lblZ.setForeground(Color.WHITE);
		lblZ.setBounds(110, 33, 17, 14);
		panel_2.add(lblZ);
		
		labelW = new JLabel("");
		labelW.setForeground(Color.WHITE);
		labelW.setBounds(82, 33, 17, 14);
		panel_2.add(labelW);
		
		labelZ = new JLabel("");
		labelZ.setForeground(Color.WHITE);
		labelZ.setBounds(130, 33, 26, 14);
		panel_2.add(labelZ);		
		//cerrar();
		
	}

	public void crearTablero(){
		int margen = 533/botones.length;
		for (int i = 0; i < botones.length; i++) {
			for (int j = 0; j < botones.length; j++) {
				botones[i][j]= new Boton((margen*i)+5,(margen*j)+5,margen,margen);
				panel.add(botones[i][j]);				
			}

		}		
	}
	
	public void cerrar(){
		try{
			this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			addWindowListener(new WindowAdapter() {
				public void windowsClosing (WindowEvent e){
					salir();
				}
			});
			this.setVisible(true);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	
	public void salir(){
		int sino1 = JOptionPane.YES_NO_OPTION;
		 int sino2 = JOptionPane.showConfirmDialog (null, "¿Desea salir?","Advertencia",sino1);
           if(sino2 == JOptionPane.YES_OPTION){ //The ISSUE is here
           	System.exit(0);
           }
	}
	
	public void mostrarCiudadesEnPanel(boolean block){
		if(!block){
			String mostrar = "";
			for (int i = 0; i < modelo.length; i++) {
				//mostrar = "<html><body>JLabel con <br> varias <br>linea :-) </body></html>" ;
				mostrar= modelo[i][0]+ "\t ("+modelo[i][1]+ ", "+modelo[i][2] + ")" ;
				JLabel ciudad = new JLabel();
				ciudad = new JLabel("New label");
				ciudad.setHorizontalAlignment(SwingConstants.LEFT);
				ciudad.setFont(new Font("Tahoma", Font.BOLD, 14));
				ciudad.setForeground(Color.WHITE);
				ciudad.setVerticalAlignment(SwingConstants.TOP);
				ciudad.setBounds(10, 82+(i*15), 169, 258);
				ciudad.setText(mostrar);
				panel_1.add(ciudad);
				//lblNewLabel.setText(lblNewLabel.getText()+modelo[i][0]+ " "+modelo[i][1]+ " "+modelo[i][2] + "\n");
				
			}
			panel_1.repaint();			
		}
		else{
			panel_1.removeAll();
		}
		
	}
	public void obtenerCoordenadas(String[] coordenadas){
		ciudadesVacias();
		for (int i = 0; i < coordenadas.length; i++) {
			int x, y;
			String valores[] = coordenadasCiudades[i].split(" ");
			x = Integer.parseInt(valores[0]);
			y = Integer.parseInt(valores[1]);
			String nombre;
			nombre = nombreCiudades[i];			
			if((x<ciudades.length) && (y<ciudades.length)){
				agregarCiudades(x, y, nombre);	
			}
			else{
				JOptionPane.showMessageDialog(null,
						"La coordenada ("+x+", "+y+" de la ciudad "+nombre+" no se puede agregar al tablero, cambie la coordenada e intente de nuevo",
					    "Error",
					    JOptionPane.ERROR_MESSAGE);
				ciudadesVacias();
				mostrarCiudadesEnPanel(true);
				break;
			}
					
		}        
	}

	public void agregarCiudades(int x1, int y1, String nombre){			
		for (int x = 0; x < ciudades.length ; x++) {            
			for (int y = 0; y < ciudades.length; y++) {            
				if((x1 == x)&&(y1== y)){                 	
					ciudades[x][y]= "3";     
					
				}            	
			}
		}                                                                                                     
	}

	public void ciudadesVacias(){
		for (int i = 0; i < ciudades.length; i++) {
			for (int j = 0; j < ciudades.length; j++) {
				ciudades[i][j]= "1"; 
			}
		}
	}
	public void actualizarTablero(){
		int aux = ciudades.length-1;
		for (int i = 0; i < ciudades.length; i++) {
			for (int j = 0; j < botones.length; j++) {
				botones[j][aux].cambiarCasilla(ciudades[j][i]);
			}
			aux--;
		}
	}
	public void imprimirmatriz(){
		for (int x =0 ; x < ciudades.length ; x++) {
			System.out.print("|");
			for (int y = 0; y < ciudades.length; y++) {
				System.out.print (ciudades[x][y]);
				if (y!=ciudades[x].length-1) System.out.print(" ");
			}
			System.out.println("|");
		}
		System.out.println("\n");
	}

	public String detalles(){
		String texto = "";
		 File archivo = null;
	      FileReader fr = null;
	      BufferedReader br = null;

	      try {
	         // Apertura del fichero y creacion de BufferedReader para poder
	         // hacer una lectura comoda (disponer del metodo readLine()).
	         archivo = new File ("detalles.txt");
	         fr = new FileReader (archivo);
	         br = new BufferedReader(fr);

	         // Lectura del fichero
	         String linea;
	         while((linea=br.readLine())!=null)
	            texto += linea+"\n";
	      }
	      catch(Exception e){
	         e.printStackTrace();
	      }finally{
	         // En el finally cerramos el fichero, para asegurarnos
	         // que se cierra tanto si todo va bien como si salta 
	         // una excepcion.
	         try{                    
	            if( null != fr ){   
	               fr.close();     
	            }                  
	         }catch (Exception e2){ 
	            e2.printStackTrace();
	         }
	      }
	      return texto;
	}

	public void abrirArchivo(){
		FileInputStream archivo = null;
		try {
			// TODO add your handling code here:
			JFileChooser buscador = new JFileChooser();
			buscador.showOpenDialog(buscador);
			String patch = buscador.getSelectedFile().getAbsolutePath();
			archivo = new FileInputStream(patch);
			DataInputStream entrada = new DataInputStream(archivo);
			BufferedReader buffer = new BufferedReader(new InputStreamReader(entrada));
			String lineas;
			int lineaActual = 0;
			while((lineas = buffer.readLine()) != null){

				if(lineaActual == 0){//Tamaño del cuadrado
					n = Integer.parseInt(lineas);
					//                    matrizLogica = new String[n][n];
					//                    matrizLogicaAuxiliar = new String[n][n];
					botones = new Boton[n][n];
					ciudades = new String[n][n];
				}
				else if(lineaActual == 1){//Numero de ciudades
					m = Integer.parseInt(lineas);
					nombreCiudades = new String[m];
					coordenadasCiudades = new String[m];
					modelo = new String[m][3];
				}else{// separar las coordenadas de cada ciudad                        
					String[] valoresFila = lineas.split(" ");
					nombreCiudades[lineaActual-2] = valoresFila[0]; 
					coordenadasCiudades[lineaActual-2] = valoresFila[1]+" "+valoresFila[2];   
					modelo[lineaActual-2][0]=valoresFila[0];
					modelo[lineaActual-2][1]=valoresFila[1];
					modelo[lineaActual-2][2]=valoresFila[2];
					
				}  
				btnNewButton.setEnabled(true);
				lineaActual++;
			}
			
//			if(ciudades.length <= coordenadasCiudades.length){
//				JOptionPane.showMessageDialog(null,
//						"El departamento no tiene espacio para tantas ciudades, intente de nuevo",
//					    "Error",
//					    JOptionPane.ERROR_MESSAGE);
//				System.exit(0);
//			}
			
			entrada.close();
		} catch (FileNotFoundException ex) {
			Logger.getLogger(tablero.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(tablero.class.getName()).log(Level.SEVERE, null, ex);
		} catch(Exception ex){
			System.out.println(ex.getMessage());
		}finally {
			try {
				archivo.close();
			} catch (IOException ex) {
				Logger.getLogger(tablero.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}
}

package proyectoOptimizacion;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Boton extends JButton implements ActionListener{

	public Boton(int posx, int posy, int ancho, int alto){
		super.setBounds(posx, posy, ancho, alto);
		super.setBackground(Color.WHITE);
//		super.setEnabled(false);
		addActionListener(this);
//		setIcon(new ImageIcon("C:\\Users\\Diego Rosero Giraldo\\Desktop\\ciudad.png"));
	}
	public void cambiarImagen(String ruta){
		
	}

	public void cambiarCasilla(String valor){
		if(valor.equals("1")) setBackground(Color.WHITE);
		if(valor.equals("5")){
			setIcon(new ImageIcon("hogwarts.png"));			
		} 
		if(valor.equals("3")) setIcon(new ImageIcon("ciudad.png"));
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		//setBackground(Color.gray);
	}
}

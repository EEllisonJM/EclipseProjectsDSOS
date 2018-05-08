package view;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class GUI_Principal extends JFrame {
	JTabbedPane tablaPaneles;
	
	JPanel panelPrincipal;
	PanelNodos panelNodos;
	PanelOperaciones panelOperaciones;
	PanelRestricciones panelRestricciones;
	private JButton jBtnProcesar;

	public GUI_Principal() {
		super("Principal");
		this.setLayout(new FlowLayout());
		tablaPaneles =new JTabbedPane();
		panelPrincipal=new JPanel();
		jBtnProcesar=new JButton("Procesar información");
		panelPrincipal.setLayout(new GridLayout(3,1));

		/* Inicializar */
		panelNodos = new PanelNodos();
		panelOperaciones = new PanelOperaciones();
		panelRestricciones = new PanelRestricciones();

		panelPrincipal.add(panelNodos);
		panelPrincipal.add(panelOperaciones);
		panelPrincipal.add(panelRestricciones);
		tablaPaneles.addTab("Principal", panelPrincipal);

		
		this.add(tablaPaneles);
		this.add(jBtnProcesar);
		this.setSize(500, 575);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		GUI_Principal gui_Principal = new GUI_Principal();
	}

}

package view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.apache.http.auth.InvalidCredentialsException;

import controller.CSVHandler;
import controller.Cliente;
import model.Nodo;

public class GUI_Principal extends JFrame implements ActionListener {
	JTabbedPane tablaPaneles;
	JPanel panelPrincipal;
	PanelTabla panelTabla;
	PanelNodos panelNodos;
	PanelOperaciones panelOperaciones;
	PanelRestricciones panelRestricciones;
	JPanel panelBotones;
	JButton jBtnProcesar;

	public GUI_Principal() {
		this.setLayout(new FlowLayout());

		/* Inicializar */
		incializarComponentes();
		panelPrincipal.setLayout(new GridLayout(4, 1));
		agregarComponentes();

		jBtnProcesar.addActionListener(this);

		this.setSize(500, 575);
		this.setVisible(true);
	}

	public void incializarComponentes() {
		tablaPaneles = new JTabbedPane();
		panelPrincipal = new JPanel();
		panelTabla = new PanelTabla();
		panelNodos = new PanelNodos();
		panelOperaciones = new PanelOperaciones();
		panelRestricciones = new PanelRestricciones();
		panelBotones = new JPanel();
		jBtnProcesar = new JButton("Procesar información");
	}

	public void agregarComponentes() {
		tablaPaneles.addTab("Principal", panelPrincipal);
		panelPrincipal.add(panelNodos);
		panelPrincipal.add(panelOperaciones);
		panelPrincipal.add(panelRestricciones);

		panelBotones.add(jBtnProcesar);
		panelPrincipal.add(panelBotones);
		this.add(tablaPaneles);

		tablaPaneles.addTab("Datos", panelTabla);
	}

	public static void main(String[] args) {
		GUI_Principal gui_Principal = new GUI_Principal();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Cliente cliente = new Cliente();
		CSVHandler csvHandler = new CSVHandler(',');
		try {
			cliente.solicitud(csvHandler.leerArchivoNodos(panelNodos.getJtfRutaCSV()));
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
}

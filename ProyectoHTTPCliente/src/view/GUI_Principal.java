package view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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

	PanelCompletados panelCompletados;
	PanelFallidos panelFallidos;
	JPanel panelResultados;

	public GUI_Principal() {
		this.setLayout(new FlowLayout());

		/* Inicializar */
		incializarComponentes();
		panelPrincipal.setLayout(new GridLayout(4, 1));

		try {
			agregarComponentes();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		jBtnProcesar.addActionListener(this);

		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		// this.setUndecorated(true);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void incializarComponentes() {
		tablaPaneles = new JTabbedPane();
		panelPrincipal = new JPanel();

		panelNodos = new PanelNodos();
		panelOperaciones = new PanelOperaciones();
		panelRestricciones = new PanelRestricciones();
		panelBotones = new JPanel();
		jBtnProcesar = new JButton("Procesar información");

		panelResultados = new JPanel();
	}

	public void agregarComponentes() throws IOException {

		panelPrincipal.add(panelNodos);
		panelPrincipal.add(panelOperaciones);
		panelPrincipal.add(panelRestricciones);

		panelBotones.add(jBtnProcesar);
		panelPrincipal.add(panelBotones);
		tablaPaneles.addTab("Principal", panelPrincipal);

		this.add(tablaPaneles);
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
			panelCompletados = new PanelCompletados(cliente.getCompletados(), tablaPaneles);

			panelFallidos = new PanelFallidos(cliente.getFallidos());
			panelResultados.add(panelCompletados);
			panelResultados.add(panelFallidos);
			// panelResultados.setLayout(new GridLayout(2, 1));
			panelResultados.setLayout(new FlowLayout());
			tablaPaneles.addTab("Resultados", panelResultados);

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
}

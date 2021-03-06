package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class GUI_Principal extends JFrame implements ActionListener {
	private JTabbedPane tablaPaneles;
	private JPanel panelPrincipal;
	private PanelNodos panelNodos;
	private JPanel panelBotones;
	private JButton jBtnProcesar;

	public GUI_Principal() {
		incializarComponentes();
		panelPrincipal.setLayout(new BorderLayout());
		agregarComponentes();

		jBtnProcesar.addActionListener(this);

		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setVisible(true);
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void incializarComponentes() {
		panelPrincipal = new JPanel();
		tablaPaneles = new JTabbedPane();
		panelNodos = new PanelNodos();
		panelBotones = new JPanel();
		jBtnProcesar = new JButton("Procesar información");
	}

	public void agregarComponentes() {
		panelPrincipal.add(panelNodos, BorderLayout.NORTH);
		panelBotones.add(jBtnProcesar);
		panelPrincipal.add(panelBotones, BorderLayout.CENTER);
		tablaPaneles.addTab("Principal", panelPrincipal);
		this.add(tablaPaneles);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (panelNodos.getJtfRutaCSV().equals("") || panelNodos.getJtfNombreArchivo().equals("")
				|| panelNodos.getJtfPuerto().equals("")) {
			JOptionPane.showMessageDialog(null, "Favor de llenar los campos.");
		} else {

			new PanelResultados(tablaPaneles, panelNodos.getJtfRutaCSV(), panelNodos.getJtfNombreArchivo(),
					Integer.parseInt(panelNodos.getJtfPuerto()));
		}
	}

	public static void main(String[] args) {
		new GUI_Principal();
	}
}
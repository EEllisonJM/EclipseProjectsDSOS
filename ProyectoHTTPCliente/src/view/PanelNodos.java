package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class PanelNodos extends JPanel implements ActionListener {
	private JTextField jtfRutaCSV;
	private JButton jbtnAbrir;

	public PanelNodos() {
		this.setBorder(BorderFactory.createTitledBorder("Seleccionar archivo Nodos.csv"));
		this.setToolTipText("Seleccionar archivo csv");
		/* Inicializar Componentes*/
		jtfRutaCSV = new JTextField("/home/eellison/eclipse-workspace/13161147/EclipseProjectsDSOS/ProyectoHTTPCliente/archivos/NodosDSOS.csv",	20);
		jbtnAbrir = new JButton("Abrir");
		/* Agregar a JPanel */
		this.add(jtfRutaCSV);
		this.add(jbtnAbrir);
		jbtnAbrir.addActionListener(this);
	}

	public String getJtfRutaCSV() {
		return jtfRutaCSV.getText();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Text CSV", "csv");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			jtfRutaCSV.setText(chooser.getSelectedFile().getAbsolutePath());
		}
	}
}
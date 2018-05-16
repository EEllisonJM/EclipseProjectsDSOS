package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class PanelNodos extends JPanel implements ActionListener {
	private JLabel jlRuta;
	private JTextField jtfRutaCSV;
	private JButton jbtnAbrir;

	private JLabel jlNombreArchivo;
	private JTextField jtfNombreArchivo;

	private JLabel jlPuerto;
	private JTextField jtfPuerto;

	public PanelNodos() {
		this.setBorder(BorderFactory.createTitledBorder("Seleccionar archivo Nodos.csv"));
		this.setToolTipText("Seleccionar archivo csv");
		/* Inicializar Componentes */
		jlRuta = new JLabel("Ruta archivo: ");
		jtfRutaCSV = new JTextField(
				"/home/eellison/eclipse-workspace/13161147/EclipseProjectsDSOS/ProyectoHTTPCliente/archivos/NodosDSOS.csv",
				20);
		jbtnAbrir = new JButton("Abrir");

		jlNombreArchivo = new JLabel("Nombre archivo: ");
		jtfNombreArchivo = new JTextField("", 20);

		jlPuerto = new JLabel("Puerto: ");
		jtfPuerto = new JTextField("1234", 20);

		/* Agregar a JPanel */
		this.add(jlRuta);
		this.add(jtfRutaCSV);
		this.add(jbtnAbrir);

		this.add(jlNombreArchivo);
		this.add(jtfNombreArchivo);

		this.add(jlPuerto);
		this.add(jtfPuerto);

		jbtnAbrir.addActionListener(this);
	}

	public String getJtfRutaCSV() {
		return jtfRutaCSV.getText();
	}

	public String getJtfNombreArchivo() {
		return jtfNombreArchivo.getText();
	}

	public String getJtfPuerto() {
		return jtfPuerto.getText();
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
package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PanelOperaciones extends JPanel implements ChangeListener {

	private String conParametro[] = new String[] { // Operaciones a realizar
			"Sumar", "Restar", "Dividir", "Multiplicar", "Duplicar",
			"Truncar parte decimal", "Redondear a n decimas"};

	
	private String[] sinParametro = new String[] {
			"Obtener parte entera", "Obtner parte no entera"};
	
	private JCheckBox[] jcheckRealizarCP, jcheckRealizarSP;
	private JTextField[] jtfValor;
	
	private List<String> listaSin;// [String]
	private List<Object[]> listaCon;// [String][Integer]

	public PanelOperaciones() {
		this.setBorder(BorderFactory.createTitledBorder("Realizar operaciones"));
		this.setToolTipText("Realizar operaciones de +, -, *, /, etc.");
		this.setLayout(new GridLayout(0, 2));
		//Operaciones que no requieren parametros
		jcheckRealizarSP = new JCheckBox[sinParametro.length];
		//Operaciones que requieren de un parámetro
		jcheckRealizarCP = new JCheckBox[conParametro.length];
		jtfValor = new JTextField[conParametro.length];
		
		inicializarJCheckBoxs(jcheckRealizarCP, conParametro);
		inicializarJCheckBoxs(jcheckRealizarSP, sinParametro);
		inicializarCamposDeTexto(jtfValor);

		agrearComponente(jcheckRealizarSP);
		agrearComponente(jcheckRealizarCP, jtfValor);
	}

	private void inicializarJCheckBoxs(JCheckBox jCheckBoxs[], String textos[]) {
		for (int i = 0; i < jCheckBoxs.length; i++) {
			jCheckBoxs[i] = new JCheckBox(textos[i]);
			jCheckBoxs[i].addChangeListener(this);

		}
	}

	private void inicializarCamposDeTexto(JTextField jTextFields[]) {
		for (int i = 0; i < jTextFields.length; i++) {
			jTextFields[i] = new JTextField();
		}
	}
	
	private void agrearComponente(JCheckBox[] jCheckBoxs) {
		JTextField aux1[] = new JTextField[jCheckBoxs.length];
		inicializarCamposDeTexto(aux1);
		for (int i = 0; i < jCheckBoxs.length; i++) {
			aux1[i].setVisible(false);
			this.add(jCheckBoxs[i]);
			this.add(aux1[i]);
		}
	}
	
	private void agrearComponente(JCheckBox[] jCheckBoxs, JTextField[] jTF1) {
		for (int i = 0; i < jCheckBoxs.length; i++) {
			this.add(jCheckBoxs[i]);
			this.add(jTF1[i]);
		}
	}

	public List<String> getListaOperacionesSinParametro() {
		for (int i = 0; i < jcheckRealizarSP.length; i++) {
			if (jcheckRealizarSP[i].isSelected()) {
				listaSin.add(sinParametro[i]);
				System.out.println(sinParametro[i]);
			}
		}
		return listaSin;
	}
	
	public List<Object[]> getListaOperacionesConParametro() {
		for (int i = 0; i < jcheckRealizarCP.length; i++) {
			if (jcheckRealizarCP[i].isSelected()) {
				listaCon.add(new Object[] { conParametro[i], jtfValor[i] });
				System.out.println(conParametro[i]);
				System.out.println(jtfValor[i]);
			}
		}
		return listaCon;
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		
	}
}
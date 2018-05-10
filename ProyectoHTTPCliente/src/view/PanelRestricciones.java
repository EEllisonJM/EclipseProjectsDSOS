
package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PanelRestricciones extends JPanel implements ChangeListener {
	
	private String[] sinParametro = new String[] {			
			"Positivo", "Negativo",
			"Par", "impar", 
			"Entero", "Punto flotante"};
	
	private String[] unParametro = new String[] {		
			"Mayor que", "Menor que", // 0[>], 1[<]
			"Igual a", "Diferente de", // 2[==], 3[!=]
			"Mayor o igual a", "Menor o Igual a"}; // 4[>=], 5[<=]
			
	private String[] dosParametros = new String[] {
			"Rango", "No esta en el rango de"};

	private JCheckBox[] jChecksSin, jChecksUn, jChecksDos;
	private JTextField[] jTFun, jTFdos_1, jTFdos_2;

	private List<String> listaSin;// [String]
	private List<Object[]> listaUn;// [String][Integer]
	private List<Object[]> listaDos;// [String][Integer][Integer]

	public PanelRestricciones() {
		//this.setSize(new Dimension(30, 30));
		this.setBorder(BorderFactory.createTitledBorder("Realizar restricciones"));
		this.setToolTipText("Realizar restricciones (>, <, =, !=, >=, <=");
		this.setLayout(new GridLayout(0, 3));
		//Restricciones sin parámetro
		jChecksSin = new JCheckBox[sinParametro.length];
		//Restricciones que llevan un parámetro
		jChecksUn = new JCheckBox[unParametro.length];
		jTFun = new JTextField[unParametro.length];
		//Restricciones que llevan dos parámetros
		jChecksDos = new JCheckBox[dosParametros.length];
		jTFdos_1 = new JTextField[dosParametros.length];
		jTFdos_2 = new JTextField[dosParametros.length];
		inicializarJCheckBoxs(jChecksSin, sinParametro);
		inicializarJCheckBoxs(jChecksUn, unParametro);
		inicializarJCheckBoxs(jChecksDos, dosParametros);
		inicializarCamposDeTexto(jTFun);
		inicializarCamposDeTexto(jTFdos_1);
		inicializarCamposDeTexto(jTFdos_2);
		agrearComponente(jChecksSin);
		agrearComponente(jChecksUn, jTFun);
		agrearComponente(jChecksDos, jTFdos_1, jTFdos_2);
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
		JTextField aux2[] = new JTextField[jCheckBoxs.length];
		inicializarCamposDeTexto(aux1);
		inicializarCamposDeTexto(aux2);
		for (int i = 0; i < jCheckBoxs.length; i++) {
			aux1[i].setVisible(false);
			aux2[i].setVisible(false);
			this.add(jCheckBoxs[i]);
			this.add(aux1[i]);
			this.add(aux2[i]);
		}
	}
	
	private void agrearComponente(JCheckBox[] jCheckBoxs, JTextField[] jTextFields) {
		JTextField aux1[] = new JTextField[jCheckBoxs.length];
		inicializarCamposDeTexto(aux1);
		for (int i = 0; i < jCheckBoxs.length; i++) {
			aux1[i].setVisible(false);
			this.add(jCheckBoxs[i]);
			this.add(jTextFields[i]);
			this.add(aux1[i]);
		}
	}
	
	private void agrearComponente(JCheckBox[] jCheckBoxs, JTextField[] jTF1, JTextField[] jTF2) {
		for (int i = 0; i < jCheckBoxs.length; i++) {
			this.add(jCheckBoxs[i]);
			this.add(jTF1[i]);
			this.add(jTF2[i]);
		}
	}

	public List<String> getListaRestriccionesSinParametro() {
		for (int i = 0; i < jChecksSin.length; i++) {
			if (jChecksSin[i].isSelected()) {
				listaSin.add(sinParametro[i]);
				System.out.println(sinParametro[i]);
			}
		}
		return listaSin;
	}
	
	public List<Object[]> getListaRestriccionesUnParametro() {
		for (int i = 0; i < jChecksUn.length; i++) {
			if (jChecksUn[i].isSelected()) {
				listaUn.add(new Object[] { unParametro[i], jTFun[i] });
				System.out.println(unParametro[i]);
				System.out.println(jTFun[i]);
			}
		}
		return listaUn;
	}
	
	public List<Object[]> getListaRestriccionesDosParametros() {
		for (int i = 0; i < jChecksDos.length; i++) {
			if (jChecksDos[i].isSelected()) {
				listaDos.add(new Object[] { dosParametros[i], jTFdos_1[i], jTFdos_2[i] });
				System.out.println(dosParametros[i]);
				System.out.println(jTFdos_1[i]);
				System.out.println(jTFdos_2[i]);
			}
		}
		return listaDos;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		/*
		 * if (jChecksDesIgualdades[0].isSelected() == true) {
		 * System.out.println(jChecksDesIgualdades[0].getText()); }
		 */
	}
}
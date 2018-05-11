package model;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Numero {
	List<Boolean> listaRestricciones;
	private Object valor;

	public Numero(int valor) {
		this.valor = valor;
	}

	public Numero(float valor) {
		this.valor = valor;
	}

	public Numero(String valor) {
		try {
			this.valor = (existePunto(valor) == true) ? Float.parseFloat(valor) : Integer.parseInt(valor);
		} catch (NumberFormatException e) {
			e.getMessage();
		}
	}

	boolean existePunto(String v) {
		for (int i = 0; i < v.length(); i++) {
			if (v.charAt(i) == '.') {
				return true;
			}
		}
		return false;
	}

	public Object getValor() {
		return valor;
	}

	public void setValor(Object valor) {
		this.valor = valor;
	}

	// Restricciones
	public boolean esPositivo() {
		if ((Float) valor > 0) {
			return true;
		}
		return false;
	}

	public boolean esNegativo() {
		if ((Float) valor < 0) {
			return true;
		}
		return false;
	}

	public boolean esPar() {
		if (((Float) valor % 2) == 0) {
			return true;
		}
		return false;
	}

	public boolean esImpar() {
		if (!esPar()) {
			return true;
		}
		return false;
	}

	public boolean esEntero() {
		if (valor.getClass().toString().equals("class java.lang.Integer")) {
			return true;
		}
		return false;

	}

	public boolean esFlotante() {
		if ((valor.getClass().toString().equals("class java.lang.Float"))) {
			return true;
		}
		return false;
	}

	public boolean mayorQue(Object n) {
		return ((Float) valor > (Float) n) ? true : false;
	}

	public boolean menorQue(Object n) {
		return ((Float) valor < (Float) n) ? true : false;
	}

	public boolean igualA(Object n) {
		return ((Float) valor == (Float) n) ? true : false;
	}

	public boolean mayorIgualQue(Object n) {
		return ((Float) valor >= (Float) n) ? true : false;
	}

	public boolean menorIgualQue(Object n) {
		return ((Float) valor <= (Float) n) ? true : false;
	}

	public boolean diferenteA(Object n) {
		return ((Float) valor != (Float) n) ? true : false;
	}

	public boolean estaEnRango(Object x, Object y) {
		if ((Float) valor >= (Float) x && (Float) valor <= (Float) y) {
			return true;
		}
		if ((Float) valor <= (Float) x && (Float) valor >= (Float) y) {
			return true;
		}
		return false;
	}

	public boolean noEstaEnRango(Object x, Object y) {
		if ((Float) valor > (Float) x && (Float) valor > (Float) y) {
			return true;
		}
		if ((Float) valor < (Float) x && (Float) valor < (Float) y) {
			return true;
		}
		return false;
	}

	// Operaciones

	public Object sumar(Object n) {
		return (Float) valor + (Float) n;
	}

	public Object restar(Object n) {
		return (Float) valor - (Float) n;
	}

	public Object multiplicar(Object n) {
		return (Float) valor * (Float) n;
	}

	public Object dividir(Float n) {
		return (Float) valor + (Float) n;
	}

	public String truncar(String v, int numDecimales) {
		String cad = "#.";
		for (int i = 0; i < numDecimales; i++) {
			cad += "0";
		}
		DecimalFormat formato = new DecimalFormat(cad);
		formato.setRoundingMode(RoundingMode.DOWN);
		v = formato.format(Double.parseDouble(v));
		return v;
	}

	public String redondear(String v, int numDecimales) {
		String cad = "#.";
		if (numDecimales == 0) {
			cad = "#";
		}
		for (int i = 0; i < numDecimales; i++) {
			cad += "0";
		}
		DecimalFormat formato = new DecimalFormat(cad);
		formato.setRoundingMode(RoundingMode.HALF_UP);
		v = formato.format(Double.parseDouble(v));
		return v;
	}

	public String obtenerParteEntera(String v) {
		DecimalFormat formato = new DecimalFormat("#");
		formato.setRoundingMode(RoundingMode.DOWN);
		v = formato.format(Double.parseDouble(v));
		return v;
	}

	public String obtenerParteNoEntera(String v) {
		return v.substring(v.indexOf('.') + 1);
	}

	public boolean aplicarRestricciones(List<Object[]> restricciones) {
		String[] nameRestricciones = new String[] { "Positivo", "Negativo", "Par", "Mayor que", "Menor que" };
		listaRestricciones = new ArrayList<>();
		for (int i = 0; i < nameRestricciones.length; i++) {
			for (int j = 0; j < nameRestricciones.length; j++) {
				if (restricciones.get(i).length == 1) {// 0,1,2
					if (restricciones.get(i).equals(nameRestricciones[j])) {
						procesar1(nameRestricciones[j]);
						break;
					}
				}
				if (restricciones.get(i).length == 2) {// 3,4
					if (restricciones.get(i).equals(nameRestricciones[j])) {
						procesar2(nameRestricciones[j], restricciones.get(i)[j]);
						break;
					}
				}

			}

		}
		for (int i = 0; i < listaRestricciones.size(); i++) {
			if (listaRestricciones.get(i) == false) {
				return false;
			}
		}
		return true;
	}

	void procesar1(String s) {
		switch (s) {
		case "Positivo":
			listaRestricciones.add(esPositivo());
			break;
		case "Negativo":
			listaRestricciones.add(esNegativo());
			break;
		case "Par":
			listaRestricciones.add(esPar());
			break;
		}
	}

	void procesar2(String s, Object valor) {
		switch (s) {
		case "Mayor que":
			listaRestricciones.add(mayorQue(valor));
			break;
		case "Menor que":
			listaRestricciones.add(menorQue(valor));
			break;
		}
	}

}
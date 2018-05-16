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
			this.valor = getTipo(valor);/* [Integer] | [Float] */
		} catch (NumberFormatException e) {
			System.out.println("Error : No es un numero");
		}
	}

	Object getTipo(String valor) {
		if (existePunto(valor) == true)
			return new Float(valor);
		else
			return new Integer(valor);
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
		if (Float.parseFloat(valor.toString()) > 0) {
			return true;
		}
		return false;
	}

	public boolean esNegativo() {
		if (Float.parseFloat(valor.toString()) < 0) {
			return true;
		}
		return false;
	}

	public boolean esPar() {
		if ((Float.parseFloat(valor.toString()) % 2) == 0) {
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
		return (Float.parseFloat(valor + "") > Float.parseFloat(n + "")) ? true : false;
	}

	public boolean menorQue(Object n) {
		return (Float.parseFloat(valor + "") < Float.parseFloat(n + "")) ? true : false;
	}

	public boolean igualA(Object n) {
		return (Float.parseFloat(valor.toString()) == Float.parseFloat(n.toString())) ? true : false;
	}

	public boolean mayorIgualQue(Object n) {
		return (Float.parseFloat(valor.toString()) >= Float.parseFloat(n.toString())) ? true : false;
	}

	public boolean menorIgualQue(Object n) {
		return (Float.parseFloat(valor.toString()) <= Float.parseFloat(n.toString())) ? true : false;
	}

	public boolean diferenteA(Object n) {
		return (Float.parseFloat(valor.toString()) != Float.parseFloat(n.toString())) ? true : false;
	}

	public boolean estaEnRango(Object x, Object y) {
		if (Float.parseFloat(valor.toString()) >= Float.parseFloat(x.toString())
				&& Float.parseFloat(valor.toString()) <= Float.parseFloat(y.toString())) {
			return true;
		}
		if (Float.parseFloat(valor.toString()) <= Float.parseFloat(x.toString())
				&& Float.parseFloat(valor.toString()) >= Float.parseFloat(y.toString())) {
			return true;
		}
		return false;
	}

	public boolean noEstaEnRango(Object x, Object y) {
		if (Float.parseFloat(valor.toString()) > Float.parseFloat(x.toString())
				&& Float.parseFloat(valor.toString()) > Float.parseFloat(y.toString())) {
			return true;
		}
		if (Float.parseFloat(valor.toString()) < Float.parseFloat(x.toString())
				&& Float.parseFloat(valor.toString()) < Float.parseFloat(y.toString())) {
			return true;
		}
		return false;
	}

	// Operaciones

	public Object sumar(Object n) {
		return Float.parseFloat("" + valor) + Float.parseFloat("" + n);
	}

	public Object restar(Object n) {
		return Float.parseFloat("" + valor) - Float.parseFloat("" + n);
	}

	public Object multiplicar(Object n) {
		return Float.parseFloat(valor.toString()) * Float.parseFloat(n.toString());
	}

	public Object dividir(Object n) {
		return Float.parseFloat(valor.toString()) / Float.parseFloat(n.toString());
	}

	public String truncar(String v, int numDecimales) {
		String cad = "#.";
		for (int i = 0; i < numDecimales; i++) {
			cad += "0";
		}
		DecimalFormat formato = new DecimalFormat(cad);
		formato.setRoundingMode(RoundingMode.DOWN);
		v = formato.format(Double.parseDouble(v));
		System.out.println(v);
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
		System.out.println(v);
		return v;
	}

	public String obtenerParteEntera(String v) {
		DecimalFormat formato = new DecimalFormat("#");
		formato.setRoundingMode(RoundingMode.DOWN);
		v = formato.format(Double.parseDouble(v));
		return v;
	}

	public String obtenerParteNoEntera(String v) {
		if (esEntero()) {
			return "0";
		}
		return v.substring(v.indexOf('.') + 1);
	}

	public boolean aplicarRestricciones(List<Object[]> restricciones) {
		String[] nameRestricciones = new String[] {
				// Restricciones que no requieren par�metros
				"Positivo", "Negativo", // 0,1
				"Par", "impar", // 2,3
				"Entero", "Punto flotante", // 4,5
				// Requieren un par�metro
				"Mayor que", "Menor que", // 6,7
				"Igual a", "Diferente de", // 8,9
				"Mayor o igual a", "Menor o Igual a", // 10,11
				// Requieren dos par�metros
				"Rango", "No esta en el rango de" };// 12,13
		// String[] nameRestricciones = new String[] { "Positivo", "Negativo", "Par",
		// "Mayor que", "Menor que" };
		listaRestricciones = new ArrayList<>();
		for (int i = 0; i < restricciones.size(); i++) {
			for (int j = 0; j < nameRestricciones.length; j++) {
				if (restricciones.get(i).length == 1) {// 0,1,2
					if (restricciones.get(i)[0].toString().equals(nameRestricciones[j].toString())) {
						procesar1(nameRestricciones[j]);
						break;
					}
				}
				if (restricciones.get(i).length == 2) {// 3,4
					if (restricciones.get(i)[0].toString().equals(nameRestricciones[j].toString())) {
						procesar2(nameRestricciones[j], restricciones.get(i)[1].toString());
						break;
					}
				}
				if (restricciones.get(i).length == 3) {// 3,4
					if (restricciones.get(i)[0].toString().equals(nameRestricciones[j])) {
						procesar3(nameRestricciones[j], restricciones.get(i)[1], restricciones.get(i)[2]);
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

	private void procesar1(String s) {
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
		case "impar":
			listaRestricciones.add(esImpar());
			break;
		case "Entero":
			listaRestricciones.add(esEntero());
			break;
		case "Punto flotante":
			listaRestricciones.add(esFlotante());
			break;
		}
	}

	private void procesar2(String s, Object valor) {
		switch (s) {
		case "Mayor que":
			listaRestricciones.add(mayorQue(valor));
			break;
		case "Menor que":
			listaRestricciones.add(menorQue(valor));
			break;
		case "Igual a":
			listaRestricciones.add(igualA(valor));
			break;
		case "Diferente de":
			listaRestricciones.add(diferenteA(valor));
			break;
		case "Mayor o igual a":
			listaRestricciones.add(mayorIgualQue(valor));
			break;
		case "Menor o Igual a":
			listaRestricciones.add(menorIgualQue(valor));
			break;
		}
	}

	private void procesar3(String s, Object valor1, Object valor2) {
		switch (s) {
		case "Rango":
			listaRestricciones.add(estaEnRango(valor1, valor2));
			break;
		case "No esta en el rango de":
			listaRestricciones.add(noEstaEnRango(valor1, valor2));
			break;
		}
	}

	public void aplicarOperaciones(List<Object[]> operaciones) {
		String[] nombresOperaciones = new String[] { // Operaciones a realizar
				// Operaciones con parametro
				"Sumar", "Restar", "Dividir", "Multiplicar", // 0,1,2,3
				"Truncar parte decimal", "Redondear a n decimas", // 4,5
				// Operaciones que no requineren par�metro
				"Obtener parte entera", "Obtner parte no entera" };// 6,7
		for (int i = 0; i < operaciones.size(); i++) {
			for (int j = 0; j < nombresOperaciones.length; j++) {
				if (operaciones.get(i).length == 1) {// 0,1,2
					if (nombresOperaciones[j].equals(operaciones.get(i)[0].toString())) {
						procesarOperacion1(operaciones.get(i)[0].toString());
						break;
					}
				}
				if (operaciones.get(i).length == 2) {// 3,4
					if (nombresOperaciones[j].equals(operaciones.get(i)[0])) {
						procesarOperacion2(nombresOperaciones[j], Float.parseFloat(operaciones.get(i)[1] + ""));
						break;
					}
				}
			}
		}
	}

	private void procesarOperacion1(String v) {
		switch (v) {
		case "Obtener parte entera":
			this.setValor(obtenerParteEntera(valor.toString()));
			break;
		case "Obtner parte no entera":
			this.setValor(obtenerParteNoEntera(valor.toString()));
			break;
		}
	}

	private void procesarOperacion2(String s, Object valor) {
		switch (s) {
		case "Sumar":
			this.setValor(sumar(valor));
			break;
		case "Restar":
			this.setValor(restar(valor));
			break;
		case "Dividir":
			this.setValor(dividir(valor));
			break;
		case "Multiplicar":
			this.setValor(multiplicar(valor));
			break;
		case "Truncar parte decimal":
			this.valor = (truncar(this.valor.toString(), (int) Float.parseFloat(valor.toString())));
			break;
		case "Redondear a n decimas":
			this.valor = (redondear(this.valor.toString(), (int) Float.parseFloat(valor.toString())));
			break;
		}
	}
}
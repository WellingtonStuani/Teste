package br.dagostini.interfaceseclasses;

public class Soma implements Calculo {

	@Override
	public double calcular(double a, double b) {
		return a + b;
	}

}

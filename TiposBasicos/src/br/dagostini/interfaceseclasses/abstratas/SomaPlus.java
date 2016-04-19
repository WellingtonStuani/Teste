package br.dagostini.interfaceseclasses.abstratas;

public class SomaPlus extends CalculoAbstratoUm {

	public SomaPlus() {
		super();
	}

	@Override
	public double calcular(double a, double b) {
		super.incrementaContador();
		return a + b;
	}

}

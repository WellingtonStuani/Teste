package br.dagostini.interfaceseclasses;

/**
 * Um interface especifica "o quê" mas não especifica "como".
 *
 * @author fernandod
 */
public interface Calculo {

	// Interfaces podem ter constantes mas nunca variáveis.
	static final double PI = 3.14d;

	double calcular(double a, double b);

	/**
	 * Interfaces podem ter implementação padrão para os métodos. Quando um
	 * método tem uma implementação padrão como abaixo sua implementação na
	 * classe se torna opcional.
	 *
	 * @return
	 */
	default String getNomeImplementacao() {
		return "Não especificado";
	}

}

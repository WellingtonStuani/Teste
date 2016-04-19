package br.dagostini.interfaceseclasses;

class Menor implements Calculo {

	@Override
	public double calcular(double a, double b) {
		return a < b ? a : b;
	}
}

public class ExecuteTestesInterfaces {

	// É possível estender de interfaces e declará-las internamente em outras
	// classes.
	public interface Maior extends Calculo {

	}

	private class Exponenciacao implements Calculo {
		@Override
		public double calcular(double a, double b) {

			double res = a;
			for (int i = 0; i < b - 1; i++) {
				res *= a;
			}
			return res;

		}
	}

	public ExecuteTestesInterfaces() {

		// Quando se instancia os objetos basta nesse
		// momento saber que eles implementam Calculo,
		// por isso o tipo da variável é Calculo mas
		// usa-se o construtor Soma e Subtracao.
		Calculo c1 = new Soma();
		Calculo c2 = new Subtracao();

		// Nessa parte do código sabe-se "o que" será
		// feito, mas não se sabe "como" será feito.
		mostra(c1, 1, 1);
		mostra(c2, 20, 2);

		// Caso seja necessaria uma implementação diferente
		// para ser usada uma única vez, ou em uma única classe,
		// pode-se criar uma classe anônima.
		Calculo c3 = new Calculo() {
			@Override
			public double calcular(double a, double b) {
				return a * b + 2;
			}
		};
		mostra(c3, 3, 5);

		// Podemos ter classes definidas dentro de métodos também.
		class Divisao implements Calculo {
			@Override
			public double calcular(double a, double b) {
				return a / b;
			}

		}

		// Mostra o uso de uma classe interna.
		Calculo c4 = new Divisao();
		mostra(c4, 10, 5);

		Calculo c5 = new Exponenciacao();
		mostra(c5, 2, 3);
	}

	/**
	 * Perceba que esse método faz sempre a mesma coisa independentemente da
	 * implementação da interface. O "o quê" é importante para ele. O "como" não
	 * é.
	 *
	 * @param c
	 * @param a
	 * @param b
	 */
	private void mostra(Calculo c, int a, int b) {
		System.out.println("Calculando " + a + " e " + b);
		System.out.println("Resultado: " + c.calcular(a, b));
		System.out.println("\n");
	}

	public static void main(String[] args) {
		new ExecuteTestesInterfaces();

	}

}

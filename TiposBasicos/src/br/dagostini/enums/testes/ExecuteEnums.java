package br.dagostini.enums.testes;

import java.math.BigDecimal;
import java.text.NumberFormat;

import br.dagostini.enums.tipos.EstadoCivil;
import br.dagostini.enums.tipos.Mes;
import br.dagostini.enums.tipos.Uf;

public class ExecuteEnums {

	public static void main(String[] args) {

		// Enum simples.
		{
			EstadoCivil estadoCivil = EstadoCivil.CASADO;

			// Dessa forma se obtém o nome do valor.
			System.out.println(estadoCivil.name());

			// Assim se obtém o número da ordem de declaração, geralmente
			// utilizado para gravar no banco.
			System.out.println(estadoCivil.ordinal());
		}

		// Enum com atributos.
		{
			Mes mes = Mes.JANEIRO;

			switch (mes) {

			case JANEIRO:
			case FEVEREIRO:
			case MARCO:
			case ABRIL:
			case MAIO:
			case JUNHO:
				System.out.println("1o semestre");
				break;

			case JULHO:
			case AGOSTO:
			case SETEMBRO:
			case OUTUBRO:
			case NOVEMBRO:
			case DEZEMBRO:
				System.out.println("2o semestre");
				break;

			}

			System.out.println("O mês de " + mes.getNome() + " possui " + mes.getDias() + " dias.");
		}

		// Enum com atributos, métodos estático e abstrato.
		{
			// Usando o método estático.
			Uf uf = Uf.getPorNome("Paraná");

			// Usando o método abstrado implementado pelo item da enum
			BigDecimal valor = new BigDecimal(100);
			BigDecimal valorImposto = uf.getValorImposto(valor);

			NumberFormat nf = NumberFormat.getCurrencyInstance();

			System.out.println("O valor " + nf.format(valor) + " no estado do " + uf.getNome() + " fica "
					+ nf.format(valorImposto) + " com o imposto.");
		}
	}
}

package br.dagostini.classesgenericas;

/**
 * Essa classe genérica implementa uma interface genérica.
 *
 */
public class PilhaImpl<T> implements Pilha<T> {

	// Não podemos ter vetores de tipos genéricos.
	// Um tipo genérico (T) não pode ser primitivo.
	private Object[] vetor = new Object[100];

	private int idx = 0;

	@Override
	public void guardar(T t) {
		vetor[idx++] = t;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T removerUltimo() {
		return (T) vetor[--idx];
	}

	@Override
	public void mostrarTodos() {
		for (int i = 0; i < idx; i++) {
			System.out.println("> " + vetor[i]);
		}
	}
}

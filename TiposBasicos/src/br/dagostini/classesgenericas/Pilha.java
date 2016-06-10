package br.dagostini.classesgenericas;

/**
 * Uma interface gen�rica possui um par�metro T que ser� definido em tempo de
 * implementa��o da interface ou de execu��o.
 *
 */
public interface Pilha<T> {

	public void guardar(T t);


	public T removerUltimo();

	public void mostrarTodos();

}

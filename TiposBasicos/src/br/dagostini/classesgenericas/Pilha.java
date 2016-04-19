package br.dagostini.classesgenericas;

/**
 * Uma interface genérica possui um parâmetro T que será definido em tempo de
 * implementação da interface ou de execução.
 *
 */
public interface Pilha<T> {

	public void guardar(T t);

	public T removerUltimo();

	public void mostrarTodos();

}

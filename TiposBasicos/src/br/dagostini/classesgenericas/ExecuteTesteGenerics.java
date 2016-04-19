package br.dagostini.classesgenericas;

public class ExecuteTesteGenerics {

	public static void main(String[] args) {

		System.out.println("Com Integer---------------------------------------");
		{
			Pilha<Integer> p = new PilhaImpl<>();

			p.guardar(1);
			p.guardar(2);
			p.guardar(3);
			p.guardar(4);
			p.guardar(5);

			p.mostrarTodos();

			int ultimo = p.removerUltimo();

			System.out.println("Removido " + ultimo);

			p.mostrarTodos();
		}

		System.out.println("Com String----------------------------------------");
		{
			Pilha<String> p = new PilhaImpl<>();

			p.guardar("Um");
			p.guardar("Dois");
			p.guardar("Três");
			p.guardar("Quatro");
			p.guardar("Cinco");

			p.mostrarTodos();

			String ultimo = p.removerUltimo();

			System.out.println("Removido " + ultimo);

			p.mostrarTodos();
		}
	}

}

package br.dagostini.basico;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class ExecuteBasico {

	private int cabecalho = 1;

	public ExecuteBasico() {

		Cliente c = new Cliente(1, "Hugo");

		cabecalho("Mostrando nome da classe:");
		mostrarNomeDaClasse(c);

		cabecalho("Mostrando nome dos atributos:");
		mostrarNomeDosAtributos(c);

		cabecalho("Mostrando nome dos métodos declarados na classe:");
		mostrarNomesDosMetodos(c);

		cabecalho("Mostrando nome de todos os métodos, inclusive os herdados, seus parâmetros e retorno:");
		mostrarNomesDosTodosOsMetodos(c);

		cabecalho("Mostrando o valor dos atributos:");
		mostrarValorDosAtributos(c);

		cabecalho("Iniciando nova instância de mesma classe:");
		instanciarNovoObjetoDeMesmaClasse(c);

		cabecalho("Invocando método sem parâmetros:");
		invocarMetodoSimples(c, "dizerOla");

		cabecalho("Invocando método como parâmetro e recebendo o retorno:");
		invocarMetodoComParametro(c, "retornarOla", "java.lang.String", "José");

	}

	private void cabecalho(String string) {
		System.out.println("\n----------------------------------------------");
		System.out.println(cabecalho++ + " - " + string);
		System.out.println("----------------------------------------------");
	}

	private void linha(String string) {
		System.out.println("\t" + string);
	}

	private void mostrarNomeDaClasse(Object obj) {
		Class<?> cl = obj.getClass();
		linha("Nome Simples: " + cl.getSimpleName());
		linha("Nome Completo: " + cl.getCanonicalName());
	}

	private void mostrarNomeDosAtributos(Object obj) {

		Class<?> cl = obj.getClass();

		Field[] vetorFields = cl.getDeclaredFields();

		for (Field field : vetorFields) {
			linha(field.getName());
		}

	}

	private void mostrarNomesDosMetodos(Object obj) {

		Class<?> cl = obj.getClass();

		Method[] vetorMetodos = cl.getDeclaredMethods();

		for (Method method : vetorMetodos) {
			linha(method.getName());
		}

	}

	private void mostrarNomesDosTodosOsMetodos(Object obj) {

		Class<?> cl = obj.getClass();

		Method[] vetorMetodos = cl.getMethods();

		for (Method method : vetorMetodos) {

			linha("Nome: " + method.getName());

			Parameter[] parametros = method.getParameters();

			if (parametros.length > 0) {
				for (int i = 0; i < parametros.length; i++) {
					linha("  Parâmetro " + (i + 1) + " " + parametros[i].getType().getCanonicalName());
				}
			} else {
				linha("  Sem parâmetros.");
			}

			linha("Retorno: " + method.getReturnType().getCanonicalName());

			linha("\n");
		}

	}

	private void mostrarValorDosAtributos(Object obj) {

		Class<?> cl = obj.getClass();

		Field[] vetorFields = cl.getDeclaredFields();

		for (Field field : vetorFields) {

			try {

				// Se o field for private essa linha faz ignorar tornando-o
				// público para essa chamada apenas.
				field.setAccessible(true);

				String nome = field.getName();
				Object valor = field.get(obj);

				linha(nome + " = " + valor);

			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	private void instanciarNovoObjetoDeMesmaClasse(Object obj1) {

		Class<?> cl = obj1.getClass();

		try {
			Object obj2 = cl.newInstance();

			// Copiando os atributos do obj1 para o obj2

			Field[] vetorFields = cl.getDeclaredFields();

			for (Field field : vetorFields) {

				field.setAccessible(true);

				if (field.getType().equals(String.class)) {

					String valor = (String) field.get(obj1);
					field.set(obj2, valor.toUpperCase());

				} else if (field.getType().equals(int.class)) {

					int valor = (Integer) field.get(obj1);
					field.set(obj2, valor + 1);

				} else {
					Object valor = field.get(obj1);
					field.set(obj2, valor);
				}

			}

			linha("Objeto original:");
			mostrarValorDosAtributos(obj1);

			linha("");

			linha("Objeto novo:");
			mostrarValorDosAtributos(obj2);

		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	private void invocarMetodoSimples(Object obj, String nomeMetodo) {

		Class<?> cl = obj.getClass();

		try {
			Method method = cl.getMethod(nomeMetodo, new Class<?>[]{});
			method.invoke(obj, new Object[]{});

		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

	}

	private void invocarMetodoComParametro(Object obj, String nomeMetodo, String tipoParametro, String parametroMetodo) {

		try {

			Class<?> cl = obj.getClass();

			// Buscando o objeto Class pelo nome em formato string.
			Class<?> clTipo = Class.forName(tipoParametro);

			Method method = cl.getMethod(nomeMetodo, new Class<?>[]{clTipo});

			Object retorno = method.invoke(obj, new Object[]{parametroMetodo});

			linha(String.valueOf(retorno));

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (NoSuchMethodException e) {
			e.printStackTrace();

		} catch (SecurityException e) {
			e.printStackTrace();

		} catch (IllegalAccessException e) {
			e.printStackTrace();

		} catch (IllegalArgumentException e) {
			e.printStackTrace();

		} catch (InvocationTargetException e) {
			e.printStackTrace();

		}
	}

	public static void main(String[] args) {

		new ExecuteBasico();

	}

}

package br.univel;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class Principal {

	public static void main(String[] args) {

		ReaderURL reader = new ReaderURL();
		List<String> lista = reader.lerUrl();

//		ReaderArquivo reader = new ReaderArquivo();
//		List<String> lista = reader.lerArquivo();

		ProdutoParser parser = new ProdutoParser();
		List<Produto> listaPrd = parser.getProduto(lista);

		NumberFormat formatUS = NumberFormat.getCurrencyInstance(new Locale("en", "US"));
		NumberFormat formatBR = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

		BigDecimal cotacao = new BigDecimal(3.37);

		listaPrd.forEach(e -> {
			System.out.println("Id: " + e.getId());
			System.out.println("Descrição: " + e.getDescricao());
			System.out.println("Preço Dólar: " + formatUS.format(e.getPreco()));
			System.out.println("Preço Real: " + formatBR.format(e.getPreco().multiply(cotacao)));
		});

	}
}

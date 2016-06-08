package br.univel.exemplos;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import javax.swing.text.NumberFormatter;

public class ExemploBigDecimal {

	
	public static void main(String[] args) {
		
		// A partir de um número double
		BigDecimal a = new BigDecimal(8.8d);

		// float
		BigDecimal b = new BigDecimal(8.8f);
		
		// String
		BigDecimal c = new BigDecimal("8.8");
		
		
		// Americano -> Brasileiro
		String strAm = "8,123.00";
		String strBr = strAm.replaceAll(",", "").replaceAll("\\.", ",");
		
		// Forma correta?!
		try {
			Number num = NumberFormat.getNumberInstance(Locale.US).parse("88,123.00");
			BigDecimal d = new BigDecimal(num.floatValue());
			
			NumberFormat nfBR = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
			
			System.out.println(nfBR.format(d));
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		// NUNCA SE ESQUEÇA DE RETORNAR O VALOR PARA
		// UMA NOVA VARIAVEL.
		BigDecimal aa = new BigDecimal(1);
		BigDecimal bb = new BigDecimal(1);
		BigDecimal somaAABB = aa.add(bb);
		
		BigDecimal multiplicacaoAABB = aa.multiply(bb);
		
		BigDecimal divisaoAABB = aa.divide(bb);
		
		
		
	}
	
}

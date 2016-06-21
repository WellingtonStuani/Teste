package br.dagostini.properties;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class ExemploProperties {

	public ExemploProperties() {
		escrever();
		ler();

	}


	private void ler() {
		Properties p = new Properties();
		try {
			p.load(new FileReader(new File("configs.properties")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(p.getProperty("user"));


		p.entrySet().forEach(e -> {
			System.out.println(e.getKey() + " = " + e.getValue());
		});

	}


	private void escrever() {
		Properties p = new Properties();

		p.put("user", "root");
		p.put("password", "raquel");
		p.put("ip", "192.168.0.1");
		p.put("tipoBando", "relacional");

		try {
			p.store(new FileWriter(new File("configs.properties")), "Configurações");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	public static void main(String[] args) {

		new ExemploProperties();

	}

}

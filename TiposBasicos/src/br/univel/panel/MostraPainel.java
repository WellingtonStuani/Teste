package br.univel.panel;

import java.awt.Container;

import javax.swing.JFrame;

public class MostraPainel {

	public static void main(String[] args) {
		JFrame jf = new JFrame();
		jf.setSize(480, 480);
		// centraliza na tela.
		jf.setLocationRelativeTo(null);
		
		Container painel = new Painel();
		jf.setContentPane(painel );
		
		jf.setVisible(true);
	}
	
}

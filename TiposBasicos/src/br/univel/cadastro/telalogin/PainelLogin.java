package br.univel.cadastro.telalogin;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;

import javax.swing.JTextField;

import java.awt.Insets;

import javax.swing.JPasswordField;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;

public class PainelLogin extends JPanel {
	
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton btnEntrar;
	private Runnable acaoOk;

	public PainelLogin() {
		setBackground(Color.WHITE);
		setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 1.0, 0.0,
				Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblUsurio = new JLabel("Usuário");
		GridBagConstraints gbc_lblUsurio = new GridBagConstraints();
		gbc_lblUsurio.insets = new Insets(20, 20, 5, 5);
		gbc_lblUsurio.anchor = GridBagConstraints.EAST;
		gbc_lblUsurio.gridx = 0;
		gbc_lblUsurio.gridy = 0;
		add(lblUsurio, gbc_lblUsurio);

		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(20, 0, 5, 20);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		add(textField, gbc_textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Senha");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 20, 20, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		add(lblNewLabel, gbc_lblNewLabel);

		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 20, 20);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 1;
		add(passwordField, gbc_passwordField);

		btnEntrar = new JButton("Entrar");
		GridBagConstraints gbc_btnEntrar = new GridBagConstraints();
		gbc_btnEntrar.insets = new Insets(0, 0, 20, 0);
		gbc_btnEntrar.gridx = 1;
		gbc_btnEntrar.gridy = 2;
		add(btnEntrar, gbc_btnEntrar);
		
		
		// final
		configuraListeners();
	}

	private void configuraListeners() {

		textField.addKeyListener(new KeyAdapter(){

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					textField.transferFocus();
				}
			}
			
		});
		
		passwordField.addKeyListener(new KeyAdapter(){

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					logar();
				}
			}
			
		});
		
		
	}

	public PainelLogin(Runnable acaoOk) {
		this();
		
		this.acaoOk = acaoOk;
		
		btnEntrar.addActionListener(e -> {
			logar();
		});

	}

	private void logar() {
		if (textField.getText().trim().equals("1")
				&& new String(passwordField.getPassword()).equals("1")) {
			acaoOk.run();
		} else {
			JOptionPane.showMessageDialog(PainelLogin.this,
					"Usuário e/ou senha inválidos!");
		}
	}

}

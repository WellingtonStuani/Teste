package br.univel.exemplos.telabusca;

import javax.swing.JPanel;

import java.awt.EventQueue;
import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;

import javax.swing.JTextField;

import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class PainelBusca extends JPanel {
	private JTextField textField;
	private JTable table;
	private Consumer<Pet> consumerOnOk;
	private Runnable runnableOnCancel;

	/**
	 * Create the panel.
	 */
	public PainelBusca() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblNome = new JLabel("Nome");
		GridBagConstraints gbc_lblNome = new GridBagConstraints();
		gbc_lblNome.anchor = GridBagConstraints.WEST;
		gbc_lblNome.insets = new Insets(0, 0, 5, 0);
		gbc_lblNome.gridx = 0;
		gbc_lblNome.gridy = 0;
		add(lblNome, gbc_lblNome);

		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 1;
		add(textField, gbc_textField);
		textField.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		add(scrollPane, gbc_scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		
		// O WindowBuilder vai ignorar o código que está
		// dentro desses comentários:
		
		// $hide>>$
		configuraTela();
		// $hide<<$
	}

	private void configuraTela() {
		PetTableModel model = new PetTableModel();
		table.setModel(model);

		
		textField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					busca(textField.getText().trim());
				}

				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					// Seleciona o primeiro item na tabela.
					table.getSelectionModel().setSelectionInterval(0, 0);
					// Transfere o foco pra frente.
					textField.transferFocus();
				}
				
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					if (PainelBusca.this.runnableOnCancel != null) {
						PainelBusca.this.runnableOnCancel.run();
					}
				}
			}
		});
		
		table.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					table.transferFocusBackward();
				}
				
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					
					// Pra cancelar a ação padrão que já existe
					// na JTable com a tecla Enter.
					e.consume();
					
					int idx = table.getSelectedRow();
					if (idx != -1) {
						Pet pet = ((PetTableModel)table.getModel()).getPetAt(idx);
						if (pet == null) {
							return;
						}
						PainelBusca.this.consumerOnOk.accept(pet);
					}
				}
				
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					if (PainelBusca.this.runnableOnCancel != null) {
						PainelBusca.this.runnableOnCancel.run();
					}
				}
			}
		});
	}

	protected void busca(String palavra) {

		List<Pet> lista = buscaNoBanco(palavra);

		((PetTableModel) table.getModel()).preencherResultado(lista);

	}

	/**
	 * Simula uma busca no banco, como se pedisse para um DAO ou fizesse o
	 * Statement diretamente no banco.
	 * 
	 * @param palavra
	 * 
	 * @return
	 */
	private List<Pet> buscaNoBanco(String palavra) {

		List<Pet> lista = new ArrayList<Pet>();

		for (int i = 0; i < DADOS_FICTICIOS.length; i++) {
			if (DADOS_FICTICIOS[i][1].toLowerCase().startsWith(palavra)) {
				Pet pet = new Pet();

				pet.setId(Integer.parseInt(DADOS_FICTICIOS[i][0]));
				pet.setNome(DADOS_FICTICIOS[i][1]);
				pet.setRaca(DADOS_FICTICIOS[i][2]);

				lista.add(pet);
			}
		}

		return lista;
	}

	public void setOnOk(Consumer<Pet> c) {
		this.consumerOnOk = c;
	}

	public void setOnCancel(Runnable r) {
		this.runnableOnCancel = r;
	}

	@Override
	public void setVisible(boolean arg0) {
		
		super.setVisible(arg0);
		
		// Depois que ficar visivel e terminar tudo
		// o que foi agendado para a EDT, solicita o
		// foco do teclado para o textField.
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				textField.requestFocusInWindow();
			}
		});
	}

	private static final String[][] DADOS_FICTICIOS = new String[][] {
			{ "254", "Atrevido", "Akita Inu" }, { "255", "Azarado", "Beagle" },
			{ "256", "Babuíno", "Beagle Harrier" },
			{ "257", "Balet", "Bobtail" },
			{ "258", "Bandido", "Boiadeiro de Berna" },
			{ "259", "Barriga", "Boxer" }, { "260", "Batman", "Braco Alemão" },
			{ "261", "Beijoca", "Braco Francês" },
			{ "262", "Bigode", "Bull Terrier Inglês" },
			{ "263", "Blue", "Bulldog Americano" },
			{ "264", "Bolinha", "Bulldog Inglês" },
			{ "265", "Boss", "Cairn Terrier" },
			{ "266", "Brhama", "Cane Corso" }, { "267", "Brutus", "Caniche" },
			{ "268", "Carioca", "Cão de Crista Chinês" },
			{ "269", "Chato", "Cão d’Água Espanhol" },
			{ "270", "Chuvisco", "Cão Lobo Checoslovaco" },
			{ "271", "Chuvisco", "Cavalier King Charles Spaniel" },
			{ "272", "Ciroc", "Chihuahua" }, { "273", "Coringa", "Chow-Chow" },
			{ "274", "Cristal", "Cocker Americano" },
			{ "275", "Dengoso", "Cocker Spaniel Inglês" },
			{ "276", "Detonalta", "Collie" }, { "277", "Diazepan", "Dálmata" },
			{ "278", "Tieta", "Doberman" },
			{ "279", "Docinho", "Dogue Alemão" },
			{ "280", "Dólar", "Dogue de Bordéus" },
			{ "281", "Ema", "Epagneul Bretão" },
			{ "282", "Esponja", "Fila Brasileiro" },
			{ "283", "Estrela", "Fox Terrier" },
			{ "284", "Fagulha", "Galgo Afegão" },
			{ "285", "Falcão", "Golden Retriever" },
			{ "286", "Fedorento", "Husky Siberiano" },
			{ "287", "Fifa", "Ibizan Hound" },
			{ "288", "Fitá", "Jack Russel Terrier" },
			{ "289", "Flop", "Lhasa Apso" }, { "290", "Fofuxo", "Mastiff" },
			{ "291", "Foguinho", "Mastim dos Pirenéus" },
			{ "292", "Frida", "Mastim Espanhol" },
			{ "293", "Gamado", "Mastim Napolitano" },
			{ "294", "Ganesh", "Norfolk Terrier" },
			{ "295", "Geppeto", "Papillon" },
			{ "296", "Guitarra", "Pastor Alemão" },
			{ "297", "Hakuna", "Pastor de Beauce" },
			{ "298", "Happy", "Pequeno Basset Griffon" },
			{ "299", "Hermano", "Pequeno Brabançon" },
			{ "300", "Hobby", "Pequeno Cão Leão" },
			{ "301", "Indio", "Pequinês" },
			{ "302", "Iron", "Perdigueiro Português" },
			{ "303", "Jaleco", "Pinscher Anão" }, { "304", "Jazz", "Pitbull" },
			{ "305", "Jeans", "Rottweiler" }, { "306", "Jóia", "Samoiedo" },
			{ "307", "Jokei", "São Bernardo" }, { "308", "King", "Schnauzer" },
			{ "309", "Lambida", "Setter Inglês" },
			{ "310", "Lanterna", "Setter Irlandês" },
			{ "311", "Lesado", "Shar Pei" }, { "312", "Lingua", "Shih Tzu" },
			{ "313", "Lion", "Spaniel Japonês" },
			{ "314", "Lobo", "Spitz Alemão" },
			{ "315", "Lord", "Staffordshire Bull Terrier" },
			{ "316", "Lua", "Teckel" }, { "317", "Mafioso", "Terranova" },
			{ "318", "Manjarino", "Terrier Brasileiro" },
			{ "319", "Marmota", "Vizsla" }, { "320", "Matata", "Waimaraner" },
			{ "321", "Melado", "West Highland White Terrier" },
			{ "322", "Metal", "Yorkshire Terrier" },
			{ "323", "Mila", "Akita Inu" }, { "324", "Molly", "Beagle" },
			{ "325", "Morena", "Beagle Harrier" },
			{ "326", "Mortífero", "Bobtail" },
			{ "327", "Music", "Boiadeiro de Berna" },
			{ "328", "Natal", "Boxer" }, { "329", "Nerd", "Braco Alemão" },
			{ "330", "Neve", "Braco Francês" },
			{ "331", "Nutella", "Bull Terrier Inglês" },
			{ "332", "Obina", "Bulldog Americano" },
			{ "333", "Oráculo", "Bulldog Inglês" },
			{ "334", "Pandora", "Cairn Terrier" },
			{ "335", "Pantera", "Cane Corso" },
			{ "336", "Parauso", "Caniche" },
			{ "337", "Paz", "Cão de Crista Chinês" },
			{ "338", "Peludo", "Cão d’Água Espanhol" },
			{ "339", "Peteca", "Cão Lobo Checoslovaco" },
			{ "340", "Poker", "Cavalier King Charles Spaniel" },
			{ "341", "Pop", "Chihuahua" }, { "342", "Popeye", "Chow-Chow" },
			{ "343", "Positivo", "Cocker Americano" },
			{ "344", "Preguiça", "Cocker Spaniel Inglês" },
			{ "345", "Proteina", "Collie" }, { "346", "Pumba", "Dálmata" },
			{ "347", "Raio", "Doberman" }, { "348", "Ralé", "Dogue Alemão" },
			{ "349", "Rebelde", "Dogue de Bordéus" },
			{ "350", "Revotril", "Epagneul Bretão" },
			{ "351", "Rex", "Fila Brasileiro" },
			{ "352", "Saci", "Fox Terrier" },
			{ "353", "Sarado", "Galgo Afegão" },
			{ "354", "Severino", "Golden Retriever" },
			{ "355", "Skol", "Husky Siberiano" },
			{ "356", "Sorriso", "Ibizan Hound" },
			{ "357", "Stella", "Jack Russel Terrier" },
			{ "358", "Suvaco", "Lhasa Apso" }, { "359", "Tarzán", "Mastiff" },
			{ "360", "Teimoso", "Mastim dos Pirenéus" },
			{ "361", "Timão", "Mastim Espanhol" },
			{ "362", "Toete", "Mastim Napolitano" },
			{ "363", "Totó", "Norfolk Terrier" },
			{ "364", "Valente", "Papillon" },
			{ "365", "Vibe", "Pastor Alemão" },
			{ "366", "Vicky", "Pastor de Beauce" },
			{ "367", "Vinil", "Pequeno Basset Griffon" },
			{ "368", "Whey", "Pequeno Brabançon" },
			{ "369", "Xadrez", "Pequeno Cão Leão" },
			{ "370", "Xeque", "Pequinês" },
			{ "371", "Xoxo", "Perdigueiro Português" },
			{ "372", "Xulipa", "Pinscher Anão" }, { "373", "Xuxa", "Pitbull" },
			{ "374", "Zeus", "Rottweiler" }, { "375", "Zika", "Samoiedo" },
			{ "376", "Zorro", "São Bernardo" } };
}

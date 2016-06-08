package br.univel.exemplos.telabusca;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class PetTableModel extends AbstractTableModel {

	private List<Pet> lista;

	void preencherResultado(List<Pet> resultado) {
		this.lista = resultado;
		
		// O comando abaixo solicita atualização da visão.
		fireTableDataChanged();
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public int getRowCount() {
		if (lista == null)
			return 0;

		return lista.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		Pet p = lista.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return p.getId();
		case 1:
			return p.getNome();
		case 2:
			return p.getRaca();
		}

		return null;
	}

	public Pet getPetAt(int idx) {
		if (idx >= this.lista.size()) {
			return null;
		}
		return this.lista.get(idx);
	}
}

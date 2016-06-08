package br.univel.exemplos.tabela;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ClienteModel extends AbstractTableModel {

	private List<Cliente> lista;

	public ClienteModel(List<Cliente> lista) {
		this.lista = lista;
	}

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public int getRowCount() {
		return lista.size();
	}

	@Override
	public Object getValueAt(int row, int col) {

		Cliente c = lista.get(row);

		switch (col) {
		case 0:
			return c.getId();
		case 1:
			return c.getNome();
		}

		return null;
	}

	public Cliente getClienteNaLinha(int index) {
		return lista.get(index);
	}

	public void removerCliente(Cliente c) {
		int idx = this.lista.indexOf(c);
		this.lista.remove(c);
		super.fireTableRowsDeleted(idx, idx);
	}

	public void adicionarCliente(Cliente c) {
		this.lista.add(c);
		super.fireTableRowsInserted(lista.size() - 1, lista.size() - 1);

	}

}

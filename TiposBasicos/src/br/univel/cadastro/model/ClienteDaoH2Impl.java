package br.univel.cadastro.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ClienteDaoH2Impl implements ClienteDao {

	private static Connection con;
	
	private Connection getConnection() {
		
		synchronized (con) {
			if (con == null) {
				// TODO
			}
			return con;
		}
	}
	
	public void inserir(Cliente c) {
		String sql = "INSERT INTO....";
		try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}

	public void atualizar(Cliente c) {
		
	}

	public void excluir(Cliente c) {
		
	}

	public Cliente buscar(int id) {
		
		return null;
	}

	public Cliente buscarPorExemplo(Cliente c) {
		
		return null;
	}

	public List<Cliente> listar() {
		
		return null;
	}
}

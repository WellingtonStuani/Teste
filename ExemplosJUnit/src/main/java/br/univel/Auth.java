package br.univel;

final class Auth {

	public boolean usuarioValido(final String usuario, final String senha) {
		
		if (usuario == null || senha == null) {
			throw new NullPointerException("Nem usuario nem senha podem ser nulos!");
		}
		
		
		if ("admin".equals(usuario) && "123".equals(senha)) {
			return true;
		} else {
			return false;
		}
		
		
		// return "admin".equals(usuario) && "123".equals(senha);
		
	}
	
}

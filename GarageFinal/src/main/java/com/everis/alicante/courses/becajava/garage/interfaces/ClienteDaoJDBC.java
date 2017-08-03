package com.everis.alicante.courses.becajava.garage.interfaces;

import java.io.IOException;
import java.sql.Connection;
import java.util.Map;

import com.everis.alicante.courses.becajava.garage.domain.Cliente;


public interface ClienteDaoJDBC{

	public void create(Cliente cliente) throws IOException;
	public Cliente read (String nif) throws IOException;
	public void update (Cliente cliente) throws IOException;
	public void delete (String dni) throws IOException;
	public Connection getConnection () throws IOException;
	public Map<String,Cliente> readClientes();
	
}

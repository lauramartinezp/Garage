package com.everis.alicante.courses.becajava.garage.interfaces.implementation;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import com.everis.alicante.courses.becajava.garage.domain.Cliente;
import com.everis.alicante.courses.becajava.garage.interfaces.ClienteDaoJDBC;

public class ClienteDaoJDBCImpl implements ClienteDaoJDBC{
	
	private final String MYSQL_JDBC_DRIVER ="com.mysql.jdbc.Driver";
	private final String JDBC_CADENA_CONEXION ="jdbc:mysql://localhost:3306/garage";
	//private final String JDBC_USR = "root";
	//private final String JDBC_PWD = "";

	@Override
	public void create(Cliente cliente) throws IOException {
		Connection cn = null;
		Statement st = null;
		
		try {
			
				cn = this.getConnection();
				st = cn.createStatement();
				
				String sql ="INSERT INTO CLIENTES VALUES ('"+cliente.getNif() + "','"+cliente.getNombreCompleto()+ "',')";
		
				st.execute(sql);
		}catch (Exception e) {
			
			System.out.println("Error al insertar cliente " + e.getMessage());
		}finally{
			
			try {
				cn.close();
			}catch (SQLException e) {
				e.printStackTrace();
			
			}
		
		}
	}
	

	@Override
	public Cliente read(String nif) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Cliente cliente) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String dni) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Connection getConnection() throws IOException {

		Connection cn = null;
		
		try {
			Class.forName(MYSQL_JDBC_DRIVER);
			
			cn = DriverManager.getConnection(JDBC_CADENA_CONEXION);
		}catch(Exception e){
			
			System.out.println("Error al obtener la conexión.");
			
		}
			
		

		return cn;
	}

	@Override
	public Map<String, Cliente> readClientes() {
		// TODO Auto-generated method stub
		return null;
	}

}

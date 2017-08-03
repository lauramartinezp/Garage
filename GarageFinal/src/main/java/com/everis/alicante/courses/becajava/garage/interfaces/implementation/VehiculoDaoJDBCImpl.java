package com.everis.alicante.courses.becajava.garage.interfaces.implementation;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.everis.alicante.courses.becajava.garage.domain.Vehiculo;
import com.everis.alicante.courses.becajava.garage.interfaces.VehiculoDaoJDBC;

public class VehiculoDaoJDBCImpl implements VehiculoDaoJDBC {
	
	private final String MYSQL_JDBC_DRIVER ="com.mysql.jdbc.Driver";
	private final String JDBC_CADENA_CONEXION ="jdbc:mysql://localhost:3306/garage";
	private final String JDBC_USR = "root";
	private final String JDBC_PWD = "";

	@Override
	public void create(Vehiculo vehiculo) throws IOException {

		Connection cn = null;
		//Statement st = null;
		
		try {
			
				
				String sql ="INSERT INTO vehiculo " + " VALUES (?,?)";
				Connection cn = this.getConnection();
				
				PreparedStatement pst = cn.prepareStatement(sql);
				pst.setString(1, vehiculo.getMatricula());
				pst.setInt(2, vehiculo.getIdTipoVehiculo());
				
				pst.execute();
				
				//String sql ="INSERT INTO vehiculo(MATRICULA, TIPO_VEHICULO) "+" VALUES ('"+vehiculo.getMatricula() + "','"+vehiculo.getIdTipoVehiculo()+ "')";
		
				//int result = st.executeUpdate(sql);
		}catch (Exception e) {
			
			System.out.println("Error al insertar vehiculo " + e.getMessage());
		}finally{
			
			try {
				cn.close();
			}catch (SQLException e) {
				System.out.println("Error al conectar " + e.getMessage());
				e.printStackTrace();
			
			}
		
		}
	}

	@Override
	public Vehiculo read(String matricula) throws IOException {
		
		Vehiculo vehiculo = null;
		try {
			
			String sql ="SELECT * FROM vehiculo, tipos_vehiculo WHERE TIPO_VEHICULO = ID_TIPO AND MATRICULA = ?";
			Connection cn = this.getConnection();
			
			PreparedStatement pst = cn.prepareStatement(sql);
			pst.setString(1, matricula);
			
			
			pst.execute();
			//Statement st = cn.createStatement();
			//String sql = "SELECT * FROM vehiculo, tipos_vehiculo WHERE TIPO_VEHICULO = ID_TIPO AND MATRICULA = '"+matricula+"'";
			ResultSet rs = pst.executeQuery(sql);
			
			while (rs.next()) {
				vehiculo = new Vehiculo();
				vehiculo.setMatricula(rs.getString("MATRICULA"));
				vehiculo.setIdTipoVehiculo(rs.getInt("TIPO_VEHICULO"));
				vehiculo.setTipoVehiculo(rs.getString("NOMBRE_TIPO"));
				
				
				
			}
			
		}catch(SQLException e){
			e.printStackTrace();
			
		}finally {
			try {
				cn.close();
			}catch(SQLException e){
				System.out.println("Error al cerrar la conexion ");
			}
			
		}
		
		return vehiculo;
	}

	@Override
	public void update(Vehiculo vehiculo) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String matricula) throws IOException {

		Connection cn = null;
		Statement st = null;
		
		try {
			
				cn = this.getConnection();
				st = cn.createStatement();
				
				String sql ="DELETE FROM vehiculo WHERE MATRICULA = '"+matricula+"'";
		
				st.execute(sql);
		}catch (Exception e) {
			
			System.out.println("Error al elimiar vehiculo " + e.getMessage());
		}finally{
			
			try {
				cn.close();
			}catch (SQLException e) {
				System.out.println("Error al conectar " + e.getMessage());
				e.printStackTrace();
			
			}
		
		}
		
	}

	@Override
	public Connection getConnection() throws IOException {
		
		Connection cn = null;
		
		try {
			Class.forName(MYSQL_JDBC_DRIVER);
			
			cn = DriverManager.getConnection(JDBC_CADENA_CONEXION, JDBC_USR, JDBC_PWD);
		}catch(Exception e){
			
			System.out.println("Error al obtener la conexión.");
			
		}
			
		

		return cn;
	}

	@Override
	public List<Vehiculo> readVehiculos() throws IOException {
		
		List<Vehiculo> vehiculos = new ArrayList<>();
		
		Connection cn = null;
		Statement st = null;
		Vehiculo vehiculo = null;
		
		try {
			
			cn = this.getConnection();
			st = cn.createStatement();
			String sql = "SELECT * FROM vehiculo, tipos_vehiculo WHERE TIPO_VEHICULO = ID_TIPO AND MATRICULA ";
		
			ResultSet rs=st.executeQuery(sql);
			
			
			while (rs.next()) {
				vehiculo = new Vehiculo();
				vehiculo.setMatricula(rs.getString("MATRICULA"));
				vehiculo.setIdTipoVehiculo(rs.getInt("TIPO_VEHICULO"));
				vehiculo.setTipoVehiculo(rs.getString("NOMBRE_TIPO"));
				
				vehiculos.add(vehiculo);
				
			}
			
			
		}catch (Exception e) {
			
			System.out.println("Error al mostrar " + e.getMessage());
		}finally{
			
			try {
				cn.close();
			}catch (SQLException e) {
				System.out.println("Error al conectar " + e.getMessage());
				e.printStackTrace();
			
			}
		
		
		}
		return vehiculos;
	
	}
}

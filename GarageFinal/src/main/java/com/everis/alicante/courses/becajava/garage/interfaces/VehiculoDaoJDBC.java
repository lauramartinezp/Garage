package com.everis.alicante.courses.becajava.garage.interfaces;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import com.everis.alicante.courses.becajava.garage.domain.Vehiculo;

public interface VehiculoDaoJDBC {
	
	public void create(Vehiculo vehiculo) throws IOException;
	public Vehiculo read (String matricula) throws IOException;
	public void update (Vehiculo vehiculo) throws IOException;
	public void delete (String matricula) throws IOException;
	public Connection getConnection () throws IOException;
	List<Vehiculo> readVehiculos() throws IOException;

}

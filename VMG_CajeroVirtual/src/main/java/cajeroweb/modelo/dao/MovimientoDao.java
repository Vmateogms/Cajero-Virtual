package cajeroweb.modelo.dao;

import java.util.List;
import cajeroweb.modelo.entidades.Movimiento;

public interface MovimientoDao {
	
	int insertMovimiento(Movimiento movimiento); //insertar el movimiento
	List<Movimiento> buscarMovimientosPorCuenta(int cuentaId); //guardar el movimiento en una lista
	
	
}


package cajeroweb.modelo.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import cajeroweb.modelo.entidades.Movimiento;
import cajeroweb.modelo.repository.MovimientoRepository;

@Repository
public class MovimientoDaoImplJpaMy8 implements MovimientoDao{

	@Autowired
	private MovimientoRepository mrepo;

	@Override
	public int insertMovimiento(Movimiento movimiento) {
		   // Guarda el movimiento en la base de datos
		 Movimiento movimientoGuardado = mrepo.save(movimiento);

	        // Retorna el ID del movimiento guardado
	        return movimientoGuardado.getIdMovimiento();
		
	}

	@Override
	public List<Movimiento> buscarMovimientosPorCuenta(int cuentaId) {
		// TODO Auto-generated method stub
		return mrepo.findByMovimientoEnCuenta(cuentaId);
	

}
}
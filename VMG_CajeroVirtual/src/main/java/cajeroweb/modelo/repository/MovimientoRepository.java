package cajeroweb.modelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cajeroweb.modelo.entidades.Movimiento;

@Repository
public interface MovimientoRepository extends JpaRepository <Movimiento, Integer> {

	//hacemos una consulta personalizada buscando los movimientos de una cuenta en especifico 
	@Query("select m from Movimiento m where m.cuenta.idCuenta = ?1       ") 
	List<Movimiento> findByMovimientoEnCuenta(int idCuenta);
	
}

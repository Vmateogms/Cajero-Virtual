package cajeroweb.modelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cajeroweb.modelo.entidades.Cuenta;

@Repository
public interface CuentaRepository  extends JpaRepository <Cuenta, Integer> {

	public Cuenta findById (int idCuenta); //metodo que usamos para buscar una cuenta por el ID
	
}

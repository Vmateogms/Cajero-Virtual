package cajeroweb.modelo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cajeroweb.modelo.entidades.Cuenta;
import cajeroweb.modelo.repository.CuentaRepository;

@Repository
public class CuentaDaoImplJpaMy8 implements CuentaDao {

	 @Autowired
	 private CuentaRepository crepo;

	@Override
	public int modificarCuenta(Cuenta cuenta) {
		crepo.save(cuenta); //guardamos los cambios en la bbdd via el repositorio
		return 0;  
	}

	@Override
	public Cuenta buscarUna(int IdCuenta) {
		return crepo.findById(IdCuenta); //buscamos una cuenta por el ID en la bbdd
	}

	
	 
	 
}

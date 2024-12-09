package cajeroweb.modelo.dao;

import cajeroweb.modelo.entidades.Cuenta;

public interface CuentaDao {

	int modificarCuenta(Cuenta cuenta); //modificar el saldo
	Cuenta buscarUna(int IdCuenta);


}
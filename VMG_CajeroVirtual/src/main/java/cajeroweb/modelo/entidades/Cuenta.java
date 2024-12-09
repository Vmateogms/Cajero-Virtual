package cajeroweb.modelo.entidades;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="cuentas")
public class Cuenta implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id_cuenta")
	private int idCuenta;
	private double saldo;
	@Column (name="tipo_cuenta")
	private String tipoCuenta;
	
	
	public int getIdCuenta() {
		return idCuenta;
	}
	public void setIdCuenta(int idCuenta) {
		this.idCuenta = idCuenta;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	public String getTipoCuenta() {
		return tipoCuenta;
	}
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}

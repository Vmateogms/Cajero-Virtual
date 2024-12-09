package cajeroweb.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cajeroweb.modelo.dao.CuentaDao;
import cajeroweb.modelo.dao.MovimientoDao;
import cajeroweb.modelo.entidades.Cuenta;
import cajeroweb.modelo.entidades.Movimiento;
import jakarta.servlet.http.HttpSession;

@Controller
public class MovimientoController {

	@Autowired
	private CuentaDao cdao;
	
	 @Autowired 
	 private MovimientoDao mdao;
	
	 
	 @PostMapping("/Ingresar")
	 public String ingresarDinero(@RequestParam double cantidad, HttpSession sesion, RedirectAttributes ratt) {
		 Cuenta cuenta = (Cuenta) sesion.getAttribute("cuenta");

	 
	 if(cuenta !=null) {
		 cuenta.setSaldo(cuenta.getSaldo() + cantidad); //sumo dinero a el saldo de la cuenta
		 cdao.modificarCuenta(cuenta); //actualizo la cuenta en la bbdd
		 Movimiento mov = new Movimiento();//Creamos objeto Movimiento
		 mov.setCuenta(cuenta); //asignamos la cuenta al movimiento
		 mov.setCantidad(cantidad);
		 mov.setOperacion("Ingreso"); //guardo el tipo de operacion que en este caso es el ingreso
		 mov.setFecha(new Date(System.currentTimeMillis())); //se guarda la fecha de el movimiento que se ha hecho
		 mdao.insertMovimiento(mov); //y el movimiento se inserta en la bbddd¡
		 ratt.addFlashAttribute("mensaje", "Has ingresado " + cantidad + " euros correctamente.");
		 
	  }
	 
	  return "redirect:/movimientos";
	
   }
	
	 @PostMapping("/extraer")
	 public String extraerDinero(@RequestParam double cantidad, HttpSession sesion, RedirectAttributes ratt) {
		 Cuenta cuenta = (Cuenta) sesion.getAttribute("cuenta");

		
		if (cuenta != null && cuenta.getSaldo() >= cantidad){
			
			 cuenta.setSaldo(cuenta.getSaldo() - cantidad);   //resto dinero al saldo
			 cdao.modificarCuenta(cuenta); //actualizo la cuenta en la bbdd
			 
			 Movimiento mov = new Movimiento(); //Creamos objeto Movimiento
			 mov.setCuenta(cuenta);
			 mov.setCantidad(cantidad);
			 mov.setOperacion("Extraer"); 
			 mov.setFecha(new Date(System.currentTimeMillis())); 
			 mdao.insertMovimiento(mov);
			 
			 ratt.addFlashAttribute("mensaje", "Extraccion de " + cantidad + " realizado con exito");
			 
		} else {
			ratt.addFlashAttribute("mensaje","Saldo insuficiente"); //si la cantidad que se quiere extraer es mayor que el saldo dara error
		}
		 return "redirect:/movimientos";
	 }
	 
	 @PostMapping("/transferir")
	 public String TransferirDinero(@RequestParam double cantidad, @RequestParam int cuentaDestinoId, HttpSession sesion, RedirectAttributes ratt) {

		 Cuenta cuentaOrigen = (Cuenta) sesion.getAttribute("cuenta");
		 Cuenta cuentaDestino = cdao.buscarUna(cuentaDestinoId); //utilizamos buscarUna para buscar por id a la cuenta de destino
		 
		 if (cuentaDestino == null) {
			 ratt.addFlashAttribute("mensaje","La cuenta de destino no existe");
			 return "redirect:/movimientos";
		 }
		
		if (cuentaOrigen != null && cuentaOrigen.getSaldo() >= cantidad){ //si la cuenta de origen existe y su saldo es mayor o igual a la cantidad que vamos a utilizar
			
			 cuentaOrigen.setSaldo(cuentaOrigen.getSaldo() - cantidad);   //resto dinero al saldo
			 cdao.modificarCuenta(cuentaOrigen); //actualizo la cuenta en la bbdd
			 
			 Movimiento movOrigen = new Movimiento(); //creamos dos objetos Movimiento (movOrigen y movDestino) para las operaciones de origen y destino y cada objeto con detalles de cuenta asociada, la cantidad transferida, la operación realizada, y la fecha
			 movOrigen.setCuenta(cuentaOrigen);
			 movOrigen.setCantidad(cantidad);
			 movOrigen.setOperacion("Extracto por transferencia"); 
			 movOrigen.setFecha(new Date(System.currentTimeMillis())); 
			 mdao.insertMovimiento(movOrigen);
			 
			 cuentaDestino.setSaldo(cuentaDestino.getSaldo() + cantidad);
			 cdao.modificarCuenta(cuentaDestino);
			 
			 Movimiento movDestino = new Movimiento();
			 movDestino.setCuenta(cuentaDestino);
			 movDestino.setCantidad(cantidad);
			 movDestino.setOperacion("Ingreso por transferencia");
			 movDestino.setFecha(new Date(System.currentTimeMillis()));
			 mdao.insertMovimiento(movDestino);
			 
			 ratt.addFlashAttribute("mensaje", "Envio de " + cantidad + " realizado con exito");
			 
		} else {
			ratt.addFlashAttribute("mensaje","Saldo insuficiente"); //si la cantidad que se quiere extraer es mayor que el saldo dara error
		}
		 return "redirect:/movimientos";
	 }
	 
	 
	
	 @GetMapping("/movimientos")
	 public String verMovimientos(HttpSession session, Model model)  {
		Cuenta cuenta = (Cuenta) session.getAttribute("cuenta");
		 
		if(cuenta != null) {
			List<Movimiento> movimientos = mdao.buscarMovimientosPorCuenta(cuenta.getIdCuenta());
			model.addAttribute("movimientos", movimientos);
			model.addAttribute("saldo", cuenta.getSaldo());
			return "verMovimientos";
			
		} else {
			return "redirect:/login";
		}
		
	 }
	 
}







 


















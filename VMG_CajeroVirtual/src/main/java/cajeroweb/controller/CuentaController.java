package cajeroweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cajeroweb.modelo.dao.CuentaDao;
import cajeroweb.modelo.entidades.Cuenta;
import jakarta.servlet.http.HttpSession;

@Controller
public class CuentaController {
	
	@Autowired
	private CuentaDao cdao;
	
	@GetMapping({"","/","/home"})
	public String inicio() {
		return "home";
	}
	
	
	@GetMapping("/login")
	public String mostrarFormLogin() {
			return "FormLogin";
		}
		
	
	@PostMapping("/login")
	public String procesarFormLogin(@RequestParam int numeroCuenta,HttpSession sesion,RedirectAttributes ratt) {
		
		Cuenta cuenta = cdao.buscarUna(numeroCuenta);
		
		if (cuenta != null) {    //logica del login
			sesion.setAttribute("cuenta", cuenta); // guardo la cuenta en la sesión
			ratt.addFlashAttribute("nombreCuenta", cuenta.getIdCuenta());
			return "redirect:/";
			
		}else {
			ratt.addFlashAttribute("mensaje", "Numero de cuenta incorrecto");
			return "redirect:/login"; //si la cuenta no existe te devuelve al login con un mensaje de error
			
		}

	}
	
	

	@GetMapping("/logout")   //si cerramos sesion hacemos primero remove el atributo luego invalidar 
	public String cerrarSesion(HttpSession sesion) {
		sesion.removeAttribute("cuenta");
		sesion.invalidate();
		return "redirect:/login";
	}

}

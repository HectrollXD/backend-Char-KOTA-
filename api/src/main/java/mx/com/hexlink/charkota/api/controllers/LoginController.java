package mx.com.hexlink.charkota.api.controllers;

import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mx.com.hexlink.charkota.api.restful.requests.AuthUserRequest;
import mx.com.hexlink.charkota.api.restful.requests.RefreshTokenRequest;
import mx.com.hexlink.charkota.api.restful.responses.AuthUserResponse;
import mx.com.hexlink.charkota.api.restful.responses.GenericResponse;
import mx.com.hexlink.charkota.api.restful.responses.IsAuthUserResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("/user")
@Tag(name = "Users", description = "All endpoints for user actions")
public class LoginController {
	// TODO: Realizar servicio para estos controladores.
	//---------------------------------------------------------------------------------------------- Controlador para iniciar sesión
	@PostMapping("/auth")
	@Operation(
		summary = "Iniciar sesión",
		description = """
			Método para iniciar sesión en el sistema y generar un token para el acceso a los
			endpoints del API. Este puede ser mediante usuario o correo electrónico; uno de los dos
			puede quedar vacío para el método del login pero por lo menos uno debe de venir.
			"""
	)
	public GenericResponse<AuthUserResponse> authUser(@RequestBody AuthUserRequest request) {
		return null;
	}

	//---------------------------------------------------------------------------------------------- Controlador para validar si hay sesión abierta.
	@PostMapping("/is-auth")
	@Operation(
		summary = "Validar sesión",
		description = """
			Método para validar si el usuario cuenta con sesión abierta y en caso que si cuente
			con una sesión abieta, retorna un token si las credenciales coinciden.  Este puede ser
			mediante usuario o correo electrónico.
			"""
	)
	public GenericResponse<IsAuthUserResponse> isAuth(@RequestBody AuthUserRequest request) {
		return null;
	}

	//---------------------------------------------------------------------------------------------- Controlador para obtenr un nuevo refresh token
	@GetMapping("/refresh-token")
	@Operation(
		summary = "Refrescar el token",
		description = """
			Método para refrescar el token cuando el anterior expire. Para ello, es neceario enviar
			un refresh token válido que se retrornó cuando se inició sesión 
			"""
	)
	public GenericResponse<IsAuthUserResponse> getRefreshToken(@RequestBody RefreshTokenRequest request) {
		return null;
	}

	//---------------------------------------------------------------------------------------------- Controlador para deslogear
	@PostMapping("/logout")
	@Operation(
		summary = "Cerrar sesión",
		description = """
			Método para cerrar la sesión en el sistema.
			"""
	)
	public GenericResponse<?> logout() {
		return null;
	}
}

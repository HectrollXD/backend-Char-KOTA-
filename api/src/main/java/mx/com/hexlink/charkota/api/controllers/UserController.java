package mx.com.hexlink.charkota.api.controllers;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mx.com.hexlink.charkota.api.restful.data.UserData;
import mx.com.hexlink.charkota.api.restful.enums.Status;
import mx.com.hexlink.charkota.api.restful.requests.UserRequest;
import mx.com.hexlink.charkota.api.restful.responses.GenericResponse;
import mx.com.hexlink.charkota.api.restful.responses.UserResponse;
import mx.com.hexlink.charkota.data.entities.User;
import mx.com.hexlink.charkota.data.services.UserService;



@RestController
@RequestMapping("/users")
@Tag(name = "Users", description = "Endpoints fro admin all user data")
public class UserController {
	@Autowired
	private UserService userService;



	// TODO: Encriptar contraseña.
	//---------------------------------------------------------------------------------------------- Get users
	/**
	 * Método para obtener la lista de los usuarios agregados. Este puede regresar por nombre de 
		usuario o por ID del usuario. Si vienen ambos, se le dará prioridad al ID.

	 * @param userId Id del usuario deseado (opcional).
	 * @param username Nombre de usuario a buscar (opcional).
	 * @return Objeto de respuesta con los datos de los usuarios.
	 */
	@GetMapping
	@Operation(
		summary = "Obtener usuarios",
		description = """
		Método para obtener la lista de los usuarios agregados. Este puede regresar por nombre de 
		usuario o por ID del usuario. Si vienen ambos, se le dará prioridad al ID.
		"""
	)
	public GenericResponse<UserResponse> getUsersData(
		@RequestParam(required = false) UUID userId, @RequestParam(required = false) String username
	){
		List<User> users;

		// Preguntamos si hay búsqueda por ID para darle preferencia, sino por nombre de usuario
		// y en caso de ininguno, se obtendrán todos.
		if( Objects.nonNull(userId) ){
			User user = userService.getById(userId);

			users = Objects.nonNull(user) ? List.of(user) : List.of();
		}
		else if( Objects.nonNull(username) ){
			users = userService.getLikeUsername(username);
		}
		else{
			users = userService.getAll();
		}

		// Creamos y retronamos el objeto de respuesta con los datos correspondientes.
		return new GenericResponse<UserResponse>(
			Status.OK.isSuccess(),
			Status.OK.getDescription(),
			UserResponse.fromUsers(users)
		);
	}

	//---------------------------------------------------------------------------------------------- Add new user
	/**
	 * Método para crear un usuario y la información de la persona asociada a la cuenta.
	 * 
	 * @param request Datos del usuario que se desea agregar.
	 * @return Objeto de respuesta con los datos del usuario agregado.
	 */
	@PostMapping
	@Operation(
		summary = "Crear usuarios",
		description = """
		Método para crear un usuario y la información de la persona asociada a la cuenta.
		"""
	)
	public GenericResponse<UserData> getUsersData(@RequestBody UserRequest request){
		// Guardamos al usuario nuevo.
		User userAdded = userService.saveData(request.toUser());

		// Creamos la respueta con los datos necesarios y los regresamos
		return new GenericResponse<>(
			Status.OK.isSuccess(),
			Status.OK.getDescription(),
			UserData.fromUser(userAdded)
		);
	}
}

package mx.com.hexlink.charkota.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import mx.com.hexlink.charkota.api.restful.data.OwnerData;
import mx.com.hexlink.charkota.api.restful.enums.Status;
import mx.com.hexlink.charkota.api.restful.requests.OwnerRequest;
import mx.com.hexlink.charkota.api.restful.responses.OwnerResponse;
import mx.com.hexlink.charkota.data.entities.Owner;
import mx.com.hexlink.charkota.data.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import mx.com.hexlink.charkota.api.restful.responses.GenericResponse;
import java.util.List;
import java.util.Objects;
import java.util.UUID;



@RestController
@RequestMapping("/owners")
@Tag(name = "Owners", description = "Endpoints for admin all owners data")
public class OwnerController {
	@Autowired
	private OwnerService ownerService;



	//---------------------------------------------------------------------------------------------- Controlador para agregar un propietario.
	/**
	 * Controlador poara realir el registro de un propietario en específico.
	 *
	 * @param request Objeto con los datos necesarios para agregar un nuevo propietario.
	 * @return Objeto de respuesta con los datos del propietario creado en la base de datos.
	 */
	@PostMapping
	@Operation(
		summary = "Agregar propietario",
		description = """
		Método para realir el registro de un propietario en específico.
		"""
	)
	public GenericResponse<OwnerData> createOwner(@RequestBody OwnerRequest request) {
		// Agregamos el propietario.
		Owner ownerAdded = ownerService.saveData(request.toOwner());

		//Creamos la respuesta y seteamos sus atributos.
		GenericResponse<OwnerData> response = new GenericResponse<>();

		response.setStatus(Status.OK.isSuccess());
		response.setMessage(Status.OK.getDescription());
		response.setData(OwnerData.fromOwner(ownerAdded));

		// Retornamos la respuesta.
		return response;
	}

	//---------------------------------------------------------------------------------------------- Controlador para modificar un propietario.
	@PutMapping("/{ownerId}")
	@Operation(
		summary = "Modificar propietario",
		description = """
		Método para modificar el registro de un propietario en específico.
		"""
	)
	public GenericResponse<OwnerData> editOwner(
		@PathVariable UUID ownerId, @RequestBody OwnerRequest request,
		HttpServletResponse httpResponse
	) {
		// Buscamos el propietario
		Owner ownerToEdit = ownerService.getById(ownerId);

		//Creamos la respuesta.
		GenericResponse<OwnerData> response = new GenericResponse<>();

		// procesamos si existe. Si no, terminamos el proceso.
		if( Objects.isNull(ownerToEdit) ){
			// Seteamos los atributos de respuesta.
			httpResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
			response.setStatus(Status.NOT_FOUND.isSuccess());
			response.setMessage(Status.NOT_FOUND.getDescription());

			return response;
		}

		// Seteamos los valores.
		ownerToEdit.setName(request.getName().trim().toUpperCase());
		ownerToEdit.setLastname(request.getLastname().trim().toUpperCase());
		ownerToEdit.setPhone(request.getPhone().trim());
		ownerToEdit.setEmail(request.getEmail().trim());
		ownerToEdit.setAddress(request.getAddress().trim().toUpperCase());

		// Guardamos los cambios.
		Owner ownerSaved = ownerService.saveData(ownerToEdit);

		response.setStatus(Status.OK.isSuccess());
		response.setMessage(Status.OK.getDescription());
		response.setData(OwnerData.fromOwner(ownerSaved));

		// Retornamos la respuesta.
		return response;
	}

	//---------------------------------------------------------------------------------------------- Controlador para obtener los propietarios registrados.
	/**
	 * Método enfocado a la obtención de los datos de los propietarios guardados en la base de
	 * datos. Este se realiza mediate el ID del propietario o por el nombre. En caso de no venir
	 * ninguno, arroja todos los propietarios registrados en la base de datos.
	 *
	 * @param id ID del propietario.
	 * @param name Nombre del propietario.
	 * @return Lista con las coincidencias de propietarios encontrados.
	 */
	@GetMapping
	@Operation(
		summary = "Propietarios registrados",
		description = """
		Método enfocado a la obtención de los datos de los propietarios guardados en la base de
		datos. Este se realiza mediate el ID del propietario o por el nombre. En caso de no venir
		ninguno, arroja todos los propietarios registrados en la base de datos.
		"""
	)
	public GenericResponse<OwnerResponse> getOwnersData(
		@RequestParam(required = false) UUID id, @RequestParam(required = false) String name
	) {
		// Declaramos la lista de propietarios.
		List<Owner> owners;

		// Comparamos querys del request dando preferencia al ID y obtenemos los datos dependiendo del caso.
		if( Objects.nonNull(id) ){
			Owner owner = ownerService.getById(id);

			owners = Objects.isNull(owner) ? List.of() : List.of(owner);
		}
		else if( Objects.nonNull(name) ){
			owners = ownerService.getByName(name.trim().toUpperCase());
		}
		else{
			owners = ownerService.getAll();
		}

		//Creamos la respuesta y seteamos sus atributos.
		GenericResponse<OwnerResponse> response = new GenericResponse<>();

		response.setStatus(Status.OK.isSuccess());
		response.setMessage(Status.OK.getDescription());
		response.setData(OwnerResponse.fromOwners(owners));

		// Retornamos la respuesta.
		return response;
	}
}

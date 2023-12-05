package mx.com.hexlink.charkota.api.controllers;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mx.com.hexlink.charkota.api.restful.enums.Status;
import mx.com.hexlink.charkota.api.restful.requests.CreateMultipleProviderRequest;
import mx.com.hexlink.charkota.api.restful.responses.GenericResponse;
import mx.com.hexlink.charkota.api.restful.responses.ProviderResponse;
import mx.com.hexlink.charkota.data.entities.Provider;
import mx.com.hexlink.charkota.data.services.ProviderService;



@RestController
@RequestMapping("/providers")
@Tag(name = "Providers", description = "Endpoints for admin providers data")
public class ProviderController {
	@Autowired
	private ProviderService providerService;



	//---------------------------------------------------------------------------------------------- Create multiple providers
	/**
	 * Método para crear uno o múltiples proveedores.
	 * 
	 * @param request Objeto con la lista de los proveedores a agregar.
	 * @return Respuesta con los datos de los proveedores creados.
	 */
	@PostMapping
	@CrossOrigin
	@Operation(
		summary = "Crear multiples proveedores",
		description = """
		Método para crear uno o múltiples proveedores.
		"""
	)
	public GenericResponse<ProviderResponse> createMultipleProviders(
		@RequestBody CreateMultipleProviderRequest request
	){
		// Guardamos la lista de proveedores en la base de datos.
		List<Provider> providersSaved = providerService.saveMultipleData(
			request.toProviders()
		);

		//Creamos la respuesta con sus atributos y retornamos la respuesta.
		return new GenericResponse<>(
			Status.OK.isSuccess(),
			Status.OK.getDescription(),
			ProviderResponse.fromProviders(providersSaved)
		);
	};

	//---------------------------------------------------------------------------------------------- Get providers
	/**
	 * Método para la obtención de todos los proveedores agregados en la base de datos.
	 *	Este recibe como parámetros el ID del proveedor y el nombre, Si se envian ambos,. se le dará
	 *	preferencia al ID.
	 * 
	 * @param providerId Id del proveedor para realizar una búsqueda (opcional)
	 * @param providerName Nombre del proveedor para realizar una búsqueda (opcional)
	 * @return Objeto de respuesta con la lista de los proveedores encontrados.
	 */
	@GetMapping
	@CrossOrigin
	@Operation(
		summary = "Lista de proveedores",
		description = """
		Método para la obtención de todos los proveedores agregados en la base de datos.
		Este recibe como parámetros el ID del proveedor y el nombre, Si se envian ambos,. se le dará
		preferencia al ID.
		"""
	)
	public GenericResponse<ProviderResponse> getAllProviders(
		@RequestParam(required = false) UUID providerId,
		@RequestParam(required = false) String providerName
	) {
		// Creamos la lista de respuesta.
		List<Provider> providers;

		// Preguntamos si hay algún parametro de búsqueda y obtenemos la información dependiendo
		// del caso.
		if ( Objects.nonNull(providerId) ) {
			Provider provider = providerService.getById(providerId);

			providers = Objects.nonNull(provider) ? List.of(provider) : List.of();
		}
		else if ( Objects.nonNull(providerName) ) {
			providers = providerService.getLikeName(providerName.trim().toUpperCase());
		}
		else{
			providers = providerService.getAll();
		}

		// Retornamos la respuesta con los datos de los datos obtenidos.
		return new GenericResponse<>(
			Status.OK.isSuccess(),
			Status.OK.getDescription(),
			ProviderResponse.fromProviders(providers)
		);
	}
}

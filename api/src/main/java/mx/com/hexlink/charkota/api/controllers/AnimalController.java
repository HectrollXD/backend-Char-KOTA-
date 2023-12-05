package mx.com.hexlink.charkota.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mx.com.hexlink.charkota.api.restful.data.AnimalBreedData;
import mx.com.hexlink.charkota.api.restful.data.AnimalFamilyData;
import mx.com.hexlink.charkota.api.restful.enums.Status;
import mx.com.hexlink.charkota.api.restful.requests.AnimalBreedRequest;
import mx.com.hexlink.charkota.api.restful.requests.CreateMultipleAnimalBreedRequest;
import mx.com.hexlink.charkota.api.restful.requests.CreateMultipleAnimalFamilyRequest;
import mx.com.hexlink.charkota.api.restful.requests.DeleteMultipleAnimalBreedsRequest;
import mx.com.hexlink.charkota.api.restful.requests.DeleteMultipleAnimalFamilyRequest;
import mx.com.hexlink.charkota.api.restful.responses.AnimalBreedResponse;
import mx.com.hexlink.charkota.api.restful.responses.AnimalFamilyResponse;
import mx.com.hexlink.charkota.api.restful.responses.GenericResponse;
import mx.com.hexlink.charkota.data.entities.AnimalBreed;
import mx.com.hexlink.charkota.data.entities.AnimalFamily;
import mx.com.hexlink.charkota.data.services.AnimalBreedService;
import mx.com.hexlink.charkota.data.services.AnimalFamilyService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/animals")
@Tag(
	name = "Animals",
	description = "Endpoints for admin all animal data"
)
public class AnimalController {
	@Autowired
	private AnimalBreedService animalBreedService;
	@Autowired
	private AnimalFamilyService animalFamilyService;



	//---------------------------------------------------------------------------------------------- Controlador para crear familia de animales.
	/**
	 * Controlador enfocado para crear una o varias familias de animales en la base de datos.
	 * 
	 * @param request Objeto que contiene la lista de las familias a agregar.
	 * @return Respueta de las familias agregadas.
	 */
	@PostMapping("/families")
	@CrossOrigin
	@Operation(
		summary = "Crear familias de animales",
		description = """
		Método para crear una o varias familias de animales. Tipo canino para referirse a todos los
		animales mamíferos cuadrúpedos pertenecientes a los perros, lobos, coyotes, etc.
		"""
	)
	public GenericResponse<AnimalFamilyResponse> createMultipleFamiliest(
		@RequestBody CreateMultipleAnimalFamilyRequest request
	){
		// Pasamos los objetos del request a una entidad de la base de datos.
		List<AnimalFamily> familiesToAdd = request.getFamilies().stream().map(
			obj -> obj.toAnimalFamily()
		).collect(Collectors.toList());

		// Guardamos la lista anterior en la base de datos.
		List<AnimalFamily> familiesSaved = animalFamilyService.saveMultipleData(familiesToAdd);

		//Creamos la respuesta y seteamos sus atributos.
		GenericResponse<AnimalFamilyResponse> response = new GenericResponse<>();

		response.setStatus(Status.OK.isSuccess());
		response.setMessage(Status.OK.getDescription());
		response.setData(AnimalFamilyResponse.fromAnimalFamily(familiesSaved));

		// Retornamos la respuesta.
		return response;
	};

	//---------------------------------------------------------------------------------------------- Controlador para obtener las familias de animales.
	/**
	 * Controlador para la obtención de todas las familias de animales agregadas en la base de datos
	 * 
	 * @return Objeto de respuesta con la lista de todas las familias de animales registradas.
	 */
	@GetMapping("/families")
	@CrossOrigin
	@Operation(
		summary = "Lista de familias de animales",
		description = """
		Método para la obtención de todas las familias de animales agregadas en la base de datos.
		"""
	)
	public GenericResponse<List<AnimalFamilyData>> getAllAnimalFamilies() {
		//Creamos la respuesta y seteamos sus atributos.
		GenericResponse<List<AnimalFamilyData>> response = new GenericResponse<>();

		response.setStatus(Status.OK.isSuccess());
		response.setMessage(Status.OK.getDescription());
		// Obtenemos las familias y las seteamos directamente en la respuesta.
		response.setData(
			animalFamilyService.getAll().stream().map(
				AnimalFamilyData::fromAnimalFamily
			).collect(Collectors.toList())
		);

		// Retornamos la respuesta.
		return response;
	}

	//---------------------------------------------------------------------------------------------- Controlador para eliminar familias de animales.
	/**
	 * Controlador para la eliminación de una o múltiples familias de animales de la base de datos.
	 * 
	 * @param request Objeto con la lista de los ID's de las familias de animales a eliminar.
	 * @return Objeto de respuesta con la confirmación de si se realizó.
	 */
	@DeleteMapping("/families")
	@CrossOrigin
	@Operation(
		summary = "Eliminar familias de animales",
		description = """
		Método para la eliminación de una o múltiples familias de animales de la base de datos.
		"""
	)
	public GenericResponse<List<AnimalFamilyData>> deleteMultipleAnimalFamilies(
		@RequestBody DeleteMultipleAnimalFamilyRequest request
	) {
		// Eliminamos los registros solicitados con los ID otorgados.
		animalFamilyService.deleteAllById(request.getFamiliesId());

		//Creamos la respuesta y seteamos sus atributos.
		GenericResponse<List<AnimalFamilyData>> response = new GenericResponse<>();

		response.setStatus(Status.DELETED.isSuccess());
		response.setMessage(Status.DELETED.getDescription());
		// Obtenemos las familias y las seteamos directamente en la respuesta.
		response.setData(null);

		// Retornamos la respuesta.
		return response;
	}

	//---------------------------------------------------------------------------------------------- Controlador para crear uno o n razas de perros
	/**
	 * Controlador para la creación de uno o varias razas de animales.
	 * 
	 * @param request Objeto que contiene una lista de razas a agregar.
	 * @return Objeto de respuesta con el status de si fué agregado y los datos agregados.
	 */
	@PostMapping("/breeds")
	@CrossOrigin
	@Operation(
		summary = "Crear razas de animales",
		description = """
		Endpoint para crear una o varias razas de animales. Estos no pueden estar ya agregadas con
		el mismo nombre y la misma familia de animales.
		"""
	)
	public GenericResponse<AnimalBreedResponse> createMultipleBreeds(
		@RequestBody CreateMultipleAnimalBreedRequest request
	) {
		// Obtenemos la lista de los ID's de las familias de animales a obtener de la base de datos.
		Set<UUID> animalFamilyIds = new HashSet<>(
			request.getAnimalBreedsList().stream().map(
				AnimalBreedRequest::getAnimalFamilyId
			).collect(Collectors.toList())
		);

		// Obtenemos los ID's de las familias necesarias.
		List<AnimalFamily> families = animalFamilyService.getAllByIds(
			new ArrayList<>(animalFamilyIds)
		);

		// Creamos la lista de las razas a agregar a la base de datos.
		List<AnimalBreed> animalBreedsToAdd = new ArrayList<>();

		// Convertimos los objetos de AnimalBreedData a una entidad de la base de datos.
		request.getAnimalBreedsList().forEach(obj -> {
			animalBreedsToAdd.add(
				obj.toAnimalBreed(
					families.stream().filter(
						fam -> fam.getId().equals(obj.getAnimalFamilyId())
					).findFirst().orElseThrow()
				)
			);
		});

		// Guardamos la entidad en la base de datos.
		List<AnimalBreed> animalBreedsSave = animalBreedService.saveMultipleData(animalBreedsToAdd);

		//Creamos la respuesta y seteamos sus atributos.
		GenericResponse<AnimalBreedResponse> response = new GenericResponse<>();

		response.setStatus(Status.OK.isSuccess());
		response.setMessage(Status.OK.getDescription());
		response.setData(AnimalBreedResponse.fromAnimalBreedsList(animalBreedsSave));

		// Retornamos la respuesta.
		return response;
	}

	//---------------------------------------------------------------------------------------------- Controlador para obtener la lista de razas agregadas.
	/**
	 * Controlador para la obtención de todas las razas de animales registradas en la base de datos.
	 * 
	 * @param familyId Objeto de tipo UUID que hace referencia al ID del registro de la familia de
	 * 					animales registrados.
	 * @return Objeto de respuesta con los registros de la base de datos.
	 */
	@GetMapping("/breeds")
	@CrossOrigin
	@Operation(
		summary = "Obtener todas las razas",
		description = """
		Método para la obtención de todas las razas de animales regitrados en la base de datos.
		Este recibe el ID de la famila a la que pertenece el animal y es un parámetro opcional.
		"""
	)
	public GenericResponse<List<AnimalBreedData>> getAllAnimalsBreeds(
		@RequestParam(required = false) UUID familyId
	) {
		List<AnimalBreedData> animalBreeds;

		if( Objects.isNull(familyId) ){
			// Obtenemos todos los datos de las razas de animales registradas.
		 	animalBreeds = animalBreedService.getAll().stream().map(
				AnimalBreedData::fromAnimalBreed
			).collect(Collectors.toList());
		}
		else{
			AnimalFamily family = new AnimalFamily(familyId);

			animalBreeds = animalBreedService.getByFamily(family).stream().map(
				AnimalBreedData::fromAnimalBreed
			).collect(Collectors.toList());
		}

		// Creamos el objeto de respuesta y seteamos sus atributos.
		GenericResponse<List<AnimalBreedData>> response = new GenericResponse<>();

		response.setStatus(Status.OK.isSuccess());
		response.setMessage(Status.OK.getDescription());
		response.setData(animalBreeds);

		// Retornamos la respuesta.
		return response;
	}

	//---------------------------------------------------------------------------------------------- Controlador para eliminar una o múltiples razas de animales.
	/**
	 * Controlador para eliminar uno o varios registros de razas de animales de la base de datos.
	 * 
	 * @param request Objeto con una lista de ID's de razas a eliminar.
	 * @return Objeto de respuesta generica sin datos.
	 */
	@DeleteMapping("/breeds")
	@CrossOrigin
	@Operation(
		summary = "Eliminar razas de animales",
		description = """
		Método para eliminar uno o varios registros de razas de animales de la base de datos
		mediante sus ID de registro.
		"""
	)
	public GenericResponse<?> deleteMultipleAnimalBreeds(
		@RequestBody DeleteMultipleAnimalBreedsRequest request
	){
		// Eliminamos los registros de la base de datos.
		animalBreedService.deleteAllById(request.getAnimalBreedsIds());

		// Creamos el objeto de respuesta y seteamos sus atributos.
		GenericResponse<List<AnimalBreedData>> response = new GenericResponse<>();

		response.setStatus(Status.DELETED.isSuccess());
		response.setMessage(Status.DELETED.getDescription());
		response.setData(null);

		// Retornamos la respuesta.
		return response;
	}
}

package mx.com.hexlink.charkota.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mx.com.hexlink.charkota.api.restful.data.AnimalBreedData;
import mx.com.hexlink.charkota.api.restful.enums.Status;
import mx.com.hexlink.charkota.api.restful.requests.AnimalBreedRequest;
import mx.com.hexlink.charkota.api.restful.requests.CreateMultipleAnimalBreedRequest;
import mx.com.hexlink.charkota.api.restful.requests.DeleteMultipleAnimalBreedsRequest;
import mx.com.hexlink.charkota.api.restful.responses.AnimalBreedResponse;
import mx.com.hexlink.charkota.api.restful.responses.GenericResponse;
import mx.com.hexlink.charkota.data.entities.AnimalBreed;
import mx.com.hexlink.charkota.data.entities.AnimalFamily;
import mx.com.hexlink.charkota.data.services.AnimalBreedService;
import mx.com.hexlink.charkota.data.services.AnimalFamilyService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
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



	// TODO: Pasar a un servicio o a un componente que nos permita llevar mejor el control de las excepciones para cuando no se agregan por cuestiones de uniques.
	//---------------------------------------------------------------------------------------------- Controlador para crear uno o n razas de perros
	/**
	 * Controlador para la creación de uno o varias razas de animales.
	 * 
	 * @param request Objeto que contiene una lista de razas a agregar.
	 * @return Objeto de respuesta con el status de si fué agregado y los datos agregados.
	 */
	@PostMapping("/breeds")
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
	
	// TODO: Pasar a un servicio o a un componente que nos permita llevar mejor el control de las excepciones para cuando no se agregan por cuestiones de uniques.
	//---------------------------------------------------------------------------------------------- Controlador para obtener la lista de razas agregadas.
	/**
	 * Controlador para la obtención de todas las razas de animales registradas en la base de datos.
	 * 
	 * @return Objeto de respuesta con los registros de la base de datos.
	 */
	@GetMapping("/breeds")
	@Operation(
		summary = "Obtener todas las razas",
		description = """
		Método para la obtención de todas las razas de animales regitrados en la base de datos.
		Este no discrimina entre una familia de animales (canino, felino, arácnido, etc).
		"""
	)
	public GenericResponse<List<AnimalBreedData>> getAllAnimalsBreeds() {
		// Obtenemos todos los datos de las razas de animales registradas.
		List<AnimalBreedData> animalBreeds = animalBreedService.getAll().stream().map(
			AnimalBreedData::fromAnimalBreed
		).collect(Collectors.toList());

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
	@Operation(
		summary = "Eliminar razas de animales",
		description = """
		Método para eliminar uno o varios registros de razas de animales de la base de datos
		mediante sus ID de registro.
		"""
	)
	public GenericResponse<?> deleteMultipleAnimalBreeds(@RequestBody DeleteMultipleAnimalBreedsRequest request){
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

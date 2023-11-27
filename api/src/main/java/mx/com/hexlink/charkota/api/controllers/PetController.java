package mx.com.hexlink.charkota.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mx.com.hexlink.charkota.api.restful.data.PetData;
import mx.com.hexlink.charkota.api.restful.enums.Status;
import mx.com.hexlink.charkota.api.restful.requests.EditPetRequest;
import mx.com.hexlink.charkota.api.restful.requests.PetRequest;
import mx.com.hexlink.charkota.api.restful.requests.PetVaccineRequest;
import mx.com.hexlink.charkota.api.restful.responses.GenericResponse;
import mx.com.hexlink.charkota.api.restful.responses.PetResponse;
import mx.com.hexlink.charkota.data.entities.AnimalBreed;
import mx.com.hexlink.charkota.data.entities.Owner;
import mx.com.hexlink.charkota.data.entities.Pet;
import mx.com.hexlink.charkota.data.entities.PetVaccine;
import mx.com.hexlink.charkota.data.services.AnimalBreedService;
import mx.com.hexlink.charkota.data.services.OwnerService;
import mx.com.hexlink.charkota.data.services.PetService;
import mx.com.hexlink.charkota.data.services.PetVaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Objects;
import java.util.UUID;



@RestController
@RequestMapping("/pets")
@Tag(name = "Pets", description = "Enpoints for admin all pets data")
public class PetController {
	@Autowired
	private PetService petService;
	@Autowired
	private PetVaccineService petVaccineService;
	@Autowired
	private OwnerService ownerService;
	@Autowired
	private AnimalBreedService animalBreedService;



	//---------------------------------------------------------------------------------------------- Obtener mascotas
	/**
	 * Método para obtener todas las mascotas registradas en la base de datos.
	 * Recibe id de registro y nombre de la mascota de forma opcinal. Si llegase a traer los 2, se
	 * le dará preferencia al ID.
	 * 
	 * @param petId Id del registro de la mascota (opcional)
	 * @param name Nombre de la mascota para buscar por coincidencia (opcional).
	 * @return Lista de coincidencias.
	 */
	@GetMapping
	@Operation(
		summary = "Obtener mascotas",
		description = """
		Método para obtener todas las mascotas registradas en la base de datos.
		Recibe id de registro y nombre de la mascota de forma opcinal. Si llegase a traer los 2, se
		le dará preferencia al ID.
		"""
	)
	public GenericResponse<PetResponse> getAllPets(
		@RequestParam(required = false) UUID petId,
		@RequestParam(required = false) String name

	){
		List<Pet> pets;

		// Comparamos querys del request dando preferencia al nombre y obtenemos los datos
		// dependiendo del caso.
		if( Objects.nonNull(petId) ){
			Pet pet = petService.getById(petId);

			pets = Objects.isNull(pet) ? List.of() : List.of(pet);
		}
		else if( Objects.nonNull(name) ){
			pets = petService.getLikeName(name.trim().toUpperCase());
		}
		else{
			pets = petService.getAll();
		}

		//Creamos la respuesta y seteamos sus atributos.
		GenericResponse<PetResponse> response = new GenericResponse<>();

		response.setStatus(Status.OK.isSuccess());
		response.setMessage(Status.OK.getDescription());
		response.setData(PetResponse.fromPets(pets));

		// Retornamos la respuesta.
		return response;
	}

	//---------------------------------------------------------------------------------------------- Crear mascotas
	/**
	 * Método para crear una mascota con una información dada. Así como agregar vacunas iniciales
	 *	de la mascota.
	 * 
	 * @param request Objeto con los datos necesarios para agregar la mascota.
	 * @return Objeto de reuesta con los datos de la mascota agregada.
	 */
	@PostMapping
	@Operation(
		summary = "Crear mascota",
		description = """
		Método para crear una mascota con una información dada. Así como agregar vacunas iniciales
		de la mascota.
		"""
	)
	public GenericResponse<PetData> createPet(@RequestBody PetRequest request) {
		// Creamos objetos necesarios.
		Owner owner = null;
		AnimalBreed animalBreed = null;

		// Preguntamos si hay IDs en los objetos y los consultamos
		if ( Objects.nonNull(request.getOwnerId()) && Objects.nonNull(request.getAnimalBreedId()) ){
			owner = ownerService.getById(request.getOwnerId());
			animalBreed = animalBreedService.getById(request.getAnimalBreedId());
		}

		// Preguntamos si ambos objetos existen, sino retornamos error.
		if ( Objects.isNull(owner) && Objects.isNull(animalBreed) ){
			return new GenericResponse<>(
				Status.ERROR.isSuccess(),
				Status.ERROR.getDescription(),
				null
			);
		}

		// Creamos el objeto mascota a agrgar.
		Pet petToSaved = request.toPet(owner, animalBreed);

		// Guardamos la mascota.
		Pet petSaved = petService.saveData(petToSaved);

		// Guardamos las vacunas en caso que tenga.
		if( !request.getVaccines().isEmpty() ){
			petSaved.setVaccines(
				petVaccineService.saveMultipleData(
					request.getVaccines().stream().map(
						vaccine -> PetVaccineRequest.toPetVaccine(vaccine, petSaved)
					).toList()
				)
			);
		}

		//Creamos la respuesta y seteamos sus atributos.
		GenericResponse<PetData> response = new GenericResponse<>();

		response.setStatus(Status.OK.isSuccess());
		response.setMessage(Status.OK.getDescription());
		response.setData(PetData.fromPet(petSaved));

		// Retornamos la respuesta.
		return response;
	}

	//---------------------------------------------------------------------------------------------- Editar mascotas
	/**
	 * Método para editar los datos esenciales de una mascota. Este requiere el ID de la mascota
	 * para saber de qué registro trata. Las vacunas que se agreguen en la lista, serán guardadas
	 * directamente al registro, puesto que no es posible eliminar vacunas de la mascota.
	 * 
	 * @param petId Id del registro de la mascota.
	 * @param request Datos necesarios para modificar el nombre de la mascota, fecha de nacimiento y
	 * 				  las vacunas que se le agregarán.
	 * @return Objeto de respuesta con los datos de la mascota midificada.
	 */
	@PutMapping("/{petID}")
	@Operation(
		summary = "Editar mascota",
		description = """
		Método para editar los datos esenciales de una mascota. Este requiere el ID de la mascota
		para saber de qué registro trata. Las vacunas que se agreguen en la lista, serán guardadas
		directamente al registro, puesto que no es posible eliminar vacunas de la mascota.
		"""
	)
	public GenericResponse<PetData> editPet(
		@PathVariable(name = "petID") UUID petId, @RequestBody EditPetRequest request
	){
		// Obtenemos a la mascota de la base de datos.
		Pet pet = petService.getById(petId);

		// Preguntamos si existe la mascota.
		if( Objects.isNull(pet) ){
			return new GenericResponse<>(
				Status.NOT_FOUND.isSuccess(),
				Status.NOT_FOUND.getDescription(),
				null
			);
		}

		// Creamos las listas necesarias para procesar las vacunas.
		List<PetVaccine> vaccinesAdded = null;

		// Editamos la mascota si existió.
		pet.setName(request.getName().trim().toUpperCase());
		pet.setBirthDate(request.getBirthDate());

		// Preguntamos si hay vacunas en el request para guardarlas.
		if( !request.getVaccines().isEmpty() ){
			vaccinesAdded = petVaccineService.saveMultipleData(request.getVaccines().stream().map(
				obj -> PetVaccineRequest.toPetVaccine(obj, pet)
			).toList());
		}

		// Si se agregaron, las metemos en la lista de vacunas de la mascota.
		if( Objects.nonNull(vaccinesAdded) && !vaccinesAdded.isEmpty() ){
			pet.getVaccines().addAll(vaccinesAdded);
		}

		// Guardamos a la mascota.
		Pet petSaved = petService.saveData(pet);

		// Creamos la respuesta con los datos necesarios.
		GenericResponse<PetData> response = new GenericResponse<>(
			Status.UPDATED.isSuccess(),
			Status.UPDATED.getDescription(),
			PetData.fromPet(petSaved)
		);
		
		// Retornamos la entidad modificada
		return response;
	}
}

package mx.com.hexlink.charkota.api.controllers;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mx.com.hexlink.charkota.api.restful.data.ProductData;
import mx.com.hexlink.charkota.api.restful.enums.Status;
import mx.com.hexlink.charkota.api.restful.requests.ProductRequest;
import mx.com.hexlink.charkota.api.restful.responses.GenericResponse;
import mx.com.hexlink.charkota.api.restful.responses.ProductResponse;
import mx.com.hexlink.charkota.data.entities.Product;
import mx.com.hexlink.charkota.data.entities.Provider;
import mx.com.hexlink.charkota.data.services.ProductService;
import mx.com.hexlink.charkota.data.services.ProviderService;



@RestController
@RequestMapping("/products")
@Tag(name = "Products", description = "Endpoints for admin products data")
public class ProductController {
	@Autowired
	private ProductService productService;
	@Autowired
	private ProviderService providerService;



	//---------------------------------------------------------------------------------------------- Controlador para obtener los productos registrados.
	/**
	 * Método para obtener todos los productos registrados en la base de datos. Este recibe 3
	 * parámetros opcionales, los cuales, se les derá prioridad al ID del producto, posterior al
	 * código de barras y por último, alguna coincidencia del nombre.
	 *
	 * @param productId Id del producto (opcional)
	 * @param name Nombre del producto (opcional)
	 * @param barCode Código de barras del producto (opcional)
	 * @return Objeto de respuesta con los datos de todos los productos encontrados.
	 */
	@GetMapping
	@Operation(
		summary = "Obtener productos",
		description = """
		Método para obtener todos los productos registrados en la base de datos. Este recibe 3
		parámetros opcionales, los cuales, se les derá prioridad al ID del producto, posterior al
		código de barras y por último, alguna coincidencia del nombre.
		"""
	)
	public GenericResponse<ProductResponse> getProducts(
		@RequestParam(required = false) UUID productId,
		@RequestParam(required = false) String name,
		@RequestParam(required = false) String barCode
	) {
		// Declaramos la lista de propietarios.
		List<Product> products;

		// Comparamos querys del request dando preferencia al ID y obtenemos los datos dependiendo del caso.
		if( Objects.nonNull(productId) ){
			Product product = productService.getById(productId);

			products = Objects.isNull(product) ? List.of() : List.of(product);
		}
		else if( Objects.nonNull(barCode) ){
			Product product = productService.getByBarCode(barCode.trim());

			products = Objects.isNull(product) ? List.of() : List.of(product);
		}
		else if( Objects.nonNull(name) ){
			products = productService.getLikeName(name.trim().toUpperCase());
		}
		else{
			products = productService.getAll();
		}

		//Creamos la respuesta y seteamos sus atributos.
		GenericResponse<ProductResponse> response = new GenericResponse<>();

		response.setStatus(Status.OK.isSuccess());
		response.setMessage(Status.OK.getDescription());
		response.setData(ProductResponse.fromProducts(products));

		// Retornamos la respuesta.
		return response;
	}

	//---------------------------------------------------------------------------------------------- Controlador para agregar un producto.
	/**
	 * Método para crear un producto y asignarle su respectivo proveedor.
	 * 
	 * @param request Objeto con los datos del producto y el ID del proveedor.
	 * @return Objeto de respuesta con los datos del producto agregado.
	 */
	@PostMapping
	@Operation(
		summary = "Crear producto",
		description = """
		Método para crear un producto y asignarle su respectivo proveedor.
		"""
	)
	public GenericResponse<ProductData> createOwner(@RequestBody ProductRequest request) {
		// Buscamos el proveedor:
		Provider provider = providerService.getById(request.getProviderId());

		// Si es nulo, retornamos no encontrado.
		if( Objects.isNull(provider) ){
			return new GenericResponse<>(
				Status.NOT_FOUND.isSuccess(),
				Status.NOT_FOUND.getDescription(),
				null
			);
		}

		// Agregamos el propietario.
		Product productAdded = productService.saveData(request.toProduct(provider));

		//Creamos la respuesta, seteamos sus atributos y lo retornamos.
		return new GenericResponse<>(
			Status.OK.isSuccess(),
			Status.OK.getDescription(),
			ProductData.fromProduct(productAdded)
		);
	}

	//---------------------------------------------------------------------------------------------- Controlador para modificar un producto.
	/**
	 * Método para modificar un producto en específico.
	 * 
	 * @param productId Id del producto a modificar.
	 * @param request Datos del producto a modificar.
	 * @return Objeto de respuesta con los datos del producto modificado o respuesta de no
	 * 		    encontrado en caso que no se encuentre el ID.
	 */
	@PutMapping("/{productId}")
	@Operation(
		summary = "Modificar producto",
		description = """
		Método para modificar un producto en específico.
		"""
	)
	public GenericResponse<ProductData> editOwner(
		@PathVariable UUID productId, @RequestBody ProductRequest request
	) {
		// Buscamos el producto.
		Product productToEdit = productService.getById(productId);

		// procesamos si existe. Si no, terminamos el proceso.
		if( Objects.isNull(productToEdit) ){
			return new GenericResponse<>(
				Status.NOT_FOUND.isSuccess(),
				Status.NOT_FOUND.getDescription(),
				null
			);
		}

		// Seteamos los valores.
		productToEdit.setBarCode(request.getBarCode().trim());
		productToEdit.setName(request.getName().trim().toUpperCase());
		productToEdit.setDescription(request.getDescription().trim().toUpperCase());
		productToEdit.setPrice(request.getPrice());
		productToEdit.setQty(request.getQty());

		// Guardamos los cambios.
		Product productSaved = productService.saveData(productToEdit);
		
		//Creamos la respuesta, seteamos sus atributos y lo retornamos.
		return new GenericResponse<>(
			Status.UPDATED.isSuccess(),
			Status.UPDATED.getDescription(),
			ProductData.fromProduct(productSaved)
		);
	}

	//---------------------------------------------------------------------------------------------- Controlador para "eliminar" un producto.
	/**
	 * Método para eliminar un producto de manera lógica.
	 * 
	 * @param productId Id del producto a eliminar
	 * @return Objeto de respuesta si se realizó la eliminación o si no se encontró el producto.
	 */
	@DeleteMapping("/{productId}")
	@Operation(
		summary = "Eliminar producto",
		description = """
			Método para eliminar un producto de manera lógica.
		"""
	)
	public GenericResponse<?> editOwner(
		@PathVariable UUID productId
	) {
		// Buscamos el producto.
		Product productToDelete = productService.getById(productId);

		// procesamos si existe. Si no, terminamos el proceso.
		if( Objects.isNull(productToDelete) ){
			return new GenericResponse<>(
				Status.NOT_FOUND.isSuccess(),
				Status.NOT_FOUND.getDescription(),
				null
			);
		}

		// Seteamos los valores.
		productToDelete.setIsDeleted(true);

		// Guardamos los cambios.
		productService.saveData(productToDelete);
		
		//Creamos la respuesta, seteamos sus atributos y lo retornamos.
		return new GenericResponse<>(
			Status.DELETED.isSuccess(),
			Status.DELETED.getDescription(),
			null
		);
	}
}

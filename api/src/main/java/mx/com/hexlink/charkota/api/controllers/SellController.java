package mx.com.hexlink.charkota.api.controllers;

import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mx.com.hexlink.charkota.api.restful.enums.Status;
import mx.com.hexlink.charkota.api.restful.requests.ProductSaleRequest;
import mx.com.hexlink.charkota.api.restful.requests.SellRequest;
import mx.com.hexlink.charkota.api.restful.responses.DetailSellResponse;
import mx.com.hexlink.charkota.api.restful.responses.GenericResponse;
import mx.com.hexlink.charkota.api.restful.responses.SellResponse;
import mx.com.hexlink.charkota.data.entities.Product;
import mx.com.hexlink.charkota.data.entities.Sale;
import mx.com.hexlink.charkota.data.entities.User;
import mx.com.hexlink.charkota.data.entities.enums.UserType;
import mx.com.hexlink.charkota.data.services.ProductService;
import mx.com.hexlink.charkota.data.services.SaleService;
import mx.com.hexlink.charkota.data.services.UserService;



@RestController
@RequestMapping("/sales")
@Tag(name =  "Sales", description = "Endpoints for admin sales data")
public class SellController {
	@Autowired
	private UserService userService;
	@Autowired
	private ProductService productService;
	@Autowired
	private SaleService saleService;



	//---------------------------------------------------------------------------------------------- Crear ventas.
	@PostMapping
	@CrossOrigin
	@Operation(
		summary = "Crear venta",
		description = """
		Método para crear una nueva venta de productos en la base de datos. Esta descuenta los
		productos existentes automáticamente.
		"""
	)
	public GenericResponse<SellResponse> createNewSell(
		@RequestBody SellRequest request
	){
		// Obtenemos al usuario.
		User user = userService.getById(request.getUserId());

		// Si no existe el usuario solicitado, retornamos la respuesta como no encontrada.
		if( Objects.isNull(user) ){
			return new GenericResponse<SellResponse>(
				Status.NOT_FOUND.isSuccess(),
				"""
				El usuario que efectuó la venta no fue encontrado en los registros del sistema.
				""",
				null
			);
		}
		
		// Preguntamos si el usuario puede efectuar ventas. Sino, finalizamos el proceso.
		if(
			!(user.getUserType().equals(UserType.ADMINISTRATOR) ||
			user.getUserType().equals(UserType.CASHIER))
		){
			return new GenericResponse<SellResponse>(
				Status.UNAUTHORIZED.isSuccess(),
				Status.UNAUTHORIZED.getDescription(),
				null
			);
		}

		// Consultamos todos los ID de los producotos.
		List<Product> products = productService.getAllByIds(
			request.getProductsSales().stream().map(obj -> obj.getProductId()).toList()
		);

		// Preguntamos si encontramos todos los productos necesarios. Sino, terminamos el proceso.
		if( products.size() != request.getProductsSales().size() ){
			return new GenericResponse<SellResponse>(
				Status.NOT_FOUND.isSuccess(),
				"""
				Algunos de los productos no fueron encontrados en la base de datos.
				""",
				null
			);
		}

		// Descontamos la cantidad de productos de la base de datos.
		for( Product product: products ){
			ProductSaleRequest psd = request.getProductsSales().stream().filter(
				obj -> obj.getProductId().equals(product.getId())
			).findFirst().orElse(null);

			if( Objects.nonNull(psd) ){
				product.setQty(product.getQty() - psd.getQty());
			}
		}

		// Guardamos los registros de los productos en la base de datos.
		productService.saveMultipleData(products);

		// Guardamos los registros de ventas en la tabla de ventas.
		Sale saleSaved = saleService.saveData(request.toSale(user));

		// Creamos la respuesta y la retornamos.
		return new GenericResponse<SellResponse>(
			Status.OK.isSuccess(),
			Status.OK.getDescription(),
			SellResponse.fromSale(saleSaved)
		);
	}



	//---------------------------------------------------------------------------------------------- Crear ventas.
	@GetMapping
	@CrossOrigin
	@Operation(
		summary = "Obtener lista de ventas",
		description = """
		Método para obtener las ventas regitradas en el sistema.
		"""
	)
	public GenericResponse<DetailSellResponse> getAllSells(){
		return new GenericResponse<>(
			Status.OK.isSuccess(),
			Status.OK.getDescription(),
			DetailSellResponse.fromSales(saleService.getAll())
		);
	}
}

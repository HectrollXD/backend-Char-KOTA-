package mx.com.hexlink.charkota.api.restful.requests;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.com.hexlink.charkota.data.entities.Product;
import mx.com.hexlink.charkota.data.entities.ProductSale;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductSaleRequest {
	private UUID productId;
	private Integer qty;
	private Double unitaryPrice;
	private Double totalPrice;



	public ProductSale toProductSale(){
		ProductSale productSale = new ProductSale();
		Product product = new Product();

		product.setId(productId);

		productSale.setProduct(product);
		productSale.setQty(qty);
		productSale.setUnitaryPrice(unitaryPrice);
		productSale.setTotalPrice(totalPrice);

		return productSale;
	}
}

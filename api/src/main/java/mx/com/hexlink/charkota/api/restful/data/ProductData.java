package mx.com.hexlink.charkota.api.restful.data;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.com.hexlink.charkota.data.entities.Product;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductData {
	private UUID id;
	private String barCode;
	private String name;
	private String description;
	private Double price;
	private Integer qty;
	private ProviderData provider;



	public static ProductData fromProduct(Product product){
		return new ProductData(
			product.getId(),
			product.getBarCode().trim(),
			product.getName().trim().toUpperCase(),
			product.getDescription().trim().toUpperCase(),
			product.getPrice(),
			product.getQty(),
			ProviderData.fromProvider(product.getProvider())
		);
	}
}

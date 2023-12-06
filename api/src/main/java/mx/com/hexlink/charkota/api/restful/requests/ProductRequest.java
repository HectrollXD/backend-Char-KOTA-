package mx.com.hexlink.charkota.api.restful.requests;

import java.util.Objects;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.com.hexlink.charkota.data.entities.Product;
import mx.com.hexlink.charkota.data.entities.Provider;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
	private String barCode;
	private String name;
	private String description;
	private Double price;
	private Integer qty;
	private UUID providerId;



	public Product toProduct(Provider provider){
		Product product = new Product();

		product.setBarCode(barCode.trim());
		product.setName(name.trim().toUpperCase());
		product.setPrice(price);
		product.setQty(qty);
		product.setProvider(provider);

		if( Objects.nonNull(description) ) {
			product.setDescription(description.trim().toUpperCase());
		}

		return product;
	}
}

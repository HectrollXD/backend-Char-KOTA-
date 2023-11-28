package mx.com.hexlink.charkota.api.restful.responses;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.com.hexlink.charkota.api.restful.data.ProductData;
import mx.com.hexlink.charkota.data.entities.Product;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
	List<ProductData> products;



	public static ProductResponse fromProducts(List<Product> products){
		return new ProductResponse(
			products.stream().map(ProductData::fromProduct).toList()
		);
	}
}

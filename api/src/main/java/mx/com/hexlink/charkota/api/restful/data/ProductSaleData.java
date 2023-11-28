package mx.com.hexlink.charkota.api.restful.data;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.com.hexlink.charkota.data.entities.ProductSale;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductSaleData {
	private UUID id;
	private Integer qty;
	private Double unitaryPrice;
	private Double totalPrice;
	private ProductData product;



	public static ProductSaleData fromProductSale(ProductSale productSale){
		return new ProductSaleData(
			productSale.getId(),
			productSale.getQty(),
			productSale.getUnitaryPrice(),
			productSale.getTotalPrice(),
			ProductData.fromProduct(productSale.getProduct())
		);
	}
}

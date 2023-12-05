package mx.com.hexlink.charkota.api.restful.requests;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.com.hexlink.charkota.data.entities.Sale;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class SellRequest {
	private UUID userId;
	private List<ProductSaleRequest> productsSales;



 	public Sale toSale(){
		Sale sale = new Sale();

		sale.setTotal(
			productsSales.stream().mapToDouble(obj -> obj.getTotalPrice()).sum()
		);
		sale.setProductSales(
			productsSales.stream().map(ProductSaleRequest::toProductSale).toList()
		);

		return sale;
	}
}

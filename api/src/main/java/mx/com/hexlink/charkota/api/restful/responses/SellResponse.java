package mx.com.hexlink.charkota.api.restful.responses;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.com.hexlink.charkota.api.restful.data.ProductSaleData;
import mx.com.hexlink.charkota.data.entities.Sale;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class SellResponse {
	private UUID sellId;
	private Double total;
	private List<ProductSaleData> productSales;



	public static SellResponse fromSale(Sale sale){
		return new SellResponse(
			sale.getId(),
			sale.getTotal(),
			sale.getProductSales().stream().map(ProductSaleData::fromProductSale).toList()
		);
	}
}

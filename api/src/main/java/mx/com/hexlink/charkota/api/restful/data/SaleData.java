package mx.com.hexlink.charkota.api.restful.data;

import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.com.hexlink.charkota.data.entities.Sale;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleData {
	private UUID id;
	private Double total;
	private UserData user;
	private List<ProductSaleData> products;


	public static SaleData fromSale(Sale sale){
		return new SaleData(
			sale.getId(),
			sale.getTotal(),
			UserData.fromUser(sale.getUser()),
			sale.getProductSales().stream().map(ProductSaleData::fromProductSale).toList()
		);
	}
}

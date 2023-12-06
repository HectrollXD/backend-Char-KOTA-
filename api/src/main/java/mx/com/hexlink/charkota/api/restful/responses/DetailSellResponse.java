package mx.com.hexlink.charkota.api.restful.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.com.hexlink.charkota.api.restful.data.SaleData;
import mx.com.hexlink.charkota.data.entities.Sale;
import java.util.List;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailSellResponse {
	private List<SaleData> sales;


	public static DetailSellResponse fromSales(List<Sale> sales){
		return new DetailSellResponse(
			sales.stream().map(SaleData::fromSale).toList()
		);
	}
}

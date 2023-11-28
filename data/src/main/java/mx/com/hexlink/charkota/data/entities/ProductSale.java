package mx.com.hexlink.charkota.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;



@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "rel_products_sales")
@EqualsAndHashCode(callSuper = true)
public class ProductSale extends CommonData {
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE, targetEntity = Product.class)
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Sale.class)
	@JoinColumn(name = "sale_id", nullable = false)
	private Sale sale;

	@Column(name = "qty", nullable = false)
	private Integer qty;

	@Column(name = "unitary_price", nullable = false)
	private Double unitaryPrice;

	@Column(name = "total_price", nullable = false)
	private Double totalPrice;
}

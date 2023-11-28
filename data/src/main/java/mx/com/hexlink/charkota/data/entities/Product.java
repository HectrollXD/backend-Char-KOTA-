package mx.com.hexlink.charkota.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.List;



@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "data_products")
@EqualsAndHashCode(callSuper = true)
public class Product extends CommonData{
	@Column(name = "bar_code", length = 16, nullable = false)
	private String barCode;

	@Column(name = "name", length = 64, nullable = false)
	private String name;

	@Column(name = "description", length = 256)
	private String description;

	@Column(name = "price", nullable = false)
	private Double price;

	@Column(name = "qty", nullable = false)
	private Integer qty;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, targetEntity = Provider.class)
	@JoinColumn(name = "provider_id", nullable = false)
	private Provider provider;

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private List<ProductSale> productSales;
}

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
@Table(name = "data_sales")
@EqualsAndHashCode(callSuper = true)
public class Sale extends CommonData{
	@Column(name = "total", nullable = false)
	private Double total;

	@OneToMany(
		mappedBy = "sale",
		cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH},
		fetch = FetchType.EAGER
	)
	private List<ProductSale> productSales;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE, targetEntity = User.class)
	private User user;
}

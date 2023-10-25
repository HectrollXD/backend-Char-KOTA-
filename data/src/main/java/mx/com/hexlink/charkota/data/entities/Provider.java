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
@Table(name = "catalog_providers")
@EqualsAndHashCode(callSuper = true)
public class Provider extends CommonData{
	@Column(name = "name", length = 0, unique = true, nullable = false)
	private String name;

	@Column(name = "rfc", length = 0, unique = true, nullable = false)
	private String rfc;

	@Column(name = "phone", length = 0, unique = true, nullable = false)
	private String phone;

	@Column(name = "email", length = 0, unique = true, nullable = false)
	private String email;

	@OneToMany(mappedBy = "provider", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Product> products;
}

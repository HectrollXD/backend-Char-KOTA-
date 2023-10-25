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
@Table(name = "data_owners")
@EqualsAndHashCode(callSuper = true)
public class Owner extends CommonData {
	@Column(name = "name", nullable = false, length = 64)
	private String name;

	@Column(name = "last_name", nullable = false, length = 64)
	private String lastname;

	@Column(name = "phone", nullable = false, length = 10)
	private String phone;

	@Column(name = "email", nullable = false, length = 128)
	private String email;

	@Column(name = "address", length = 256)
	private String address;

	@OneToMany(mappedBy = "owner", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	private List<Pet> pets;
}

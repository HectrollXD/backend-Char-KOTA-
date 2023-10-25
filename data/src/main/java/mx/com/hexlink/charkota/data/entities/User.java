package mx.com.hexlink.charkota.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import mx.com.hexlink.charkota.data.entities.enums.UserType;
import java.util.List;



@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "data_user")
@EqualsAndHashCode(callSuper = true)
public class User extends CommonData{
	@Column(name = "email", nullable = false, length = 128)
	private String email;

	@Column(name = "username", nullable = false, unique = true, length = 24)
	private String username;

	@Column(name = "password", nullable = false, length = 256)
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(name = "user_type", nullable = false, length = 16)
	private UserType userType;

	@OneToMany(mappedBy = "vet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<ConsultDate> consultDates;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Sale> sales;
}

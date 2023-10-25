package mx.com.hexlink.charkota.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.time.LocalDate;



@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "data_vaccines")
@EqualsAndHashCode(callSuper = true)
public class PetVaccine extends CommonData {
	@Column(name = "vaccine_name", nullable = false, length = 64)
	private String vaccineName;

	@Column(name = "application_date", nullable = false)
	private LocalDate applicationDate;

	@Column(name = "dose", nullable = false)
	private Double dose;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Pet.class)
	@JoinColumn(name = "pet_id", nullable = false)
	private Pet pet;
}

package mx.com.hexlink.charkota.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalTime;



@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(
	name = "data_consult_date",
	uniqueConstraints = @UniqueConstraint(
		columnNames = {"cite_date", "cite_time", "vet_id", "pet_id"}
	)
)
public class ConsultDate extends CommonData{
	@Column(name = "cite_date", nullable = false)
	private LocalDate citeDate;

	@Column(name = "cite_time", nullable = false)
	private LocalTime citeTime;

	@Column(name = "is_treated", nullable = false, columnDefinition = "boolean default false")
	private Boolean treated;

	@Column(name = "annotations", length = 512)
	private String annotations;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = User.class)
	@JoinColumn(name = "vet_id", nullable = false)
	private User vet;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Pet.class)
	@JoinColumn(name = "pet_id", nullable = false)
	private Pet pet;
}

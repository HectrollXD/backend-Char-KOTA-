package mx.com.hexlink.charkota.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.UUID;



@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class CommonData {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid")
	private UUID id;

	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt = LocalDateTime.now();

	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt = LocalDateTime.now();

	@Column(name = "is_deleted", nullable = false)
	private Boolean isDeleted = false;
}

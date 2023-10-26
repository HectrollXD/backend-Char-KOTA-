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
@Table(name = "data_persons")
@EqualsAndHashCode(callSuper = true)
public class Person extends CommonData {
    @Column(name = "name", length = 64, nullable = false)
    private String name;

    @Column(name = "last_name", length = 64)
    private String lastname;

    @Column(name = "phone", length = 16, nullable = false)
    private String phone;

    @Column(name = "email", length = 128, nullable = false)
    private String email;

    @OneToOne(mappedBy = "person", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;
}

package amplasystem.api.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vendedor {
    @Id
    private Integer id;
    private String email;
    private String senha;
    private String nome;
    private String cargo;

}

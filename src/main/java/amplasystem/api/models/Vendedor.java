package amplasystem.api.models;

import java.util.List;

import amplasystem.api.enuns.Cargo;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
    private Cargo cargo;

    @OneToMany(mappedBy = "vendedor")
    private List<Cliente> clientes;

}

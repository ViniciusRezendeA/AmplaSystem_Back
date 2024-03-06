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
public class Cliente {
    @Id
    private Integer id;
    private String cnpj;
    private String telefone;
    private String cidade;
    private String endereco;
    private String nomeFantasia;

    private Vendedor vendedor;
    
}

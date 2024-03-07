package amplasystem.api.models;

import java.util.List;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

    @ManyToOne
    @JoinColumn(name = "vendedor_id")
    private Vendedor vendedor;


    @OneToMany(mappedBy = "cliente")
    private List<OrdemDeCompra> ordemDeCompras;
    
}

package amplasystem.api.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome_fantasia", nullable = false, length = 100)
    @NotBlank(message = "Nome fantasia do cliente é obrigatorio")
    private String nomeFantasia;




    @ManyToOne
    @JoinColumn(name = "vendedor_id")
    private Vendedor vendedor;

    @OneToMany(mappedBy = "cliente")
    private List<OrdemDeCompra> ordemDeCompras;
    
}

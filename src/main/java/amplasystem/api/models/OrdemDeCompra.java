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
public class OrdemDeCompra {
    @Id
    private Integer id;
    private Double valorOrdem;
    private String codigoPedido;
    private Boolean totalmenteFaturada;


    @ManyToOne
    @JoinColumn(name = "industria_id")
    private Industria industria;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "ordemDeCompra")
    private List<PedidoFaturado> pedidoFaturados;

}

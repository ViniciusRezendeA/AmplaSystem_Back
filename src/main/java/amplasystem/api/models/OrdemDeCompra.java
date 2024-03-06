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
public class OrdemDeCompra {
    @Id
    private Integer id;
    private Double valorOrdem;
    private String codigoPedido;
    private Boolean totalmenteFaturada;

    private Industria industria;
    private Cliente cliente;

}

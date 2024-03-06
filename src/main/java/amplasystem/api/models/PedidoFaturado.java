package amplasystem.api.models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoFaturado {
    @Id
    private Integer id;
    private LocalDate dataFaturamento;
    private Double valorFaturado;
    private String notaFiscal;
    private LocalDate dataVencimento;

    private OrdemDeCompra ordemDeCompra;

}

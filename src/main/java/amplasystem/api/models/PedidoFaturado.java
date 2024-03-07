package amplasystem.api.models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoFaturado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate dataFaturamento;
    private Double valorFaturado;
    private String notaFiscal;
    private LocalDate dataVencimento;

    @ManyToOne
    @JoinColumn(name = "ordemDeCompra_id")
    private OrdemDeCompra ordemDeCompra;

}

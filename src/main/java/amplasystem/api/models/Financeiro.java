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
public class Financeiro {
    @Id
    private Integer id;
    private Double comissao;
    private String faturamento;
    private String tipoFiscal;
    
    private Industria industria;

}

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
public class Industria {
    @Id
    private Integer id;
    private String nome;
    private String contatoComercial;
    private String telefoneComercial;
    private String emailComercial;
    private String contatoLogistica;
    private String telefoneLogistica;
    private String emailLogistica;
    private String telefoneFinaceiro;
    private String emailFinanceiro;
    private String contatoPagamento;
    private String telefonePagamento;
    private String emailPagamento;

}

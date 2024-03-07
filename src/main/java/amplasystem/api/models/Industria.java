package amplasystem.api.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Industria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToOne(mappedBy = "industria")
    private Financeiro financeiro;

    @OneToMany(mappedBy = "industria")
    private List<OrdemDeCompra> ordemDeCompras;

}

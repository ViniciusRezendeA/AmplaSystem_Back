package amplasystem.api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Industria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Nome da indústria é obrigatório.")
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

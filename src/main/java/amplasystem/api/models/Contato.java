package amplasystem.api.models;

import amplasystem.api.enuns.TipoContato;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(length = 45)
    @NotNull
    @NotBlank(message = "Nome do contato é obrigatório.")
    String nome;

    @Column(length = 100)
    String email;

    @NotNull
    @NotBlank(message = "Tipo do contato é obrigatório.")
    TipoContato tipo;

    @ManyToOne
    @JoinColumn(name = "industria_id")
    private Industria industria;

    @OneToOne
    @JoinColumn(name = "telefone_id")
    private Telefone Telefone;
}

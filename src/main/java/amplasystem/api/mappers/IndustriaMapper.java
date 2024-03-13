package amplasystem.api.mappers;

import amplasystem.api.dtos.IndustriaDTO;
import amplasystem.api.models.Industria;


public class IndustriaMapper {
    public static IndustriaDTO toDTO(Industria industria) {
        return new IndustriaDTO(
                industria.getId(),
                industria.getNome(),
                industria.getContatoComercial(),
                industria.getTelefoneComercial(),
                industria.getEmailComercial(),
                industria.getContatoLogistica(),
                industria.getTelefoneLogistica(),
                industria.getEmailLogistica(),
                industria.getTelefoneFinaceiro(),
                industria.getEmailFinanceiro(),
                industria.getContatoPagamento(),
                industria.getTelefonePagamento(),
                industria.getEmailPagamento(),
                industria.getFinanceiro(),
                industria.getOrdemDeCompras()
        );
    }
}

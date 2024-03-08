package amplasystem.api.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Service;

import amplasystem.api.dtos.ResponseTokenDTO;
import amplasystem.api.dtos.VendedorLoginDTO;
import amplasystem.api.models.Vendedor;
import amplasystem.api.utils.Cryptography;
import jakarta.transaction.Transactional;

@Transactional
@Service
public class LoginService {

    @Autowired
    VendedorService vendedorService;

    public ResponseTokenDTO login(VendedorLoginDTO vendedorLoginDTO) throws AuthenticationCredentialsNotFoundException {

        Vendedor vendedor = vendedorService.getVendedoresByEmailAndPassword(vendedorLoginDTO.getEmail(),
                vendedorLoginDTO.getSenha());
        return new ResponseTokenDTO(Cryptography.tokenGenerate(vendedor.getNome()));
    }

}

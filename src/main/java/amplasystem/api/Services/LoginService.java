package amplasystem.api.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Service;

import amplasystem.api.dtos.ResponseTokenDTO;
import amplasystem.api.dtos.VendedorLoginDTO;
import amplasystem.api.exceptions.InvalidInformationException;
import amplasystem.api.models.Vendedor;
import amplasystem.api.utils.Cryptography;
import amplasystem.api.utils.Regex;
import jakarta.transaction.Transactional;

@Transactional
@Service
public class LoginService {

    @Autowired
    VendedorService vendedorService;

    public ResponseTokenDTO login(VendedorLoginDTO vendedorLoginDTO) throws AuthenticationCredentialsNotFoundException {
        if(!Regex.isValidEmail(vendedorLoginDTO.getEmail())){
            throw new InvalidInformationException("email invalido");
        }
        Vendedor vendedor = vendedorService.getVendedoresByEmailAndPassword(vendedorLoginDTO.getEmail(),
                vendedorLoginDTO.getSenha());
        return new ResponseTokenDTO(Cryptography.tokenGenerate(vendedor.getNome()));
    }

}

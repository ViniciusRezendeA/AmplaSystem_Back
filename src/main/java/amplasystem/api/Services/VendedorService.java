package amplasystem.api.Services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

import amplasystem.api.Services.exceptions.ObjectNotFoundException;
import amplasystem.api.dtos.ChangePasswordDTO;
import amplasystem.api.models.Vendedor;
import amplasystem.api.repositories.VendedorRepository;
import jakarta.transaction.Transactional;

@Transactional
@Service
public class VendedorService {
    @Autowired
    private VendedorRepository vendedorRepository;

    @Autowired
    private final PasswordEncoder encoder = new BCryptPasswordEncoder();

    public List<Vendedor> getAllVendedors() {
        return vendedorRepository.findAll();
    }

    public PasswordEncoder getBcrypt() {
        return this.encoder;
    }

    public Vendedor getVendedorByEmail(String email) throws NoSuchElementException {
        Vendedor user = vendedorRepository.findByEmail(email);
        if (user == null) {
            throw new NoSuchElementException("Usuário não encontrado na base de dados");
        }
        return user;
    }

    public Vendedor getVendedorById(Integer id) throws NoSuchElementException {
        Optional<Vendedor> user = vendedorRepository.findById(id);
        if (!user.isPresent()) {
            throw new NoSuchElementException("Usuário não encontrado na base de dados");
        }
        return vendedorRepository.findById(id).get();
    }

    public void changePassword(ChangePasswordDTO changePasswordDTO) {
        if (changePasswordDTO.getToken() != null && !changePasswordDTO.getToken().equals("")) {

            Vendedor vendedor = vendedorRepository.findByEmail(changePasswordDTO.getEmail());
            if (vendedor == null) {
                throw new ObjectNotFoundException("Usuário não existente");
            }
            if (!changePasswordDTO.getNovaSenha().equals(changePasswordDTO.getConfirmacaoNovaSenha())) {
                throw new ForgetPasswordException("Nova senha e a confirmação não são iguais");
            }
            vendedor.setSenha(this.encoder.encode(changePasswordDTO.getNovaSenha()));
        }
    }

    public void save(Vendedor vendedor) {

        vendedor.setSenha(this.encoder.encode(vendedor.getSenha()));
        vendedorRepository.save(vendedor);
    }
}

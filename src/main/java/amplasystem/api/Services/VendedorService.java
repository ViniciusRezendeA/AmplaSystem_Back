package amplasystem.api.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import amplasystem.api.services.exceptions.ObjectNotFoundException;
import amplasystem.api.dtos.ChangePasswordDTO;
import amplasystem.api.dtos.VendedorDTO;
import amplasystem.api.exceptions.ChangePasswordException;
import amplasystem.api.mappers.VendedorMapper;
import amplasystem.api.models.Vendedor;
import amplasystem.api.repositories.VendedorRepository;
import amplasystem.api.utils.Cryptography;
import amplasystem.api.utils.Generator;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Transactional
@Service
public class VendedorService {
    @Autowired
    private VendedorRepository vendedorRepository;

    @Autowired
    private final PasswordEncoder encoder = new BCryptPasswordEncoder();

    public List<VendedorDTO> getAllVendedores() {
        return vendedorRepository.findAll().stream().map(VendedorMapper::toDTO).collect(Collectors.toList());
    }

    public PasswordEncoder getBcrypt() {
        return this.encoder;
    }

    public VendedorDTO getVendedoresByEmail(String email) throws ObjectNotFoundException {
        Vendedor vendedor = vendedorRepository.findByEmail(email);
        if(vendedor == null){
            throw new ObjectNotFoundException("Email não cadastrado na base");
        }
        return VendedorMapper.toDTO(vendedor);
    }

    public VendedorDTO getVendedoresById(Integer id) throws ObjectNotFoundException {
        Vendedor vendedor = vendedorRepository.findById(id).get();
        if(vendedor == null){
            throw new ObjectNotFoundException("Vendedor não cadastrado");
        }
        return VendedorMapper.toDTO(vendedor);
    }

    private Vendedor getById(Integer id) throws ObjectNotFoundException {
        Vendedor vendedor = vendedorRepository.findById(id).get();
        if(vendedor == null){
            throw new ObjectNotFoundException("Vendedor não cadastrado");
        }
        return vendedor;
    }

    public void changePassword(ChangePasswordDTO changePasswordDTO) throws ObjectNotFoundException, ChangePasswordException {
        if (changePasswordDTO.getToken() != null && !changePasswordDTO.getToken().equals("")
                && changePasswordDTO.getToken().equals(Cryptography.tokenGenerate(changePasswordDTO.getEmail()))) {

            Vendedor vendedor = vendedorRepository.findByEmail(changePasswordDTO.getEmail());
           
            if (vendedor == null) {
                throw new ObjectNotFoundException("Usuário não existente");
            }
            if (!changePasswordDTO.getNovaSenha().equals(changePasswordDTO.getConfirmacaoNovaSenha())) {
                throw new ChangePasswordException("Nova senha e a confirmação não são iguais");
            }

    
            System.out.println(vendedor.getSenha());
            vendedor.setSenha(changePasswordDTO.getNovaSenha());
            save(vendedor);
        }
    }

    public void save(Vendedor vendedor)  {

        vendedor.setSenha(Cryptography.tokenGenerate(vendedor.getSenha()));
        vendedorRepository.save(vendedor);
    }
    public void update(Vendedor vendedor)  {    
        Vendedor vendedorInBase = getById(vendedor.getId());
        vendedor.setSenha(vendedor.getSenha());
        vendedorRepository.save(vendedor);
    }

    public Vendedor getVendedoresByEmailAndPassword(String email, String senha) {
        System.out.println(Cryptography.tokenGenerate(senha));
        Vendedor vendedor = vendedorRepository.findByEmailAndSenha(email, Cryptography.tokenGenerate(senha));
        if(vendedor == null){
            throw new ObjectNotFoundException("Confira se os campos estão corretos");
        }
        return vendedor;
    }

    public VendedorDTO deleteVendedorById(Integer id) throws EntityNotFoundException{
        Vendedor obj = this.getById(id);
        this.vendedorRepository.delete(obj);

        return VendedorMapper.toDTO(obj);
    }
}

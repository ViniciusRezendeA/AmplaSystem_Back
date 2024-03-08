package amplasystem.api.controller;

import java.util.NoSuchElementException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import amplasystem.api.Services.EmailSenderService;
import amplasystem.api.Services.VendedorService;
import amplasystem.api.dtos.ChangePasswordDTO;
import amplasystem.api.dtos.ResponseDTO;
import amplasystem.api.dtos.ForgetPasswordDTO;
import amplasystem.api.dtos.VendedorDTO;
import amplasystem.api.exceptions.ChangePasswordException;
import amplasystem.api.exceptions.InvalidInformationException;
import amplasystem.api.models.Vendedor;
import amplasystem.api.utils.Cryptography;
import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/vendedor")
public class VendedorController {

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    VendedorService vendedorService;

    @GetMapping()
    @ResponseBody
    public ResponseEntity<List<VendedorDTO>> getAllVendedores() {
        return ResponseEntity.ok(vendedorService.getAllVendedores());
    }

    @GetMapping(value = "/getVendedor/{id}")
    @ResponseBody
    public ResponseEntity<?> getVendedorById(@PathVariable Integer id) throws NoSuchElementException {
        try {
            VendedorDTO vendedor = vendedorService.getVendedoresById(id);
            return ResponseEntity.ok(vendedor);
        } catch (NoSuchElementException e) {
            ResponseDTO responseDTO = new ResponseDTO("Usuário não encontrado.",
                    e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
        }
    }

    @PostMapping(value = "/save")
    @ResponseBody
    public ResponseEntity<?> save(@RequestBody Vendedor vendedor) {
        try {
            vendedorService.save(vendedor);
            return ResponseEntity.status(HttpStatus.CREATED).body("Cadastrado");
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().build();
        } catch (DataIntegrityViolationException e) {
            ResponseDTO responseDTO = new ResponseDTO("Email ja cadastrado",
                    "o email ja se encontra cadastrado, tente realizar o login");
            return ResponseEntity.status(422).body(responseDTO);
        }

    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> deleteVendedorById(@PathVariable Integer id) throws NoSuchElementException {
        try {
            VendedorDTO vendedor = vendedorService.deleteVendedorById(id);
            ResponseDTO responseDTO = new ResponseDTO("Vendedor deletado",
                    "O vendedor " + vendedor.getNome() + " foi deletado com sucesso!!");
            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);

        } catch (NoSuchElementException e) {
            ResponseDTO responseDTO = new ResponseDTO("Vendedor não encontrado.",
                    "O vendedor não foi localizado em nossa base de dados");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
        } catch (DataIntegrityViolationException e) {
            ResponseDTO responseDTO = new ResponseDTO("Vendedor ",
                    "Existem relações com Clientes, Pedidos Faturados e Ordens de compra para este vendedor, para deleta-lo, altere o vendedor");
            return ResponseEntity.status(422).body(responseDTO);
        }
    }

    @PostMapping(value = "/changePassword")
    @ResponseBody
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDTO changePasswordDTO) {
        try {
            vendedorService.changePassword(changePasswordDTO);
            return ResponseEntity.status(200).body("Password changed");
        } catch (ChangePasswordException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            ResponseDTO responseDTO = new ResponseDTO("System Error",
                    "Infelizmente estamos com dificuldade no sistema");
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(responseDTO);
        }

    }

    @PostMapping("/forgotPassword")
    public ResponseEntity<?> sendToken(@RequestBody ForgetPasswordDTO forgetPasswordDTO) {

        try {
            emailSenderService.sendRecoveryPasswordMail(forgetPasswordDTO.getEmail(),
                    Cryptography.tokenGenerate(forgetPasswordDTO.getEmail()));

            return ResponseEntity.status(200).body("Email enviado para o usuário " + forgetPasswordDTO.getEmail());

        } catch (InvalidInformationException e) {
            ResponseDTO responseDTO = new ResponseDTO("Dados inválido",
                    e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDTO);
        } catch (NoSuchElementException e) {
            ResponseDTO responseDTO = new ResponseDTO("Vendedor não encontrado",
                    e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDTO);
        } catch (Exception e) {
            ResponseDTO responseDTO = new ResponseDTO("System Error",
                    "Infelizmente estamos com dificuldade no sistema, tente novamente, se persistir entre em contato com o suporte");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(responseDTO);
        }
    }

}

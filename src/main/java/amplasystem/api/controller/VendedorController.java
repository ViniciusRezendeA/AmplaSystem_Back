package amplasystem.api.controller;


import java.util.NoSuchElementException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import amplasystem.api.services.EmailSenderService;
import amplasystem.api.services.VendedorService;
import amplasystem.api.dtos.ChangePasswordDTO;
import amplasystem.api.dtos.ErrorResponse;
import amplasystem.api.dtos.ForgetPasswordDTO;
import amplasystem.api.dtos.VendedorDTO;
import amplasystem.api.exceptions.ForgetPasswordException;
import amplasystem.api.models.Vendedor;
import amplasystem.api.utils.Cryptography;

@RestController
@RequestMapping("/vendedor")
public class VendedorController {

    @Autowired
    private EmailSenderService emailSenderService;


    @Autowired
    VendedorService vendedorService;


    @GetMapping()
    @ResponseBody
    public ResponseEntity<List<VendedorDTO>> getAllUsuarios() {
        return ResponseEntity.ok(vendedorService.getAllVendedores());
    }

    @GetMapping(value = "/getVendedor/{id}")
    @ResponseBody
    public ResponseEntity<?> getUsuarioById(@PathVariable Integer id) throws NoSuchElementException {
        try {
            VendedorDTO vendedor = vendedorService.getVendedoresById(id);
            return ResponseEntity.ok(vendedor);
        } catch (NoSuchElementException e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Usuário não encontrado.",
                    e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @PostMapping(value = "/save")
    @ResponseBody
    public ResponseEntity<?> save(@RequestBody Vendedor usuario) {
        try {
            vendedorService.save(usuario);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Cadastrado");
    }



    @PostMapping(value = "/changePassword")
    @ResponseBody
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDTO changePasswordDTO) {
        try {
            vendedorService.changePassword(changePasswordDTO);
        } catch (ForgetPasswordException e) {
            return ResponseEntity.status(500).body(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(200).body("Password changed");
    }


    @PostMapping("/forgotPassword")
    public ResponseEntity<?> sendToken(@RequestBody ForgetPasswordDTO forgetPasswordDTO) {

        try {// add check if user exist here
            emailSenderService.sendRecoveryPasswordMail(forgetPasswordDTO.getEmail(),
                    Cryptography.tokenGenerate(forgetPasswordDTO.getEmail()));
            return ResponseEntity.status(200).body("Email enviado para o usuário " + forgetPasswordDTO.getEmail());

        } catch (ForgetPasswordException e) {
            ErrorResponse errorResponse = new ErrorResponse(400, "Email inválido",
                    "Favor verificar dados e destinatário");
            return ResponseEntity.status(400).body(errorResponse);
        } catch (NoSuchElementException e) {

            ErrorResponse errorResponse = new ErrorResponse(400, "Email não encontrado",
                    "O email informado não se contra  cadastrado");
            return ResponseEntity.status(400).body(errorResponse);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(500, "System Error",
                    "Infelizmente estamos com dificuldade no sistema");
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

}

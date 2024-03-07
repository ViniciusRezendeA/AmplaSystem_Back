package amplasystem.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import amplasystem.api.dtos.ErrorResponse;
import amplasystem.api.dtos.ForgetPasswordDTO;
import amplasystem.api.exceptions.ForgetPasswordException;
import amplasystem.api.service.EmailSenderService;
import amplasystem.api.utils.Cryptography;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private EmailSenderService emailSenderService;

    @GetMapping
    public String get() {
        return "AmplaSystems GET";
    }

    @PostMapping
    public String post() {
        return "AmplaSystems POST";
    }

    @PutMapping
    public String put() {
        return "AmplaSystems PUT";
    }

    @DeleteMapping
    public String delete() {
        return "AmplaSystems DELETE";
    }

    @GetMapping("/forgotPassword")
    public ResponseEntity<?> getToken(@RequestBody ForgetPasswordDTO forgetPasswordDTO) {

        try {// add check if user exist here
            emailSenderService.sendRecoveryPasswordMail(forgetPasswordDTO.getEmail(),
                    Cryptography.tokenGenerate(forgetPasswordDTO.getEmail()));
            return ResponseEntity.status(200).body("Email enviado para o usuário " + forgetPasswordDTO.getEmail());

        } catch (ForgetPasswordException e) {
            ErrorResponse errorResponse = new ErrorResponse(400, "Email inválido",
                    "Favor verificar dados e destinatário");
            return ResponseEntity.status(400).body(errorResponse);
        }
    }

}

package amplasystem.api.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    
    @GetMapping
    public String get() {
        return "GET";
    }

    @PostMapping
    public String post() {
        return "POST";
    }

    @PutMapping
    public String put() {
        return "PUT";
    }

    @DeleteMapping
    public String delete() {
        return "DELETE";
    }
}

package amplasystem.api.controller;

import amplasystem.api.Services.IndustriaService;
import amplasystem.api.dtos.ErrorResponse;
import amplasystem.api.dtos.IndustriaDTO;
import amplasystem.api.models.Industria;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/industria")
public class IndustriaController {

    @Autowired
    IndustriaService industriaService;

    @Autowired
    private Validator validator;


    @GetMapping(value = "/")
    @ResponseBody
    public ResponseEntity<List<IndustriaDTO>> getAllIndustrias() {
        return ResponseEntity.ok(industriaService.getAllIndustrias());
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<?> getIndustriaById(@PathVariable Integer id) throws NoSuchElementException {
        try {
            IndustriaDTO industria = industriaService.getIndustriaById(id);
            return ResponseEntity.ok(industria);
        } catch (NoSuchElementException e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Indústria não encontrada.",
                    e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @PostMapping(value = "/")
    @ResponseBody
    public ResponseEntity<?> save(@RequestBody Industria industria) {
        try {
            return ResponseEntity.ok(industriaService.save(industria));
        } catch (IllegalStateException e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Indústria já cadastrada", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Erro ao cadastrar indústria", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            industriaService.delete(id);
        } catch (NoSuchElementException e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Indústria não encontrada.",
                    e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/")
    @ResponseBody
    public ResponseEntity<?> update(@RequestBody Industria industria) {
        try {
            industriaService.update(industria);
        } catch (NoSuchElementException e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Indústria não encontrada.",
                    e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
        return ResponseEntity.ok().build();
    }

}

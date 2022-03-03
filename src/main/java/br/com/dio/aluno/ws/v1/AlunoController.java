package br.com.dio.aluno.ws.v1;

import br.com.dio.aluno.model.request.AlunoRequest;
import br.com.dio.aluno.model.request.AlunoResponse;
import br.com.dio.aluno.service.AlunoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v1")
public class AlunoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlunoController.class);

    @Autowired
    private AlunoService service;

    @PostMapping
    public ResponseEntity<AlunoResponse> create(@RequestBody AlunoRequest alunoRequest) {
        LOGGER.info("Requisição recebida");
        return ResponseEntity.ok(service.create(alunoRequest));
    }

    @GetMapping
    public ResponseEntity<Page<AlunoResponse>> getall(Pageable pageable) {
        LOGGER.info("Buscando os Registros");
        Page<AlunoResponse> alunoResponses = service.getAll(pageable);
        return ResponseEntity.ok(alunoResponses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoResponse> update(@PathVariable("id") Long id, @RequestBody AlunoRequest alunoRequest) {
        Optional<AlunoResponse> update = service.update(id, alunoRequest);
        if (!update.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(update.get());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponse> get(@PathVariable("id") Long id) {
        LOGGER.info("Iniciando a busca pelo registro");
        Optional<AlunoResponse> alunoResponse = service.get(id);
        if (!alunoResponse.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(alunoResponse.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        LOGGER.info("Iniciando a remoção do registro");
        if (service.delete(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

}
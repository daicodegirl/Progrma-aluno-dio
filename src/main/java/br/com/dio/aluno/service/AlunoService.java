package br.com.dio.aluno.service;

import br.com.dio.aluno.model.request.AlunoRequest;
import br.com.dio.aluno.model.request.AlunoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AlunoService {

    AlunoResponse create(AlunoRequest alunoRequest);

    Page <AlunoResponse> getAll(Pageable pageable);
    Optional<AlunoResponse> update(Long id, AlunoRequest alunoRequest);
    Optional<AlunoResponse> get(Long id);
    boolean delete(Long id);
}

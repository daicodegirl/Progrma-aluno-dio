package br.com.dio.aluno.service;

import br.com.dio.aluno.model.request.AlunoRequest;
import br.com.dio.aluno.model.request.AlunoResponse;
import br.com.dio.aluno.persistence.entity.Aluno;
import br.com.dio.aluno.persistence.repository.AlunoRepository;
import br.com.dio.aluno.service.mapper.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.util.Assert.notNull;

@Service
public class AlunoServiceImpl implements AlunoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlunoService.class);

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private Mapper<AlunoRequest, Aluno> requestMapper;

    @Autowired
    private Mapper<Aluno, AlunoResponse> responseMapper;

    @Override
    public AlunoResponse create(AlunoRequest alunoRequest) {
        LOGGER.info("Criando um registro do Aluno");
        notNull(alunoRequest, "Request Inválida");
        Aluno aluno = this.requestMapper.map(alunoRequest);
        return alunoRepository.saveAndFlush(aluno).map((Aluno input) -> this.responseMapper.map(input));
    }

    @Override
    public Page<AlunoResponse> getAll(Pageable pageable) {
        LOGGER.info("Buscando todos os registros");
        notNull(pageable, "Página inválida");
        return alunoRepository.findAll(pageable).map(aluno -> this.responseMapper.map(aluno));
    }

    @Override
    public Optional<AlunoResponse> update(Long id, AlunoRequest alunoRequest) {
        LOGGER.info("Atualizando o registro");
        notNull(id, "ID Inváido");

        Aluno alunoUpdate = this.requestMapper.map(alunoRequest);

        return alunoRepository.findById(id).map(aluno -> {
            aluno.setDocumento(alunoUpdate.getDocumento());
            aluno.setName(alunoUpdate.getName());
            return this.responseMapper.map(alunoRepository.saveAndFlush(aluno));
        });
    }

    @Override
    public Optional<AlunoResponse> get(Long id) {
        LOGGER.info("Buscando Registro");
        notNull(id, "ID Inválido");
        return alunoRepository.findById(id).map(this.responseMapper::map);
    }

    @Override
    public boolean delete(Long id) {
        LOGGER.info("Removendo o Registro");
        notNull(id, "ID inválido");

        try {
            alunoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            LOGGER.warn("Erro ao remover o registro {}", id);
        }
        return false;
    }
}

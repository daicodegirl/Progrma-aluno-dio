package br.com.dio.aluno.service.mapper;

import br.com.dio.aluno.model.request.AlunoRequest;
import br.com.dio.aluno.persistence.entity.Aluno;
import org.springframework.stereotype.Component;

@Component
public class AlunoRequestMapper implements Mapper<AlunoRequest, Aluno> {

    @Override
    public Aluno map(AlunoRequest input) {
        if (input == null) {
            return null;
        }
        Aluno aluno = new Aluno();
        aluno.setName(input.getName());
        aluno.setDocumento(input.getDocumento());
        return aluno;
    }
}
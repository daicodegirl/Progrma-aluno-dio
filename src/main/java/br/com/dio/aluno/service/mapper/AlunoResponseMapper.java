package br.com.dio.aluno.service.mapper;

import br.com.dio.aluno.persistence.entity.Aluno;
import org.springframework.stereotype.Component;
import br.com.dio.aluno.model.request.AlunoResponse;

@Component
public class AlunoResponseMapper implements Mapper<Aluno, AlunoResponse> {

    @Override
    public AlunoResponse map(Aluno input) {
        if (input == null) {
            return null;
        }

        AlunoResponse alunoResponse = new AlunoResponse();
        alunoResponse.setId(input.getId());
        alunoResponse.setName(input.getName());
        alunoResponse.setDocumento(input.getDocumento());

        return alunoResponse;
    }


}

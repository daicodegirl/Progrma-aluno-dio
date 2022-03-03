package br.com.dio.aluno.model.request;

import javax.validation.constraints.NotBlank;

public class AlunoRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String documento;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }
}

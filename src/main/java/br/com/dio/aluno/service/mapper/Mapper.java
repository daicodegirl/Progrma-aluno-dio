package br.com.dio.aluno.service.mapper;

public interface Mapper<A, B> {
    B map(A input);
}

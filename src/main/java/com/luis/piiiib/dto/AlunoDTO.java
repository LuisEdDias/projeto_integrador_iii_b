package com.luis.piiiib.dto;

import com.luis.piiiib.entities.Aluno;

public record AlunoDTO(String nome, String cpf, String dataNasc, String turma, int matricula) {

    public AlunoDTO(Aluno aluno){
        this(aluno.getNome(), aluno.getCpf(), aluno.getDataNasc(), aluno.getTurma().getNome(), aluno.getMatricula());
    }
}

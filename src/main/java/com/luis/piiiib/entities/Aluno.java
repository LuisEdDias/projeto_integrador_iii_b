package com.luis.piiiib.entities;

import com.luis.piiiib.dto.AlunoCadastroDTO;
import com.luis.piiiib.dto.AlunoAtualizarDTO;

public class Aluno {
    private static int proximaMatricula = 1000;
    private String nome;
    private final String cpf;
    private String dataNasc;
    private Turma turma;
    private final int matricula;

    public Aluno(AlunoCadastroDTO alunoCadastroDTO){
        this.nome = alunoCadastroDTO.nome();
        this.cpf = alunoCadastroDTO.cpf();
        this.dataNasc = formatarDataNasc(alunoCadastroDTO.dataNasc());
        this.turma = Turma.getTurma(alunoCadastroDTO.turma());
        this.matricula = proximaMatricula;
        proximaMatricula++;
    }

    public void atualizarDados(AlunoAtualizarDTO alunoAtualizarDTO){
        this.nome = alunoAtualizarDTO.nome();
        this.dataNasc = formatarDataNasc(alunoAtualizarDTO.dataNasc());
        this.turma = Turma.getTurma(alunoAtualizarDTO.turma());
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public Turma getTurma() {
        return turma;
    }

    public int getMatricula() {
        return matricula;
    }

    private String formatarDataNasc(String dataNasc){
        return String.format("%s/%s/%s",
                dataNasc.substring(8),
                dataNasc.substring(5, 7),
                dataNasc.substring(0, 4));
    }
}

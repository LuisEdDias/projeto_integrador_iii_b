package com.luis.piiiib.entities;

import com.luis.piiiib.util.ListaDeAlunos;

import java.util.ArrayList;
import java.util.List;

public class Turma extends ListaDeAlunos {
    private static final List<Turma> turmas = new ArrayList<>();
    private final String nome;

    public Turma(String nome) {
        this.nome = nome;
        turmas.add(this);
    }

    public Turma(){
        this.nome = null;
    }

    public String getNome() {
        return nome;
    }

    public List<Aluno> alunosDaTurma(){
        return buscarTodos().stream().filter(x -> x.getTurma().equals(this)).toList();
    }

    public static List<String> getNomes() {
        return turmas.stream().map(turma -> turma.nome).toList();
    }

    public static Turma getTurma(String nome) {
        for (Turma turma : turmas) {
            if (turma.getNome().equals(nome)) {
                return turma;
            }
        }
        return null;
    }
}

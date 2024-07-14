package com.luis.piiiib.util;

import com.luis.piiiib.entities.Aluno;

import java.util.*;

public class ListaDeAlunos {
    private static final HashMap<Integer, Aluno> alunos = new HashMap<>();
    private static final List<String> cpfCadastrados = new ArrayList<>();

    public void adicionarAluno(Aluno aluno) {
        if (alunoCadastrado(aluno.getCpf())){
            throw new IllegalArgumentException("Já existe um aluno cadastrado com esse CPF");
        }

        alunos.put(aluno.getMatricula(), aluno);
        cpfCadastrados.add(aluno.getCpf());
    }

    public void removerAluno(int matricula) {
        Aluno aluno = aluno(matricula);
        alunos.remove(matricula);
        cpfCadastrados.remove(aluno.getCpf());
    }

    public Aluno aluno(String cpf) {
        return alunos.values().stream()
                .filter(aluno -> aluno.getCpf().equals(cpf)).findFirst()
                .orElseThrow(() -> new NoSuchElementException("Aluno não encontrado!"));
    }

    public Aluno aluno(int matricula) {
        Optional<Aluno> aluno = Optional.ofNullable(alunos.get(matricula));

        aluno.orElseThrow(() -> new NoSuchElementException("Aluno não encontrado!"));
        return aluno.get();
    }

    public List<Aluno> buscarTodos() {
        return alunos.values().stream().toList();
    }

    public static boolean alunoCadastrado(String cpf) {
        return cpfCadastrados.contains(cpf);
    }
}

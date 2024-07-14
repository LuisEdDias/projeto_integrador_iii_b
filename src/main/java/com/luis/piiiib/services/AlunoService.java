package com.luis.piiiib.services;

import com.luis.piiiib.dto.AlunoCadastroDTO;
import com.luis.piiiib.dto.AlunoDTO;
import com.luis.piiiib.dto.AlunoAtualizarDTO;
import com.luis.piiiib.entities.Aluno;
import com.luis.piiiib.entities.Turma;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {
    private final Turma turma = new Turma();

    public List<AlunoDTO> buscarTodos(){
        return turma.buscarTodos().stream().map(AlunoDTO::new).toList();
    }

    public AlunoDTO aluno(int matricula){
        return new AlunoDTO(turma.aluno(matricula));
    }

    public AlunoDTO buscarAluno(String query, String type){
            if (type.equals("matricula")) {
                int matricula;
                try {
                    matricula = Integer.parseInt(query);
                } catch (NumberFormatException e) {
                    throw new NumberFormatException("Matrícula inválida!");
                }
                return new AlunoDTO(turma.aluno(matricula));
            } else {
                return new AlunoDTO(turma.aluno(query));
            }
    }

    public AlunoDTO novoAluno(AlunoCadastroDTO aluno){
        Aluno novoAluno = new Aluno(aluno);
        turma.adicionarAluno(novoAluno);
        return new AlunoDTO(novoAluno);
    }

    public AlunoDTO atualizarAluno(int matricula, AlunoAtualizarDTO alunoAtualizarDTO){
        Aluno aluno = turma.aluno(matricula);
        aluno.atualizarDados(alunoAtualizarDTO);
        return new AlunoDTO(aluno);
    }

    public void removerAluno(int matricula){
        turma.removerAluno(matricula);
    }
}

package com.luis.piiiib.controllers;

import com.luis.piiiib.dto.*;
import com.luis.piiiib.entities.Turma;
import com.luis.piiiib.services.AlunoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/")
public class AlunoController {
    private final AlunoService alunoService = new AlunoService();

    @GetMapping
    public String indexView(Model model){
        model.addAttribute("alunos", alunoService.buscarTodos());
        return "alunos/index";
    }

    @GetMapping("/aluno/{matricula}")
    public String alunoView(Model model, @PathVariable int matricula){
        try {
            AlunoDTO aluno = alunoService.aluno(matricula);
            model.addAttribute("aluno", aluno);
            return "alunos/aluno";
        } catch (Exception e) {
            return indexViewComErro(model, e.getMessage());
        }
    }

    @GetMapping("/buscar")
    public String buscar(Model model, @RequestParam String type, @RequestParam String query){
        try {
            AlunoDTO aluno = alunoService.buscarAluno(query, type);
            model.addAttribute("aluno", aluno);
            return "alunos/aluno";
        } catch (Exception e) {
            return indexViewComErro(model, e.getMessage());
        }
    }

    @GetMapping("/cadastro")
    public String cadastrarView(Model model){
        String dataHoje = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        model.addAttribute("data", dataHoje);
        model.addAttribute("turmas", Turma.getNomes());
        return "alunos/cadastro";
    }

    @PostMapping
    public String cadastrar(Model model, @Valid AlunoCadastroDTO alunoCadastroDTO, BindingResult result){
        if(result.hasErrors()){
            String error = "Dados de cadastro inválidos!";
            return indexViewComErro(model, error);
        }

        try {
            AlunoDTO aluno = alunoService.novoAluno(alunoCadastroDTO);
            model.addAttribute("success", "Aluno cadastrado com sucesso!");
            model.addAttribute("aluno", aluno);
            return "alunos/aluno";
        } catch (Exception e) {
            return indexViewComErro(model, e.getMessage());
        }
    }

    @GetMapping("aluno/{matricula}/atualizar")
    public String atualizarView(Model model, @PathVariable int matricula){
        try {
            AlunoDTO aluno = alunoService.aluno(matricula);
            model.addAttribute("aluno", aluno);
            model.addAttribute("turmas", Turma.getNomes());
            String dataHoje = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            model.addAttribute("data", dataHoje);
            return "alunos/atualizar";
        } catch (Exception e) {
            return indexViewComErro(model, e.getMessage());
        }
    }

    @PutMapping("atualizar/{matricula}")
    public String atualizar(Model model, @PathVariable int matricula, @Valid AlunoAtualizarDTO alunoAtualizarDTO, BindingResult result){
        if(result.hasErrors()){
            String error = "Dados de cadastro inválidos!";
            return indexViewComErro(model, error);
        }

        try {
            AlunoDTO aluno = alunoService.atualizarAluno(matricula, alunoAtualizarDTO);
            model.addAttribute("success", "Os dados do aluno " + aluno.nome() + " foram atualizados com sucesso!");
            model.addAttribute("aluno", aluno);
            return "alunos/aluno";
        } catch (Exception e) {
            return indexViewComErro(model, e.getMessage());
        }
    }

    @DeleteMapping("/deletar/{matricula}")
    public String deletar(Model model, @PathVariable int matricula){
        try {
            alunoService.removerAluno(matricula);
            model.addAttribute("success", "Aluno removido com sucesso!");
            model.addAttribute("alunos", alunoService.buscarTodos());
            return "alunos/index";
        } catch (Exception e) {
            return indexViewComErro(model, e.getMessage());
        }
    }

    private String indexViewComErro(Model model, String erro){
        model.addAttribute("error", erro);
        model.addAttribute("alunos", alunoService.buscarTodos());
        return "alunos/index";
    }
}

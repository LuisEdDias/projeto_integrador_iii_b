package com.luis.piiiib.controllers;

import com.luis.piiiib.dto.AlunoCadastroDTO;
import com.luis.piiiib.dto.AlunoDTO;
import com.luis.piiiib.dto.AlunoAtualizarDTO;
import com.luis.piiiib.entities.Turma;
import com.luis.piiiib.services.AlunoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
            return retornarIndexComErro(model, e.getMessage());
        }
    }

    @GetMapping("/buscar")
    public String buscar(Model model, @RequestParam String type, @RequestParam String query){
        try {
            AlunoDTO aluno = alunoService.buscarAluno(query, type);
            model.addAttribute("aluno", aluno);
            return "alunos/aluno";
        } catch (Exception e) {
            return retornarIndexComErro(model, e.getMessage());
        }
    }

    @GetMapping("/cadastro")
    public String cadastrarView(Model model){
        model.addAttribute("turmas", Turma.getNomes());
        return "alunos/cadastro";
    }

    @PostMapping
    public String cadastrar(Model model, AlunoCadastroDTO alunoCadastroDTO){
        try {
            AlunoDTO aluno = alunoService.novoAluno(alunoCadastroDTO);
            model.addAttribute("success", "Aluno cadastrado com sucesso!");
            model.addAttribute("aluno", aluno);
            return "alunos/aluno";
        } catch (Exception e) {
            return retornarIndexComErro(model, e.getMessage());
        }
    }

    @GetMapping("aluno/{matricula}/atualizar")
    public String atualizarView(Model model, @PathVariable int matricula){
        try {
            AlunoDTO aluno = alunoService.aluno(matricula);
            model.addAttribute("aluno", aluno);
            model.addAttribute("turmas", Turma.getNomes());
            return "alunos/atualizar";
        } catch (Exception e) {
            return retornarIndexComErro(model, e.getMessage());
        }
    }

    @PutMapping("atualizar/{matricula}")
    public String atualizar(Model model, @PathVariable int matricula, AlunoAtualizarDTO alunoAtualizarDTO){
        try {
            AlunoDTO aluno = alunoService.atualizarAluno(matricula, alunoAtualizarDTO);
            model.addAttribute("success", "Os dados do aluno " + aluno.nome() + " foram atualizados com sucesso!");
            model.addAttribute("aluno", aluno);
            return "alunos/aluno";
        } catch (Exception e) {
            return retornarIndexComErro(model, e.getMessage());
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
            return retornarIndexComErro(model, e.getMessage());
        }
    }

    private String retornarIndexComErro(Model model, String erro){
        model.addAttribute("error", erro);
        model.addAttribute("alunos", alunoService.buscarTodos());
        return "alunos/index";
    }
}

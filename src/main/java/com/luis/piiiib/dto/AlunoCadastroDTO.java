package com.luis.piiiib.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AlunoCadastroDTO(
        @NotBlank @Pattern(regexp = "^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]{8,}")
        String nome,
        @NotBlank @Pattern(regexp = "^\\d{3}[.]\\d{3}[.]\\d{3}-\\d{2}$")
        String cpf,
        @NotBlank @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$")
        String dataNasc,
        @NotBlank
        String turma
) {
}

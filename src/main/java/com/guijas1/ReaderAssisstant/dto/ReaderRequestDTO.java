package com.guijas1.ReaderAssisstant.dto;

import com.guijas1.ReaderAssisstant.entity.TypeReader;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ReaderRequestDTO(
        @NotBlank
        @Size(max = 150)
        String nome,

        @Size(max = 1000)
        String descricao,

        @NotNull
        TypeReader tipoReader,

        @NotNull
        @Min(1)
        Integer totalPaginas,

        @NotBlank
        @Size(max = 500)
        String arquivoChave,

        @Size(max = 255)
        String nomeArquivo,

        @Min(0)
        Long tamanhoArquivoBytes
) {
}

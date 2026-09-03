package com.guijas1.ReaderAssisstant.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "reader_entity")
@Getter
@Setter
public class ReaderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 150)
    @Column(name = "nome", nullable = false, length = 150)
    private String nome;

    @Size(max = 1000)
    @Column(name = "descricao", length = 1000)
    private String descricao;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_reader", nullable = false, length = 20)
    private TypeReader tipoReader;

    @NotNull
    @Min(1)
    @Column(name = "total_paginas", nullable = false)
    private Integer totalPaginas;

    /** Identifica o PDF no serviço de armazenamento (S3, filesystem etc.). */
    @NotBlank
    @Size(max = 500)
    @Column(name = "arquivo_chave", nullable = false, length = 500)
    private String arquivoChave;

    @Size(max = 255)
    @Column(name = "nome_arquivo", length = 255)
    private String nomeArquivo;

    @Column(name = "tamanho_arquivo_bytes")
    @Min(0)
    private Long tamanhoArquivoBytes;

    @Column(name = "data_importacao", nullable = false, updatable = false)
    private LocalDateTime dataImportacao;

    @PrePersist
    private void registrarImportacao() {
        if (dataImportacao == null) {
            dataImportacao = LocalDateTime.now();
        }
    }

}

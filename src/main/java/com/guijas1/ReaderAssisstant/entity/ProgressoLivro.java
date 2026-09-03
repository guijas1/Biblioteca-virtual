package com.guijas1.ReaderAssisstant.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "progresso_livro", uniqueConstraints = {
        @UniqueConstraint(
                name = "uk_progresso_usuario_livro",
                columnNames = {"usuario_id", "livro_id"}
        )
})
@Getter
@Setter
public class ProgressoLivro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "livro_id", nullable = false)
    private ReaderEntity livro;

    @NotNull
    @Min(0)
    @Column(name = "pagina_atual", nullable = false)
    @Setter(AccessLevel.NONE)
    private Integer paginaAtual = 0;

    @Column(name = "iniciado_em")
    private LocalDateTime iniciadoEm;

    @Column(name = "finalizado_em")
    private LocalDateTime finalizadoEm;

    @Column(name = "atualizado_em", nullable = false)
    private LocalDateTime atualizadoEm;

    @PrePersist
    @PreUpdate
    private void atualizarData(){
        atualizadoEm = LocalDateTime.now();
    }

    @Transient
    public double getPercentual(){
        if(livro == null || livro.getTotalPaginas() == null || livro.getTotalPaginas() == 0){
            return 0;
        }

        return paginaAtual * 100.0 / livro.getTotalPaginas();

    }

    @Transient
    public boolean isConcluido(){
        return livro != null && paginaAtual >= livro.getTotalPaginas();
    }

    public void atualizarPagina(Integer novaPagina){
        if(novaPagina == null || novaPagina < 0){
            throw new IllegalArgumentException(
                    "A pagina deve ser maior ou igual a zero"
            );
        }
        if(livro == null){
            throw new IllegalStateException("O livro precisa estar definido antes da página");
        }
        if(novaPagina > livro.getTotalPaginas()){
            throw new IllegalArgumentException(
                    "A página não pode ultrapassar o total do livro."
            );
        }
        if(iniciadoEm == null && novaPagina > 0){
            iniciadoEm = LocalDateTime.now();
        }

        paginaAtual = novaPagina;
        if(isConcluido() && finalizadoEm == null){
            finalizadoEm = LocalDateTime.now();
        }
    }

}

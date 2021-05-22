package com.projects.logapi.domain.model;

import com.projects.logapi.domain.exception.NegocioException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Entrega {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cliente cliente;

    @Embedded
    private Destinatario destinatario;

    private BigDecimal taxa;

    @Enumerated(EnumType.STRING)
    private StatusEntrega status;

    private OffsetDateTime dataPedido;
    private OffsetDateTime dataFinalizacao;

    @OneToMany(mappedBy = "entrega", cascade = CascadeType.ALL)
    List<Ocorrencia> ocorrencias = new ArrayList<>();

    public Ocorrencia adicionarOcorrencia(String descricao) {
        Ocorrencia ocorrencia = new Ocorrencia();
        ocorrencia.setDescricao(descricao);
        ocorrencia.setEntrega(this);
        ocorrencia.setDataRegistro(OffsetDateTime.now());

        ocorrencias.add(ocorrencia);

        return ocorrencia;
    }

    public void finalizar() {
        if(naoPodeSerFinalizada())
            throw new NegocioException("Entrega não pode ser finalizada");

        this.setStatus(StatusEntrega.FINALIZADA);
        this.setDataFinalizacao(OffsetDateTime.now());
    }

    public boolean podeSerFinalizada() {
        return StatusEntrega.PENDENTE.equals(this.getStatus());
    }

    public boolean naoPodeSerFinalizada() {
        return !podeSerFinalizada();
    }

}

package br.com.banco.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transferencia")
public class Transferencia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate data_transferencia;

    private Double valor;

    private String tipo;

    private String nome_operador_transacao;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "conta_id")
    private Conta conta;

    public Transferencia(Integer id, LocalDate data_transferencia,
                         Double valor, String tipo, String nome_operador_transacao) {
        this.id = id;
        this.data_transferencia = data_transferencia;
        this.valor = valor;
        this.tipo = tipo;
        this.nome_operador_transacao = nome_operador_transacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getData_transferencia() {
        return data_transferencia;
    }

    public void setData_transferencia(LocalDate data_transferencia) {
        this.data_transferencia = data_transferencia;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome_operador_transacao() {
        return nome_operador_transacao;
    }

    public void setNome_operador_transacao(String nome_operador_transacao) {
        this.nome_operador_transacao = nome_operador_transacao;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transferencia that = (Transferencia) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

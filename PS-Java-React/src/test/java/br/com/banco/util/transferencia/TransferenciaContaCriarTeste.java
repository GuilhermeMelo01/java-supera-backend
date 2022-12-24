package br.com.banco.util.transferencia;

import br.com.banco.model.Conta;
import br.com.banco.model.Transferencia;

import java.time.LocalDate;
import java.util.List;

public class TransferenciaContaCriarTeste {

    public static Transferencia criarTransferenciaConta() {
        Conta conta = new Conta(1, "Monalisa");
        Transferencia transferencia = new Transferencia(1, LocalDate.now(), 100.0,
                "Deposito", "Guilherme", conta);
        conta.getTransferencias().add(transferencia);
        return transferencia;
    }
}

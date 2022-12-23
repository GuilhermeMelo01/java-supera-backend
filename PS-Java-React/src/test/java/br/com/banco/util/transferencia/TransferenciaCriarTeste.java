package br.com.banco.util.transferencia;

import br.com.banco.model.Transferencia;

import java.time.LocalDate;

public class TransferenciaCriarTeste {

    public static Transferencia transferenciasCriada(){
        return new Transferencia(1, LocalDate.now(), 100.0, "Deposito", "Guilherme");
    }

}

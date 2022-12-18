package br.com.banco.service;

import br.com.banco.model.Conta;
import br.com.banco.model.Transferencia;
import br.com.banco.repository.ContaRepository;
import br.com.banco.repository.TransferenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransferenciaService {

    private final TransferenciaRepository transferenciaRepository;
    private final ContaRepository contaRepository;

    public List<Transferencia> buscarTodasTransferencias() {
        return transferenciaRepository.findAll();
    }

    public List<Transferencia> buscarTodasTransferenciaPorIdConta(Integer idConta) {
        Conta conta = contaRepository.findById(idConta).orElseThrow();
        return conta.getTransferencias();
    }

    public List<Transferencia> buscarTransferenciasPorData(LocalDate dataInicial, LocalDate dataFinal) {
        List<Transferencia> transferencias = transferenciaRepository.findAll();
        int count = transferencias.size() - 1;
        while (count >= 0) {
            if (transferencias.get(count).getData_transferencia().isBefore(dataInicial) ||
                    transferencias.get(count).getData_transferencia().isAfter(dataFinal)) {
                transferencias.remove(count);
            }
            count--;
        }
        return transferencias;
    }

    public List<Transferencia> buscarTransferenciasPorNomeOperador(String nome) {
        List<Transferencia> retornoTrans = new ArrayList<>();
        List<Transferencia> transferencias = transferenciaRepository.findAll();
        for (int i = 0; i < transferencias.size(); i++) {
            if (transferencias.get(i).getNome_operador_transacao() != null &&
                    transferencias.get(i).getNome_operador_transacao().equals(nome)) {
                retornoTrans.add(transferencias.get(i));
            }
        }
        return retornoTrans;
    }
}

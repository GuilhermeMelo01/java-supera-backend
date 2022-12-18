package br.com.banco.service;

import br.com.banco.model.Conta;
import br.com.banco.model.Transferencia;
import br.com.banco.repository.ContaRepository;
import br.com.banco.repository.TransferenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public List<Transferencia> buscarTransferenciasPorData(Integer id, LocalDate dataInicial, LocalDate dataFinal) {
        Conta conta = contaRepository.findById(id).orElseThrow();
        List<Transferencia> transferencias = conta.getTransferencias();
        int count = transferencias.size()-1;
        while (count >= 0) {
            if (transferencias.get(count).getData_transferencia().isBefore(dataInicial) ||
                    transferencias.get(count).getData_transferencia().isAfter(dataFinal)) {
                transferencias.remove(count);
            }
            count--;
        }
        return transferencias;
    }
}

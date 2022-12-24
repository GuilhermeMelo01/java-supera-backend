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

    public List<Transferencia> buscarTodasTransferenciasPorData(LocalDate dataInicial, LocalDate dataFinal) {
        return buscaPorDatas(dataInicial, dataFinal);
    }

    public List<Transferencia> buscarTransferenciasPorNomeOperador(String nome) {
        List<Transferencia> retornoTransferencias = new ArrayList<>();
        List<Transferencia> transferencias = transferenciaRepository.findAll();
        for (Transferencia transferencia : transferencias) {
            if (transferencia.getNome_operador_transacao() != null &&
                    transferencia.getNome_operador_transacao().equals(nome)) {
                retornoTransferencias.add(transferencia);
            }
        }
        return retornoTransferencias;
    }
    public List<Transferencia> buscarTransferenciasPorTodosFiltros(LocalDate dataInicial, LocalDate dataFinal,
                                                                   String nomeOperador){
        List<Transferencia> transferencias = buscarTransferenciasPorNomeOperador(nomeOperador);
        return buscarTransferenciaData(transferencias, dataInicial, dataFinal);
    }

    private List<Transferencia> buscaPorDatas(LocalDate dataInicial, LocalDate dataFinal) {
        List<Transferencia> transferencias = transferenciaRepository.findAll();
        return buscarTransferenciaData(transferencias, dataInicial, dataFinal);
    }

    private List<Transferencia> buscarTransferenciaData(List<Transferencia> transferencias,
                                       LocalDate dataInicial, LocalDate dataFinal){
        List<Transferencia> retornoTransferencia = new ArrayList<>();
        for (Transferencia transferencia : transferencias) {
            if (transferencia.getData_transferencia().isAfter(dataInicial) &&
                    transferencia.getData_transferencia().isBefore(dataFinal)) {
                retornoTransferencia.add(transferencia);
            }
        }
        return retornoTransferencia;
    }
}

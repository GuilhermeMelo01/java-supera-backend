package br.com.banco.controller;

import br.com.banco.model.Transferencia;
import br.com.banco.service.TransferenciaService;
import br.com.banco.util.transferencia.TransferenciaContaCriarTeste;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

@ExtendWith(SpringExtension.class)
class TransferenciaControllerTest {

    @InjectMocks
    private TransferenciaController transferenciaController;

    @Mock
    private TransferenciaService transferenciaServiceMock;

    @BeforeEach
    void Setup(){
        List<Transferencia> transferencias = List.of(TransferenciaContaCriarTeste.criarTransferenciaConta());
        BDDMockito.when(transferenciaServiceMock.buscarTodasTransferencias())
                .thenReturn(transferencias);

        BDDMockito.when(transferenciaServiceMock.buscarTodasTransferenciaPorIdConta(ArgumentMatchers.anyInt()))
                .thenReturn(transferencias);

        BDDMockito.when(transferenciaServiceMock.buscarTodasTransferenciasPorData(
                ArgumentMatchers.any(), ArgumentMatchers.any()))
                .thenReturn(transferencias);
    }

    @Test
    @DisplayName("Retorna sucesso quando for retornado uma lista de transferencias")
    void list_RetornaListaDeTransferencias_Sucesso(){
        String nomeOperadorEsperado = TransferenciaContaCriarTeste
                .criarTransferenciaConta().getNome_operador_transacao();

        List<Transferencia> transferencias = transferenciaController.buscarTransferencias().getBody();

        Assertions.assertThat(transferencias).isNotNull();

        Assertions.assertThat(transferencias)
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(transferencias.get(0).getNome_operador_transacao()).isEqualTo(nomeOperadorEsperado);
    }

    @Test
    @DisplayName("Retorna sucesso quando for retornado uma lista de transferencias a partir do Id da conta")
    void list_RetornaListaDeTransferenciasPorIdConta_Sucesso(){
        Integer idContaEsperado = TransferenciaContaCriarTeste
                .criarTransferenciaConta().getConta().getId_conta();

        List<Transferencia> transferencias = transferenciaController.buscarTransferencias(1).getBody();

        Assertions.assertThat(transferencias).isNotNull();

        Assertions.assertThat(transferencias)
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(transferencias.get(0).getConta().getId_conta()).isEqualTo(idContaEsperado);
    }

    @Test
    @DisplayName("Retorna sucesso quando for retornado uma lista de transferencias entre a data inicial e data final")
    void list_RetornaListaDeTransferenciasComDataInicialEDataFinal_Sucesso(){
        LocalDate dataTransferenciaEsperada = TransferenciaContaCriarTeste
                .criarTransferenciaConta().getData_transferencia();

        List<Transferencia> transferencias = transferenciaController.buscarTransferencias(LocalDate.now(),
                LocalDate.now()).getBody();

        Assertions.assertThat(transferencias).isNotNull();

        Assertions.assertThat(transferencias)
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(transferencias.get(0).getData_transferencia()).isEqualTo(dataTransferenciaEsperada);
    }
}
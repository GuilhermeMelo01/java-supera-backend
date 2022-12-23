package br.com.banco.service;

import br.com.banco.model.Transferencia;
import br.com.banco.repository.ContaRepository;
import br.com.banco.repository.TransferenciaRepository;
import br.com.banco.util.transferencia.TransferenciaCriarTeste;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
class TransferenciaServiceTest {

    @InjectMocks
    private TransferenciaService transferenciaService;

    @Mock
    private TransferenciaRepository transferenciaRepositoryMock;
    private ContaRepository contaRepositoryMock;
    @BeforeEach
    void setUp() {
        List<Transferencia> listaTransferencia = List.of(TransferenciaCriarTeste.transferenciasCriada());
        BDDMockito.when(transferenciaRepositoryMock.findAll())
                .thenReturn(listaTransferencia);
    }

    @Test
    @DisplayName("Retorna sucesso quando for retornado uma lista de transferencias")
    void retornaListaDeTransferencias_QuandoTiverSucesso(){
        String nomeEsperado = TransferenciaCriarTeste.transferenciasCriada().getNome_operador_transacao();
        List<Transferencia> listaTransferencias = transferenciaService.buscarTodasTransferencias();

        Assertions.assertThat(listaTransferencias)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(listaTransferencias.get(0).getNome_operador_transacao()).isEqualTo(nomeEsperado);
    }
}
package br.com.banco.controller;

import br.com.banco.model.Transferencia;
import br.com.banco.service.TransferenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/transferencias")
@RestController
@CrossOrigin(origins = "*")
public class TransferenciaController {

    private final TransferenciaService service;

    @GetMapping
    public ResponseEntity<List<Transferencia>> buscarTransferencias() {
        List<Transferencia> transferencias = service.buscarTodasTransferencias();
        return ResponseEntity.ok().body(transferencias);
    }

    @GetMapping(value = "/{idConta}")
    public ResponseEntity<List<Transferencia>> buscarTransferencias(@PathVariable Integer idConta) {
        List<Transferencia> transferencias = service.buscarTodasTransferenciaPorIdConta(idConta);
        return ResponseEntity.ok().body(transferencias);
    }

    @GetMapping(value = "/data")
    public ResponseEntity<List<Transferencia>> buscarTransferencias(@RequestParam
                                                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                    LocalDate dataInicial,
                                                                    @RequestParam
                                                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                    LocalDate dataFinal) {
        List<Transferencia> transferencias = service.buscarTodasTransferenciasPorData(dataInicial, dataFinal);
        return ResponseEntity.ok().body(transferencias);
    }

    @GetMapping(value = "/nomeOperador")
    public ResponseEntity<List<Transferencia>> buscarTransferencias(@RequestParam("nome") String nome) {
        List<Transferencia> transferencias = service.buscarTodasTransferenciasPorNomeOperador(nome);
        return ResponseEntity.ok().body(transferencias);
    }

    @GetMapping(value = "/todos")
    public ResponseEntity<List<Transferencia>> buscarTransferencias(@RequestParam("nome") String nome,
                                                                    @RequestParam
                                                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                    LocalDate dataInicial,
                                                                    @RequestParam
                                                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                    LocalDate dataFinal) {
        List<Transferencia> transferencias = service.buscarTodasTransferenciasComTodosFiltros(dataInicial, dataFinal, nome);
        return ResponseEntity.ok().body(transferencias);
    }

}

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

    @GetMapping(value = "data/{idConta}")
    public ResponseEntity<List<Transferencia>> buscarTransferencias(@PathVariable Integer idConta,
                                                                    @RequestParam
                                                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                    LocalDate dataInicial,
                                                                    @RequestParam
                                                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                    LocalDate dataFinal) {
        List<Transferencia> transferencias = service.buscarTransferenciasPorData(idConta, dataInicial, dataFinal);
        return ResponseEntity.ok().body(transferencias);
    }
}

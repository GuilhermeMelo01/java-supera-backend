package br.com.banco.controller;

import br.com.banco.model.Transferencia;
import br.com.banco.service.TransferenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/transferencias")
@RestController
public class TransferenciaController {

    private final TransferenciaService service;

    @GetMapping
    public ResponseEntity<List<Transferencia>> buscarTodasTransferencias(){
        List<Transferencia> transferencias = service.buscarTodasTransferencias();
        return ResponseEntity.ok().body(transferencias);
    }

    @GetMapping(value = "{/idConta}")
    public ResponseEntity<List<Transferencia>> buscarTodasTransferenciasPorIdConta(@PathVariable Integer idConta){
        List<Transferencia> transferencias = service.buscarTodasTransferenciaPorIdConta(idConta);
        return ResponseEntity.ok().body(transferencias);
    }
}

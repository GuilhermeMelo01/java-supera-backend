package br.com.banco.service;

import br.com.banco.model.Conta;
import br.com.banco.repository.ContaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContaService {

    private final ContaRepository contaRepository;

    public Conta buscarPorId(Integer id){
        return contaRepository.findById(id).orElseThrow();
    }
}

package com.java.diogo.challenge.service;

import com.java.diogo.challenge.dto.PagamentoRequestDTO;
import com.java.diogo.challenge.dto.PagamentoResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PagamentoService {
    PagamentoResponseDTO realizarPagamento(PagamentoRequestDTO request);
    PagamentoResponseDTO consultarPagamento(String id);
    List<PagamentoResponseDTO> consultarTodosPagamentos();
    PagamentoResponseDTO realizarEstorno(String id);
}

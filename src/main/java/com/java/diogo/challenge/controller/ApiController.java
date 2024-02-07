package com.java.diogo.challenge.controller;

import com.java.diogo.challenge.api.ApiResponse;
import com.java.diogo.challenge.dto.TransacaoRequestDTO;
import com.java.diogo.challenge.service.PagamentoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pagamento")
public class ApiController {

    private final PagamentoService service;

    public ApiController(PagamentoService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(description = "Operação Realizar Pagamento")
    ResponseEntity<ApiResponse> realizarPagamento(@RequestBody TransacaoRequestDTO request) {
        return ResponseEntity.ok(ApiResponse
                .builder()
                .transacao(service.realizarPagamento(request.getTransacao()))
                .build());
    }

    @GetMapping("/{id}")
    @Operation(description = "Operação Consultar Pagamento")
    ResponseEntity<ApiResponse> consultarPagamento(@PathVariable String id) {
        return ResponseEntity.ok(ApiResponse
                .builder()
                .transacao(service.consultarPagamento(id))
                .build());
    }

    @GetMapping
    @Operation(description = "Operação Consultar Todos Pagamentos")
    ResponseEntity<ApiResponse> consultarTodosPagamentos() {
        return ResponseEntity.ok(ApiResponse
                .builder()
                .transacao(service.consultarTodosPagamentos())
                .build());
    }

    @PatchMapping("/estorno/{id}")
    @Operation(description = "Operação Estornar Pagamento")
    ResponseEntity<ApiResponse> estornoPagamento(@PathVariable String id) {
        return ResponseEntity.ok(ApiResponse
                .builder()
                .transacao(service.realizarEstorno(id))
                .build());
    }
}

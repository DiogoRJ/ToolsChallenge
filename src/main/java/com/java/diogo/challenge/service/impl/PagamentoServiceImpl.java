package com.java.diogo.challenge.service.impl;

import com.java.diogo.challenge.dto.PagamentoRequestDTO;
import com.java.diogo.challenge.dto.PagamentoResponseDTO;
import com.java.diogo.challenge.entity.Pagamento;
import com.java.diogo.challenge.enums.StatusEnum;
import com.java.diogo.challenge.exception.BadRequestException;
import com.java.diogo.challenge.exception.NotFoundException;
import com.java.diogo.challenge.mapper.Mappable;
import com.java.diogo.challenge.repository.PagamentoRepository;
import com.java.diogo.challenge.service.PagamentoService;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PagamentoServiceImpl implements PagamentoService, Mappable {

    private final PagamentoRepository pagamentoRepository;

    public PagamentoServiceImpl(PagamentoRepository pagamentoRepository) {
        this.pagamentoRepository = pagamentoRepository;
    }

    @Override
    @Transactional
    public PagamentoResponseDTO realizarPagamento(PagamentoRequestDTO request) {

        validarPagamentoRequest(request);
        Pagamento saved = salvarPagamento(request);

        return map(saved, PagamentoResponseDTO.class);
    }

    @Transactional
    private Pagamento salvarPagamento(PagamentoRequestDTO request) {
        Pagamento entity = map(request, Pagamento.class);
        entity.getDescricao().setPagamento(entity);
        entity.getFormaPagamento().setPagamento(entity);

        return pagamentoRepository.save(entity);
    }

    private void validarPagamentoRequest(PagamentoRequestDTO request) {
        if (pagamentoRepository.existsById(request.getId())) {
            throw new BadRequestException("id informado já está cadastrado.");
        }
    }

    @Override
    @Transactional
    public PagamentoResponseDTO consultarPagamento(String id) {
        validarConsultaPagamento(id);
        Pagamento find = pagamentoRepository.getReferenceById(id);
        return map(find, PagamentoResponseDTO.class);
    }

    private void validarConsultaPagamento(String id) {
        if (StringUtils.isBlank(id)) {
            throw new BadRequestException("Informe o 'id'.");
        }
        if (BooleanUtils.isFalse(pagamentoRepository.existsById(id))) {
            throw new NotFoundException("Não foi possivel encontrar o registro.");
        }
    }

    @Override
    @Transactional
    public List<PagamentoResponseDTO> consultarTodosPagamentos() {
        return map(pagamentoRepository.findAll(), PagamentoResponseDTO.class);
    }

    @Override
    public PagamentoResponseDTO realizarEstorno(String id) {
        validarConsultaPagamento(id);

        Pagamento entity = pagamentoRepository.findById(id).get();
        entity.getDescricao().setStatus(StatusEnum.CANCELADO);

        return map(pagamentoRepository.saveAndFlush(entity), PagamentoResponseDTO.class);
    }
}

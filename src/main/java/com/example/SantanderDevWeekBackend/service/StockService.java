package com.example.SantanderDevWeekBackend.service;

import com.example.SantanderDevWeekBackend.exceptions.BusinessException;
import com.example.SantanderDevWeekBackend.exceptions.NotFoundException;
import com.example.SantanderDevWeekBackend.mapper.StockMapper;
import com.example.SantanderDevWeekBackend.model.Stock;
import com.example.SantanderDevWeekBackend.model.dto.StockDTO;
import com.example.SantanderDevWeekBackend.repository.StockRepository;
import com.example.SantanderDevWeekBackend.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StockService {

    @Autowired
    private StockRepository repository;

    @Autowired
    private StockMapper mapper;

    // Cria um novo registro
    @Transactional
    public StockDTO save(StockDTO dto) {
        Optional<Stock> optionalStock = repository.findByNameAndDate(dto.getName(), dto.getDate());
        if(optionalStock.isPresent()){
            throw new BusinessException(MessageUtils.STOCK_ALREADY_EXISTS);
        }
        Stock stock = mapper.toEntity(dto);
        repository.save(stock);
        return mapper.toDTO(stock);
    }

    // Atualiza um registro
    @Transactional
    public StockDTO update(StockDTO dto) {
        Optional<Stock> optionalStock = repository.findByStockUpdate(dto.getName(), dto.getDate(), dto.getId());
        if(optionalStock.isPresent()){
            throw new BusinessException(MessageUtils.STOCK_ALREADY_EXISTS);
        }
        Stock stock = mapper.toEntity(dto);
        repository.save(stock);
        return mapper.toDTO(stock);
    }

    // Seleciona a partir do ID
    @Transactional(readOnly = true)
    public StockDTO findByID(Long id) {
        return repository.findById(id).map(mapper::toDTO).orElseThrow(NotFoundException::new);
    }

    // Seleciona todos os registros
    @Transactional(readOnly = true)
    public List<StockDTO> findAll() {
        List<Stock> list = repository.findAll();
        return mapper.toDTO(list);
    }

    // Seleciona todos os registros da data corrente
    @Transactional(readOnly = true)
    public List<StockDTO> findByToday() {
        return repository.findByToday(LocalDate.now()).map(mapper::toDTO).orElseThrow(NotFoundException::new);
    }

    // Deleta um registro se ele existir
    @Transactional
    public StockDTO delete(Long id) {
        StockDTO dto = this.findByID(id);
        repository.deleteById(dto.getId());
        return dto;
    }
}

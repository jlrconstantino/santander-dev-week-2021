package com.example.SantanderDevWeekBackend.mapper;

import com.example.SantanderDevWeekBackend.model.Stock;
import com.example.SantanderDevWeekBackend.model.dto.StockDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StockMapper {

    // Converte DTO em entidade
    public Stock toEntity(StockDTO dto) {
        Stock stock = new Stock();
        stock.setId(dto.getId());
        stock.setName(dto.getName());
        stock.setPrice(dto.getPrice());
        stock.setVariation(dto.getVariation());
        stock.setDate(dto.getDate());
        return stock;
    }

    // Conversão entidade -> DTO
    public StockDTO toDTO(Stock stock) {
        StockDTO dto = new StockDTO();
        dto.setId(stock.getId());
        dto.setName(stock.getName());
        dto.setPrice(stock.getPrice());
        dto.setVariation(stock.getVariation());
        dto.setDate(stock.getDate());
        return dto;
    }

    // Conversão de entidades a DTOs
    public List<StockDTO> toDTO(List<Stock> listStock){
        return listStock.stream().map(this::toDTO).collect(Collectors.toList());
    }
}

package com.invoiceapp.invoice_API.mapper;

import com.invoiceapp.invoice_API.dto.InvoiceDTO;
import com.invoiceapp.invoice_API.model.Invoice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {

    InvoiceMapper INSTANCE = Mappers.getMapper(InvoiceMapper.class);

    // DTO -> Entity
    @Mapping(target = "id", ignore = true)
    Invoice toEntity(InvoiceDTO dto);

    // Entity -> DTO
    InvoiceDTO toDto(Invoice entity);

    // List<DTO> -> List<Entity>
    List<Invoice> toEntityList(List<InvoiceDTO> dtoList);

    // List<Entity> -> List<DTO>
    List<InvoiceDTO> toDtoList(List<Invoice> entityList);
}

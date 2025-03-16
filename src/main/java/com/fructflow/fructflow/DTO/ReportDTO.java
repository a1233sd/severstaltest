package com.fructflow.fructflow.DTO;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class ReportDTO {
    private Long supplierId;
    private String supplierName;
    private String productCategory;
    private BigDecimal totalQuantity = BigDecimal.ZERO;
    private BigDecimal totalCost = BigDecimal.ZERO;
}

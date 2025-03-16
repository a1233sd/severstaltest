package com.fructflow.fructflow.service;

import com.fructflow.fructflow.DTO.ReportDTO;
import com.fructflow.fructflow.dbase.Delivery;
import com.fructflow.fructflow.dbase.DeliveryItem;
import com.fructflow.fructflow.dbase.Supplier;
import com.fructflow.fructflow.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    /**
     * Генерирует отчет по поставкам за указанный период.
     * Отчет агрегируется по поставщику и категории продукта.
     *
     * @param startDate дата начала периода
     * @param endDate   дата окончания периода
     * @return список объектов ReportDTO с итоговыми данными
     */
    public List<ReportDTO> generateReport(LocalDate startDate, LocalDate endDate) {
        List<Delivery> deliveries = deliveryRepository.findByDeliveryDateBetween(startDate, endDate);
        // Группируем данные по поставщику и категории продукта
        Map<String, ReportDTO> reportMap = new HashMap<>();

        for (Delivery delivery : deliveries) {
            Supplier supplier = delivery.getSupplier();
            for (DeliveryItem item : delivery.getDeliveryItems()) {
                String category = item.getProduct().getCategory().name();
                String key = supplier.getId() + "_" + category;
                ReportDTO report = reportMap.getOrDefault(key, new ReportDTO());
                report.setSupplierId(supplier.getId());
                report.setSupplierName(supplier.getName());
                report.setProductCategory(category);
                // Суммируем количество и стоимость
                report.setTotalQuantity(report.getTotalQuantity().add(item.getQuantity()));
                report.setTotalCost(report.getTotalCost().add(item.getCost()));
                reportMap.put(key, report);
            }
        }
        return new ArrayList<>(reportMap.values());
    }
}

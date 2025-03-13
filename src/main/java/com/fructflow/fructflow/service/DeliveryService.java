package com.fructflow.fructflow.service;

import com.fructflow.fructflow.dbase.Delivery;

import com.fructflow.fructflow.repository.DeliveryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

    public DeliveryService(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    public List<Delivery> getAllDeliveries() {
        return deliveryRepository.findAll();
    }

    public Optional<Delivery> getDeliveryById(Long id) {
        return deliveryRepository.findById(id);
    }

    @Transactional
    public Delivery createDelivery(Delivery delivery) {
        if (delivery.getDeliveryItems() != null) {
            delivery.getDeliveryItems().forEach(item -> item.setDelivery(delivery));
        }
        return deliveryRepository.save(delivery);
    }


    @Transactional
    public Delivery updateDelivery(Long id, Delivery updatedDelivery) {
        return deliveryRepository.findById(id)
                .map(existingDelivery -> {
                    existingDelivery.setSupplier(updatedDelivery.getSupplier());
                    existingDelivery.setDeliveryDate(updatedDelivery.getDeliveryDate());
                    if (updatedDelivery.getDeliveryItems() != null) {
                        existingDelivery.getDeliveryItems().clear();
                        updatedDelivery.getDeliveryItems().forEach(item -> {
                            item.setDelivery(existingDelivery);
                            existingDelivery.getDeliveryItems().add(item);
                        });
                    }
                    return deliveryRepository.save(existingDelivery);
                })
                .orElseThrow(() -> new RuntimeException("Доставка с ID " + id + " не найдена"));
    }


    @Transactional
    public void deleteDelivery(Long id) {
        deliveryRepository.deleteById(id);
    }
}

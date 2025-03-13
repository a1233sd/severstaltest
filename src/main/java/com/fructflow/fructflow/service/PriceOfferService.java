package com.fructflow.fructflow.service;

import com.fructflow.fructflow.dbase.PriceOffer;
import com.fructflow.fructflow.repository.PriceOfferRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PriceOfferService {

    private final PriceOfferRepository priceOfferRepository;

    public PriceOfferService(PriceOfferRepository priceOfferRepository) {
        this.priceOfferRepository = priceOfferRepository;
    }

    public List<PriceOffer> getAllPriceOffers() {
        return priceOfferRepository.findAll();
    }

    public Optional<PriceOffer> getPriceOfferById(Long id) {
        return priceOfferRepository.findById(id);
    }

    @Transactional
    public PriceOffer createPriceOffer(PriceOffer priceOffer) {
        return priceOfferRepository.save(priceOffer);
    }

    @Transactional
    public PriceOffer updatePriceOffer(Long id, PriceOffer updatedPriceOffer) {
        return priceOfferRepository.findById(id)
                .map(existingOffer -> {
                    existingOffer.setSupplier(updatedPriceOffer.getSupplier());
                    existingOffer.setProduct(updatedPriceOffer.getProduct());
                    existingOffer.setPrice(updatedPriceOffer.getPrice());
                    existingOffer.setValidFrom(updatedPriceOffer.getValidFrom());
                    existingOffer.setValidTo(updatedPriceOffer.getValidTo());
                    return priceOfferRepository.save(existingOffer);
                })
                .orElseThrow(() -> new RuntimeException("PriceOffer not found with id " + id));
    }

    @Transactional
    public void deletePriceOffer(Long id) {
        priceOfferRepository.deleteById(id);
    }
}


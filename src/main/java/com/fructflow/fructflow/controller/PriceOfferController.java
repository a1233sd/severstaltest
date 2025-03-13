package com.fructflow.fructflow.controller;

import com.fructflow.fructflow.dbase.PriceOffer;
import com.fructflow.fructflow.service.PriceOfferService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/price-offers")
public class PriceOfferController {

    private final PriceOfferService priceOfferService;

    public PriceOfferController(PriceOfferService priceOfferService) {
        this.priceOfferService = priceOfferService;
    }

    @GetMapping
    public ResponseEntity<List<PriceOffer>> getAllPriceOffers() {
        List<PriceOffer> offers = priceOfferService.getAllPriceOffers();
        return new ResponseEntity<>(offers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PriceOffer> getPriceOfferById(@PathVariable Long id) {
        Optional<PriceOffer> offer = priceOfferService.getPriceOfferById(id);
        return offer.map(priceOffer -> new ResponseEntity<>(priceOffer, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<PriceOffer> createPriceOffer(@RequestBody PriceOffer priceOffer) {
        PriceOffer createdOffer = priceOfferService.createPriceOffer(priceOffer);
        return new ResponseEntity<>(createdOffer, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PriceOffer> updatePriceOffer(@PathVariable Long id, @RequestBody PriceOffer priceOffer) {
        try {
            PriceOffer updatedOffer = priceOfferService.updatePriceOffer(id, priceOffer);
            return new ResponseEntity<>(updatedOffer, HttpStatus.OK);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePriceOffer(@PathVariable Long id) {
        priceOfferService.deletePriceOffer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}


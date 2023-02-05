package com.neurogine.assignment.demo.controller;

import com.neurogine.assignment.demo.entity.Merchant;
import com.neurogine.assignment.demo.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MerchantController {

    @Autowired
    private MerchantService merchantService;


    @PostMapping("/create-merchant")
    public ResponseEntity<Merchant> createMerchant(@RequestParam String name, @RequestParam String merchantId) {

        Merchant savedMerchant = merchantService.createMerchant(name, merchantId);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMerchant);

    }

    @DeleteMapping("/delete-merchant")
    public ResponseEntity<String> deleteMerchant(String merchantId) {

        merchantService.deleteMerchant(merchantId);
        return ResponseEntity.status(HttpStatus.OK).body("Record Deleted Successfully");

    }


}

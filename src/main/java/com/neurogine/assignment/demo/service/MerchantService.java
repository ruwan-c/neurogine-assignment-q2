package com.neurogine.assignment.demo.service;

import com.neurogine.assignment.demo.entity.Merchant;
import com.neurogine.assignment.demo.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MerchantService {
    @Autowired
    private MerchantRepository merchantRepository;

    /**
     * @param name       - merchant name
     * @param merchantId - unique merchant id
     * @return
     */
    public Merchant createMerchant(String name, String merchantId) {
        Merchant merchant = new Merchant();
        merchant.setName(name);
        merchant.setMerchantId(merchantId);
        return merchantRepository.save(merchant);
    }

    /**
     * @param merchantId - unique merchant id to delete
     */
    public void deleteMerchant(String merchantId) {
        merchantRepository.deleteById(merchantId);
    }
}

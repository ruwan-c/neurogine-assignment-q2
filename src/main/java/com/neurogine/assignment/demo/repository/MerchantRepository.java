package com.neurogine.assignment.demo.repository;

import com.neurogine.assignment.demo.entity.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchantRepository extends JpaRepository<Merchant, String> {

}

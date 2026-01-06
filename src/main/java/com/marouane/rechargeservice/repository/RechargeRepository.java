package com.marouane.rechargeservice.repository;

import com.marouane.rechargeservice.model.entity.Recharge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RechargeRepository extends JpaRepository<Recharge, UUID> {
}

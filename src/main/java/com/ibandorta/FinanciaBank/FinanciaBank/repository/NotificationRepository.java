package com.ibandorta.FinanciaBank.FinanciaBank.repository;

import com.ibandorta.FinanciaBank.FinanciaBank.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
}

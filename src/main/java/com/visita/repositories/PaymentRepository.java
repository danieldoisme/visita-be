package com.visita.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.visita.entities.PaymentEntity;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity, String> {
    java.util.Optional<PaymentEntity> findByBooking_BookingIdAndStatus(String bookingId,
            com.visita.entities.PaymentStatus status);

    java.util.Optional<PaymentEntity> findByTransactionId(String transactionId);

    @org.springframework.data.jpa.repository.Query("SELECT SUM(p.amount) FROM PaymentEntity p WHERE p.status = :status AND p.paymentDate BETWEEN :startDate AND :endDate")
    java.math.BigDecimal sumAmountByStatusAndPaymentDateBetween(com.visita.entities.PaymentStatus status,
            java.time.LocalDateTime startDate, java.time.LocalDateTime endDate);

    java.util.List<PaymentEntity> findTop10ByStatusOrderByPaymentDateDesc(com.visita.entities.PaymentStatus status);
}

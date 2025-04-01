package ru.boshchenko.serviceshipping.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.boshchenko.serviceshipping.model.ShippingOrder;

import java.util.UUID;

@Repository
public interface ShippingOrderRepository extends JpaRepository<ShippingOrder, UUID> {
}
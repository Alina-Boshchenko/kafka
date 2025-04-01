package ru.boshchenko.serviceshipping.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "shippings_orders")
public class ShippingOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "order_id", nullable = false, unique = true)
    private UUID orderId;

    @Column(name = "tracking_number", nullable = false)
    private String trackingNumber;

    @Enumerated(EnumType.STRING)
    private ShippingStatus status;

    @Column(name = "shipping_address", nullable = false)
    private String shippingAddress;




    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ShippingOrder that = (ShippingOrder) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
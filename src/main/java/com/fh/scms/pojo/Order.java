package com.fh.scms.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fh.scms.enums.OrderStatus;
import com.fh.scms.enums.OrderType;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "\"order\"")
public class Order extends _BaseEntity implements Serializable {

    @Builder.Default
    @NotNull(message = "{order.orderNumber.notNull}")
    @Column(name = "order_number", nullable = false, unique = true, length = 36, updatable = false)
    private String orderNumber = String.valueOf(UUID.randomUUID());

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @NotNull(message = "{order.type.notNull}")
    @Column(name = "order_type", nullable = false)
    private OrderType type = OrderType.OUTBOUND;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @NotNull(message = "{order.status.notNull}")
    @Column(name = "order_status", nullable = false)
    private OrderStatus status = OrderStatus.PENDING;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "expected_delivery")
    private Date expectedDelivery;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @JsonIgnore
    @OneToOne(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Invoice invoice;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_schedule_id", referencedColumnName = "id")
    private DeliverySchedule deliverySchedule;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderDetails> orderDetailsSet;

    @Override
    public String toString() {
        return "com.fh.scm.pojo.Order[ id=" + this.id + " ]";
    }
}

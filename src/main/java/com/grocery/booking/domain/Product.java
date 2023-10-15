package com.grocery.booking.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Product {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_seq")
    @SequenceGenerator(name = "product_id_seq", sequenceName = "product_id_seq", initialValue = 109, allocationSize = 1)
    private Long id;

    @Column(name = "product_title", nullable = false)
    private String productTitle;

    @Column(name = "description")
    private String description;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "weight", nullable = false)
    private String weight;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "mfgYear", nullable = false)
    private Integer mfgYear;

    @Column(name = "expYear", nullable = false)
    private Integer expYear;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;
}

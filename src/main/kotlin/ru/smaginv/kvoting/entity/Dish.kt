package ru.smaginv.kvoting.entity

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "dishes")
class Dish(
    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "dish_gen"
    )
    @SequenceGenerator(
        name = "dish_gen",
        sequenceName = "dish_seq",
        initialValue = 20,
        allocationSize = 10
    )
    @Column(name = "dish_id")
    var id: Long?,
    @Column(name = "title")
    var title: String,
    @Column(name = "price")
    var price: Int,
    @Column(name = "date")
    var date: LocalDate,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    var restaurant: Restaurant
)
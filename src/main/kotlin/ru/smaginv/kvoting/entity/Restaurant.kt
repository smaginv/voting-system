package ru.smaginv.kvoting.entity

import jakarta.persistence.*

@Entity
@Table(name = "restaurants")
class Restaurant(
    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "restaurant_gen"
    )
    @SequenceGenerator(
        name = "restaurant_gen",
        sequenceName = "restaurant_seq",
        initialValue = 20,
        allocationSize = 10
    )
    @Column(name = "restaurant_id")
    var id: Long?,
    @Column(name = "title")
    var title: String
)
package ru.smaginv.kvoting.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "votes")
class Vote(
    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "vote_gen"
    )
    @SequenceGenerator(
        name = "vote_seq",
        sequenceName = "vote_seq",
        initialValue = 20,
        allocationSize = 10
    )
    @Column(name = "vote_id")
    var id: Long?,
    @Column(name = "timestamp")
    var timeStamp: LocalDateTime,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    var restaurant: Restaurant,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: User
)
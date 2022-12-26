package ru.smaginv.kvoting.entity

import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

@Entity
@Table(name = "users")
class User(
    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "user_gen"
    )
    @SequenceGenerator(
        name = "user_gen",
        sequenceName = "user_seq",
        initialValue = 20,
        allocationSize = 10
    )
    @Column(name = "user_id")
    var id: Long?,
    @Column(name = "email")
    var email: String,
    @Column(name = "username")
    var username: String,
    @Column(name = "password")
    var password: String,
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "roles", joinColumns = [JoinColumn(name = "user_id")])
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Column(name = "role")
    var roles: MutableSet<Role>?
)
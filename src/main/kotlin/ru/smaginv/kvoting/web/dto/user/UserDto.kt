package ru.smaginv.kvoting.web.dto.user

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

@JsonPropertyOrder("id", "email", "username", "role")
class UserDto(
    @JsonIgnoreProperties(allowGetters = true)
    var id: Long?,
    @NotBlank
    @Email
    @Size(max = 128)
    var email: String,
    @NotBlank
    @Size(min = 4, max = 64)
    var username: String,
    @JsonIgnoreProperties(allowSetters = true)
    @NotBlank
    @Size(min = 4, max = 32)
    var password: String,
    @NotNull
    var roles: MutableSet<String>
)
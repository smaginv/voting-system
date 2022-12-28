package ru.smaginv.kvoting.web.dto.user

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

@JsonPropertyOrder("id", "email", "username")
@JsonIgnoreProperties(value = ["id"], allowGetters = true)
class UserDto(
    var id: Long?,
    @field:NotBlank
    @field:Email
    @field:Size(max = 128)
    var email: String?,
    @field:NotBlank
    @field:Size(min = 4, max = 64)
    var username: String?,
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @field:NotBlank
    @field:Size(min = 4, max = 32)
    var password: String?,
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    var roles: MutableSet<String>?
) {
    override fun toString(): String {
        return "UserDto(id=$id, email='$email', username='$username', password='$password')"
    }
}
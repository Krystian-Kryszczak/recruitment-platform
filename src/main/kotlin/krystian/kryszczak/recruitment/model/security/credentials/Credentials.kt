package krystian.kryszczak.recruitment.model.security.credentials

import io.micronaut.security.authentication.AuthenticationRequest
import krystian.kryszczak.recruitment.model.Item
import java.io.Serializable

abstract class Credentials(override val id: String? = null) :
    Serializable, AuthenticationRequest<String, String>, Item(id)

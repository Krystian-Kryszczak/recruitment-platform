package io.kotest.provided

import io.kotest.core.config.AbstractProjectConfig
import io.micronaut.test.extensions.kotest5.MicronautKotest5Extension
import krystian.kryszczak.test.kotest.extension.mongodb.MongoDBExtension

object ProjectConfig : AbstractProjectConfig() {
    override fun extensions() = listOf(MicronautKotest5Extension, MongoDBExtension)
}

package krystian.kryszczak.test.kotest.extension.mongodb

import io.kotest.core.extensions.Extension
import io.kotest.core.listeners.AfterProjectListener
import io.kotest.core.listeners.BeforeProjectListener
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.utility.DockerImageName

object MongoDBExtension : Extension, BeforeProjectListener, AfterProjectListener {
    private val mongoDB = MongoDBContainer(DockerImageName.parse("mongo:7.0.5"))

    override suspend fun beforeProject() {
        mongoDB.start()

        System.setProperty("MONGO_HOST", mongoDB.host)
        System.setProperty("MONGO_PORT", mongoDB.firstMappedPort.toString())
    }

    override suspend fun afterProject() {
        mongoDB.stop()
    }
}

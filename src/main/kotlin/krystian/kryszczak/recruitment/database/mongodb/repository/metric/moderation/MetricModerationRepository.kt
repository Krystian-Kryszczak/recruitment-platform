package krystian.kryszczak.recruitment.database.mongodb.repository.metric.moderation

import io.micronaut.data.mongodb.annotation.MongoRepository
import krystian.kryszczak.recruitment.database.mongodb.repository.CrudRepositoryBase
import krystian.kryszczak.recruitment.model.metric.moderation.MetricModeration

@MongoRepository
interface MetricModerationRepository : CrudRepositoryBase<MetricModeration, String>

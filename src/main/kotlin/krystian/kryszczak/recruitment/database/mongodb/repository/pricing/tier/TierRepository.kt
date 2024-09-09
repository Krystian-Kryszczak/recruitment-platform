package krystian.kryszczak.recruitment.database.mongodb.repository.pricing.tier

import io.micronaut.data.mongodb.annotation.MongoRepository
import krystian.kryszczak.recruitment.model.pricing.tier.Tier
import krystian.kryszczak.recruitment.database.mongodb.repository.CrudRepositoryBase

@MongoRepository
interface TierRepository : CrudRepositoryBase<Tier, String>

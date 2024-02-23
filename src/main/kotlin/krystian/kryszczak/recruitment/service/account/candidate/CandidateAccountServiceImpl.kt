package krystian.kryszczak.recruitment.service.account.candidate

import jakarta.inject.Singleton
import krystian.kryszczak.recruitment.model.being.candidate.Candidate
import krystian.kryszczak.recruitment.model.being.candidate.formation.CandidateFormation
import krystian.kryszczak.recruitment.repository.being.candidate.CandidateRepository
import krystian.kryszczak.recruitment.service.DataAccessServiceImpl
import krystian.kryszczak.recruitment.service.mail.smtp.SmtpEmailService
import reactor.core.publisher.Mono

@Singleton
class CandidateAccountServiceImpl(
    candidateAccountRepository: CandidateRepository, private val smtpEmailService: SmtpEmailService) :
    CandidateAccountService, DataAccessServiceImpl<Candidate>(candidateAccountRepository) {
    override fun register(formation: CandidateFormation): Mono<String> {
        TODO("Not yet implemented")
    }
}

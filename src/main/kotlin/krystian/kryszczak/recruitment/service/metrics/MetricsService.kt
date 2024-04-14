package krystian.kryszczak.recruitment.service.metrics

interface MetricsService {
    fun incrementGeneratedActivationCodes()
    fun incrementActivatedAccounts()
    fun incrementActivationAccountFails()
}

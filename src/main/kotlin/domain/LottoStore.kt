package domain

class LottoStore(
    private val lottoGenerator: LottoGenerator,
) {

    fun buyManualLotto(count: Int, vararg lottos: IntArray): LottoTickets {
        require(count >= lottos.size) { ERROR_INVALID_COUNT.format(count) }
        return LottoTickets(lottos.map(::Lotto))
    }

    fun buyAutoLotto(count: Int): LottoTickets {
        require(count in MINIMUM_COUNT..MAXIMUM_COUNT) { ERROR_CREATE_COUNT.format(count) }
        return LottoTickets(List(count) { lottoGenerator.generateLotto() })
    }

    companion object {
        const val LOTTO_PRICE = 1000
        private const val MINIMUM_COUNT = 1
        private const val MAXIMUM_COUNT = 100
        private const val ERROR_INVALID_COUNT = "잔액보다 많은 로또를 구매할 수 없습니다.\n잘못된 값: %d"
        private const val ERROR_CREATE_COUNT = "한 번에 생성할 수 있는 로또 개수는 1개 이상 100개 이하입니다.\n잘못된 값: %d"
    }
}

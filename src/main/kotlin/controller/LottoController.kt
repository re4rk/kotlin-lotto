package controller

import domain.Lotto
import domain.LottoNumber
import domain.LottoStore
import domain.RandomLottoGenerator
import domain.WinningLotto
import view.InputView
import view.OutputView

class LottoController {
    private val inputView by lazy { InputView() }
    private val outputView by lazy { OutputView() }

    fun run() {
        val amount = askAmount()
        val lottos = buyLotto(amount)
        outputView.outputLottos(lottos)
        val winningLotto = WinningLotto(askWinningNumbers(), askBonusNumber())
        val result = winningLotto.match(lottos)
        outputView.outputResult(result)
    }

    private fun askAmount(): Int {
        outputView.outputGetAmount()
        return inputView.inputAmount()
    }

    private fun buyLotto(amount: Int): List<Lotto> {
        val store = LottoStore(RandomLottoGenerator)
        return store.buyLotto(amount)
    }

    private fun askWinningNumbers(): Lotto {
        outputView.outputGetWinningNumbers()
        return Lotto(*inputView.inputWinningNumbers())
    }

    private fun askBonusNumber(): LottoNumber {
        outputView.outputGetBonusNumber()
        return LottoNumber.from(inputView.inputBonusNumber())
    }
}

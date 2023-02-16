package domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class WinningLottoTest {
    @Test
    fun `로또 번호, 로또 번호와 중복되지 않는 보너스 번호로 이루어진 당첨 번호를 생성할 수 있다`() {
        val winningLotto = WinningLotto(intArrayOf(1, 2, 3, 4, 5, 6), 10)
        assertAll(
            { assertEquals(winningLotto.lotto, Lotto(1, 2, 3, 4, 5, 6)) },
            { assertEquals(winningLotto.bonusNumber, LottoNumber(10)) },
        )
    }

    @Test
    fun `보너스 번호가 로또 번호와 중복되면 에러가 발생한다`() {
        val numbers = intArrayOf(1, 2, 3, 4, 5, 6)
        val bonusNumber = 3
        assertThatIllegalArgumentException()
            .isThrownBy { WinningLotto(numbers, bonusNumber) }
            .withMessage(
                "보너스 번호는 당첨 번호와 중복될 수 없습니다. \n잘못된 값 : ${
                    Lotto(
                        numbers.map(::LottoNumber).toSet(),
                    )
                }, ${LottoNumber(bonusNumber)}",
            )
    }

    @Test
    fun `당첨 번호는 로또에 대해 맞는 개수를 반환할 수 있다`() {
        val winningLotto = WinningLotto(intArrayOf(1, 2, 3, 4, 5, 6), 7)
        val lotto = Lotto(1, 2, 3, 4, 5, 6)

        val result = winningLotto.getCountOfMatch(lotto)

        assertThat(result).isEqualTo(6)
    }

    @Test
    fun `당첨 번호는 로또에 대해 보너스 번호 매치 여부를 반환할 수 있다`() {
        val winningLotto = WinningLotto(intArrayOf(1, 2, 3, 4, 5, 6), 7)
        val lotto = Lotto(1, 2, 3, 4, 5, 7)

        val result = winningLotto.matchBonus(lotto)

        assertThat(result).isTrue
    }
}

package presentation.presenter

import io.ktor.client.plugins.ResponseException
import io.ktor.client.statement.HttpResponse
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.example.domain.model.exception.PariStyleException
import org.example.presentation.presenter.BasePresenter
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class BasePresenterTest {

    private lateinit var presenter: TestableBasePresenter

    private val block: suspend () -> String = mockk()
    private val onSuccess: (String) -> Unit = mockk(relaxed = true)
    private val onError: (Throwable) -> Unit = mockk(relaxed = true)

    private val successResult = "Success"
    private val responseException = ResponseException(mockk<HttpResponse>(), "Response error")
    private val genericException = Exception("Generic error")

    @BeforeEach
    fun setUp() {
        presenter = TestableBasePresenter()
    }

    @Test
    fun `executeWithErrorHandling should call onSuccess when block executes successfully`() = runTest {
        coEvery { block() } returns successResult

        presenter.testExecuteWithErrorHandling(block, onError, onSuccess)

        coVerify { onSuccess(successResult) }
        coVerify(exactly = 0) { onError(any()) }
    }

    @Test
    fun `executeWithErrorHandling should call onError with PariStyleException when block throws it`() = runTest {
         val pariStyleException = PariStyleException.InvalidInputException("PariStyle error")

        coEvery { block() } throws pariStyleException

        presenter.testExecuteWithErrorHandling(block, onError, onSuccess)

        coVerify { onError(pariStyleException) }
        coVerify(exactly = 0) { onSuccess(any()) }
    }

    @Test
    fun `executeWithErrorHandling should call onError with ResponseException when block throws it`() = runTest {
        coEvery { block() } throws responseException

        presenter.testExecuteWithErrorHandling(block, onError, onSuccess)

        coVerify { onError(responseException) }
        coVerify(exactly = 0) { onSuccess(any()) }
    }

    @Test
    fun `executeWithErrorHandling should call onError with generic Exception when block throws it`() = runTest {
        coEvery { block() } throws genericException

        presenter.testExecuteWithErrorHandling(block, onError, onSuccess)

        coVerify { onError(genericException) }
        coVerify(exactly = 0) { onSuccess(any()) }
    }
}

private class TestableBasePresenter : BasePresenter<String, Unit>(Unit) {
    suspend fun testExecuteWithErrorHandling(
        block: suspend () -> String,
        onError: (Throwable) -> Unit,
        onSuccess: (String) -> Unit
    ) {
        executeWithErrorHandling(block, onError, onSuccess)
    }
}
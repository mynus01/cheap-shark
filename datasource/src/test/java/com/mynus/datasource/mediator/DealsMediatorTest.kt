package com.mynus.datasource.mediator

import androidx.paging.PagingData
import com.mynus.datasource.local.repository.GetDealsLocalRepository
import com.mynus.datasource.remote.repository.GetDealsRemoteRepository
import com.mynus.domain.model.Deal
import com.mynus.domain.util.ConnectionChecker
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
open class DealsMediatorTest {
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()

    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    lateinit var remoteRepository: GetDealsRemoteRepository

    @MockK
    lateinit var localRepository: GetDealsLocalRepository

    @MockK
    lateinit var connectionChecker: ConnectionChecker
    lateinit var mediator: DealsMediator

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)

        mediator = DealsMediatorImpl(remoteRepository, localRepository, connectionChecker)
    }

    @After
    fun teardown() {
        Dispatchers.resetMain()
    }

    class DealsMediatorTestImpl : DealsMediatorTest() {
        private val responseFlow = flow<PagingData<Deal>> { emit(PagingData.empty()) }

        @Test
        fun `should call remote service when online`() = runTest {
            every { connectionChecker.hasConnection() } returns true
            coEvery { remoteRepository.get() } returns responseFlow
            coEvery { localRepository.get() } throws IllegalStateException()

            val result = mediator.getDeals().first()

            Assert.assertEquals(PagingData.empty<Deal>(), result)
        }

        @Test
        fun `should call local repository when offline`() = runTest {
            every { connectionChecker.hasConnection() } returns false
            coEvery { remoteRepository.get() } throws IllegalStateException()
            coEvery { localRepository.get() } returns responseFlow

            val result = mediator.getDeals().first()

            Assert.assertEquals(PagingData.empty<Deal>(), result)

        }
    }
}

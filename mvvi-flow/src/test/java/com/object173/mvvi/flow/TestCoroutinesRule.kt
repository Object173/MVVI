package com.object173.mvvi.flow

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.*
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class TestCoroutinesRule(val dispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()) : TestRule {

    override fun apply(base: Statement, description: Description?) = object : Statement() {

        @Throws(Throwable::class)
        override fun evaluate() {
            Dispatchers.setMain(dispatcher)

            base.evaluate()

            Dispatchers.resetMain()
            dispatcher.cleanupTestCoroutines()
        }
    }
}
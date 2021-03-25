package com.antoine.androiddemo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.internal.schedulers.ExecutorScheduler.ExecutorWorker
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import org.junit.ClassRule
import org.junit.Rule
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import java.util.concurrent.Executor

abstract class BaseTestCase  {

    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }

    @get:Rule
    val rule = InstantTaskExecutorRule()

    class RxImmediateSchedulerRule : TestRule {
        private val immediate = object : Scheduler() {
            override fun createWorker(): Worker {
                return ExecutorWorker(Executor(Runnable::run), false, false)
            }
        }

        override fun apply(base: Statement, description: Description): Statement {
            return object : Statement() {
                @Throws(Throwable::class)
                override fun evaluate() {
                    RxJavaPlugins.setInitIoSchedulerHandler { immediate }
                    RxJavaPlugins.setInitComputationSchedulerHandler { immediate }
                    RxJavaPlugins.setInitNewThreadSchedulerHandler { immediate }
                    RxJavaPlugins.setInitSingleSchedulerHandler { immediate }
                    RxAndroidPlugins.setInitMainThreadSchedulerHandler { immediate }

                    try {
                        base.evaluate()
                    } finally {
                        RxJavaPlugins.reset()
                        RxAndroidPlugins.reset()
                    }
                }
            }
        }
    }
}
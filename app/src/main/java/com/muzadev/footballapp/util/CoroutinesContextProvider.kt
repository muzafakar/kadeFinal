package com.muzadev.footballapp.util

import kotlinx.coroutines.experimental.Unconfined
import kotlinx.coroutines.experimental.android.UI
import kotlin.coroutines.experimental.CoroutineContext

/**
 * Created by zulfakar on 19/10/18.
 * For educational purposes
 */
open class CoroutinesContextProvider {
    open val main: CoroutineContext by lazy { UI }
}

class TestContextProvider : CoroutinesContextProvider() {
    override val main: CoroutineContext = Unconfined
}
package com.beam.tictactoexml.composeui

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import androidx.compose.ui.test.hasText
import androidx.test.platform.app.InstrumentationRegistry

val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

fun SemanticsNodeInteractionsProvider.onNodeWithText(
    @StringRes stringId: Int,
    substring: Boolean = false,
    ignoreCase: Boolean = false,
    useUnmergedTree: Boolean = false
): SemanticsNodeInteraction =
    onNode(hasText(context.getString(stringId), substring, ignoreCase), useUnmergedTree)
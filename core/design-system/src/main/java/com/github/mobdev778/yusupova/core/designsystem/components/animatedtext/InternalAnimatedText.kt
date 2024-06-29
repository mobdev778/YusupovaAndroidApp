package com.github.mobdev778.yusupova.core.designsystem.components.animatedtext

import android.graphics.Typeface
import android.text.Layout
import android.text.TextPaint
import android.util.TypedValue
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.mobdev778.yusupova.core.designsystem.DesignSystem
import com.github.mobdev778.yusupova.core.designsystem.components.text.InternalText
import com.github.mobdev778.yusupova.core.designsystem.preview.BooleanPreviewParameterProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.launch
import java.util.concurrent.Executors
import kotlin.math.absoluteValue

@Composable
@Suppress("LongParameterList")
internal fun InternalAnimatedText(
    textStyle: TextStyle,
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    fontStyle: FontStyle? = null,
    fontFamily: FontFamily? = null,
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
) {
    var job: Job? = null
    val backgroundScope: CoroutineScope by lazy {
        CoroutineScope(Executors.newFixedThreadPool(1).asCoroutineDispatcher())
    }
    val textPaint = TextPaint().apply {
        isAntiAlias = true
        textSize = with(LocalDensity.current) { textStyle.fontSize.toPx().absoluteValue }
        this.color = if (color != Color.Unspecified) color.toArgb() else textStyle.color.toArgb()
        typeface = textStyle.toTypeface(fontFamily, fontStyle)
    }
    var state: InternalAnimatedTextState by remember { mutableStateOf(InternalAnimatedTextState()) }
    val alignment = textAlign?.toAlignment() ?: textStyle.textAlign.toAlignment()

    var width by remember { mutableStateOf(0) }
    var height by remember { mutableStateOf(0) }

    Box(
        modifier = modifier
            .onGloballyPositioned { coordinates ->
                width = coordinates.size.width
                height = coordinates.size.height
            }
    ) {
        InternalText(
            textStyle = textStyle,
            text = text,
            modifier = modifier,
            color = Color.Transparent,
            fontStyle = fontStyle,
            fontFamily = fontFamily,
            textAlign = textAlign,
            overflow = overflow,
            softWrap = softWrap,
            maxLines = maxLines,
            minLines = minLines,
        )
    }

    Canvas(
        modifier
    ) {
        if (!state.isInitialized() && job?.isActive != true && width > 0 && height > 0) {
            job = backgroundScope.launch {
                state = state.initialize(text, width, height, textPaint, alignment)
            }
        }
        state.getBitmap()?.let { bitmap ->
            drawIntoCanvas {
                it.nativeCanvas.drawBitmap(bitmap, 0f, 0f, textPaint)
                job = backgroundScope.launch {
                    val newState = state.nextState()
                    if (newState != state) {
                        state = newState
                    }
                }
            }
        }
    }
}

@Composable
private fun Float.spToPx(): Int {
    val context = LocalContext.current
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, this, context.resources.displayMetrics).toInt()
}

private fun TextAlign.toAlignment(): Layout.Alignment {
    return when(this) {
        TextAlign.Center -> Layout.Alignment.ALIGN_CENTER
        TextAlign.Start,
        TextAlign.Left -> Layout.Alignment.ALIGN_NORMAL
        TextAlign.Right,
        TextAlign.End -> Layout.Alignment.ALIGN_OPPOSITE
        else -> Layout.Alignment.ALIGN_NORMAL
    }
}

private fun TextStyle.toTypeface(externalFontFamily: FontFamily?, externalFontStyle: FontStyle?): Typeface {
    val tFamily = externalFontFamily ?: fontFamily
    val family = (tFamily ?: FontFamily.Default).toTypeface()

    val tStyle = externalFontStyle ?: fontStyle
    val isItalic = tStyle == FontStyle.Italic
    val isBold = fontWeight == FontWeight.Bold
    val style = when {
        isBold && isItalic -> Typeface.BOLD_ITALIC
        isBold -> Typeface.BOLD
        isItalic -> Typeface.ITALIC
        else -> Typeface.NORMAL
    }
    return Typeface.create(family, style)
}

private fun FontFamily.toTypeface(): Typeface {
    return when (this) {
        FontFamily.Serif -> Typeface.SERIF
        FontFamily.Monospace -> Typeface.MONOSPACE
        FontFamily.SansSerif -> Typeface.SANS_SERIF
        else -> Typeface.DEFAULT
    }
}

@Preview
@Composable
private fun InternalAnimatedPreview(
    @PreviewParameter(BooleanPreviewParameterProvider::class) isDarkTheme: Boolean
) {
    DesignSystem.Theme(isDarkTheme = isDarkTheme) {
        // we are passing our composable
        // function to display its preview.
        InternalAnimatedText(
            text = "Liliya Yusupova",
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(16.dp),
            textStyle = TextStyle(
                fontSize = 40.sp,
                color = Color(0xFF4D3731),
                textAlign = TextAlign.Center,
                fontStyle = FontStyle.Italic,
                fontFamily = FontFamily.Serif,
                shadow = Shadow(
                    color = Color.DarkGray,
                    offset = Offset(2.0f, 2.0f),
                    blurRadius = 3f
                )
            )
        )
    }
}

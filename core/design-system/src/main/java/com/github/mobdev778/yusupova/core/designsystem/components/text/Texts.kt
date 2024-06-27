package com.github.mobdev778.yusupova.core.designsystem.components.text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.github.mobdev778.yusupova.core.designsystem.rememberTypography

class Texts {

    val Title: dsTitle = dsTitle()
    val Subtitle: dsSubtitle = dsSubtitle()
    val Label: dsLabel = dsLabel()

    class dsTitle {

        @Composable
        fun Large(
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
            InternalText(
                rememberTypography().headline3,
                text = text,
                modifier = modifier,
                color = color,
                fontStyle = fontStyle,
                fontFamily = fontFamily,
                textAlign = textAlign,
                overflow = overflow,
                softWrap = softWrap,
                maxLines = maxLines,
                minLines = minLines
            )
        }

        @Composable
        fun Medium(
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
            InternalText(
                rememberTypography().headline5,
                text = text,
                modifier = modifier,
                color = color,
                fontStyle = fontStyle,
                fontFamily = fontFamily,
                textAlign = textAlign,
                overflow = overflow,
                softWrap = softWrap,
                maxLines = maxLines,
                minLines = minLines
            )
        }

        @Composable
        fun Small(
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
            InternalText(
                rememberTypography().headline6,
                text = text,
                modifier = modifier,
                color = color,
                fontStyle = fontStyle,
                fontFamily = fontFamily,
                textAlign = textAlign,
                overflow = overflow,
                softWrap = softWrap,
                maxLines = maxLines,
                minLines = minLines
            )
        }
    }

    class dsSubtitle {

        @Composable
        fun Large(
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
            InternalText(
                rememberTypography().subtitle1,
                text = text,
                modifier = modifier,
                color = color,
                fontStyle = fontStyle,
                fontFamily = fontFamily,
                textAlign = textAlign,
                overflow = overflow,
                softWrap = softWrap,
                maxLines = maxLines,
                minLines = minLines
            )
        }

        @Composable
        fun Medium(
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
            InternalText(
                rememberTypography().subtitle2,
                text = text,
                modifier = modifier,
                color = color,
                fontStyle = fontStyle,
                fontFamily = fontFamily,
                textAlign = textAlign,
                overflow = overflow,
                softWrap = softWrap,
                maxLines = maxLines,
                minLines = minLines
            )
        }
    }

    class dsLabel {

        @Composable
        fun Large(
            text: String,
            modifier: Modifier = Modifier,
            fontStyle: FontStyle? = null,
            fontFamily: FontFamily? = null,
            color: Color = Color.Unspecified,
            textAlign: TextAlign? = null,
            overflow: TextOverflow = TextOverflow.Clip,
            softWrap: Boolean = true,
            maxLines: Int = Int.MAX_VALUE,
            minLines: Int = 1,
        ) {
            InternalText(
                rememberTypography().body1,
                text = text,
                modifier = modifier,
                color = color,
                fontStyle = fontStyle,
                fontFamily = fontFamily,
                textAlign = textAlign,
                overflow = overflow,
                softWrap = softWrap,
                maxLines = maxLines,
                minLines = minLines
            )
        }

        @Composable
        fun Medium(
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
            InternalText(
                rememberTypography().body2,
                text = text,
                modifier = modifier,
                color = color,
                fontStyle = fontStyle,
                fontFamily = fontFamily,
                textAlign = textAlign,
                overflow = overflow,
                softWrap = softWrap,
                maxLines = maxLines,
                minLines = minLines
            )
        }
    }

    @Composable
    fun Button(
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
        InternalText(
            rememberTypography().button,
            text = text,
            modifier = modifier,
            color = color,
            fontStyle = fontStyle,
            fontFamily = fontFamily,
            textAlign = textAlign,
            overflow = overflow,
            softWrap = softWrap,
            maxLines = maxLines,
            minLines = minLines
        )
    }

    @Composable
    fun Caption(
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
        InternalText(
            rememberTypography().caption,
            text = text,
            modifier = modifier,
            color = color,
            fontStyle = fontStyle,
            textAlign = textAlign,
            overflow = overflow,
            softWrap = softWrap,
            maxLines = maxLines,
            minLines = minLines
        )
    }
}

package com.example.anamnesedrapp.util

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.substring

class MaskTransformationCpfCnpj() : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return maskFilterCpfCnpj(text = text)
    }

}

fun maskFilterCpfCnpj(text: AnnotatedString): TransformedText {
    val isCnpj = if (text.text.length >= 12) true else false
    val sText: String = text.text
    var out = ""
    if (isCnpj) {
        for (i in sText.indices) {
            out += sText[i]
            if (i == 1 || i == 4) out += "."
            if (i == 7) out += "/"
            if (i == 11) out += "-"
        }
    } else {
        for (i in sText.indices) {
            out += sText[i]
            if (i == 2 || i == 5 ) out += "."
            if (i == 8) out += "-"
        }
    }

    val numberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (isCnpj) {
                if (offset <= 2) return offset
                if (offset <= 5) return offset + 1
                if (offset <= 8) return offset + 2
                if (offset <= 12) return offset + 3
                if (offset <= 13) return offset + 4
                return 18
            }
            if (offset <= 5) return offset
            if (offset <= 10) return offset + 3
            return 14
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (isCnpj) {
                if (offset <= 2) return offset
                if (offset <= 5) return offset - 1
                if (offset <= 8) return offset - 2
                if (offset <= 12) return offset - 3
                if (offset <= 14) return offset - 4
                return 14
            }
            if (offset <= 5) return offset
            if (offset <= 11) return offset + 3
            return 14
        }

    }

    return TransformedText(AnnotatedString(out), numberOffsetTranslator)
}
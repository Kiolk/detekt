package io.gitlab.arturbosch.detekt.rules.style

import org.jetbrains.kotlin.com.intellij.openapi.util.TextRange
import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.psi.KtElement
import org.jetbrains.kotlin.psi.KtFile
import org.jetbrains.kotlin.psi.psiUtil.elementsInRange
import org.jetbrains.kotlin.psi.psiUtil.getNonStrictParentOfType

/**
 * Util function to search for the [KtElement]s in the parents of
 * the given [line] from a given offset in a [KtFile].
 */
internal fun findKtElementInParents(file: KtFile, offset: Int, line: String): Sequence<PsiElement> {
    return file.elementsInRange(TextRange.create(offset, offset + line.length))
        .asSequence()
        .plus(file.findElementAt(offset))
        .mapNotNull { it?.getNonStrictParentOfType() }
}

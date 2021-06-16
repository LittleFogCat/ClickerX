package top.littlefogcat.clickerx.scriptdetail

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.text.Editable
import android.text.Spanned
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText

/**
 * EditText for Lua editing.
 *
 * @Author：littlefogcat
 * @Email：littlefogcat@foxmail.com
 */
class LuaScriptEditor : AppCompatEditText {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            /**
             * todo Only resolve note now. And remove this to [onTextChanged] for efficiency.
             */
            override fun afterTextChanged(txt: Editable) {
                var i = 0
                txt.getSpans(0, txt.length, ForegroundColorSpan::class.java).forEach { txt.removeSpan(it) }
                txt.getSpans(0, txt.length, StyleSpan::class.java).forEach { txt.removeSpan(it) }
                var isInString = false
                while (i < txt.length - 1) {
                    if (!isInString && txt[i] == '-' && txt[i + 1] == '-') {
                        // find next \n or end
                        var j = i + 2
                        while (j < txt.length && txt[j] != '\n') j++
                        // set span to txt[i..j]
                        val noteColor = Color.parseColor("#43dd1c")
                        txt.setSpan(ForegroundColorSpan(noteColor), i, j, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
                        txt.setSpan(StyleSpan(Typeface.ITALIC), i, j, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
                        i = j + 1
                    } else {
                        // find next '-'
                        var j = i + 1
                        while (j < txt.length && txt[j] != '-') {
                            if (txt[j] == '\"') {
                                isInString = !isInString
                            }
                            j++
                        }
                        val normalColor = Color.parseColor("#1d1d1d")
                        txt.setSpan(ForegroundColorSpan(normalColor), i, i + 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
                        txt.setSpan(StyleSpan(Typeface.NORMAL), i, i + 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
                        i = j
                    }
                }
            }

        })
    }
}
package io.mohamed.hidesystemkeyboard

import android.os.Build
import android.text.InputType
import android.widget.EditText
import com.google.appinventor.components.annotations.SimpleFunction
import com.google.appinventor.components.runtime.AndroidNonvisibleComponent
import com.google.appinventor.components.runtime.ComponentContainer
import com.google.appinventor.components.runtime.TextBoxBase

class HideSystemKeyboard(container: ComponentContainer) :
    AndroidNonvisibleComponent(container.`$form`()) {

    /**
     * Disables the system keyboard for the specified textbox, disallows the keyboard to show up when the textbox it clicked.
     *
     * @param textbox the textbox for which the keyboard will be enabled.
     * It could be a normal textbox, a password textbox, or an email picker component.
     */
    @SimpleFunction(description = "Disables the system keyboard for the specified textbox, disallows the keyboard to show up when the textbox it clicked.")
    fun DisableSystemKeyboard(textbox: TextBoxBase) {
        val et: EditText = textbox.view as EditText
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            et.showSoftInputOnFocus = false
        } else {
            // the showSoftInputOnFocus method was added in API 21 ( Android 5 ),
            // for the earlier Android APIs, to maintain backward compatibility,
            // we set the edit text input type to NULL to disable the keyboard.
            et.inputType = InputType.TYPE_NULL
        }
    }

    /**
     * Re-enables the system keyboard for the specified textbox, allows the keyboard to show up again when the textbox it clicked.
     *
     * @param textbox the textbox for which the keyboard will be enabled.
     * It could be a normal textbox, a password textbox, or an email picker component.
     */
    @SimpleFunction(description = "Re-enables the system keyboard for the specified textbox, allows the keyboard to show up again when the textbox it clicked.")
    fun EnableSystemKeyboard(textbox: TextBoxBase) {
        val et: EditText = textbox.view as EditText
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            et.showSoftInputOnFocus = true
        } else {
            et.inputType = InputType.TYPE_CLASS_TEXT
        }
    }
}

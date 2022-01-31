package io.mohamed.hidesystemkeyboard;

import android.os.Build.VERSION;
import android.text.InputType;
import android.widget.EditText;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.runtime.AndroidNonvisibleComponent;
import com.google.appinventor.components.runtime.ComponentContainer;
import com.google.appinventor.components.runtime.TextBoxBase;
import org.jetbrains.annotations.NotNull;

public final class HideSystemKeyboard extends AndroidNonvisibleComponent {

  /**
   * Creates a new HideSystemKeyboard component
   *
   * @param container the container in which the component will be placed into
   */
  public HideSystemKeyboard(@NotNull ComponentContainer container) {
    super(container.$form());
  }

  /**
   * Disables the system keyboard for the specified textbox, disallows the keyboard to show up when
   * the textbox it clicked.
   *
   * @param textbox the textbox for which the keyboard will be disabled. It could be a normal
   *                textbox, a password textbox, or an email picker component.
   */
  @SimpleFunction(
      description = "Disables the system keyboard for the specified textbox, disallows the keyboard to show up when the textbox it clicked.")
  public final void DisableSystemKeyboard(@NotNull TextBoxBase textbox) {
    EditText et = (EditText) textbox.getView();
    if (VERSION.SDK_INT >= 21) {
      et.setShowSoftInputOnFocus(false);
    } else {
      et.setInputType(InputType.TYPE_NULL);
    }
  }

  /**
   * Re-enables the system keyboard for the specified textbox, allows the keyboard to show up again
   * when the textbox it clicked.
   *
   * @param textbox the textbox for which the keyboard will be enabled. It could be a normal
   *                textbox, a password textbox, or an email picker component.
   */
  @SimpleFunction(
      description = "Re-enables the system keyboard for the specified textbox, allows the keyboard to show up again when the textbox it clicked."
  )
  public final void EnableSystemKeyboard(@NotNull TextBoxBase textbox) {
    EditText et = (EditText) textbox.getView();
    if (VERSION.SDK_INT >= 21) {
      et.setShowSoftInputOnFocus(true);
    } else {
      et.setInputType(InputType.TYPE_CLASS_TEXT);
    }
  }
}

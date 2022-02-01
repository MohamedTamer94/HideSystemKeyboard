package io.mohamed.hidesystemkeyboard;

import android.os.Build.VERSION;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
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
  public void DisableSystemKeyboard(@NotNull TextBoxBase textbox) {
    EditText et = (EditText) textbox.getView();
    disableEditTextKeyboard(et);
  }

  /**
   * Disables the system keyboard for all the textboxes, same as DisableSystemKeyboard function
   */

  @SimpleFunction(
      description = "Disables the keyboard for all the text boxes in the app")
  public void DisableKeyboards() {
    View view = form.getWindow().getDecorView();
    if (!(view instanceof ViewGroup)) {
      Log.e("HideSystemKeyboard", "Something went wrong");
      return;
    }
    disableAllEditTextKeyboards((ViewGroup) view);
  }

  private void disableAllEditTextKeyboards(ViewGroup group) {
    for (int i = 0; i < group.getChildCount(); i++) {
      View childView = group.getChildAt(i);
      if (childView instanceof ViewGroup) {
        disableAllEditTextKeyboards((ViewGroup) childView);
      } else if (childView instanceof EditText) {
        disableEditTextKeyboard((EditText) childView);
      }
    }
  }

  private void disableEditTextKeyboard(EditText et) {
    if (VERSION.SDK_INT >= 21) {
      et.setShowSoftInputOnFocus(false);
    } else {
      et.setInputType(InputType.TYPE_NULL);
    }
    if (et.hasFocus()) {
      // since the EditText has focus,
      // the keyboard might be active, so hide it
      form.HideKeyboard();
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
  public void EnableSystemKeyboard(@NotNull TextBoxBase textbox) {
    EditText et = (EditText) textbox.getView();
    if (VERSION.SDK_INT >= 21) {
      et.setShowSoftInputOnFocus(true);
    } else {
      et.setInputType(InputType.TYPE_CLASS_TEXT);
    }
  }
}

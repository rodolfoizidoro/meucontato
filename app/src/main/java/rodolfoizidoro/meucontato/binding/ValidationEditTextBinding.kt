package rodolfoizidoro.meucontato.binding

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import br.com.youse.forms.validators.ValidationMessage
import com.google.android.material.textfield.TextInputLayout

object ValidationEditTextBinding {

    /**
     *  Use this two way databinding to feed the form with EditTexts text changes.
     */
    @BindingAdapter(value = ["app:fieldText"])
    @JvmStatic
    fun setEditTextFormField(view: EditText, newValue: String?) {
        val oldValue = view.text.toString()
        if (oldValue != newValue) {
            view.setText(newValue)
        }
    }

    @InverseBindingAdapter(attribute = "fieldText", event = "fieldTextAttrChanged")
    @JvmStatic
    fun getEditTextFormField(view: EditText): String {
        return view.text.toString()
    }

    @BindingAdapter(value = ["fieldTextAttrChanged"])
    @JvmStatic
    fun setEditTextFormFieldListener(view: EditText, listener: InverseBindingListener?) {
        if (listener == null) {
            return
        }
        view.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                listener.onChange()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        listener.onChange()
    }
}

package rodolfoizidoro.meucontato.binding

import android.view.View
import androidx.databinding.BindingAdapter
import br.com.youse.forms.validators.ValidationMessage
import com.google.android.material.textfield.TextInputLayout

object ValidationFieldBinding {
    @BindingAdapter(value = ["app:fieldError"])
    @JvmStatic
    fun onFieldValidationChange(view: TextInputLayout,
                                validations: List<ValidationMessage>?) {
        view.error = validations?.firstOrNull()?.message
    }

    @BindingAdapter(value = ["app:formEnabled"])
    @JvmStatic
    fun onFormValidationChange(view: View, enabled: Boolean?) {
        enabled?.let {
            view.isEnabled = enabled
        }
    }
}

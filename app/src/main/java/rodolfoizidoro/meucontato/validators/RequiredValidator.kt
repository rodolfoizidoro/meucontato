package rodolfoizidoro.meucontato.validators

import br.com.youse.forms.validators.ValidationMessage
import br.com.youse.forms.validators.Validator
import rodolfoizidoro.meucontato.validators.ValidationTypes.Companion.REQUIRED


class RequiredValidator(val message: String) : Validator<String> {

    private val validationMessage = ValidationMessage(message = message, validationType = REQUIRED)

    override fun validationMessage(): ValidationMessage {
        return validationMessage
    }

    override fun isValid(input: String?): Boolean {
        return !input.isNullOrEmpty()
    }
}

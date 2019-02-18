package rodolfoizidoro.meucontato.binding

import android.graphics.drawable.Drawable
import androidx.databinding.BindingAdapter
import rodolfoizidoro.meucontato.widget.IconTitleMessage

object IconTitleMessageBinding {
    @JvmStatic
    @BindingAdapter("app:icon", "app:title", "app:message", requireAll = false)
    fun setITM(view: IconTitleMessage, icon: Drawable, title: String, message: String?) {
        view.setup(icon, title, message)
    }
}

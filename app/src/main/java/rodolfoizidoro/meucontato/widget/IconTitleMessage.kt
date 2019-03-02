package rodolfoizidoro.meucontato.widget

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import kotlinx.android.synthetic.main.widget_icon_title_message.view.*
import rodolfoizidoro.meucontato.R

class IconTitleMessage @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    RelativeLayout(context, attrs) {

    init {
        LayoutInflater.from(context).inflate(R.layout.widget_icon_title_message, this, true)
    }

    fun setup(@DrawableRes icon: Drawable?, title: String, message: String?) {

        ivITMIcon.setImageDrawable(icon)
        tvITMTitle.text = title

        if (message.isNullOrEmpty()) {
            tvITMMessage.visibility = View.GONE
        } else {
            tvITMMessage.text = message
        }
    }

}

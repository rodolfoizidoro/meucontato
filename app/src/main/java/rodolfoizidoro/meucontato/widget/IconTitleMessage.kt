package rodolfoizidoro.meucontato.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import kotlinx.android.synthetic.main.widget_icon_title_message.view.*
import rodolfoizidoro.meucontato.R

class IconTitleMessage @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    RelativeLayout(context, attrs) {

    init {
        LayoutInflater.from(context).inflate(R.layout.widget_icon_title_message, this, true)

        val arr = context.obtainStyledAttributes(attrs, R.styleable.IconTitleMessage)
        val iconId = arr.getResourceId(R.styleable.IconTitleMessage_itm_icon, 0)
        val title = arr.getString(R.styleable.IconTitleMessage_itm_title)
        val message = arr.getString(R.styleable.IconTitleMessage_itm_message)

        ivITMIcon.setImageResource(iconId)
        tvITMTitle.text = title
        tvITMMessage.text = message
    }

}

package rodolfoizidoro.meucontato.binding

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.amulyakhare.textdrawable.TextDrawable
import com.amulyakhare.textdrawable.util.ColorGenerator
import com.squareup.picasso.Picasso

object ImageBinding {
    @JvmStatic
    @BindingAdapter("app:imageUrl", "app:placeHolder")
    fun setImageUrl(imageView: ImageView, url: String?, placeholder: Drawable) {
        Picasso.get()
            .load(url)
            .fit()
            .placeholder(placeholder)
            .centerInside()
            .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("app:imageUrl")
    fun setImageUrl(imageView: ImageView, url: String?) {
        Picasso.get()
            .load(url)
            .fit()
            .centerInside()
            .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("app:imageLetter")
    fun setImageLetter(imageView: ImageView, name: String) {
        val color = ColorGenerator.MATERIAL.getColor(name)
        val drawable = TextDrawable.builder()
            .beginConfig()
            .width(45)
            .withBorder(4)
            .height(45)
            .endConfig()
            .buildRound(name.first().toString(), color)

        imageView.setImageDrawable(drawable)
    }
}

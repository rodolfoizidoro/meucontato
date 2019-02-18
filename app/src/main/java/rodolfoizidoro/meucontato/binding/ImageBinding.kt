package rodolfoizidoro.meucontato.binding

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

object ImageBinding {
    @JvmStatic
    @BindingAdapter("app:imageUrl", "app:placeHolder")
    fun setImageUrl(imageView: ImageView, url: String?, placeholder : Drawable) {
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
}

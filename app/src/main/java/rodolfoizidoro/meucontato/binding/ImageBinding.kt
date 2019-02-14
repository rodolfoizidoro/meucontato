//package rodolfoizidoro.meucontato.binding
//
//import android.widget.ImageView
//import androidx.databinding.BindingAdapter
//import com.squareup.picasso.Picasso
//
//object ImageBinding {
//    @JvmStatic
//    @BindingAdapter("app:imageUrl")
//    fun setImageUrl(imageView: ImageView, url: String?) {
//        url?.let {
//            if (url.isNotEmpty()) {
//                Picasso.get()
//                    .load(url)
//                    .fit()
//                    .centerInside()
//                    .into(imageView)
//            }
//        }
//    }
//}

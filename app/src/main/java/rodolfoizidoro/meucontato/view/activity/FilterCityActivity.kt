package rodolfoizidoro.meucontato.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_filter_city.*

import rodolfoizidoro.meucontato.R

class FilterCityActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter_city)
        ivBack.setOnClickListener { finish() }
    }
}

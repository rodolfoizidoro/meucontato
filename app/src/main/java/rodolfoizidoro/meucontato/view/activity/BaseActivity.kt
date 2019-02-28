package rodolfoizidoro.meucontato.view.activity

import android.annotation.SuppressLint
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.primary_toolbar.*

@SuppressLint("Registered")
abstract class BaseActivity : AppCompatActivity() {

   override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
               onToolbarBackPressed()
                return true
            }
        }
        return false
    }

    fun onToolbarBackPressed() {
        finish()
    }

    fun prepareToolbar(bar : Toolbar? = toolbar, displayHome: Boolean = true) {
        setSupportActionBar(bar)
        supportActionBar?.setDisplayHomeAsUpEnabled(displayHome)
    }
}

package rodolfoizidoro.meucontato.common

import android.app.Activity
import android.content.Intent
import rodolfoizidoro.meucontato.view.activity.FilterCityActivity

class Navigator {

    fun openFilterCityActivity(activity: Activity){
        val intent = Intent(activity, FilterCityActivity::class.java)
        activity.startActivityForResult(intent, 0)
    }
}

package rodolfoizidoro.meucontato.util

import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

fun AppCompatActivity.addFragment(frameId: Int, fragment: Fragment, tag: String) {
    supportFragmentManager.inTransaction { add(frameId, fragment, tag) }
}


fun AppCompatActivity.replaceFragment(frameId: Int, fragment: Fragment, tag: String) {
    Handler().postDelayed({
        supportFragmentManager.inTransaction { replace(frameId, fragment, tag) }
    }, 120)
}

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().commit()
}

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}




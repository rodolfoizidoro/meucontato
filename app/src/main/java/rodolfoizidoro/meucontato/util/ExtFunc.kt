package rodolfoizidoro.meucontato.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

fun AppCompatActivity.addFragment(frameId: Int, fragment: Fragment, tag: String) {
    supportFragmentManager.inTransaction { add(frameId, fragment, tag) }
}


fun AppCompatActivity.replaceFragment(frameId: Int, fragment: Fragment, tag: String) {
    supportFragmentManager.inTransaction { replace(frameId, fragment, tag) }
}

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().commit()
}

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}


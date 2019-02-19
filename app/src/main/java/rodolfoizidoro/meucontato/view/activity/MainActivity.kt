package rodolfoizidoro.meucontato.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.intentFor
import org.koin.android.ext.android.inject
import rodolfoizidoro.meucontato.R
import rodolfoizidoro.meucontato.util.addFragment
import rodolfoizidoro.meucontato.util.replaceFragment
import rodolfoizidoro.meucontato.view.fragments.*


class MainActivity : AppCompatActivity() {

    val firebaseAuth : FirebaseAuth by inject()
    private var authListener: FirebaseAuth.AuthStateListener =
        FirebaseAuth.AuthStateListener {
            if (it.currentUser == null) {
                goToLogin()
            }
        }

    private fun goToLogin() {
        startActivity(intentFor<LoginActivity>())
        finish()
    }

    override fun onStart() {
        super.onStart()
        firebaseAuth.addAuthStateListener(authListener)
    }

    override fun onStop() {
        super.onStop()
        firebaseAuth.removeAuthStateListener(authListener)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        addFragment(R.id.frmContainer, ContactsFragment.newInstance(), ContactsFragment.TAG)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
        when (it.itemId) {
            R.id.navContactList -> {
                replaceFragment(R.id.frmContainer, ContactsFragment.newInstance(), ContactsFragment.TAG)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navMeetups -> {
                replaceFragment(R.id.frmContainer, MeetupsFragment.newInstance(), MeetupsFragment.TAG)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navShare -> {
                replaceFragment(R.id.frmContainer, ShareFragment.newInstance(), ShareFragment.TAG)
                return@OnNavigationItemSelectedListener true
            }

            R.id.navInfo -> {
                replaceFragment(R.id.frmContainer, InfoFragment.newInstance(), InfoFragment.TAG)
                return@OnNavigationItemSelectedListener true
            }

            R.id.navProfiles -> {
                replaceFragment(R.id.frmContainer, ProfilesFragment.newInstance(), ProfilesFragment.TAG)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
}

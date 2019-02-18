package rodolfoizidoro.meucontato.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import rodolfoizidoro.meucontato.R
import rodolfoizidoro.meucontato.util.addFragment
import rodolfoizidoro.meucontato.util.replaceFragment
import rodolfoizidoro.meucontato.view.fragments.*


class MainActivity : AppCompatActivity() {

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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        addFragment(R.id.frmContainer, ContactsFragment.newInstance(), ContactsFragment.TAG)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
}

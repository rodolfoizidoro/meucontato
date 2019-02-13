package rodolfoizidoro.meucontato.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import rodolfoizidoro.meucontato.R
import rodolfoizidoro.meucontato.util.loadFragment
import rodolfoizidoro.meucontato.view.fragments.*
import android.content.Intent



class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
        when (it.itemId) {
            R.id.navContactList -> {
                loadFragment {
                    replace(R.id.frmContainer, ContactsFragment.newInstance(), ContactsFragment.TAG)
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.navMeetups -> {
                loadFragment {
                    replace(R.id.frmContainer, MeetupsFragment.newInstance(), MeetupsFragment.TAG)
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.navShare -> {
                loadFragment {
                    replace(R.id.frmContainer, ShareFragment.newInstance(), ShareFragment.TAG)
                }
                return@OnNavigationItemSelectedListener true
            }

            R.id.navInfo -> {
                loadFragment {
                    replace(R.id.frmContainer, InfoFragment.newInstance(), InfoFragment.TAG)
                }
                return@OnNavigationItemSelectedListener true
            }

            R.id.navProfiles -> {
                loadFragment {
                    replace(R.id.frmContainer, ProfilesFragment.newInstance(), ProfilesFragment.TAG)
                }
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        loadFragment { replace(R.id.frmContainer, ContactsFragment.newInstance(), ContactsFragment.TAG) }
    }

   public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
}

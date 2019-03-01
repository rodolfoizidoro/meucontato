package rodolfoizidoro.meucontato.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.startActivity
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import rodolfoizidoro.meucontato.R
import rodolfoizidoro.meucontato.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private var googleApiClient: GoogleApiClient? = null
    private val fbAuth : FirebaseAuth by inject()
    private val viewModel by viewModel<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initGoogleSignIn()
        btnLoginGoogle.setOnClickListener { signIn() }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_GOOGLE_SIGN_IN) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            if (result.isSuccess) {
                val account = result.signInAccount
                if (account != null) {
                    firebaseAuthWithGoogle(account)
                } else {
                    showErrorSignIn()
                }
            } else {
                showErrorSignIn()
            }
        }
    }

    private fun initGoogleSignIn() {
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .requestProfile()
            .build()

        googleApiClient = GoogleApiClient.Builder(this)
            .enableAutoManage(this) {
                showErrorSignIn()
            }
            .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
            .build()
    }

    private fun signIn() {
        val signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient)
        startActivityForResult(
            signInIntent,
            RC_GOOGLE_SIGN_IN
        )
    }

    private fun firebaseAuthWithGoogle(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        val email = account.email ?: ""
        val name = account.displayName ?: ""
        fbAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val isNewUser = task.result?.additionalUserInfo?.isNewUser ?: false
                    if (isNewUser) {
                        registerDataForNewUser(name, email)
                    }
                    openMainActivity()

                } else {
                    showErrorSignIn()
                }
            }
    }

    private fun openMainActivity() {
        startActivity<MainActivity>()
        finish()
    }

    private fun registerDataForNewUser(name: String, email: String) {
        viewModel.error().observe(this, Observer {
            showErrorSignIn()
        })
        viewModel.success().observe(this, Observer {
            rootLogin.snackbar("Sucessoo ao fazer login")
        })

        viewModel.registerDataForNewUser(name, email)
    }

    private fun showErrorSignIn() {
        rootLogin.snackbar(R.string.login_error)
    }

    companion object {
        const val RC_GOOGLE_SIGN_IN = 1
    }
}

package rodolfoizidoro.meucontato.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.toast
import rodolfoizidoro.meucontato.R

class LoginActivity : AppCompatActivity() {

    private var googleApiClient: GoogleApiClient? = null
    private var fbAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initGoogleSignIn()

        btnLoginGoogle.setOnClickListener { signIn() }
        registerDataForNewUser("teste2", "r@gmail.com")
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
        startActivityForResult(signInIntent,
            RC_GOOGLE_SIGN_IN
        )
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
                } else {
                    showErrorSignIn()
                }
            }
    }

    private fun registerDataForNewUser(name : String, email : String) {
        val db = FirebaseFirestore.getInstance()
        val uid = FirebaseAuth.getInstance().uid.toString()
        val user : HashMap<String, Any>  = HashMap()

        val profiles : HashMap<String, Any>  = HashMap()

        val social : HashMap<String, Any>  = HashMap()
        social["id"] = db.collection("user").document(uid).collection("social").document().id
        social["tag"] = "gmail"
        social["type"] = "email"
        social["value"] = email

        val socialPerfil : HashMap<String, Any>  = HashMap()
        socialPerfil["id"] = db.collection("user").document(uid).collection("social").document().id
        socialPerfil["checked"] = true
        socialPerfil["id_social"] = social["id"].toString()

        profiles["id"] = db.collection("user").document(uid).collection("profiles").document().id
        profiles["name"] = "Perfil 1"
        profiles["display_name"] = name
        profiles["photo"] = uid
        profiles["description"] = "Perfil de contato de $name"
        profiles["profiles_social"] = arrayListOf(socialPerfil)

        user["id"] = uid
        user["name"] = name
        user["email"] = email
        user["profiles"] = arrayListOf(profiles)
        user["social"] = arrayListOf(social)

        val contato : HashMap<String, Any>  = HashMap()
        contato[uid] = user

        FirebaseFirestore.getInstance()
            .collection("user").document(uid).set(user).addOnSuccessListener {
                toast("sucesso")
            }
    }

    private fun showErrorSignIn() {
        rootLogin.snackbar(R.string.login_error)
    }

    companion object {
        const val RC_GOOGLE_SIGN_IN = 1
    }
}

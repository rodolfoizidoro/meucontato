package rodolfoizidoro.meucontato.view.fragments

import android.content.Intent
import android.hardware.Camera
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.share_fragment.*
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rodolfoizidoro.meucontato.R
import rodolfoizidoro.meucontato.view.activity.QrCodeActivity
import rodolfoizidoro.meucontato.viewmodel.ShareViewModel

class ShareFragment : Fragment() {

    companion object {
        const val TAG = "ShareFragment"
        fun newInstance() = ShareFragment()
    }

    private val viewModel: ShareViewModel by sharedViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.share_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observerSaveSuccess()
        observerError()
        ivShareReceiveQr.setOnClickListener { openScanner() }
        ivShareSendQr.setOnClickListener { openShareQrCode(viewModel.shareId()) }
        btnShareTestContact.setOnClickListener {
            btnShareTestContact.isEnabled = false
            viewModel.saveContact(viewModel.shareId())
        }

        viewModel.loadProfiles()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        viewModel.saveContact(result.contents)
        toast(result.contents)
    }

    private fun observerSaveSuccess() {
        viewModel.saveSuccess().observe(this, Observer {
            rootShare.snackbar("Contato adicionado na aba de contatos.")
        })
    }

    private fun observerError() {
        viewModel.error().observe(this, Observer {
            toast(it)
        })
    }

    private fun openShareQrCode(info: String) {
        startActivity<QrCodeActivity>(QrCodeActivity.EXTRA_INFO to info)
    }

    private fun openScanner() {
        val integrator = IntentIntegrator(activity)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES)
        integrator.setPrompt("Scan")
        integrator.setCameraId(cameraBack())
        integrator.setBeepEnabled(false)
        integrator.setBarcodeImageEnabled(true)

        integrator.initiateScan()
    }

    private fun cameraBack(): Int {
        val info = android.hardware.Camera.CameraInfo()
        android.hardware.Camera.getCameraInfo(1, info)
        if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            return 0
        } else if (info.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
            return 1
        }
        return info.facing
    }
}

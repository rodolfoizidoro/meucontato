package rodolfoizidoro.meucontato.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.journeyapps.barcodescanner.BarcodeEncoder
import kotlinx.android.synthetic.main.activity_qr_code.*
import rodolfoizidoro.meucontato.R

class QrCodeActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_INFO = "extra_info"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_code)

        try {
            val bitMatrix = MultiFormatWriter().encode(getInfo(), BarcodeFormat.QR_CODE, 200, 200)
            val barcodeEncoder = BarcodeEncoder()
            val bitmap = barcodeEncoder.createBitmap(bitMatrix)
            ivQrCode.setImageBitmap(bitmap)


        } catch (e: Exception) {
            e.printStackTrace()
        }

        btnQrCodeClose.setOnClickListener { finish() }
    }

    private fun getInfo() = intent.getStringExtra(EXTRA_INFO)
}

package rodolfoizidoro.meucontato.application

import android.app.Application
import org.koin.android.ext.android.startKoin
import rodolfoizidoro.meucontato.di.AppModule.MeuContatoModules

class MeuContatoApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(MeuContatoModules))
    }
}

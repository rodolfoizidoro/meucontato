package rodolfoizidoro.meucontato.application

import android.app.Application
import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric
import org.koin.android.ext.android.startKoin
import rodolfoizidoro.meucontato.di.AppModule.ContactsModule
import rodolfoizidoro.meucontato.di.AppModule.InfoModule
import rodolfoizidoro.meucontato.di.AppModule.LoginModule
import rodolfoizidoro.meucontato.di.AppModule.MeetupModule
import rodolfoizidoro.meucontato.di.AppModule.MeuContatoModules
import rodolfoizidoro.meucontato.di.AppModule.ProfileModule
import rodolfoizidoro.meucontato.di.AppModule.ShareModule

class MeuContatoApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Fabric.with(this, Crashlytics())
        startKoin(this, listOf(MeuContatoModules,
            MeetupModule, LoginModule, InfoModule, ProfileModule,
            ContactsModule, ShareModule))
    }
}

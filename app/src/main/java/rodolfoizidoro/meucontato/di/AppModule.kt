package rodolfoizidoro.meucontato.di

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import rodolfoizidoro.meucontato.api.MeetupRepository
import rodolfoizidoro.meucontato.api.MeetupService
import rodolfoizidoro.meucontato.viewmodel.MeetupsViewModel

object AppModule {
    val MeuContatoModules = module {

        single<Gson> {
            GsonBuilder()
                .setDateFormat("dd-MM-yyyy'T'HH:mm:ssZ")
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
        }

        single<MeetupService> {
            Retrofit.Builder()
                .baseUrl("")
                .addConverterFactory(get())
                .build()
                .create(MeetupService::class.java)
        }

        single {
            MeetupRepository(get())
        }
        viewModel { MeetupsViewModel(get()) }
    }
}

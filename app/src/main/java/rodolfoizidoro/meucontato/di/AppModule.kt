package rodolfoizidoro.meucontato.di

import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import rodolfoizidoro.meucontato.BuildConfig
import rodolfoizidoro.meucontato.api.MeetupRepository
import rodolfoizidoro.meucontato.api.MeetupService
import rodolfoizidoro.meucontato.common.SharedPrefController
import rodolfoizidoro.meucontato.viewmodel.FilterCityViewModel
import rodolfoizidoro.meucontato.viewmodel.MeetupsViewModel
import java.util.concurrent.TimeUnit

object AppModule {
    const val CONNECTION_TIMEOUT = 60000L

    private fun provideOkHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS)
            .readTimeout(CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS)
            .writeTimeout(CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS)

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            client.addInterceptor(logging)
        }

        return client.build()
    }

    val MeuContatoModules = module {
        single<Gson> {
            GsonBuilder()
                .setDateFormat("dd-MM-yyyy'T'HH:mm:ssZ")
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
        }

        single<MeetupService> {
            Retrofit.Builder()
                .baseUrl("https://api.meetup.com")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create(get()))
                .client(provideOkHttpClient())
                .build()
                .create(MeetupService::class.java)
        }

        single { MeetupRepository(get()) }
        single { SharedPrefController(androidContext()) }
        viewModel { MeetupsViewModel(get()) }
        viewModel { FilterCityViewModel(get()) }

        single { FirebaseFirestore.getInstance() }
    }
}

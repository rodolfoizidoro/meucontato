package rodolfoizidoro.meucontato.common

import android.content.Context
import android.content.Context.MODE_PRIVATE
import rodolfoizidoro.meucontato.model.City

class SharedPrefController(val context: Context) {
    companion object {
        private const val MY_PREFS_NAME = "meucontato"
        private const val CITY_NAME = "meucontato_city_name"
        private const val CITY_LAT = "meucontato_city_lat"
        private const val CITY_LON = "meucontato_city_lon"
        private const val CITY_COUNTRY = "meucontato_city_country"
    }

    fun saveCity(city: City) {
        getEditor().apply {
            putString(CITY_NAME, city.name)
            putString(CITY_LAT, city.lat.toString())
            putString(CITY_LON, city.lon.toString())
            putString(CITY_COUNTRY, city.country)
        }.apply()
    }

    fun getCity(): City {
        val name = getPrefs().getString(CITY_NAME, "São Paulo")
        val lat = getPrefs().getString(CITY_LAT, "-23.530000686645508").toDouble()
        val lon = getPrefs().getString(CITY_LON, "-46.630001068115234").toDouble()
        val country = getPrefs().getString(CITY_COUNTRY, "Brasil")
        return City(name, lat, lon, country)
    }

    private fun getEditor() = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit()
    private fun getPrefs() = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE)
}

package rodolfoizidoro.meucontato.util

import android.content.Context
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapUtil(val context: Context, val googleMap: GoogleMap) {

    fun setupMap(allowGesture : Boolean = false) {
        googleMap.uiSettings.apply {
            isZoomControlsEnabled = true
            isCompassEnabled = false
            isMyLocationButtonEnabled = false
            setAllGesturesEnabled(allowGesture)
            isMapToolbarEnabled = true

        }
    }

    fun setLocation(latLng: LatLng, name : String) {
        googleMap.clear()
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15.toFloat()))
        googleMap.addMarker(MarkerOptions().position(latLng).title(name)).showInfoWindow()
    }
}

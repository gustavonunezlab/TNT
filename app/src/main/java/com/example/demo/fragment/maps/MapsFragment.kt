package com.example.demo.fragment.maps

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.demo.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import java.util.*


class MapsFragment : Fragment() {

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        val conicet = LatLng(-42.78469053756446, -65.00895665709373)
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(conicet, 12F))
        setMapLongClick(googleMap)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    private fun setMapLongClick(map: GoogleMap) {

        val height = 140
        val width = 140
        val bitmapdraw = resources.getDrawable(R.drawable.map_icon2) as BitmapDrawable
        val b = bitmapdraw.bitmap
        val smallMarker = Bitmap.createScaledBitmap(b, width, height, false)

        var marker: Marker? = null

        map.setOnMapLongClickListener { latLng ->
// Snippet --> texto adicional que se muestra debajo del titulo.
            val snippet = String.format(
                Locale.getDefault(),
                "Lat: %1$.5f, Long: %2$.5f",
                latLng.latitude,
                latLng.longitude
            )

            if (marker == null) {
                marker = map.addMarker(
                    MarkerOptions()
                        .position(latLng)
                        .title("Captura")
                        .snippet(snippet)
                        .icon(BitmapDescriptorFactory.fromBitmap(smallMarker))
                )
                marker!!.showInfoWindow()
            } else {
                marker!!.hideInfoWindow()
                marker!!.position = latLng
                marker!!.snippet = snippet
                marker!!.showInfoWindow()
            }
        }
    }

}

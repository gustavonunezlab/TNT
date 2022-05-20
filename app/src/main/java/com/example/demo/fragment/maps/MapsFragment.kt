package com.example.demo.fragment.maps

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.demo.R
import com.example.demo.databinding.FragmentMapsBinding
import com.example.demo.viewModel.ReportViewModel
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

    private var _binding: FragmentMapsBinding? = null
    private val binding get() = _binding!!
    private val args: MapsFragmentArgs by navArgs()
    private lateinit var model: ReportViewModel

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

        _binding = FragmentMapsBinding.inflate(inflater, container, false)
        val view = binding.root
        model = ViewModelProvider(this)[ReportViewModel::class.java]

        _binding!!.goBackActionButton.setOnClickListener { goBack() }
        _binding!!.sendReportActionButton.setOnClickListener { sendReport() }


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    private fun setMapLongClick(map: GoogleMap) {

        val height = 140
        val width = 140
        val bitmapdraw = resources.getDrawable(R.drawable.map_icon) as BitmapDrawable
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
                args.currentReport.latitude = latLng.latitude
                args.currentReport.longitude = latLng.longitude
                Log.i("Pos: Latitude = ", args.currentReport.latitude.toString())
                Log.i("Pos: Longitude = ", args.currentReport.longitude.toString())
                marker!!.showInfoWindow()
            } else {
                marker!!.hideInfoWindow()
                marker!!.position = latLng
                marker!!.snippet = snippet
                args.currentReport.latitude = latLng.latitude
                args.currentReport.longitude = latLng.longitude
                Log.i("Pos: Latitude = ", args.currentReport.latitude.toString())
                Log.i("Pos: Longitude = ", args.currentReport.longitude.toString())
                marker!!.showInfoWindow()
            }
        }
    }

    private fun goBack() {
        findNavController().popBackStack()
    }

    private fun sendReport() {
        if (checkCoords()) {
            model.insert(args.currentReport)
            Toast.makeText(activity, "Reporte agregado correctamente", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.my_reports_fragment)
        }
    }

    private fun checkCoords(): Boolean {
        if(args.currentReport.latitude == null && args.currentReport.longitude == null) {
            Toast.makeText(activity, "Seleccione una ubicación en el mapa", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }
}

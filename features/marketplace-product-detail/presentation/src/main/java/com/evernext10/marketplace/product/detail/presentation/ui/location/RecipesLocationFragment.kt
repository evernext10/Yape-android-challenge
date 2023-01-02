package com.evernext10.marketplace.product.detail.presentation.ui.location

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.evernext10.core.domain.model.recipes.Location
import com.evernext10.marketplace.product.detail.presentation.R
import com.evernext10.marketplace.product.detail.presentation.databinding.FragmentRecipesLocationBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class RecipesLocationFragment : Fragment() {

    private var _binding: FragmentRecipesLocationBinding? = null
    private val binding get() = _binding!!

    private val location: Location by lazy {
        requireArguments()["location"] as Location
    }

    private val recipesName: String by lazy {
        requireArguments()["recipes_name"] as String
    }

    private val callback = OnMapReadyCallback { googleMap ->
        LatLng(location.latitude ?: 0.0, location.longitude ?: 0.0).let {
            googleMap.addMarker(MarkerOptions().position(it).title(recipesName))
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(it))
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(it, 15.0f))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipesLocationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)

        toolbar.title = recipesName
        toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }
}

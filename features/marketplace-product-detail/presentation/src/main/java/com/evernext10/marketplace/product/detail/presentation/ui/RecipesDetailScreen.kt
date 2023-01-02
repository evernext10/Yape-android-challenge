package com.evernext10.marketplace.product.detail.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.evernext10.core.domain.model.recipes.Recipes
import com.evernext10.core.domain.model.recipes.state.StateRecipesDetail
import com.evernext10.core.ext.launchAndRepeatWithViewLifecycle
import com.evernext10.core.ext.showAlertDialogErrorApi
import com.evernext10.core.ext.toFormattedNumber
import com.evernext10.core.ext.visible
import com.evernext10.marketplace.product.detail.presentation.R
import com.evernext10.marketplace.product.detail.presentation.adapter.PhotosRecipesAdapter
import com.evernext10.marketplace.product.detail.presentation.databinding.FragmentProductDetailScreenBinding
import com.evernext10.navigation.Destination
import com.evernext10.navigation.navigateToDestination
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecipesDetailScreen : Fragment() {

    private var _binding: FragmentProductDetailScreenBinding? = null
    private val binding get() = _binding!!

    private val detailProductDetailViewModel by viewModel<RecipesDetailViewModel>()

    private val photosViewPager: PhotosRecipesAdapter by lazy {
        PhotosRecipesAdapter()
    }

    private val itemRecipes: Recipes by lazy {
        requireArguments()["recipes"] as Recipes
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductDetailScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i("STATE_INSTANCE", "Saved")
    }
    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.i("STATE_INSTANCE", "Restored")
        observerState()
        initViews(itemRecipes)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.product_item_menu, menu)
    }

    private fun observerState() {
        launchAndRepeatWithViewLifecycle {
            detailProductDetailViewModel.recipesDetailState.observe(viewLifecycleOwner) {
                Log.i("ResponseStatus", it.toString())
                when (it) {
                    is StateRecipesDetail.Loading -> {
                        binding.progress.visible(true)
                    }
                    is StateRecipesDetail.Success -> {
                        binding.progress.visible(false)
                    }
                    is StateRecipesDetail.Unauthorized -> {
                        binding.progress.visible(true)
                        Log.i("Response", "Unauthorized")
                    }
                    is StateRecipesDetail.Error -> {
                        binding.progress.visible(false)
                        showAlertDialogErrorApi()
                    }
                    else -> {
                        binding.progress.visible(false)
                        Log.i("Response", "Unknow")
                    }
                }
            }
        }
    }

    private fun initViews(product: Recipes) = with(binding) {
        photosViewPager.submitList(product.pictures)
        productSold.text = "${product.average_time} min"
        productTitle.text = product.name
        productPrice.text = product.average_cost?.toFormattedNumber()
        productOffer.text = product.type
        viewPager2.adapter = photosViewPager
        progress.visible(false)

        toolbar.apply {
            inflateMenu(R.menu.product_item_menu)
            setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                tvImagesCount.text = "${position.plus(1)}/${photosViewPager.itemCount}"
            }
        })

        recipesLocationButton.setOnClickListener {
            navigateToDestination(
                Destination.RecipesLocation(itemRecipes.location!!, itemRecipes.name!!)
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

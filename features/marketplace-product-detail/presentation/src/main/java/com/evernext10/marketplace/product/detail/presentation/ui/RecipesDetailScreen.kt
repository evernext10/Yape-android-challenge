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
import com.evernext10.core.ext.visible
import com.evernext10.marketplace.product.detail.presentation.R
import com.evernext10.marketplace.product.detail.presentation.adapter.PhotosRecipesAdapter
import com.evernext10.marketplace.product.detail.presentation.databinding.FragmentProductDetailScreenBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecipesDetailScreen : Fragment() {

    private var _binding: FragmentProductDetailScreenBinding? = null
    private val binding get() = _binding!!

    private val detailProductDetailViewModel by viewModel<RecipesDetailViewModel>()

    private val photosViewPager: PhotosRecipesAdapter by lazy {
        PhotosRecipesAdapter()
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
        detailProductDetailViewModel.getDataFromStateHandled(requireArguments()["productId"] as String)
        observerState()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.product_item_menu, menu)
    }

    private fun observerState() {
        launchAndRepeatWithViewLifecycle {
            detailProductDetailViewModel.productDetailState.observe(viewLifecycleOwner) {
                Log.i("ResponseStatus", it.toString())
                when (it) {
                    is StateRecipesDetail.Loading -> {
                        binding.progress.visible(true)
                    }
                    is StateRecipesDetail.Success -> {
                        binding.progress.visible(false)
                        initViews(it.product)
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
        productSold.text = ""
        productTitle.text = product.name
        productPrice.text = ""
        viewPager2.adapter = photosViewPager

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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

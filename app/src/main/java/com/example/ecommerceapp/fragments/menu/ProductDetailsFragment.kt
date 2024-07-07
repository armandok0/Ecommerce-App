package com.example.ecommerceapp.fragments.menu

import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.ecommerceapp.R
import com.example.ecommerceapp.data.Cart
import com.example.ecommerceapp.data.Favorite
import com.example.ecommerceapp.data.CartViewModel
import com.example.ecommerceapp.data.FavoriteViewModel
import com.example.ecommerceapp.data.ProductViewModel
import com.example.ecommerceapp.adapters.HomeColorsAdapter
import com.example.ecommerceapp.adapters.HomeSizesAdapter
import com.example.ecommerceapp.adapters.HomeViewerPager2Adapter
import kotlinx.coroutines.launch

class ProductDetailsFragment : Fragment() {

    private lateinit var productViewModel: ProductViewModel
    private lateinit var cartViewModel: CartViewModel
    private lateinit var favoriteViewModel: FavoriteViewModel
    private lateinit var colorsAdapter: HomeColorsAdapter
    private lateinit var sizesAdapter: HomeSizesAdapter
    private lateinit var viewPagerAdapter: HomeViewerPager2Adapter
    private lateinit var rvColors: RecyclerView
    private lateinit var rvSizes: RecyclerView
    private lateinit var viewPager: ViewPager2
    private lateinit var btnAddToCart: Button
    private lateinit var imgClose: ImageView
    private lateinit var imgFavorite: ImageView

    private var selectedColor: Int? = null
    private var selectedSize: String? = null
    private var isFavorite: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_home_product_details, container, false)

        val tvProductName: TextView = rootView.findViewById(R.id.tv_product_name)
        val tvProductPrice: TextView = rootView.findViewById(R.id.tv_product_price)
        val tvProductDiscountedPrice: TextView = rootView.findViewById(R.id.tv_product_discounted_price)
        val tvProductDescription: TextView = rootView.findViewById(R.id.tv_product_description)
        viewPager = rootView.findViewById(R.id.view_pager_product_images)
        rvColors = rootView.findViewById(R.id.rv_colors)
        rvSizes = rootView.findViewById(R.id.rv_sizes)
        btnAddToCart = rootView.findViewById(R.id.btn_add_to_cart)
        imgClose = rootView.findViewById(R.id.img_close)
        imgFavorite = rootView.findViewById(R.id.img_favorite)

        val productId = arguments?.getInt("productId") ?: 0

        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
        cartViewModel = ViewModelProvider(requireActivity()).get(CartViewModel::class.java)
        favoriteViewModel = ViewModelProvider(requireActivity()).get(FavoriteViewModel::class.java)

        productViewModel.getProductById(productId).observe(viewLifecycleOwner, Observer { product ->
            tvProductName.text = product.name
            tvProductPrice.text = "$${String.format("%.2f", product.price)}"
            tvProductDescription.text = product.description

            product.offerPercentage?.let { offer ->
                val discountedPrice = product.price * (1 - offer / 100)
                if (discountedPrice < product.price) {
                    tvProductDiscountedPrice.visibility = View.VISIBLE
                    tvProductDiscountedPrice.text = "$${String.format("%.2f", discountedPrice)}"
                    tvProductPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                } else {
                    tvProductDiscountedPrice.visibility = View.GONE
                    tvProductPrice.paintFlags = 0
                }
            } ?: run {
                tvProductDiscountedPrice.visibility = View.GONE
                tvProductPrice.paintFlags = 0
            }

            setupViewPager(product.imageResIds)
            setupColorsRecyclerView(product.colors)
            setupSizesRecyclerView(product.sizes)

            btnAddToCart.setOnClickListener {
                if (selectedColor == null) {
                    Toast.makeText(requireContext(), "Please select a color", Toast.LENGTH_SHORT).show()
                } else if (selectedSize == null) {
                    Toast.makeText(requireContext(), "Please select a size", Toast.LENGTH_SHORT).show()
                } else {
                    val quantity = 1
                    val cartItem = Cart.fromProduct(product, quantity, selectedColor, selectedSize)
                    cartViewModel.addToCart(cartItem)
                    Toast.makeText(requireContext(), "Added to cart", Toast.LENGTH_SHORT).show()
                }
            }

            // Check if the product is already a favorite
            lifecycleScope.launch {
                isFavorite = favoriteViewModel.isProductFavorited(productId)
                updateFavoriteIcon()
            }

            imgFavorite.setOnClickListener {
                isFavorite = !isFavorite
                updateFavoriteIcon()
                if (isFavorite) {
                    val favoriteItem = Favorite.fromProduct(product)
                    favoriteViewModel.addToFavorites(favoriteItem)
                    Toast.makeText(requireContext(), "Added to favorites", Toast.LENGTH_SHORT).show()
                } else {
                    favoriteViewModel.removeFromFavorites(product.id)
                    Toast.makeText(requireContext(), "Removed from favorites", Toast.LENGTH_SHORT).show()
                }
            }
        })

        imgClose.setOnClickListener {
            findNavController().navigateUp()
        }

        return rootView
    }

    private fun updateFavoriteIcon() {
        if (isFavorite) {
            imgFavorite.setImageResource(R.drawable.home_favorites_filled)
        } else {
            imgFavorite.setImageResource(R.drawable.home_favorites_outlined)
        }
    }

    private fun setupViewPager(imageResIds: List<Int>) {
        viewPagerAdapter = HomeViewerPager2Adapter()
        viewPager.adapter = viewPagerAdapter
        viewPagerAdapter.differ.submitList(imageResIds)
    }

    private fun setupColorsRecyclerView(colors: List<Int>) {
        colorsAdapter = HomeColorsAdapter()
        rvColors.apply {
            adapter = colorsAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
        }
        colorsAdapter.differ.submitList(colors)
        colorsAdapter.onItemClick = { color ->
            selectedColor = color
        }
    }

    private fun setupSizesRecyclerView(sizes: List<String>) {
        sizesAdapter = HomeSizesAdapter()
        rvSizes.apply {
            adapter = sizesAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
        }
        sizesAdapter.differ.submitList(sizes)
        sizesAdapter.onItemClick = { size ->
            selectedSize = size
        }
    }
}

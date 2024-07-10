package com.example.ecommerceapp.fragments.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceapp.R
import com.example.ecommerceapp.data.Favorite
import com.example.ecommerceapp.adapters.FavoriteAdapter
import com.example.ecommerceapp.data.FavoriteViewModel

class FavoritesFragment : Fragment() {

    private var _binding: View? = null
    private val binding get() = _binding!!
    private val favoriteViewModel: FavoriteViewModel by viewModels()
    private lateinit var favoriteAdapter: FavoriteAdapter
    private lateinit var textViewEmptyState: TextView
    private lateinit var recyclerViewFavorites: RecyclerView
    private lateinit var textViewTitle: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = inflater.inflate(R.layout.fragment_favorites, container, false)
        return binding
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textViewTitle = binding.findViewById(R.id.textViewTitle)
        textViewEmptyState = binding.findViewById(R.id.textViewEmptyState)
        recyclerViewFavorites = binding.findViewById(R.id.recyclerViewFavorites)

        setupRecyclerView()

        favoriteViewModel.allFavoriteItems.observe(viewLifecycleOwner) { favoriteItems ->
            if (favoriteItems.isEmpty()) {
                textViewEmptyState.visibility = View.VISIBLE
                recyclerViewFavorites.visibility = View.GONE
            } else {
                textViewEmptyState.visibility = View.GONE
                recyclerViewFavorites.visibility = View.VISIBLE
                favoriteAdapter.submitList(favoriteItems)
            }
        }
    }

    private fun setupRecyclerView() {
        favoriteAdapter = FavoriteAdapter(
            onItemClick = { productId ->
                navigateToProductDetails(productId)
            },
            onFavoriteClick = { favorite ->
                toggleFavoriteStatus(favorite)
            }
        )
        recyclerViewFavorites.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = favoriteAdapter
            setHasFixedSize(true)
        }
    }

    private fun navigateToProductDetails(productId: Int) {
        val action =
            FavoritesFragmentDirections.actionFavoritesFragmentToProductDetailsFragment(productId)
        findNavController().navigate(action)
    }

    private fun toggleFavoriteStatus(favorite: Favorite) {
        favoriteViewModel.removeFromFavorites(favorite.productId)
        Toast.makeText(requireContext(), "Removed from favorites", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

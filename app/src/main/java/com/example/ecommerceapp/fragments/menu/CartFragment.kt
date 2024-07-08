package com.example.ecommerceapp.fragments.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceapp.R
import com.example.ecommerceapp.adapters.CartAdapter
import com.example.ecommerceapp.data.Cart
import com.example.ecommerceapp.data.CartViewModel
import com.example.ecommerceapp.data.OrderViewModel
import java.text.DecimalFormat

class CartFragment : Fragment(), CartAdapter.CartItemClickListener {

    private lateinit var cartViewModel: CartViewModel
    private lateinit var orderViewModel: OrderViewModel
    private lateinit var cartAdapter: CartAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var btnPlaceOrder: Button
    private lateinit var emptyCartMessage: TextView
    private lateinit var textViewTotalPrice: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cart, container, false)
        recyclerView = view.findViewById(R.id.recyclerViewCart)
        btnPlaceOrder = view.findViewById(R.id.btnPlaceOrder)
        emptyCartMessage = view.findViewById(R.id.textViewEmptyCart)
        textViewTotalPrice = view.findViewById(R.id.textViewTotalPrice)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cartAdapter = CartAdapter(this)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = cartAdapter
        }

        btnPlaceOrder.setOnClickListener {
            cartViewModel.placeOrderAndClearCart()
            Toast.makeText(requireContext(), "Order placed successfully!", Toast.LENGTH_SHORT).show()
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        cartViewModel = ViewModelProvider(this).get(CartViewModel::class.java)
        orderViewModel = ViewModelProvider(this).get(OrderViewModel::class.java)

        cartViewModel.allCartItems.observe(viewLifecycleOwner, Observer { cartItems ->
            if (cartItems.isEmpty()) {
                showEmptyCartMessage()
            } else {
                hideEmptyCartMessage()
                cartAdapter.submitList(cartItems)
                showPlaceOrderButton()
                updateTotalPrice(cartItems)
            }
        })
    }

    private fun showEmptyCartMessage() {
        emptyCartMessage.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
        btnPlaceOrder.visibility = View.GONE
        textViewTotalPrice.visibility = View.GONE
    }

    private fun hideEmptyCartMessage() {
        emptyCartMessage.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
        textViewTotalPrice.visibility = View.VISIBLE
    }

    private fun showPlaceOrderButton() {
        btnPlaceOrder.visibility = View.VISIBLE
    }

    private fun updateTotalPrice(cartItems: List<Cart>) {
        val totalPrice = cartItems.sumByDouble { (it.price * it.quantity).toDouble() }.toFloat()
        val decimalFormat = DecimalFormat("#.##")
        textViewTotalPrice.text = "Total: ${decimalFormat.format(totalPrice)} $"
    }

    override fun onDecreaseQuantityClick(cartItem: Cart, position: Int) {
        if (cartItem.quantity > 1) {
            cartItem.quantity--
            cartViewModel.updateCartItem(cartItem)
        }
    }

    override fun onIncreaseQuantityClick(cartItem: Cart, position: Int) {
        cartItem.quantity++
        cartViewModel.updateCartItem(cartItem)
    }

    override fun onDeleteItemClick(cartItem: Cart, position: Int) {
        cartViewModel.deleteCartItem(cartItem)
    }
}

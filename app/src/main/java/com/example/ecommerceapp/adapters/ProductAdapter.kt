import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceapp.data.Cart
import com.example.ecommerceapp.databinding.ItemOrdersProductReviewBinding

class ProductAdapter(
    private val products: List<Cart>,
    private val listener: ReviewSubmitListener
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    interface ReviewSubmitListener {
        fun onReviewSubmit(productId: Int, rating: Float, comment: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemOrdersProductReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int = products.size

    inner class ProductViewHolder(private val binding: ItemOrdersProductReviewBinding) : RecyclerView.ViewHolder(binding.root) {
        private val imageViewProductImage = binding.imageViewProductImage
        private val textViewProductName = binding.textViewProductName
        private val textViewProductPrice = binding.textViewProductPrice
        private val textViewProductQuantity = binding.textViewProductQuantity
        private val textViewProductColor = binding.textViewProductColor
        private val textViewProductSize = binding.textViewProductSize
        private val ratingBarProduct = binding.ratingBarProduct
        private val editTextReviewComment = binding.editTextReviewComment
        private val btnSubmitReview = binding.btnSubmitReview

        fun bind(product: Cart) {
            // Bind product data to UI elements
            imageViewProductImage.setImageResource(product.imageResId)
            textViewProductName.text = product.productName
            textViewProductPrice.text = "Price: $${product.price}"
            textViewProductQuantity.text = "Quantity: ${product.quantity}"
            textViewProductColor.text = "Color: ${product.selectedColor}"
            textViewProductSize.text = "Size: ${product.selectedSize}"

            btnSubmitReview.setOnClickListener {
                val rating = ratingBarProduct.rating
                val comment = editTextReviewComment.text.toString()
                listener.onReviewSubmit(product.productId, rating, comment)
            }
        }
    }
}

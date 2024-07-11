import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceapp.R
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
        val binding = ItemOrdersProductReviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int = products.size

    inner class ProductViewHolder(private val binding: ItemOrdersProductReviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val imageViewProductImage = binding.imageViewProductImage
        private val textViewProductName = binding.textViewProductName
        private val textViewProductPrice = binding.textViewProductPrice
        private val textViewProductQuantity = binding.textViewProductQuantity
        private val textViewProductColor = binding.textViewProductColor
        private val textViewProductSize = binding.textViewProductSize
        private val ratingBarProduct = binding.ratingBarProduct
        private val editTextReviewComment = binding.editTextReviewComment
        private val btnSubmitReview = binding.btnSubmitReview

        init {
            btnSubmitReview.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val product = products[position]
                    val rating = ratingBarProduct.rating
                    val comment = editTextReviewComment.text.toString()
                    listener.onReviewSubmit(product.productId, rating, comment)

                    btnSubmitReview.isEnabled = false
                    editTextReviewComment.isEnabled = false
                    ratingBarProduct.isEnabled = false
                }
            }
        }

        fun bind(product: Cart) {
            imageViewProductImage.setImageResource(product.imageResId)
            textViewProductName.text = product.productName
            textViewProductPrice.text = itemView.context.getString(R.string.price_text, product.price.toString())
            textViewProductQuantity.text = itemView.context.getString(R.string.quantity_text, product.quantity)
            // Set color and size information
            val hexColor =
                product.selectedColor?.let { String.format("#%06X", 0xFFFFFF and it) } ?: "#000000"

            try {
                val color = Color.parseColor(hexColor)
                val circleDrawable =
                    ContextCompat.getDrawable(itemView.context, R.drawable.cart_circle)
                circleDrawable?.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
                textViewProductColor.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    null,
                    null,
                    circleDrawable,
                    null
                )
            } catch (e: IllegalArgumentException) {
                // Handle error
                val circleDrawable =
                    ContextCompat.getDrawable(itemView.context, R.drawable.cart_circle)
                circleDrawable?.setColorFilter(
                    Color.TRANSPARENT,
                    PorterDuff.Mode.SRC_ATOP
                )
                textViewProductColor.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    null,
                    null,
                    circleDrawable,
                    null
                )
            }
            textViewProductSize.text = itemView.context.getString(R.string.size_text, product.selectedSize ?: "N/A")

            ratingBarProduct.isEnabled = true
            btnSubmitReview.isEnabled = true
            editTextReviewComment.isEnabled = true
        }
    }
}
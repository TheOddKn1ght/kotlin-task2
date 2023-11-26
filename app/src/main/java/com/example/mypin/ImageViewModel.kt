package com.example.mypin

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import kotlinx.coroutines.launch
import java.io.IOException

class ImageViewModel(
    private val repository: ImageRepository,
    private val picasso: Picasso
) : ViewModel() {

    private val _imageLiveData = MutableLiveData<Result<ImageItem>>()
    val imageLiveData: LiveData<Result<ImageItem>> get() = _imageLiveData

    fun getMemes(url: String) {
        viewModelScope.launch {
            try {
                val response = repository.getMemes(url)

                if (response is Result.Success) {
                    val imageUrl = response.data.imageUrl
                    if (!imageUrl.isNullOrBlank()) {
                        loadImage(imageUrl)
                    }
                }
                _imageLiveData.value = response
            } catch (e: IOException) {
                _imageLiveData.value = Result.Error("IOException: ${e.message}")
            }
        }
    }

    private fun loadImage(imageUrl: String) {
        // Assuming you are loading the image into an ImageView
        val target = object : Target {
            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                // Do something with the loaded bitmap if needed
            }

            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                // Handle the failure scenario if needed
            }

            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                // Handle pre-loading actions if needed
            }
        }

        picasso.load(imageUrl)
            // Add any additional Picasso configurations if needed
            .into(target)
    }
}

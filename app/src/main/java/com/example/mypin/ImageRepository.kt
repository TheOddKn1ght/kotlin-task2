package com.example.mypin

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.await
import java.io.IOException
import retrofit2.Response


class ImageRepository(private val memesApi: MemesApi) {

    suspend fun getMemes(url: String): ImageItem? {
        return withContext(Dispatchers.IO) {
            try {
                val response = memesApi.getMemes(url).await()

                if (response.isSuccessful) {
                    val imageUrl = response.body()?.string()
                    if (!imageUrl.isNullOrBlank()) {
                        ImageItem(imageUrl)
                    } else {
                        null // Return null for empty image URL
                    }
                } else {
                    null // Return null for failed response
                }
            } catch (e: IOException) {
                null // Return null for IO exception
            }
        }
    }
}

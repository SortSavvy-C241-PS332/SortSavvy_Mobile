package com.bangkit.sortsavvy.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import com.bangkit.sortsavvy.R
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.common.ops.CastOp
import org.tensorflow.lite.support.common.ops.NormalizeOp
import org.tensorflow.lite.task.core.BaseOptions
import org.tensorflow.lite.task.vision.classifier.ImageClassifier
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.image.ops.ResizeOp
import org.tensorflow.lite.support.metadata.schema.NormalizationOptions
import org.tensorflow.lite.support.metadata.schema.NormalizationOptionsT

class ImageClassifierUtil(
    var threshold: Float = 0.1f,
    var maxResults: Int = 1,
    var modelName: String = "model_ML_sort_savvy.tflite",
    val context: Context,
    val classifierListener: ClassifierListener?
) {
    private var imageClassifier: ImageClassifier? = null

    init {
        setupImageClassifier()
    }

    interface ClassifierListener {
        fun onError(error: String)
        fun onResults(
            result: String,
            accuracy: Float)
    }

    private fun setupImageClassifier() {
        val optionsBuilder = ImageClassifier.ImageClassifierOptions.builder()
            .setScoreThreshold(threshold)
            .setMaxResults(maxResults)

        val baseOptionsBuilder = BaseOptions.builder()
            .setNumThreads(4)

        optionsBuilder.setBaseOptions(baseOptionsBuilder.build())

        try {
            imageClassifier = ImageClassifier.createFromFileAndOptions(
                context,
                modelName,
                optionsBuilder.build()
            )
        } catch (e: Exception) {
            classifierListener?.onError(context.getString(R.string.error_initializing_model))
            Log.e(TAG, e.message.toString())
        }
    }

    fun classifyStaticImage(imageUri: Uri) {
        if (imageClassifier == null) {
            setupImageClassifier()
        }

        val imageProcessor = ImageProcessor.Builder()
            .add(ResizeOp(224, 224, ResizeOp.ResizeMethod.NEAREST_NEIGHBOR))
            .add(CastOp(DataType.FLOAT32))
//            .add(NormalizeOp(0.0f, 1.0f))
            .add(NormalizeOp(127.5f, 127.5f))  // This scales the values from [0, 255] to [-1, 1]
            .build()

        val copiedBitmap = uriToBitmap(imageUri, imageProcessor)

        copiedBitmap?.let { bitmap ->
            val tensorImage = imageProcessor.process(TensorImage.fromBitmap(bitmap))

            val results = imageClassifier?.classify(tensorImage)

            val topResult = results?.get(0)?.categories?.get(0)
            topResult?.let { category ->
                val result = category.label // label result cancer or non-cancer
                val accuracy = category.score * 100 // accuracy/confidenceScore
                classifierListener?.onResults(result, accuracy)
            }
        }
    }

    // perlu konversi Uri menjadi Bitmap terlebih dahulu untuk digunakan sebagai TensorImage
    private fun uriToBitmap(imageUri: Uri, imageProcessor: ImageProcessor): Bitmap? {
        // buat object bitmap
        // use ImageDecoder for Pie version or higher
        val bitmap: Bitmap? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val source = ImageDecoder.createSource(context.contentResolver, imageUri)
            ImageDecoder.decodeBitmap(source)
        } else {
            // use MediaStore to decode the image for lower than Pie version
            MediaStore.Images.Media.getBitmap(context.contentResolver, imageUri)
        }
        // setelah Bitmap didapatkan, copy dengan Bitmap.Config.ARGB_8888 configuration
        // configuration ini allow setiap pixel to be stored on 4 bytes
        val copiedBitmap = bitmap?.copy(Bitmap.Config.ARGB_8888, true)

        return copiedBitmap
    }

    companion object {
        private const val TAG = "ImageClassifierUtil"
    }
}
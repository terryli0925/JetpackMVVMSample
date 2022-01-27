package com.terry.jetpackmvvm.sample.util

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.content.FileProvider
import com.terry.jetpackmvvm.sample.BuildConfig
import java.io.File
import java.io.FileOutputStream


object FileUtils {

    /**
     * context.getFilesDir(): /data/user/0/<package name>/files
     * context.getExternalFilesDir(""): /storage/emulated/0/Android/data/<package name>/files
     */
    fun saveImageToAppDir(context: Context, bmp: Bitmap?) {
        saveImageToAppDir(context, bmp, System.currentTimeMillis().toString() + ".jpg")
    }

    fun saveImageToAppDir(context: Context, bmp: Bitmap?, filename: String) {
        bmp ?: return

        val appDir = File(context.filesDir, "images")
//        val appDir = File(context.getExternalFilesDir(null), "images")
        if (!appDir.exists()) {
            appDir.mkdir()
        }
        val file = File(appDir, filename)
        try {
            val fos = FileOutputStream(file)
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos)
            fos.flush()
            fos.close()
            Toast.makeText(context, "Save successfully.", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, file.name)
        values.put(MediaStore.Images.Media.DISPLAY_NAME, file.name)
        values.put(MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis())
        values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis())
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            values.put(MediaStore.MediaColumns.RELATIVE_PATH, "")
        } else {
            values.put(MediaStore.MediaColumns.DATA, file.absolutePath)
        }
        values.put(MediaStore.Images.Media.SIZE, file.length())
        val uri =
            context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        bmp.recycle()
    }

    fun saveImageToPicture(context: Context, bmp: Bitmap?) {
        saveImageToSharedStorage(context, bmp, Environment.DIRECTORY_PICTURES)
    }

    fun saveImageToSharedStorage(context: Context, bmp: Bitmap?, directory: String) {
        bmp ?: return

        val fileName = System.currentTimeMillis().toString() + ".jpg"
        val values = ContentValues()
        values.put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
        values.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            values.put(MediaStore.MediaColumns.RELATIVE_PATH, directory)
        } else {
            values.put(
                MediaStore.MediaColumns.DATA,
                "${Environment.getExternalStorageDirectory().path}/$directory/$fileName"
            )
        }
        val uri = context.contentResolver.insert(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            values
        )
//        val uri = context.contentResolver.insert(MediaStore.Images.Media.INTERNAL_CONTENT_URI, values)
        if (uri != null) {
            val outputStream = context.contentResolver.openOutputStream(uri)
            if (outputStream != null) {
                bmp.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                outputStream.close()
                Toast.makeText(context, "Save successfully.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun fileToUri(context: Context, file: File?): Uri? {
        if (!isFileExists(file)) return null
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val authority: String = BuildConfig.APPLICATION_ID + ".fileprovider"
            try {
                FileProvider.getUriForFile(context, authority, file!!)
            } catch (e: IllegalArgumentException) {
                return null
            }
        } else {
            Uri.fromFile(file)
        }
    }

    fun isFileExists(file: File?): Boolean {
        if (file == null) return false
        return file.exists()
    }
}
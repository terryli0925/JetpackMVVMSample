package com.terry.jetpackmvvm.sample.util

import android.content.Context
import android.net.Uri
import androidx.core.app.ShareCompat
import java.io.File


object ShareUtils {

    fun shareText(context: Context, title: String?, text: String) {
        // 舊方法
//        val intent = Intent(Intent.ACTION_SEND).apply {
//            this.type = "text/plain"
//            this.putExtra(Intent.EXTRA_TEXT, text)
//        }
//        context.startActivity(intent)
//        context.startActivity(Intent.createChooser(intent, title))

        // 新方法
        ShareCompat.IntentBuilder(context)
            .setType("text/plain")
            .setChooserTitle(title)
            .setText(text)
            .startChooser()
    }

    fun shareImage(context: Context, file: File) {
        shareImage(context, FileUtils.fileToUri(context, file))
    }

    fun shareImage(context: Context, uri: Uri?) {
        uri ?: return

        // 舊方法
//        val intent = Intent(Intent.ACTION_SEND).apply {
//            this.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
//            this.putExtra(Intent.EXTRA_STREAM, uri)
//            this.setDataAndType(uri, context.contentResolver.getType(uri))
//            this.type = context.contentResolver.getType(uri)
//        }
//        context.startActivity(intent)

        // 新方法
        ShareCompat.IntentBuilder(context)
            .setType(context.contentResolver.getType(uri))
            .setStream(uri)
            .startChooser()
    }

    fun shareImageFiles(context: Context, files: List<File>) {
        val uris = mutableListOf<Uri>()
        for (file in files) {
            val uri = FileUtils.fileToUri(context, file)
            uri?.let {
                uris.add(uri)
            }
        }

        shareImageUris(context, uris)
    }

    fun shareImageUris(context: Context, uris: List<Uri>) {
        val builder = ShareCompat.IntentBuilder(context).apply {
            for (uri in uris) {
                addStream(uri)
            }
            setType("image/*")
        }
        builder.startChooser()
    }

    fun shareContent(context: Context, content: String, file: File) {
        shareContent(context, content, FileUtils.fileToUri(context, file))
    }

    fun shareContent(context: Context, content: String, uri: Uri?) {
        uri ?: return

        // 舊方法
//        val intent = Intent(Intent.ACTION_SEND)
//        intent.putExtra(Intent.EXTRA_TEXT, content)
//        intent.putExtra(Intent.EXTRA_STREAM, uri)
//        intent.type = context.contentResolver.getType(uri)
//        context.startActivity(Intent.createChooser(intent, ""))

        // 新方法
        ShareCompat.IntentBuilder(context)
            .setType(context.contentResolver.getType(uri))
            .setText(content)
            .setStream(uri)
            .startChooser()
    }
}
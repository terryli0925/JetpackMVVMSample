package com.terry.jetpackmvvm.sample.ui.utiltest

import android.Manifest
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import autodispose2.androidx.lifecycle.AndroidLifecycleScopeProvider
import autodispose2.autoDispose
import com.chad.library.adapter.base.BaseQuickAdapter
import com.jakewharton.rxbinding4.view.clicks
import com.tbruyelle.rxpermissions3.RxPermissions
import com.terry.jetpackmvvm.sample.R
import com.terry.jetpackmvvm.sample.databinding.DialogLoadingBinding
import com.terry.jetpackmvvm.sample.databinding.DialogVerificationCodeBinding
import com.terry.jetpackmvvm.sample.databinding.FragmentUtilTestBinding
import com.terry.jetpackmvvm.sample.ui.base.BaseFragment
import com.terry.jetpackmvvm.sample.ui.utiltest.dialog.ScreenshotDialog
import com.terry.jetpackmvvm.sample.ui.utiltest.dialog.ScrollingFragment
import com.terry.jetpackmvvm.sample.util.*
import com.terry.jetpackmvvm.sample.util.image.ImageUtils
import com.terry.jetpackmvvm.sample.widget.AnimDialog
import com.terry.jetpackmvvm.sample.widget.SpinnerView
import com.terry.jetpackmvvm.sample.widget.VerificationCodeView
import java.io.File
import java.util.concurrent.TimeUnit


class UtilTestFragment : BaseFragment<FragmentUtilTestBinding>(FragmentUtilTestBinding::inflate) {

    private lateinit var viewModel: UtilTestViewModel
    private lateinit var rxPermissions: RxPermissions

    override fun init() {
        rxPermissions = RxPermissions(this)
        viewModel = ViewModelProvider(this).get(UtilTestViewModel::class.java)

        RxUtils.throttleFirst(viewLifecycleOwner, binding.btnScreenshot, {
            showScreenShotDialog(parentFragmentManager)
        })

        RxUtils.throttleFirst(viewLifecycleOwner, binding.btnAnimDialogZoom, {
            showAnimDialog(requireContext(), AnimDialog.AnimInType.ZOOM)
        })

        RxUtils.throttleFirst(viewLifecycleOwner, binding.btnAnimDialogTop, {
            showAnimDialog(requireContext(), AnimDialog.AnimInType.TOP)
        })

        RxUtils.throttleFirst(viewLifecycleOwner, binding.btnAnimDialogBottom, {
            showAnimDialog(requireContext(), AnimDialog.AnimInType.BOTTOM)
        })

        RxUtils.throttleFirst(viewLifecycleOwner, binding.btnVerificationCodeDialog, {
            showVerificationViewDialog(requireContext())
        })

        binding.btnViewToImage.clicks()
            .throttleFirst(3, TimeUnit.SECONDS)
            .compose(rxPermissions.ensureEach(Manifest.permission.WRITE_EXTERNAL_STORAGE))
            .autoDispose(
                AndroidLifecycleScopeProvider.from(
                    viewLifecycleOwner,
                    Lifecycle.Event.ON_DESTROY
                )
            )
            .subscribe(
                {
                    saveView()
                },
                { e: Throwable ->
                    Toast.makeText(context, e.localizedMessage, Toast.LENGTH_SHORT).show()
                })

        RxUtils.throttleFirst(viewLifecycleOwner, binding.btnShareText, {
            shareText()
        })

        RxUtils.throttleFirst(viewLifecycleOwner, binding.btnShareImage, {
            shareImage()
        })

        RxUtils.throttleFirst(viewLifecycleOwner, binding.btnShareContent, {
            shareContent()
        })

        RxUtils.throttleFirst(viewLifecycleOwner, binding.btnListDialog, {
            ScrollingFragment().show(childFragmentManager, "")
        })

        initSpinner()
    }

    private fun showScreenShotDialog(fm: FragmentManager) {
        ScreenshotDialog().show(fm, "")
    }

    private fun showAnimDialog(context: Context, type: AnimDialog.AnimInType) {
        val dialog = AnimDialog(context)
        val binding = DialogLoadingBinding.inflate(LayoutInflater.from(context))
        dialog.contentView(binding.root)
            .animType(type).show()
    }

    private fun showVerificationViewDialog(context: Context) {
        val dialog = AnimDialog(context)
        val binding = DialogVerificationCodeBinding.inflate(LayoutInflater.from(context))
        binding.verificationCodeView.onCodeFinishListener =
            object : VerificationCodeView.OnCodeFinishListener {
                override fun onTextChange(view: View, content: String) {
                }

                override fun onComplete(view: View, content: String) {
                    Toast.makeText(context, content, Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
            }
        dialog.contentView(binding.root).cancelable(false).show()
    }

    private fun saveView() {
        val shareView = LayoutInflater.from(context).inflate(
            R.layout.fragment_constraint_layout_sample,
            null,
            false
        )

        /// Get screen size
        val metric = ScreenUtils.getScreenDisplay()
        val mScreenWidth = metric[0] // 屏幕宽度（像素）
        val mScreenHeight = metric[1] // 屏幕高度（像素）

        // Layout view
        shareView.measure(
            View.MeasureSpec.makeMeasureSpec(mScreenWidth, View.MeasureSpec.EXACTLY),
            View.MeasureSpec.makeMeasureSpec(mScreenHeight, View.MeasureSpec.EXACTLY)
        )
        shareView.layout(0, 0, shareView.measuredWidth, shareView.measuredHeight)

//        val bitmap = UIUtils.loadBitmapFromView(shareView)
//        context?.let { FileUtils.saveImageToPicture(it, bitmap) }

        val bitmap2 = UIUtils.loadBitmapFromView(shareView)
        context?.let { FileUtils.saveImageToPicture(it, bitmap2) }
    }

    private fun shareText() {
        context?.let {
            ShareUtils.shareText(it, null, "test share content")
        }
    }

    private fun shareImage() {
        context?.let {
            val bitmap = ImageUtils.getBitmap(it, R.mipmap.ic_launcher_round)
            val filename = System.currentTimeMillis().toString() + ".jpg"
            FileUtils.saveImageToAppDir(it, bitmap, filename)

            val appDir = File(it.filesDir, "images")
            val file = File(appDir, filename)
            ShareUtils.shareImage(it, file)
        }
    }

    private fun shareContent() {
        context?.let {
            val bitmap = ImageUtils.getBitmap(it, R.mipmap.ic_launcher_round)
            val filename = System.currentTimeMillis().toString() + ".jpg"
            FileUtils.saveImageToAppDir(it, bitmap, filename)

            val appDir = File(it.filesDir, "images")
            val file = File(appDir, filename)
            ShareUtils.shareContent(it, "test content", file)
        }
    }

    private fun initSpinner() {
        val list = mutableListOf<String>()
        for (i in 1..20) {
            list.add(i.toString())
        }

        binding.spinner.setTitle("Custom spinner")
        binding.spinner.setList(list)
        binding.spinner.onItemSelectedListener = object : SpinnerView.OnItemSelectedListener {
            override fun onItemSelected(
                adapter: BaseQuickAdapter<*, *>,
                view: View,
                position: Int
            ) {
                Toast.makeText(context, "Selected: ${adapter.data[position]}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        fun newInstance() = UtilTestFragment()
    }
}
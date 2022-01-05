package com.terry.jetpackmvvm.sample.ui.utiltest

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.terry.jetpackmvvm.sample.databinding.DialogLoadingBinding
import com.terry.jetpackmvvm.sample.databinding.DialogVerificationCodeBinding
import com.terry.jetpackmvvm.sample.databinding.FragmentUtilTestBinding
import com.terry.jetpackmvvm.sample.ui.base.BaseFragment
import com.terry.jetpackmvvm.sample.ui.utiltest.dialog.ScreenshotDialog
import com.terry.jetpackmvvm.sample.util.RxUtils
import com.terry.jetpackmvvm.sample.widget.AnimDialog
import com.terry.jetpackmvvm.sample.widget.VerificationCodeView

class UtilTestFragment : BaseFragment<FragmentUtilTestBinding>(FragmentUtilTestBinding::inflate) {
    private lateinit var viewModel: UtilTestViewModel

    override fun init() {
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

    companion object {
        fun newInstance() = UtilTestFragment()
    }
}
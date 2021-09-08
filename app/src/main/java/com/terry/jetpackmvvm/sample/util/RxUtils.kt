package com.terry.jetpackmvvm.sample.util

import android.view.View
import android.widget.EditText
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import autodispose2.androidx.lifecycle.AndroidLifecycleScopeProvider
import autodispose2.autoDispose
import com.jakewharton.rxbinding4.view.clicks
import com.jakewharton.rxbinding4.widget.textChanges
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit


/**
 * Ref:
 * RxBinding: https://github.com/JakeWharton/RxBinding
 * AutoDispose: https://github.com/uber/AutoDispose
 */
object RxUtils {

    fun throttleFirst(owner: LifecycleOwner, view: View, consumer: Consumer<Any?>?) {
        view.clicks()
            .throttleFirst(3, TimeUnit.SECONDS)
            .autoDispose(AndroidLifecycleScopeProvider.from(owner, Lifecycle.Event.ON_DESTROY))
            .subscribe(consumer)
    }

    fun textChanges(owner: LifecycleOwner, view: EditText, consumer: Consumer<String?>?) {
        view.textChanges()
            .map { charSequence -> charSequence.toString() }
            .autoDispose(AndroidLifecycleScopeProvider.from(owner, Lifecycle.Event.ON_DESTROY))
            .subscribe(consumer)
    }

    fun countDown(owner: LifecycleOwner, second: Long, timeListener: ((timeLeft: Long) -> Unit)) {
        Observable.interval(0, 1, TimeUnit.SECONDS)
            .take(second)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .autoDispose(AndroidLifecycleScopeProvider.from(owner, Lifecycle.Event.ON_DESTROY))
            .subscribe {
                val timeLeft = second - it
                timeListener.invoke(timeLeft)
            }
    }
}
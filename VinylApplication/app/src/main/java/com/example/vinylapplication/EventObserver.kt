package com.example.vinylapplication

import androidx.lifecycle.Observer

//This was taken from the web: https://stackoverflow.com/questions/58342060/why-is-onchanged-being-called-when-i-navigate-back-to-a-fragment

class EventObserver<T>(private val onEventUnhandledContent: (T) -> Unit) : Observer<Event<T>> {
    override fun onChanged(event: Event<T>?) {
        event?.getContentIfNotHandled()?.let { value ->
            onEventUnhandledContent(value)
        }
    }
}
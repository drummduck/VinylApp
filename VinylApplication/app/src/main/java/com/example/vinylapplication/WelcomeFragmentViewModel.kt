package com.example.vinylapplication
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WelcomeFragmentViewModel : ViewModel() {
    val navigationStatus: MutableLiveData<Event<NavigateStatus>> by lazy{ MutableLiveData<Event<NavigateStatus>>() }

    fun goToSignIn(){
        navigationStatus.postValue(Event(NavigateStatus.SIGN_IN));
    }

    fun goToRegister(){
        navigationStatus.postValue(Event(NavigateStatus.REGISTER));
    }
}
package com.example.vinylapplication

import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController

class SignInFragmentViewModel : ViewModel() {
    val navigationStatus: MutableLiveData<Event<NavigateStatus>> by lazy{ MutableLiveData<Event<NavigateStatus>>() }
    val userName = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    init {
        userName.value = "";
        password.value = "";
    }

    fun goToMainScreen(){
        if(userName.value?.length!! > 3) {
            val regex = "^(?=.*?[A-Za-z])(?=.*?[0-9])(?=.*?[#?!@\$%^&*-]).{8}\$".toRegex()
            if(regex.matches(password.value!!)) {
                navigationStatus.postValue(Event(NavigateStatus.SIGN_IN))
            } else{
                navigationStatus.postValue(Event(NavigateStatus.FAIL_PASSWORD))
            }
        } else{
            navigationStatus.postValue(Event(NavigateStatus.FAIL_USERNAME))
        }
    }

    fun goToRegister(){
        navigationStatus.postValue(Event(NavigateStatus.REGISTER))
    }

    fun setPassword(password: String){
        this.password.value = password;
    }

    fun setUserName(userName: String){
        this.userName.value = userName;
    }
}
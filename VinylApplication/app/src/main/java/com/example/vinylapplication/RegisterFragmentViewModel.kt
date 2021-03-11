package com.example.vinylapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterFragmentViewModel : ViewModel() {
    val navigationStatus: MutableLiveData<Event<NavigateStatus>> by lazy{ MutableLiveData<Event<NavigateStatus>>() }
    val userName = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val name = MutableLiveData<String>()

    init {
        userName.value = "";
        password.value = "";
        name.value = ""
    }

    fun goToMainScreen(){
        if(name.value?.trim()?.length!! > 0) {
            if (userName.value?.length!! > 3) {
                val regex = "^(?=.*?[A-Za-z])(?=.*?[0-9])(?=.*?[#?!@\$%^&*-]).{8}\$".toRegex()
                if (regex.matches(password.value!!)) {
                    navigationStatus.postValue(Event(NavigateStatus.REGISTER))
                } else {
                    navigationStatus.postValue(Event(NavigateStatus.FAIL_PASSWORD))
                }
            } else {
                navigationStatus.postValue(Event(NavigateStatus.FAIL_USERNAME))
            }
        }  else{
            navigationStatus.postValue(Event(NavigateStatus.FAIL_NAME))
        }
    }

    fun goToSignIn(){
        navigationStatus.postValue(Event(NavigateStatus.SIGN_IN))
    }

    fun setUserName(userName: String){
        this.userName.value = userName;
    }

    fun setPassword(password: String){
        this.password.value = password;
    }
}
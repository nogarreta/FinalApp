package fr.isep.noelle.covoiturage.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import fr.isep.noelle.covoiturage.data.LoginRepository
import fr.isep.noelle.covoiturage.data.Result

import fr.isep.noelle.covoiturage.R


class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun login(username: String, password: String) {
        // can be launched in a separate asynchronous job
        val result = loginRepository.login(username, password)

        if (result is Result.Success) {
            _loginResult.value =
                LoginResult(success = LoggedInUserView(displayName = result.data.displayName))
        } else {
            _loginResult.value = LoginResult(error = R.string.login_failed)
        }
    }

    fun loginDataChanged(username: String, password: String) {
        val isUserNameValid = isUserNameValid(username)
        val isPasswordValid = isPasswordValid(password)

        val usernameError = if (!isUserNameValid) R.string.invalid_username else null
        val passwordError = if (!isPasswordValid) R.string.invalid_password else null

        _loginForm.value = LoginFormState(
            usernameError = usernameError,
            passwordError = passwordError,
            isDataValid = isUserNameValid && isPasswordValid
        )
    }

    private fun isUserNameValid(username: String): Boolean {
        return username.contains('@') && Patterns.EMAIL_ADDRESS.matcher(username).matches()
    }

    private fun isPasswordValid(password: String): Boolean {
        val passwordRegex = "(?=.*[0-9])(?=.*[A-Z]).{6,}"
        return password.matches(passwordRegex.toRegex())
    }
}
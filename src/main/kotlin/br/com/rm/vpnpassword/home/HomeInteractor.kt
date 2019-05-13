package br.com.rm.vpnpassword.home

import br.com.rm.vpnpassword.helpers.OtpHelper
import br.com.rm.vpnpassword.helpers.PreferencesHelper
import br.com.rm.vpnpassword.utils.EmptyPassword
import br.com.rm.vpnpassword.utils.EmptySecret
import br.com.rm.vpnpassword.utils.ValidateException
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection

class HomeInteractor(
    private val otpHelper: OtpHelper,
    private val preferences: PreferencesHelper
) : HomeContract.Interactor {


    override fun loginDataIsValid(secret: String, password: String): Boolean {
        val errorList = mutableListOf<Throwable>()

        checkSecretIsValid(secret, errorList)

        checkPasswordIsValid(password, errorList)

        if (errorList.isEmpty()) {
            return true
        } else {
            throw ValidateException(errorList)
        }
    }

    private fun checkPasswordIsValid(password: String, errorList: MutableList<Throwable>) {
        if (password.isEmpty()) {
            errorList.add(EmptyPassword)
        }
    }

    private fun checkSecretIsValid(secret: String, errorList: MutableList<Throwable>) {
        if (secret.isEmpty()) {
            errorList.add(EmptySecret)
        }
    }

    override fun generateVpnPassword(secret: String, password: String): String {
        val passwordBuilder: StringBuilder = StringBuilder(password)
        val generateOtpCode = otpHelper.generateOtpCode(secret)
        return passwordBuilder.append(generateOtpCode).toString()
    }

    override fun saveCurrentUserData(secret: String, password: String) {
        preferences.saveSecurityKey(secret)
        preferences.savePassword(password)
    }

    override fun loadCurrentUserData(): Pair<String?, String> {
        val secret = preferences.loadSecurityKey()
        val password = preferences.loadPassword()
        return Pair(secret, password)
    }

    override fun sendToClipboard(it: String?) {
        val clipboard = Toolkit.getDefaultToolkit().systemClipboard
        val selection = StringSelection(it)
        clipboard.setContents(selection, null)
    }
}
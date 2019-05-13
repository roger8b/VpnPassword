package br.com.rm.vpnpassword.home

import br.com.rm.vpnpassword.helpers.OtpHelper
import br.com.rm.vpnpassword.helpers.PreferencesHelper

class HomePresenter(private val view: HomeContract.View) : HomeContract.Presenter {

    private var interactor: HomeContract.Interactor? = null

    init {
        interactor = HomeInteractor(OtpHelper(), PreferencesHelper())
        loadUserData()
    }

    private fun loadUserData() {
        val userData = interactor?.loadCurrentUserData()
        val userSecurityKey = userData?.first
        val userPassword = userData?.second

        if (!userSecurityKey.isNullOrEmpty() && !userPassword.isNullOrEmpty()) {
            view.showLoadedUserData(userSecurityKey, userPassword)
        }
    }

    override fun getVpnPassword(secret: String, password: String): String? {
        val loginDataIsValid = interactor?.loginDataIsValid(secret, password)
        var vpnPassword = ""


        if (loginDataIsValid != null && loginDataIsValid) {
            val generateVpnPassword = interactor?.generateVpnPassword(secret, password)
            if (generateVpnPassword != null) {
                vpnPassword = generateVpnPassword
                interactor?.saveCurrentUserData(secret, password)
            }
        }
        return vpnPassword
    }

    override fun copyVpnPasswordToClipBoard(value: String?) {
        interactor?.sendToClipboard(value)
        view.showMessagePasswordCopiedToClipboard()
    }
}



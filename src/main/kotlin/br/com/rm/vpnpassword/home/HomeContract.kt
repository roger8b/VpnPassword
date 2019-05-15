package br.com.rm.vpnpassword.home

interface HomeContract {
    interface View {
        fun showMessagePasswordCopiedToClipboard()
        fun showLoadedUserData(userSecurityKey: String, userPassword: String)

    }

    interface Presenter {
        fun copyVpnPasswordToClipBoard(value: String?)
        fun getVpnPassword(secret: String, password: String): String?
        fun openGithub()

    }

    interface Interactor {
        fun loginDataIsValid(secret: String, password: String): Boolean
        fun sendToClipboard(it: String?)
        fun generateVpnPassword(secret: String, password: String): String
        fun loadCurrentUserData() : Pair<String?, String>
        fun saveCurrentUserData(secret: String, password: String)

    }
}
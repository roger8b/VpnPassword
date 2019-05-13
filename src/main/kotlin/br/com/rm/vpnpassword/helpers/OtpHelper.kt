package br.com.rm.vpnpassword.helpers

import com.marcelkliemannel.kotlinonetimepassword.GoogleAuthenticator


class OtpHelper {

    fun generateOtpCode(secret: String): String {
        val auth = GoogleAuthenticator(secret)
        return auth.generate()
    }
}

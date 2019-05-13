package br.com.rm.vpnpassword.home

import tornadofx.Stylesheet
import tornadofx.box
import tornadofx.cssclass
import tornadofx.px

class HomeStyles : Stylesheet() {
    companion object {
        val homeScreen by cssclass()
    }

    init {
        homeScreen {
            padding = box(20.px)
            vgap = 7.px
            hgap = 10.px
        }
    }
}
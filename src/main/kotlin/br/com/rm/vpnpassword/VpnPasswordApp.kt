package br.com.rm.vpnpassword

import br.com.rm.vpnpassword.home.HomeStyles
import br.com.rm.vpnpassword.home.HomeView
import javafx.application.Application
import javafx.stage.Stage
import sun.tools.jar.Main
import tornadofx.App
import tornadofx.launch

class VpnPasswordApp : App(HomeView::class, HomeStyles::class)

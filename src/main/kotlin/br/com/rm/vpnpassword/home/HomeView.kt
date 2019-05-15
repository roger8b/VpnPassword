package br.com.rm.vpnpassword.home

import br.com.rm.vpnpassword.components.Toast
import br.com.rm.vpnpassword.home.HomeStyles.Companion.homeScreen
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import javafx.geometry.Pos
import javafx.scene.Parent
import javafx.scene.control.Hyperlink
import tornadofx.*

class HomeView : View("Gerador de senha para VPN"), HomeContract.View {

    private val model = object : ViewModel() {
        val securityKey = bind { SimpleStringProperty() }
        val password = bind { SimpleStringProperty() }
        val vpnPassword = bind { SimpleStringProperty() }

    }

    private var presenter: HomeContract.Presenter? = null

    init {
        presenter = HomePresenter(this)
    }

    override fun showLoadedUserData(userSecurityKey: String, userPassword: String) {
        model.securityKey.value = userSecurityKey
        model.password.value = userPassword
    }

    override val root: Parent = gridpane {
        row {
            useMaxWidth = true
            form {
                addClass(homeScreen)
                fieldset(title, labelPosition = Orientation.VERTICAL) {
                    field("Chave de Segurança") {
                        passwordfield(model.securityKey) {
                            bind(model.securityKey)
                            required()
                            whenDocked { requestFocus() }
                        }
                    }

                    field("Senha") {
                        passwordfield(model.password) {
                            bind(model.password)
                            required()
                        }
                    }

                    field("Senha da VPN") {
                        textfield {
                            isEditable = false
                            bind(model.vpnPassword)
                            textProperty().addListener { _, _, _ ->
                                presenter?.copyVpnPasswordToClipBoard(model.vpnPassword.value)
                            }
                        }
                    }

                    button("Gerar Senha") {
                        isDefaultButton = true
                        setOnAction {
                            model.commit {
                                model.vpnPassword.value = presenter?.getVpnPassword(
                                        model.securityKey.value,
                                        model.password.value
                                )
                            }
                        }
                    }
                }

            }

        }
        row {
            hyperlink("GitHub",Hyperlink("http://google.com").clip) {


                action {
                    presenter?.openGithub()
                }
                useMaxWidth = true
                alignment = Pos.BOTTOM_RIGHT
                gridpaneConstraints {
                }

            }

        }

    }

    override fun showMessagePasswordCopiedToClipboard() {
        val toastMsg = "Senha copiada para\n área de transferencia"
        val toastMsgTime = 1500
        val fadeInTime = 500
        val fadeOutTime = 500
        Toast.makeText(this.primaryStage, toastMsg, toastMsgTime, fadeInTime, fadeOutTime)
    }

}

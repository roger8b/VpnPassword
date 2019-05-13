package br.com.rm.vpnpassword.utils


sealed class BaseException(
    override var message: String
) : Throwable()

class ValidateException(errorList: List<Throwable>?) : Throwable()

object InvalidPassword: BaseException("A senha precisa ser bonita")

object WrongPassword: BaseException("A senha esta incorreta")

object EmptyPassword: BaseException("A senha esta incorreta")

object InvalidSecret: BaseException("O seu secret é invalido")

object EmptySecret: BaseException("O seu secret esta vazio")

object LoadSecretError : BaseException("Erro ao recuperar os dados da sua chave")

object LoadPasswordError : BaseException("Erro ao recuperar sua senha")




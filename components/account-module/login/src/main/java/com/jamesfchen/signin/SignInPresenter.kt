package com.jamesfchen.signin

import androidx.annotation.MainThread
import com.jamesfchen.common.util.Util
import com.jamesfchen.modle.*
import com.jamesfchen.source.SignInDataSource
import com.jamesfchen.source.mock.MockSignInDataSource
import com.jamesfchen.source.mock.UncertaintyConditions
import com.jamesfchen.source.remote.rest.signin.RemoteSignInDataSource
import com.orhanobut.logger.Logger
import io.reactivex.Single
import java.util.concurrent.TimeUnit

/**
 * Copyright ® $ 2017
 * All right reserved.
 *
 * @author: hawks.jamesf
 * @since: Nov/11/2018  Sun
 */
class SignInPresenter private constructor(
        private var dataSource: SignInDataSource
) : SignInContract.Presenter() {
    private val TAG = "Client"
    private val communicationTimeoutSeconds: Long = 10

    companion object {
        var INSTANCE = SignInPresenter(if (false) {
            MockSignInDataSource(Util.getApp(), UncertaintyConditions.UncertaintyParams(
                    0f, 0f, 1500L, 500L
            ))
        } else {
            RemoteSignInDataSource()
        })
    }

    init {
        /**
         *refresh token，token
         * token存在三种状态：NONE、UNAUTHORIZED(token到期)、OTHERS
         * refresh token也存在三种状态：OK，REFRESH_FAILURE，OTHERS
         *
         * 1.自动登入：判断token是否存在
         * 2.自动刷新token，如果token为UNAUTHORIZED，也就是token到期，则会自动调用refresh token接口刷新token，
         * 如果刷新失败(REFRESH_FAILURE),自动退出。
         *
         */
    }

    @MainThread
    override fun sendCode(sendCodeReq: SendCodeReqBody): Single<SendCodeRespBody> {
        Logger.t(TAG).d("send code--->req:$sendCodeReq")
        stateData.apply {
            signinginDisposable?.dispose()
            signingupDisposable?.dispose()
        }
        return dataSource.sendCode(sendCodeReq)
                .onErrorResumeNext {
                    Single.error(it as? ClientException
                            ?: ClientException.Unknown)
                }
    }

    @MainThread
    override fun signUp(signUpReq: SignUpReqBody): Single<Profile> {
        Logger.t(TAG).d("sign up--->req:$signUpReq")
        stateData.apply {
            signinginDisposable?.dispose()
            signingupDisposable?.dispose()
        }
        return dataSource.signUp(signUpReq)
                .timeout(communicationTimeoutSeconds, TimeUnit.SECONDS)
                .onErrorResumeNext {
                    Single.error(it as? ClientException
                            ?: ClientException.Unknown)
                }


    }

    @MainThread
    override fun signIn(signInReq: SignInReqBody): Single<Profile> {
        Logger.t(TAG).d("sign in--->req:$signInReq")
        stateData.apply {
            signinginDisposable?.dispose()
            signingupDisposable?.dispose()
        }
        return dataSource.signIn(signInReq)
                //.timeout(communicationTimeoutSeconds, TimeUnit.SECONDS)
                .onErrorResumeNext {
                    Single.error(it as? ClientException
                            ?: ClientException.Unknown)
                }


    }

    @MainThread
    override fun signOut() {
        Logger.t(TAG).d("sign out")
        stateData.apply {
            signinginDisposable?.dispose()
            signingupDisposable?.dispose()
        }
        stateData = StateData()
        dataSource.signOut()

        //todo:profile 处理
    }
}
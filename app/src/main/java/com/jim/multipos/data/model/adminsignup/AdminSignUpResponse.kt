package com.jim.multipos.data.model.adminsignup

import com.jim.multipos.utils.Status


class AdminSignUpResponse(val status: Status, val data: AdminSignUpModel?, val error: Throwable?) {

    companion object {
        fun loading(): AdminSignUpResponse {
            return AdminSignUpResponse(Status.LOADING, null, null)
        }

        fun success(data: AdminSignUpModel): AdminSignUpResponse {
            return AdminSignUpResponse(Status.SUCCESS, data, null)
        }

        fun error(error: Throwable): AdminSignUpResponse {
            return AdminSignUpResponse(Status.ERROR, null, error)
        }
    }
}
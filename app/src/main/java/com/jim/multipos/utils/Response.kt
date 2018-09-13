package com.jim.multipos.utils

class Response<T>(val status: Status, val data: T?, val error: Throwable?) {

    companion object {

        fun <V>loading(): Response<V> {
            return Response<V>(Status.LOADING, null, null)
        }

        fun <V>success(data: V): Response<V> {
            return Response<V>(Status.SUCCESS, data, null)
        }

        fun <V>error(error: Throwable): Response<V> {
            return Response<V>(Status.ERROR, null, error)
        }
    }
}

package com.apiCalling

data class CommentApiStatePost<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        // In case of Success,set status as
        // Success and data as the response
        fun <T> success(data: T?): CommentApiStatePost<T> {
            return CommentApiStatePost(Status.SUCCESS, data, null)
        }
        // In case of failure ,set state to Error ,
        // add the error message,set data to null
        fun <T> error(msg: String): CommentApiStatePost<T> {
            return CommentApiStatePost(Status.ERROR, null, msg)
        }
        // When the call is loading set the state
        // as Loading and rest as null
        fun <T> loading(): CommentApiStatePost<T> {
            return CommentApiStatePost(Status.LOADING, null, null)
        }
    }
    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }
}



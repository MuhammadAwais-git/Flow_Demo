package com.FlowsDemo.apiusingFlow

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class CommentsRepository(private val apiInterface: ApiInterface) {
    suspend fun getComment(id: Int): Flow<CommentApiState<DataModel>> {
        return flow {
            // get the comment Data from the api
            val comment=apiInterface.getComents(id)

            emit(CommentApiState.success(comment))
        }.flowOn(Dispatchers.IO)
    }
}

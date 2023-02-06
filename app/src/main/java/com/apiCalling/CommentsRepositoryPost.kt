package com.apiCalling

import com.example.navigatorapp.Weather.weatherModel.DataWeatherModelw
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class CommentsRepositoryPost(private val apiInterfacepost: ApiInterfacepost) {
    suspend fun getComment(): Flow<CommentApiStatePost<DataWeatherModelw>> {
        return flow {
            // get the comment Data from the api
             val appid="3626c1b87e7e6d9a7ceb6b30fadd28c2"
            val post=apiInterfacepost.getWeatherData("33.5465","74.345",appid)

            emit(CommentApiStatePost.success(post))
        }.flowOn(Dispatchers.IO)
    }
}

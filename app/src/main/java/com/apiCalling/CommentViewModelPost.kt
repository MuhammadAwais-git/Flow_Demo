package com.apiCalling

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.navigatorapp.Weather.weatherModel.DataWeatherModelw
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch


class CommentViewModelPost : ViewModel() {

    private val repository = CommentsRepositoryPost(RetrofitInstancePost.api)

    val commentState = MutableStateFlow(
        CommentApiStatePost(
            CommentApiStatePost.Status.LOADING, DataWeatherModelw(null),
            ""))

    init {

        getWeatherData()
    }

    fun getWeatherData() {

        commentState.value = CommentApiStatePost.loading()

        viewModelScope.launch {

            repository.getComment().catch {
                commentState.value = CommentApiStatePost.error(it.message.toString())
                Log.d("TAG", "getNewComment:error ${it.message.toString()} ")
            }
                .collect {dataList->
                    commentState.value = CommentApiStatePost.success(dataList.data)
                    Log.d("TAG", "getNewComment:success ${dataList.data} ")

                }
        }

    }
}
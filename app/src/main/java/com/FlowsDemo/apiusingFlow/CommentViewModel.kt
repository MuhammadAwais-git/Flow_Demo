package com.FlowsDemo.apiusingFlow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch


class CommentViewModel : ViewModel() {

    private val repository = CommentsRepository(RetrofitInstance.api)

    val commentState = MutableStateFlow(CommentApiState(CommentApiState.Status.LOADING, DataModel(), ""))

    init {

        getNewComment(1)
    }

     fun getNewComment(id: Int) {

        commentState.value = CommentApiState.loading()

        viewModelScope.launch {

            repository.getComment(id).catch {
                commentState.value = CommentApiState.error(it.message.toString())
            }
                .collect {
                    commentState.value = CommentApiState.success(it.data)
                }
        }

    }
}
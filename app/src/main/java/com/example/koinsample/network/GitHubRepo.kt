package com.example.koinsample.network

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.koinsample.utils.extensions.isValid
import com.example.koinsample.reposearch.models.GitHubSearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GitHubRepo(private val gitHubApi: GitHubApi) {
    fun getRepositories(liveData : MutableLiveData<Result<GitHubSearchResponse>>, query : String){
        liveData.value = Result.Loading(true)
        gitHubApi.getRepos(query)
            .enqueue(object : Callback<GitHubSearchResponse> {
                override fun onFailure(call: Call<GitHubSearchResponse>, t: Throwable) {
                    Log.e("onFailure", t.message.toString())
                    if (t.message.isValid()){
                        liveData.value = Result.Error.RecoverableError(Exception(t.message.toString()))
                    }else{
                        liveData.value = Result.Error.NonRecoverableError(Exception("Un-traceable"))
                    }
                }

                override fun onResponse(
                    call: Call<GitHubSearchResponse>,
                    response: Response<GitHubSearchResponse>
                ) {
                    if (response.isSuccessful) {
                        liveData.value = Result.Success(response.body()!!)
                    } else {
                        if (response.message().isValid()) {
                            liveData.value = Result.Error.RecoverableError(Exception(response.message()))
                        }else{
                            liveData.value = Result.Error.NonRecoverableError(Exception("Un-traceable"))
                        }
                    }
                }
            })
    }


}

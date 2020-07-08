package com.example.koinsample.network

import com.example.koinsample.reposearch.models.GitHubSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubApi {

    @GET("search/repositories")
    fun getRepos(@Query("q") name : String) : Call<GitHubSearchResponse>

}
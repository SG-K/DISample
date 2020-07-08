package com.example.koinsample.reposearch.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.koinsample.reposearch.models.GitHubSearchResponse
import com.example.koinsample.network.GitHubRepo
import com.example.koinsample.network.Result

class GitHubSearchViewmodel(private val gitHubRepo: GitHubRepo) : ViewModel() {


    private val githubRepoLiveData = MutableLiveData<Result<GitHubSearchResponse>>()

    fun observeData() : MutableLiveData<Result<GitHubSearchResponse>>{
        return githubRepoLiveData
    }

    fun getReposFromGitHub(data : String){
        gitHubRepo.getRepositories(liveData = githubRepoLiveData, query = data)
    }

}
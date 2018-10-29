package com.muzadev.footballapp.presenter

import com.google.gson.Gson
import com.muzadev.footballapp.api.Api
import com.muzadev.footballapp.api.ApiRepo
import com.muzadev.footballapp.model.Match
import com.muzadev.footballapp.model.MatchResponse
import com.muzadev.footballapp.presenter.interfaces.MatchView
import com.muzadev.footballapp.util.TestContextProvider
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

/**
 * Created by zulfakar on 29/10/18.
 * For educational purposes
 */
class MatchPresenterTest {
    @Mock
    private
    lateinit var view: MatchView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepo

    private lateinit var presenter: MatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        presenter = MatchPresenter(view, apiRepository, gson, TestContextProvider())
    }


    @Test
    fun getNextMatch() {
        val matches = mutableListOf<Match>()
        val response = MatchResponse(matches)
        val leagueId = "4328"

        `when`(gson.fromJson(apiRepository
                .doRequest(Api.getNextMatches(leagueId))
                , MatchResponse::class.java)).thenReturn(response)

        presenter.getNextMatch(leagueId)

        verify(view).showLoading()
        verify(view).showNextMatch(matches)
        verify(view).hideLoading()
    }
}
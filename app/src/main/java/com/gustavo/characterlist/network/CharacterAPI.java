package com.gustavo.characterlist.network;

import com.gustavo.characterlist.model.CharacterResponse;
import com.gustavo.characterlist.model.Episode;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface CharacterAPI {

    @GET("api/character")
    Call<CharacterResponse> listCharacter();

    @GET
    Call<Episode> getEpisode(@Url String episodeUrl);
}
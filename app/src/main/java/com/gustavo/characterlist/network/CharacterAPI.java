package com.gustavo.characterlist.network;

import com.gustavo.characterlist.model.CharacterResponse;
import com.gustavo.characterlist.model.Episodes;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface CharacterAPI {

    @GET("api/character")
    Call<CharacterResponse> listCharacter();

    @GET
    Call<Episodes> getEpisode(@Url String episodeUrl);
}
package com.gustavo.characterlist.repository;

import androidx.annotation.NonNull;

import com.gustavo.characterlist.model.CharacterResponse;
import com.gustavo.characterlist.model.Episodes;
import com.gustavo.characterlist.network.CharacterAPI;
import com.gustavo.characterlist.network.RetrofitClient;
import com.gustavo.characterlist.model.Characters;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharacterRepository {

    private final CharacterAPI api = RetrofitClient.getInstance().create(CharacterAPI.class);

    public void listarPersonagens(RepositoryCallback<List<Characters>> callback) {
        Call<CharacterResponse> call = api.listCharacter();
        call.enqueue(new Callback<CharacterResponse>() {
            @Override
            public void onResponse(@NonNull Call<CharacterResponse> call, @NonNull Response<CharacterResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body().getResults());
                } else {
                    callback.onError("Erro na resposta: " + response.code());
                }            }

            @Override
            public void onFailure(@NonNull Call<CharacterResponse> call, @NonNull Throwable throwable) {
                callback.onError("Falha de rede: " + throwable.getMessage());
            }
        });
    }

    public void getEpisode(String episodeUrl, RepositoryCallback<String> callback) {
        Call<Episodes> call = api.getEpisode(episodeUrl);
        call.enqueue(new Callback<Episodes>() {
            @Override
            public void onResponse(@NonNull Call<Episodes> call, @NonNull Response<Episodes> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body().getName());
                } else {
                    callback.onError("Erro na resposta: " + response.code());
                }            }

            @Override
            public void onFailure(@NonNull Call<Episodes> call, @NonNull Throwable throwable) {
                callback.onError("Falha de rede: " + throwable.getMessage());
            }
        });
    }
}

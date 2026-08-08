package com.gustavo.characterlist.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gustavo.characterlist.model.Character;
import com.gustavo.characterlist.repository.CharacterRepository;
import com.gustavo.characterlist.repository.RepositoryCallback;

import java.util.List;

public class CharacterViewModel extends ViewModel {

    private final MutableLiveData<List<Character>> personagens = new MutableLiveData<>();
    private final  MutableLiveData<String> erro = new MutableLiveData<>();

    private CharacterRepository repository;

    public void loadPersonagens() {
        repository.listarPersonagens(new RepositoryCallback<List<Character>>() {
            @Override
            public void onSuccess(List<Character> data) {
                personagens.postValue(data);
            }

            @Override
            public void onError(String message) {
                erro.postValue(message);
            }
        });
    }

    public LiveData<List<Character>> getPersonagens() {
        return personagens;
    }

    public LiveData<String> getErro() {
        return erro;
    }
}

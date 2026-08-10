package com.gustavo.characterlist.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gustavo.characterlist.model.Characters;
import com.gustavo.characterlist.repository.CharacterRepository;
import com.gustavo.characterlist.repository.RepositoryCallback;

import java.util.List;

public class CharacterViewModel extends ViewModel {

    private final MutableLiveData<List<Characters>> personagens = new MutableLiveData<>();
    private final  MutableLiveData<String> erro = new MutableLiveData<>();

    private final CharacterRepository repository = new CharacterRepository();

    public void loadPersonagens() {
        repository.listarPersonagens(new RepositoryCallback<List<Characters>>() {
            @Override
            public void onSuccess(List<Characters> data) {
                personagens.postValue(data);
            }

            @Override
            public void onError(String message) {
                erro.postValue(message);
            }
        });
    }

    public LiveData<List<Characters>> getPersonagens() {
        return personagens;
    }

    public LiveData<String> getErro() {
        return erro;
    }
}

package com.gustavo.characterlist.repository;

public interface RepositoryCallback<T> {
    void onSuccess(T data);
    void onError(String message);
}

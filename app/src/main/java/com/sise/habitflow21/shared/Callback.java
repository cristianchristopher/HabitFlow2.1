package com.sise.habitflow21.shared;

public interface Callback<T> {
    void onSuccess(T result);
    void onFailure();

}

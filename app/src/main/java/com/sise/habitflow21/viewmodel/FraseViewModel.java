package com.sise.habitflow21.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sise.habitflow21.entities.FraseMotivacional;
import com.sise.habitflow21.repositories.FraseRepository;
import com.sise.habitflow21.shared.Callback;

import java.util.Collections;
import java.util.List;


public class FraseViewModel extends ViewModel {

    private final MutableLiveData<Boolean> insertarFraseStatus;
    private final FraseRepository fraseRepository;

    public FraseViewModel() {
        insertarFraseStatus = new MutableLiveData<>();
        fraseRepository = new FraseRepository();
    }

    public LiveData<Boolean> getInsertarFraseStatus() {
        return insertarFraseStatus;
    }

    public void insertarFrase(FraseMotivacional frase) {
        fraseRepository.insertarFrase(frase, new Callback<FraseMotivacional>() {
            @Override
            public void onSuccess(FraseMotivacional result) {
                insertarFraseStatus.setValue(true);
            }

            @Override
            public void onFailure() {
                insertarFraseStatus.setValue(false);
            }
        });
    }

    private final MutableLiveData<List<FraseMotivacional>> listaFrases = new MutableLiveData<>();

    public LiveData<List<FraseMotivacional>> getListaFrases() {
        return listaFrases;
    }

    public void obtenerFrases() {
        new Thread(() -> {
            fraseRepository.obtenerFrases(new Callback<List<FraseMotivacional>>() {
                @Override
                public void onSuccess(List<FraseMotivacional> result) {
                    listaFrases.postValue(result);
                }

                @Override
                public void onFailure() {
                    listaFrases.postValue(Collections.emptyList());
                }
            });
        }).start();
    }



}

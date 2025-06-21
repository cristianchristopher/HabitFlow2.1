package com.sise.habitflow21.repositories;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sise.habitflow21.entities.FraseMotivacional;
import com.sise.habitflow21.shared.BaseResponse;
import com.sise.habitflow21.shared.Callback;
import com.sise.habitflow21.shared.Constants;
import com.sise.habitflow21.shared.HttpUtil;

import java.util.List;


public class FraseRepository {

    public void insertarFrase(FraseMotivacional incidencia, Callback<FraseMotivacional> callback) {
        try {

            String response = HttpUtil.POST(Constants.BASE_URL_API, "/frases", new Gson().toJson(incidencia));
            if (response == null) {
                callback.onFailure();
                return;
            }
            // Convertir el response String en el objeto BaseResponse<Incidencia>
            BaseResponse<FraseMotivacional> baseResponse = new Gson().fromJson(response, TypeToken.getParameterized(BaseResponse.class, FraseMotivacional.class).getType());
            if(baseResponse == null){
                callback.onFailure();
                return;
            }

            if(!baseResponse.isSuccess()){
                callback.onFailure();
                return;
            }
            callback.onSuccess(baseResponse.getData());
        } catch (Exception e){
            System.out.println(e);
            e.printStackTrace();
            callback.onFailure();
        }

    }


    public void obtenerFrases(Callback<List<FraseMotivacional>> callback) {
        try {
            // Realiza la petición GET a tu endpoint
            String response = HttpUtil.GET(Constants.BASE_URL_API, "/frases");

            if (response == null) {
                callback.onFailure();
                return;
            }

            // Convertimos el JSON a BaseResponse<List<FraseMotivacional>>
            BaseResponse<List<FraseMotivacional>> baseResponse = new Gson().fromJson(
                    response,
                    TypeToken.getParameterized(BaseResponse.class,
                            TypeToken.getParameterized(List.class, FraseMotivacional.class).getType()
                    ).getType()
            );

            if (baseResponse == null || !baseResponse.isSuccess()) {
                callback.onFailure();
                return;
            }

            callback.onSuccess(baseResponse.getData());

        } catch (Exception e) {
            e.printStackTrace();
            callback.onFailure();
        }
    }

}

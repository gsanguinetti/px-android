package com.mercadopago.controllers;

import com.mercadopago.preferences.ServicePreference;

public class CustomServicesHandler {
    private static com.mercadopago.controllers.CustomServicesHandler mInstance;
    private ServicePreference servicePreference;

    private CustomServicesHandler(){}

    public static com.mercadopago.controllers.CustomServicesHandler getInstance() {
        if(mInstance == null) {
            mInstance = new com.mercadopago.controllers.CustomServicesHandler();
        }
        return mInstance;
    }

    public void clear() {
        servicePreference = null;
    }

    public void setServices(ServicePreference servicePreference) {
        this.servicePreference = servicePreference;
    }

    public ServicePreference getServicePreference() {
        return servicePreference;
    }
}

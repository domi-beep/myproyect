package com.evertecinc.b2c.enex.client.model.dto2;

public class Views {
    // Esta vista será usada para los requests
    public static class RequestView {}
    
    // Esta vista se extiende de RequestView para que también incluya los campos visibles en la request
    public static class ResponseView extends RequestView {}
}

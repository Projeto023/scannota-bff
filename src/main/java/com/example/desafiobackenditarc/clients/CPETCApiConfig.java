package com.example.desafiobackenditarc.clients;

public class CPETCApiConfig {
    public static final String NAME = "cptecClient";

    public static final String GET_CITIES = "/listaCidades";
    public static final String GET_CITY_FORECAST = "/cidade/{cityId}/previsao.xml";
    public static final String GET_CITY_WAVE_FORECAST = "/cidade/{cityId}/dia/{dayParam}/ondas.xml";

    public static final String URL = "${clients.feign.cptec.url:http://servicos.cptec.inpe.br/XML}";
}

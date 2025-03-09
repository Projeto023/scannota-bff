package com.example.desafiobackenditarc.clients;

import com.example.desafiobackenditarc.dto.cptec.CityForecastDTO;
import com.example.desafiobackenditarc.dto.cptec.CityListDTO;
import com.example.desafiobackenditarc.dto.cptec.WaveForecastDTO;
import com.example.desafiobackenditarc.exception.CPTECException;

public interface CPTECApiService {

    CityListDTO getCityList(String cityName) throws CPTECException;

    CityForecastDTO getCityForecast(int cityId) throws CPTECException;

    WaveForecastDTO getWaveForecast(int cityId, int dayParam) throws CPTECException;
}
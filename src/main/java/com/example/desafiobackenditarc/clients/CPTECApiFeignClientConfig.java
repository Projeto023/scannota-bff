package com.example.desafiobackenditarc.clients;

import com.example.desafiobackenditarc.dto.cptec.CityForecastDTO;
import com.example.desafiobackenditarc.dto.cptec.CityListDTO;
import com.example.desafiobackenditarc.dto.cptec.WaveForecastDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = CPETCApiConfig.NAME, url = CPETCApiConfig.URL)
public interface CPTECApiFeignClientConfig {

    @GetMapping(CPETCApiConfig.GET_CITIES)
    CityListDTO getCityList(@RequestParam("city") String cityName);

    @GetMapping(CPETCApiConfig.GET_CITY_FORECAST)
    CityForecastDTO getCityForecast(@PathVariable("cityId") int cityId);

    @GetMapping(CPETCApiConfig.GET_CITY_WAVE_FORECAST)
    WaveForecastDTO getWaveForecast(@PathVariable("cityId") int cityId, @PathVariable("dayParam") int dayParam);
}
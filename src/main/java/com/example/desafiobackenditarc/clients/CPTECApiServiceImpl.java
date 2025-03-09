package com.example.desafiobackenditarc.clients;

import com.example.desafiobackenditarc.dto.cptec.CityForecastDTO;
import com.example.desafiobackenditarc.dto.cptec.CityListDTO;
import com.example.desafiobackenditarc.dto.cptec.WaveForecastDTO;
import com.example.desafiobackenditarc.exception.CPTECException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CPTECApiServiceImpl implements CPTECApiService {

    private final CPTECApiFeignClientConfig CPTECApiFeignClientConfig;

    @Override
    @Cacheable(value = "cities", key = "#cityName")
    public CityListDTO getCityList(String cityName) throws CPTECException {
        try {
            return CPTECApiFeignClientConfig.getCityList(cityName);
        } catch (final FeignException feignException) {
            log.error("[DesafioBackendItArcApiService] Error fetching city list from API: {}",
                    feignException.getMessage());
            throw new CPTECException(feignException.getMessage());
        }
    }

    @Override
    public CityForecastDTO getCityForecast(int cityId) throws CPTECException {
        try {
            return CPTECApiFeignClientConfig.getCityForecast(cityId);
        } catch (final FeignException feignException) {
            log.error("[DesafioBackendItArcApiService] Error fetching city forecast from API: {}",
                    feignException.getMessage());
            throw new CPTECException(feignException.getMessage());
        }
    }

    @Override
    public WaveForecastDTO getWaveForecast(int cityId, int dayParam) throws CPTECException {
        try {
            return CPTECApiFeignClientConfig.getWaveForecast(cityId, dayParam);
        } catch (final FeignException feignException) {
            log.error("[DesafioBackendItArcApiService] Error fetching wave forecast from API: {}",
                    feignException.getMessage());
            throw new CPTECException(feignException.getMessage());
        }
    }
}
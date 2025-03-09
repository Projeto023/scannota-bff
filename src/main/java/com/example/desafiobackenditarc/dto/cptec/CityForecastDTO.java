package com.example.desafiobackenditarc.dto.cptec;

import lombok.Data;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import javax.xml.bind.annotation.XmlTransient;

@Data
@XmlRootElement(name = "cidade")
public class CityForecastDTO {
    @XmlElement(name = "nome")
    private String name;

    @XmlElement(name = "uf")
    private String state;

    @XmlElement(name = "atualizacao")
    private String updateDate;

    @XmlElement(name = "previsao")
    private List<ForecastDTO> forecasts;

    @XmlTransient
    public String getName() {
        return name;
    }

    @XmlTransient
    public String getState() {
        return state;
    }

    @XmlTransient
    public String getUpdateDate() {
        return updateDate;
    }

    @XmlTransient
    public List<ForecastDTO> getForecasts() {
        return forecasts;
    }

    @Data
    public static class ForecastDTO {
        @XmlElement(name = "dia")
        private String day;

        @XmlElement(name = "tempo")
        private String weather;

        @XmlElement(name = "maxima")
        private Integer maxTemperature;

        @XmlElement(name = "minima")
        private Integer minTemperature;

        @XmlElement(name = "iuv")
        private Double uvIndex;

        @XmlTransient
        public String getDay() {
            return day;
        }

        @XmlTransient
        public String getWeather() {
            return weather;
        }

        @XmlTransient
        public Integer getMaxTemperature() {
            return maxTemperature;
        }

        @XmlTransient
        public Integer getMinTemperature() {
            return minTemperature;
        }

        @XmlTransient
        public Double getUvIndex() {
            return uvIndex;
        }
    }
}

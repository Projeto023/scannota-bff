package com.example.desafiobackenditarc.dto.cptec;

import lombok.Data;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Data
@XmlRootElement(name = "cidade")
public class WaveForecastDTO {
    @XmlElement(name = "nome")
    private String name;

    @XmlElement(name = "uf")
    private String state;

    @XmlElement(name = "atualizacao")
    private String updateDate;

    @XmlElement(name = "manha")
    private WaveDetailDTO morning;

    @XmlElement(name = "tarde")
    private WaveDetailDTO afternoon;

    @XmlElement(name = "noite")
    private WaveDetailDTO night;

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
    public WaveDetailDTO getMorning() {
        return morning;
    }

    @XmlTransient
    public WaveDetailDTO getAfternoon() {
        return afternoon;
    }

    @XmlTransient
    public WaveDetailDTO getNight() {
        return night;
    }

    @Data
    public static class WaveDetailDTO {
        @XmlElement(name = "dia")
        private String day;

        @XmlElement(name = "agitacao")
        private String agitation;

        @XmlElement(name = "altura")
        private Double height;

        @XmlElement(name = "direcao")
        private String direction;

        @XmlElement(name = "vento")
        private Double wind;

        @XmlElement(name = "vento_dir")
        private String windDirection;

        @XmlTransient
        public String getDay() {
            return day;
        }

        @XmlTransient
        public String getAgitation() {
            return agitation;
        }

        @XmlTransient
        public Double getHeight() {
            return height;
        }

        @XmlTransient
        public String getDirection() {
            return direction;
        }

        @XmlTransient
        public Double getWind() {
            return wind;
        }

        @XmlTransient
        public String getWindDirection() {
            return windDirection;
        }
    }
}

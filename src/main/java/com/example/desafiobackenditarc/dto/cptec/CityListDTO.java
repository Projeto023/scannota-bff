package com.example.desafiobackenditarc.dto.cptec;

import lombok.Data;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

@Data
@XmlRootElement(name = "cidades")
public class CityListDTO {

    @XmlElement(name = "cidade")
    private List<CityDTO> cities;

    @XmlTransient
    public List<CityDTO> getCities() { return cities; }

    @Data
    public static class CityDTO {
        @XmlElement(name = "nome")
        private String name;

        @XmlElement(name = "uf")
        private String state;

        @XmlElement(name = "id")
        private Integer id;

        @XmlTransient
        public String getName() { return name; }

        @XmlTransient
        public String getState() { return state; }

        @XmlTransient
        public Integer getId() { return id; }
    }
}

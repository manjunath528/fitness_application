package com.gym.app.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

/**
 * Created by Manjunath Reddy
 */
@Entity
@Table(name = "country")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Country implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_sequence_country")
    @SequenceGenerator(name = "id_sequence_country", sequenceName = "sequence_country", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "country")
    private String country;

    public Country() {
    }
    public Country(Long id, String country) {
        this.id = id;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Country)) return false;

        Country country1 = (Country) o;

        return new EqualsBuilder()
                .append(getId(), country1.getId())
                .append(getCountry(), country1.getCountry())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getId())
                .append(getCountry())
                .toHashCode();
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", country='" + country + '\'' +
                '}';
    }
}

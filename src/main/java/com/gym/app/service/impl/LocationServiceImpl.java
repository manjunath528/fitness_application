package com.gym.app.service.impl;



import com.gym.app.baseframework.exception.SystemException;
import com.gym.app.entity.City;
import com.gym.app.entity.Country;
import com.gym.app.entity.State;
import com.gym.app.repository.CityRepository;
import com.gym.app.repository.CountryRepository;
import com.gym.app.repository.StateRepository;
import com.gym.app.service.LocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Manjunath Reddy
 */
@Service
public class LocationServiceImpl implements LocationService {

    private static final Logger logger = LoggerFactory.getLogger(LocationServiceImpl.class);

    @Autowired
    CountryRepository countryRepository;
    @Autowired
    StateRepository stateRepository;
    @Autowired
    CityRepository cityRepository;


    @Override
    public List<Country> countryDetails() throws SystemException {
        List<Country> countryList = countryRepository.findAll();
        logger.info("countryDetails master data of size : {}", countryList.size());
        return countryList;
    }
    @Override
    public List<State> stateDetailsByCountryId(Long countryId) throws SystemException {
        List<State> stateList = stateRepository.stateDetailsByCountry(countryId);
        logger.info("stateDetailsByCountryId master data data of size : {}", stateList.size());
        return stateList;
    }

    @Override
    public List<City> cityDetailsByStateId(Long stateId) throws SystemException {
        List<City> cityList = cityRepository.cityDetailsForStateNameByStateId(stateId);
        logger.info("cityDetailsByStateId master data data of size : {}", cityList.size());
        return cityList;
    }


}

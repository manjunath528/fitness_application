package com.gym.app.service;



import com.gym.app.baseframework.exception.SystemException;
import com.gym.app.entity.City;
import com.gym.app.entity.Country;
import com.gym.app.entity.State;

import java.util.List;

/**
 * Created by Manjunath Reddy
 */
public interface LocationService {

    List<Country> countryDetails() throws SystemException;
    List<State> stateDetailsByCountryId(Long countryId) throws SystemException;
    List<City> cityDetailsByStateId(Long stateId) throws SystemException;

}
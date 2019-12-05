package com.scholanova.projectapi.services;

import com.scholanova.projectapi.exceptions.StationCityCannotBeEmptyException;
import com.scholanova.projectapi.exceptions.StationCountryCannotBeEmptyException;
import com.scholanova.projectapi.exceptions.StationNameCannotBeEmptyException;
import com.scholanova.projectapi.models.Station;
import com.scholanova.projectapi.repositories.StationRepository;
import org.springframework.stereotype.Service;

@Service
public class StationService {

    private StationRepository stationRepository;

    public StationService(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    public Station create(Station station) throws StationNameCannotBeEmptyException, StationCityCannotBeEmptyException, StationCountryCannotBeEmptyException {

        if (isNameMissing(station)) {
            throw new StationNameCannotBeEmptyException();
        }

        if (isCityMissing(station)) {
            throw new StationCityCannotBeEmptyException();
        }

        if (isCountryMissing(station)) {
            throw new StationCountryCannotBeEmptyException();
        }

        return stationRepository.create(station);
    }

    private boolean isNameMissing(Station station) {
        return station.getName() == null ||
                station.getName().trim().length() == 0;
    }

    private boolean isCityMissing(Station station) {
        return station.getCity() == null ||
                station.getCity().trim().length() == 0;
    }

    private boolean isCountryMissing(Station station) {
        return station.getCountry() == null ||
                station.getCountry().trim().length() == 0;
    }
}

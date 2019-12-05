package com.scholanova.projectapi.controllers;

import com.scholanova.projectapi.exceptions.StationCityCannotBeEmptyException;
import com.scholanova.projectapi.exceptions.StationCountryCannotBeEmptyException;
import com.scholanova.projectapi.exceptions.StationNameCannotBeEmptyException;
import com.scholanova.projectapi.models.Station;
import com.scholanova.projectapi.services.StationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StationController {

    private final StationService stationService;

    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    @GetMapping(path = "/stations/{id}")
    public Station getStation() {
        return null;
    }

    @PostMapping(path = "/stations")
    public Station createStation(@RequestBody Station station) throws StationCountryCannotBeEmptyException, StationNameCannotBeEmptyException, StationCityCannotBeEmptyException {
        return stationService.create(station);
    }
}

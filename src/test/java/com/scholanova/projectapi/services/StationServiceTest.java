package com.scholanova.projectapi.services;

import com.scholanova.projectapi.exceptions.StationCityCannotBeEmptyException;
import com.scholanova.projectapi.exceptions.StationCountryCannotBeEmptyException;
import com.scholanova.projectapi.exceptions.StationNameCannotBeEmptyException;
import com.scholanova.projectapi.models.Station;
import com.scholanova.projectapi.repositories.StationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StationServiceTest {

    private StationService stationService;

    @Mock
    private StationRepository stationRepository;

    @BeforeEach
    void setUp() {
        stationService = new StationService(stationRepository);
    }

    @Test
    void givenNoStationName_whenCreated_failsWithNoEmptyStationNameError() {
        // GIVEN
        Integer id = null;
        String name = null;
        String city = "Paris";
        String country = "France";

        Station emptyNameStation = new Station(id, name, city, country);

        // WHEN
        assertThrows(StationNameCannotBeEmptyException.class, () -> {
            stationService.create(emptyNameStation);
        });

        // THEN
        verify(stationRepository, never()).create(emptyNameStation);
    }

    @Test
    void givenNoStationCity_whenCreated_failsWithNoEmptyStationCityError() {
        // GIVEN
        Integer id = null;
        String name = "Gare de Lyon";
        String city = null;
        String country = "France";

        Station emptyCityStation = new Station(id, name, city, country);

        // WHEN
        assertThrows(StationCityCannotBeEmptyException.class, () -> {
            stationService.create(emptyCityStation);
        });

        // THEN
        verify(stationRepository, never()).create(emptyCityStation);
    }

    @Test
    void givenNoStationCountry_whenCreated_failsWithNoEmptyStationCountryError() {
        // GIVEN
        Integer id = null;
        String name = "Gare de Lyon";
        String city = "Paris";
        String country = "";

        Station emptyCityStation = new Station(id, name, city, country);

        // WHEN
        assertThrows(StationCountryCannotBeEmptyException.class, () -> {
            stationService.create(emptyCityStation);
        });

        // THEN
        verify(stationRepository, never()).create(emptyCityStation);
    }

    @Test
    void givenCorrectStation_whenCreated_savesStationInRepository() throws Exception {
        // GIVEN
        Integer id = 1234;
        String name = "Gare de Lyon";
        String city = "Paris";
        String country = "France";

        Station correctStation = new Station(null, name, city, country);
        Station savedStation = new Station(id, name, city, country);
        when(stationRepository.create(correctStation)).thenReturn(savedStation);

        // WHEN
        Station returnedStation = stationService.create(correctStation);

        // THEN
        verify(stationRepository, atLeastOnce()).create(correctStation);
        assertThat(returnedStation).isEqualTo(savedStation);
    }
}
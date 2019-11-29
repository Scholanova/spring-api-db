package com.scholanova.projectapi.repositories;

import com.scholanova.projectapi.exceptions.ModelNotFoundException;
import com.scholanova.projectapi.models.Station;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.jdbc.JdbcTestUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringJUnitConfig(StationRepository.class)
@JdbcTest
class StationRepositoryTest {

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @AfterEach
    void cleanUp() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "STATIONS");
    }

    @Nested
    class Test_getById {

        @Test
        void whenNoStationWithThatId_thenThrowsException() throws Exception {
            // Given
            Integer id = 1000;

            // When
            // Then
            assertThrows(ModelNotFoundException.class, () -> {
                stationRepository.getById(id);
            });
        }

        @Test
        void whenStationExists_thenReturnsTheStation() throws Exception {
            // Given
            Integer id = 1;
            Station station = new Station(id, "Gare du Nord", "Paris", "France");
            insertStation(station);

            // When
            Station extractedStation = stationRepository.getById(id);

            // Then
            assertThat(extractedStation).isEqualToComparingFieldByField(station);
        }
    }

    @Nested
    class Test_create {

        @Test
        void whenCreateStation_thenStationIsInDatabaseWithId() {
            // Given
            String stationName = "Gare du Nord";
            String stationCity = "Paris";
            String stationCountry = "France";
            Station stationToCreate = new Station(null, stationName, stationCity, stationCountry);

            // When
            Station createdStation = stationRepository.create(stationToCreate);

            // Then
            assertThat(createdStation.getId()).isNotNull();
            assertThat(createdStation.getName()).isEqualTo(stationName);
            assertThat(createdStation.getCity()).isEqualTo(stationCity);
            assertThat(createdStation.getCountry()).isEqualTo(stationCountry);
        }
    }

    private void insertStation(Station station) {
        String query = "INSERT INTO STATIONS " +
                "(ID, NAME, CITY, COUNTRY) " +
                "VALUES ('%d', '%s', '%s', '%s')";
        jdbcTemplate.execute(
                String.format(query, station.getId(), station.getName(), station.getCity(), station.getCountry()));
    }
}
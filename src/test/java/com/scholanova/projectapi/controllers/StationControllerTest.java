package com.scholanova.projectapi.controllers;

import com.scholanova.projectapi.models.Station;
import com.scholanova.projectapi.services.StationService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.OK;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class StationControllerTest {

    @LocalServerPort
    private int port;

    private TestRestTemplate template = new TestRestTemplate();

    @MockBean
    private StationService stationService;

    @Captor
    ArgumentCaptor<Station> createStationArgumentCaptor;

    @Nested
    class Test_createStation {

        @Test
        void givenCorrectBody_whenCalled_createsStation() throws Exception {
            // given
            String url = "http://localhost:{port}/stations";

            Map<String, String> urlVariables = new HashMap<>();
            urlVariables.put("port", String.valueOf(port));

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            String requestJson = "{" +
                    "\"name\":\"Gare de Lyon\"," +
                    "\"city\":\"Paris\"," +
                    "\"country\":\"France\"" +
                    "}";
            HttpEntity<String> httpEntity = new HttpEntity<>(requestJson, headers);

            Station createdStation = new Station(123, "Gare de Lyon", "Paris", "France");
            when(stationService.create(createStationArgumentCaptor.capture())).thenReturn(createdStation);

            // When
            ResponseEntity responseEntity = template.exchange(url,
                    HttpMethod.POST,
                    httpEntity,
                    String.class,
                    urlVariables);

            // Then
            assertThat(responseEntity.getStatusCode()).isEqualTo(OK);
            assertThat(responseEntity.getBody()).isEqualTo(
                    "{" +
                            "\"id\":123," +
                            "\"name\":\"Gare de Lyon\"," +
                            "\"city\":\"Paris\"," +
                            "\"country\":\"France\"" +
                            "}"
            );
            Station stationToCreate = createStationArgumentCaptor.getValue();
            assertThat(stationToCreate.getName()).isEqualTo("Gare de Lyon");
            assertThat(stationToCreate.getCity()).isEqualTo("Paris");
            assertThat(stationToCreate.getCountry()).isEqualTo("France");
        }
    }
}
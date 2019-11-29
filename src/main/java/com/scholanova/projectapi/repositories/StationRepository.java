package com.scholanova.projectapi.repositories;

import com.scholanova.projectapi.exceptions.ModelNotFoundException;
import com.scholanova.projectapi.models.Station;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class StationRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public StationRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Station getById(Integer id) throws ModelNotFoundException {
        String query = "SELECT ID as id, " +
                "NAME AS name, " +
                "CITY AS city, " +
                "COUNTRY AS country " +
                "FROM STATIONS " +
                "WHERE ID = :id";

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", id);

        return jdbcTemplate.query(query,
                parameters,
                new BeanPropertyRowMapper<>(Station.class))
                .stream()
                .findFirst()
                .orElseThrow(ModelNotFoundException::new);
    }

    public Station create(Station stationToCreate) {
        KeyHolder holder = new GeneratedKeyHolder();

        String query = "INSERT INTO STATIONS " +
                "(NAME, CITY, COUNTRY) VALUES " +
                "(:name, :city, :country)";

        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("name", stationToCreate.getName())
                .addValue("city", stationToCreate.getCity())
                .addValue("country", stationToCreate.getCountry());

        jdbcTemplate.update(query, parameters, holder);

        Integer newlyCreatedId = (Integer) holder.getKey();
        try {
            return this.getById(newlyCreatedId);
        } catch (ModelNotFoundException e) {
            return null;
        }
    }
}

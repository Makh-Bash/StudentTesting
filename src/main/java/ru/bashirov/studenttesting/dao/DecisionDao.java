package ru.bashirov.studenttesting.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.bashirov.studenttesting.models.Test;
import ru.bashirov.studenttesting.models.User;

@Component
public class DecisionDao {

    private final JdbcTemplate jdbcTemplate;

    public DecisionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


}

package ru.pflb.scores.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import ru.pflb.scores.entity.User;
import ru.pflb.scores.service.UserService;

import java.sql.Types;

@Service
@AllArgsConstructor
public class UserServiceJdbc implements UserService {

    private final JdbcTemplate jdbcTemplate;
    private final TransactionTemplate transactionTemplate;

    @Override
    public long registerUser(String name) {
        return transactionTemplate.execute(status -> registerUserLocking(name));
    }

    private long registerUserLocking(String name) {
        jdbcTemplate.execute("LOCK TABLE scores.user IN ROW EXCLUSIVE MODE");

        long id = generateId(); // ой, но что если кто-то теперь успеет обновить БД раньше нас

        jdbcTemplate.update(
                "INSERT INTO scores.user (id, name) VALUES (?, ?)", id, name
        );
        return id;
    }

    @Override
    public User getUser(long id) {
        return jdbcTemplate.queryForObject("select id, name from scores.user where id = ?",
                new Long[]{id},
                new int[]{Types.BIGINT},
                (rs, rowNum) -> {
                    User user = new User();
                    user.setId(rs.getLong("id"));
                    user.setName(rs.getString("name"));
                    return user;
                });

    }

    private long generateId() {
        return jdbcTemplate.queryForObject("select coalesce(max(id), 0) from scores.user", Long.class) + 1;
    }
}

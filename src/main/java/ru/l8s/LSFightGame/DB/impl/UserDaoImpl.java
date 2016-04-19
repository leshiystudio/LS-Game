package ru.l8s.LSFightGame.DB.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.metadata.PostgresCallMetaDataProvider;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import ru.l8s.LSFightGame.DB.api.IDBConnector;
import ru.l8s.LSFightGame.authentication.impl.User;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.*;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by LS on 17.04.2016.
 */
public class UserDaoImpl implements IDBConnector<User>{

    @Autowired
    private DataSource dataSource;

    //private JdbcTemplate jdbcTemplate;

    //public void setDataSource(DataSource dataSource){
    //    this.jdbcTemplate = new JdbcTemplate(dataSource);
    //}

    private RowMapper<User> rowMapper = new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            return user;
        }
    };
    @Override
    public void insert(User user) {
        String sql = "INSERT INTO users(login, password) VALUE (?,?)";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(sql, getPreparedStatementSetter(user));
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE users SET login=?, password=? WHERE id=?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(sql, getPreparedStatementSetter(user));
    }

    @Override
    public void delete(User user) {
        String sql = "DELETE FROM users WHERE username=?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(sql, user.getUsername());
    }

    @Override
    public User get(String val) {
        String sql = "SELECT * FROM users WHERE username=?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.queryForObject(sql, rowMapper, val);
    }

    @Override
    public User get(Integer id) {
        String sql = "SELECT * FROM users WHERE id=?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public List<User> getAll()
    {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.query("SELECT * FROM users", rowMapper);
    }

    private PreparedStatementSetter getPreparedStatementSetter(final User user){
        return  new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                int i=0;
                ps.setString(++i,user.getUsername());
                ps.setString(++i,user.getPassword());
                ps.setInt(++i,user.getId());
            }
        };
    }
}


package ru.l8s.LSFightGame.DB.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.metadata.PostgresCallMetaDataProvider;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.l8s.LSFightGame.DB.api.IDBConnector;
import ru.l8s.LSFightGame.authentication.impl.User;
import ru.l8s.LSFightGame.gamecore.impl.Character;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.io.PrintWriter;
import java.sql.*;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by LS on 17.04.2016.
 */
@Repository
public class UserDaoImpl implements IDBConnector<User>{

    //private JdbcTemplate jdbcTemplate;

    @Autowired
    CharacterDaoImpl characterDao;

    //@Autowired
    private DataSource dataSource;

    public UserDaoImpl() {
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private RowMapper<User> rowMapper = new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            return user;
        }
    };
    @Override
    public int insert(User user) {
        System.out.println(user.getUsername()+" "+user.getPassword());
        String sql = "INSERT INTO lsusers (username, password) VALUES(?, ?)";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps =
                                connection.prepareStatement(sql, new String[] {"id"});
                        int i=0;
                        ps.setString(++i,user.getUsername());
                        ps.setString(++i,user.getPassword());
                        return ps;
                    }
                },
                keyHolder
        );
        int userId=(int)keyHolder.getKey();

        String sql2 = "INSERT INTO authorities (user_id) VALUES(?) ";
        jdbcTemplate.update(sql2,userId);

        return userId;
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE lsusers SET username=?, password=? WHERE id=?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(sql, getPreparedStatementSetter(user));
    }

    @Override
    public void delete(User user) {
        String sql = "DELETE FROM lsusers WHERE username=?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(sql, user.getUsername());
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM lsusers WHERE id=?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(sql, id);
    }

    @Override
    public User get(String username) {
        String sql = "SELECT * FROM lsusers WHERE username=?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        User user;
        try {
            user = jdbcTemplate.queryForObject(sql, rowMapper, username);
        }
        catch (EmptyResultDataAccessException e){
            user = null;
        }
        return user;
    }

    public boolean exist(String username) {
        String sql = "SELECT * FROM lsusers WHERE username=?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        try {
            jdbcTemplate.queryForObject(sql, rowMapper, username);
            return true;
        }
        catch (EmptyResultDataAccessException e){
            return false;
        }
    }

    @Override
    public User get(Integer id) {
        String sql = "SELECT * FROM lsusers WHERE id=?";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public List<User> getAll()
    {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.query("SELECT * FROM lsusers", rowMapper);
    }
    public List<User> getAll(int start, int count)
    {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.query("SELECT * FROM lsusers LIMIT "+count+" OFFSET "+start, rowMapper);
    }

    private PreparedStatementSetter getPreparedStatementSetter(final User user){
        return  new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                int i=0;
                ps.setString(++i,user.getUsername());
                ps.setString(++i,user.getPassword());
            }
        };
    }
}


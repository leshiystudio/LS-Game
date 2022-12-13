package ru.l8s.LSFightGame.DB.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import ru.l8s.LSFightGame.DB.api.IDBConnector;
import ru.l8s.LSFightGame.authentication.impl.User;
import ru.l8s.LSFightGame.gamecore.impl.Character;
import ru.l8s.LSFightGame.gamecore.impl.FIghtEvent;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by LS on 13.05.2016.
 */
public class CharacterDaoImpl implements IDBConnector<Character> {

    //private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public CharacterDaoImpl() {
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        //this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private RowMapper<Character> rowMapper = new RowMapper<Character>() {
        @Override
        public Character mapRow(ResultSet rs, int rowNum) throws SQLException {
            Character character = new Character();

            character.setId(rs.getInt("id"));
            character.setName(rs.getString("name"));
            character.setHealth(rs.getInt("health"));
            character.setArmor(rs.getInt("armor"));
            character.setDexterity(rs.getInt("dexterity"));
            character.setSpeed(rs.getInt("speed"));
            character.setPower(rs.getInt("power"));
            character.setUser_id(rs.getInt("user_id"));
            character.setCountWin(rs.getInt("count_win"));
            character.setCountLoss(rs.getInt("count_losing"));

            return character;
        }
    };

    private PreparedStatementSetter getPreparedStatementSetter(final Character character){
        return  new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                int i=0;
                ps.setString(++i,character.getName());
                ps.setInt(++i,character.getHealth());
                ps.setInt(++i,character.getArmor());
                ps.setInt(++i,character.getDexterity());
                ps.setInt(++i,character.getSpeed());
                ps.setInt(++i,character.getPower());
            }
        };
    }

    @Override
    public int insert(Character character) {
        //System.out.println(character.getHealth()+" "+character.getArmor());
        String sql = "INSERT INTO characters (user_id, name, health, armor, dexterity, speed, power) VALUES(?, ?, ?, ?, ?, ?, ?)";
        //JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps =
                                connection.prepareStatement(sql, new String[] {"id"});
                        int i=0;
                        ps.setInt(++i,character.getUser_id());
                        ps.setString(++i,character.getName());
                        ps.setInt(++i,character.getHealth());
                        ps.setInt(++i,character.getArmor());
                        ps.setInt(++i,character.getDexterity());
                        ps.setInt(++i,character.getSpeed());
                        ps.setInt(++i,character.getPower());
                        return ps;
                    }
                },
                keyHolder
        );
        return (int) keyHolder.getKey();
    }

    public int setWin(Character character) {
        String sql = "UPDATE characters SET count_win = count_win + 1  WHERE id=?";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps =
                                connection.prepareStatement(sql, new String[] {"id"});
                        int i=0;
                        ps.setInt(++i,character.getId());
                        return ps;
                    }
                },
                keyHolder
        );
        return (int) keyHolder.getKey();
    }

    public int setLoss(Character character) {
        String sql = "UPDATE characters SET count_losing = count_losing + 1  WHERE id=?";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps =
                                connection.prepareStatement(sql, new String[] {"id"});
                        int i=0;
                        ps.setInt(++i,character.getId());
                        return ps;
                    }
                },
                keyHolder
        );
        return (int) keyHolder.getKey();
    }

    @Override
    public void update(final Character character) {
        String sql = "UPDATE characters SET name=?, health=?, armor=?, dexterity=?, speed=?, power=? WHERE id=?";
        //JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                int i=0;
                ps.setString(++i,character.getName());
                ps.setInt(++i,character.getHealth());
                ps.setInt(++i,character.getArmor());
                ps.setInt(++i,character.getDexterity());
                ps.setInt(++i,character.getSpeed());
                ps.setInt(++i,character.getPower());
                ps.setInt(++i,character.getId());
            }
        });
    }

    public void updateOnlySkills(final Character character) {
        System.out.println(character.getId()+" "+character.getName()+" "+character.getDexterity()+" "+character.getSpeed()+" "+character.getPower());
        String sql = "UPDATE characters SET dexterity=?, speed=?, power=? WHERE id=?";
        //JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                int i=0;
                ps.setInt(++i,character.getDexterity());
                ps.setInt(++i,character.getSpeed());
                ps.setInt(++i,character.getPower());
                ps.setInt(++i,character.getId());
            }
        });
    }

    public void updateOnlyHealthAndArmor(final Character character) {
       String sql = "UPDATE characters SET health=?, armor=? WHERE id=?";
        //JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
       jdbcTemplate.update(sql, new PreparedStatementSetter() {
           @Override
           public void setValues(PreparedStatement ps) throws SQLException {
               int i=0;
               ps.setInt(++i,character.getHealth());
               ps.setInt(++i,character.getArmor());
               ps.setInt(++i,character.getId());
           }
       });
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM characters WHERE id=?";
        //JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void delete(Character character) {
        String sql = "DELETE FROM characters WHERE id=?";
        //JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(sql, character.getId());
    }

    @Override
    public Character get(Integer id) {
        String sql = "SELECT ch.id,ch.name,ch.health,ch.armor,ch.dexterity,ch.speed,ch.power,ch.user_id, COUNT(fwin.winner_id) AS count_win, (\n" +
                "  SELECT COUNT(fwin.id) FROM fight_history fwin WHERE fwin.losser_id = ch.id\n" +
                ") AS count_losing  FROM characters ch\n" +
                "  FULL OUTER JOIN fight_history fwin ON ch.id = fwin.winner_id\n" +
                "WHERE ch.id=?\n" +
                "GROUP BY ch.id\n" +
                "ORDER BY ch.id DESC  ";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public Integer getCount() {
        String sql = "SELECT COUNT(*) FROM characters ";
        //JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql);
        Integer res=-1;
        if(rs.next()) {
            res = rs.getInt(1);
        }
        return res;
    }

    @Override
    public Character get(String name) {
        String sql = "SELECT ch.id,ch.name,ch.health,ch.armor,ch.dexterity,ch.speed,ch.power,ch.user_id, COUNT(fwin.winner_id) AS count_win, (\n" +
                "  SELECT COUNT(fwin.id) FROM fight_history fwin WHERE fwin.losser_id = ch.id\n" +
                ") AS count_losing  FROM characters ch\n" +
                "  FULL OUTER JOIN fight_history fwin ON ch.id = fwin.winner_id\n" +
                "WHERE ch.name=? \n" +
                "GROUP BY ch.id\n" +
                "ORDER BY ch.id DESC LIMIT 1";
        return jdbcTemplate.queryForObject(sql, rowMapper, name);
    }

    public Character get(User user, int count) {
        String sql = "SELECT ch.id,ch.name,ch.health,ch.armor,ch.dexterity,ch.speed,ch.power,ch.user_id, COUNT(fwin.winner_id) AS count_win, (\n" +
                "  SELECT COUNT(fwin.id) FROM fight_history fwin WHERE fwin.losser_id = ch.id\n" +
                ") AS count_losing  FROM characters ch\n" +
                "  FULL OUTER JOIN fight_history fwin ON ch.id = fwin.winner_id\n" +
                "WHERE ch.user_id=? \n" +
                "GROUP BY ch.id\n" +
                "ORDER BY ch.id DESC  LIMIT 1 OFFSET "+(--count);
        //String sql = "SELECT * FROM characters WHERE user_id=? ORDER BY id DESC LIMIT 1 OFFSET "+(--count);
        return jdbcTemplate.queryForObject(sql, rowMapper, user.getId());
    }

    @Override
    public List<Character> getAll() {
        String sql = "SELECT ch.id,ch.name,ch.health,ch.armor,ch.dexterity,ch.speed,ch.power,ch.user_id, COUNT(fwin.winner_id) AS count_win, (\n" +
                "  SELECT COUNT(fwin.id) FROM fight_history fwin WHERE fwin.losser_id = ch.id\n" +
                ") AS count_losing  FROM characters ch\n" +
                "  FULL OUTER JOIN fight_history fwin ON ch.id = fwin.winner_id\n" +
                "GROUP BY ch.id\n" +
                "ORDER BY ch.id DESC";
        return jdbcTemplate.query(sql, rowMapper);
    }
    public List<Character> getAll(int start, int count) {
        String sql = "SELECT ch.id,ch.name,ch.health,ch.armor,ch.dexterity,ch.speed,ch.power,ch.user_id, COUNT(fwin.winner_id) AS count_win, (\n" +
                "  SELECT COUNT(fwin.id) FROM fight_history fwin WHERE fwin.losser_id = ch.id\n" +
                ") AS count_losing  FROM characters ch\n" +
                "  FULL OUTER JOIN fight_history fwin ON ch.id = fwin.winner_id\n" +
                "GROUP BY ch.id\n" +
                "ORDER BY ch.id DESC  LIMIT "+count+" OFFSET "+start;
        return jdbcTemplate.query(sql, rowMapper);
    }
    public List<Character> getAll(int start, int count, int exceptUserId) {
        String sql = "SELECT ch.id,ch.name,ch.health,ch.armor,ch.dexterity,ch.speed,ch.power,ch.user_id, COUNT(fwin.winner_id) AS count_win, (\n" +
                "  SELECT COUNT(fwin.id) FROM fight_history fwin WHERE fwin.losser_id = ch.id\n" +
                ") AS count_losing  FROM characters ch\n" +
                "  FULL OUTER JOIN fight_history fwin ON ch.id = fwin.winner_id\n" +
                "WHERE user_id!="+exceptUserId+"\n" +
                "GROUP BY ch.id\n" +
                "ORDER BY ch.id DESC  LIMIT "+count+" OFFSET "+start;
        return jdbcTemplate.query(sql, rowMapper);
    }

    public void resetHealthArmorAllChars() {
        String sql = "UPDATE characters SET health=100, armor=100 ";
        jdbcTemplate.update(sql);
    }


    public int saveFight(Character winner, Character losser) {
        String sql = "INSERT INTO fight_history (winner_id, losser_id) VALUES(?, ?)";
        //JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps =
                                connection.prepareStatement(sql, new String[] {"id"});
                        int i=0;
                        ps.setInt(++i,winner.getId());
                        ps.setInt(++i,losser.getId());
                        return ps;
                    }
                },
                keyHolder
        );
        return (int) keyHolder.getKey();
    }

    public List<FIghtEvent> getAllFights(int start, int count) {
        String sql = "SELECT * FROM fight_history ORDER BY id DESC LIMIT "+count+" OFFSET "+start;
        List res = jdbcTemplate.query(sql, fIghtEventRowMapper);

        return res;

    }

    RowMapper<FIghtEvent> fIghtEventRowMapper = new RowMapper<FIghtEvent>() {
        @Override
        public FIghtEvent mapRow(ResultSet rs, int rowNum) throws SQLException {
            FIghtEvent fIghtEvent = new FIghtEvent();
            Character winner = new Character();
            winner.setId(rs.getInt("winner_id"));
            fIghtEvent.setWinner(winner);

            Character losser = new Character();
            losser.setId(rs.getInt("losser_id"));
            fIghtEvent.setWinner(losser);

            fIghtEvent.setWinner(CharacterDaoImpl.this.get(winner.getId()));
            fIghtEvent.setLoser(CharacterDaoImpl.this.get(losser.getId()));
            fIghtEvent.setDate(rs.getTimestamp("date"));
            return fIghtEvent;
        }
    };
}

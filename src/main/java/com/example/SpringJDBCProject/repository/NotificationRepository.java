package com.example.SpringJDBCProject.repository;

import com.example.SpringJDBCProject.model.Notification;
import com.example.SpringJDBCProject.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class NotificationRepository {

    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Notification notification) {
        String sql = "insert into notification (id, type, message) values(?,?,?)";
        int rows = jdbcTemplate.update(sql, notification.getId(),  notification.getType(), notification.getMessage());
        System.out.println(rows + " effected");
    }

    public List<Notification> findAll() {
        String sql ="select * from notification";
        RowMapper<Notification> mapper = (ResultSet rs, int rowNum) -> {
                Notification notification = new Notification();
                notification.setId(rs.getInt("id"));
                notification.setType(rs.getString("type"));
                notification.setMessage(rs.getString("message"));
                return notification;
        };
        return jdbcTemplate.query(sql, mapper);
    }
}

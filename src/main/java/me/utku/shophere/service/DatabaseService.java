package me.utku.shophere.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.time.Instant;

@Service
@Slf4j
public class DatabaseService {
    private final String jdbcUrl = "jdbc:postgresql://localhost:5432/bil344";
    private final String username = "utkuonursahin";
    private Connection connection;
    public Boolean connectDb(){
        try{
            if(connection != null && !connection.isClosed()){
                log.warn("Connection is already established");
                return true;
            }
            connection = DriverManager.getConnection(jdbcUrl, username, null);
            if(connection != null) log.info("Successfully connected to the database at: {}", Instant.now());
            return true;
        } catch (Exception e){
            log.error("Connection is not successful: {}", e.getMessage());
            return false;
        }
    }

    public Boolean disconnectDb(){
        try{
            if(connection.isClosed()){
                log.warn("Connection is already closed");
                return true;
            }
            connection.close();
            log.info("Successfully disconnected from the database at: {}", Instant.now());
            return true;
        } catch (Exception e){
            log.error("Disconnection request failed: {}", e.getMessage());
            return false;
        }
    }
}

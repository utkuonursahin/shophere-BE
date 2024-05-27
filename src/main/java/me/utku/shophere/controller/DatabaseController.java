package me.utku.shophere.controller;

import lombok.RequiredArgsConstructor;
import me.utku.shophere.dto.GenericResponse;
import me.utku.shophere.service.DatabaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class DatabaseController {
    private final DatabaseService databaseService;

    @PostMapping("/db-connection")
    public ResponseEntity<GenericResponse<Boolean>> dbConnection(){
        Boolean result = databaseService.connectDb();
        GenericResponse<Boolean> response = new GenericResponse<>(
                HttpStatus.OK.value(),
                "DB connection is successful",
                result
        );
        return response.toResponseEntity();
    }

    @PostMapping("/db-disconnection")
    public ResponseEntity<GenericResponse<Boolean>> dbDisconnection(){
        Boolean result = databaseService.disconnectDb();
        GenericResponse<Boolean> response = new GenericResponse<>(
                HttpStatus.OK.value(),
                "DB disconnection is successful",
                result
        );
        return response.toResponseEntity();
    }
}

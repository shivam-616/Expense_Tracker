package com.example.expensetracker.expenceservice.controller;


import com.example.expensetracker.expenceservice.entites.expense;
import com.example.expensetracker.expenceservice.requestDTO.addDTO;
import com.example.expensetracker.expenceservice.service.expenseService;
import jakarta.persistence.PreUpdate;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@Controller
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // <-- Add this line to allow frontend testing
@RequestMapping("/expense/v1")
public class expenseController {

    //need sevice for business logic

    private expenseService expenseService;


    //insight

    // basic crud

    // create
    @PostMapping("/addExpense")
    public ResponseEntity<Boolean> addexpense(@RequestHeader("X-User-Id") String userId, @RequestBody addDTO entrydetail) {
        addDTO userEntry = new addDTO(
                userId,
                entrydetail.merchant(),
                entrydetail.curreny(),
                entrydetail.externalId(),
                entrydetail.amount(),
                entrydetail.category(),
                entrydetail.date()
        );
        try {
            return new ResponseEntity<>(expenseService.saveExpense(userEntry), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    // read
    @GetMapping("/getExpense")
    public ResponseEntity<List<addDTO>> getentry(@RequestHeader("X-User-Id")@NonNull String userId) {
        try{
            List<addDTO> entries = expenseService.getExpense(userId);
            return new ResponseEntity<>(entries , HttpStatus.OK );
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.NOT_FOUND);
        }
    }

    //update
    //update
    @PostMapping("/updateExpense")
    public ResponseEntity<Boolean> updateentry(@RequestHeader("X-User-Id") @NonNull String userId, @RequestBody addDTO entrydetail) {

        // 1. Create a new record instance because Java records are immutable
        addDTO userEntry = new addDTO(
                userId,
                entrydetail.merchant(),
                entrydetail.curreny(),
                entrydetail.externalId(),
                entrydetail.amount(),
                entrydetail.category(),
                entrydetail.date()
        );


        try {
            // 2. Pass the arguments to the service and include the HttpStatus
            return new ResponseEntity<>(expenseService.updateexpense(userId, userEntry), HttpStatus.OK);
        } catch (Exception e) {
            // 3. Return false instead of null for the Boolean response
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

}

package com.example.expensetracker.expenceservice.service;


import com.example.expensetracker.expenceservice.entites.expense;
import com.example.expensetracker.expenceservice.repository.expenseRepo;
import com.example.expensetracker.expenceservice.requestDTO.addDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Data
public class expenseService {


    @Autowired
    private final expenseRepo expenserepo;
    private final ObjectMapper objectMapper = new ObjectMapper();


    public boolean saveExpense(addDTO entrydetail) {
        try {
            // 1. Create a fresh instance of the entity from the DTO
            expense newExpense = objectMapper.convertValue(entrydetail, expense.class);

            // 2. Set the currency
            newExpense.setCurrency(entrydetail.curreny() != null ? entrydetail.curreny() : "INR");
            newExpense.setUserId(entrydetail.userID()); // Ensure UserID is mapped

            // 3. Save it
            expenserepo.save(newExpense);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<addDTO> getExpense(String userID) {
        List<expense> ls = expenserepo.findByUserId(userID);
        return objectMapper.convertValue(ls,
                new TypeReference<List<addDTO>>() {
                });
    }

    public boolean updateexpense(String userID, addDTO entrydetail) {
        Optional<expense> expenseFoundOpt = expenserepo.findByUserIdAndExternalId(userID, entrydetail.externalId());

        if (expenseFoundOpt.isEmpty()) {
            return false;
        }

        // Renamed variable to existingExpense to avoid shadowing the Entity class name
        expense existingExpense = expenseFoundOpt.get();
        existingExpense.setAmount(entrydetail.amount());

        // Replaced Strings.isNotBlank with standard Java String checks
        if (entrydetail.merchant() != null && !entrydetail.merchant().trim().isEmpty()) {
            existingExpense.setMerchant(entrydetail.merchant());
        }

        if (entrydetail.curreny() != null && !entrydetail.curreny().trim().isEmpty()) {
            existingExpense.setCurrency(entrydetail.curreny());
        } else {
            existingExpense.setCurrency("INR");
        }

        expenserepo.save(existingExpense);
        return true;
    }
}

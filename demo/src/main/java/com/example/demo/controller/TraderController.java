package com.example.demo.controller;
import java.util.NoSuchElementException;
import com.example.demo.service.TraderService;
import com.example.demo.exceptions.TraderNotFoundException;
import com.example.demo.exceptions.TraderAlreadyExistException;
import com.example.demo.model.Trader;
import com.example.demo.model.AddMoneyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/trading/traders")
public class TraderController {

    private final TraderService traderService;

    @Autowired
    public TraderController(TraderService traderService) {
        this.traderService = traderService;
    }


    @PostMapping("/register")
    public ResponseEntity<T> registerTrader(Trader trader) {

        try {
            trader = traderService.registerTrader(trader);
            return  ResponseEntity.status(200).body("successfully registered");
        } 
        
        catch (TraderAlreadyExistException e){
            return  ResponseEntity.status(400).body("failure to register (exists already)");

        }
    }

    
    @GetMapping("/all")
    public ResponseEntity<T> getAllTraders() {
        List<Trader> traders = traderService.getAllTraders();
        return ResponseEntity.status(200).body(traders);
    }


    @GetMapping
    public ResponseEntity<T> getTraderByEmail(@RequestParam String email) {
        
        try {
            Trader trader = traderService.getTraderByEmail(email);
            return  ResponseEntity.status(200).body(trader);
        } 
        
        catch (TraderNotFoundException e){
            return  ResponseEntity.status(404).body("failure to get (doesn't exist)");

        }
            
    }
    


    @PutMapping
    public ResponseEntity<T> updateTraderName(Trader trader) {

        try {
            trader = traderService.updateTraderName(trader.getEmail(), trader.getName());
            return ResponseEntity.status(200).body("successfully updated trader");
        } 
        
        catch (TraderNotFoundException e){
            return ResponseEntity.status(404).body("failure to put (doesn't exist)");
        }
    }

    @PutMapping("/add")
    public ResponseEntity<T> addMoney(AddMoneyRequest request) {
        
        try {
            Trader trader = traderService.addMoney(request.getEmail(), request.getAmount());
            return ResponseEntity.status(200).body("successfully updated trader");
        } 
        
        catch (TraderNotFoundException e){
            return ResponseEntity.status(404).body("failure to put (doesn't exist)");
        }
    }
}

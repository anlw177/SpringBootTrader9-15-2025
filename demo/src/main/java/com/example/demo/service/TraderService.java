package com.example.demo.service;

import com.example.demo.repository.TraderRepository;
import com.example.demo.exceptions.TraderNotFoundException;
import com.example.demo.exceptions.TraderAlreadyExistException;
import com.example.demo.model.Trader;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.List;
import java.util.Comparator;


@Service
public class TraderService {

    private final TraderRepository traderRepository;

    @Autowired
    public TraderService(TraderRepository traderRepository) {
        this.traderRepository = traderRepository;
    }

    @Transactional
    public Trader registerTrader(Trader trader) {
        trader = traderRepository.findByEmail(trader.getEmail());
        return trader;
    }

    public List<Trader> getAllTraders() {
        List<Trader> allTraders = traderRepository.findAll();
        allTraders.sort(Comparator.comparing(Trader::getId)); //Records should be sorted by ID in ascending order.
        return allTraders;
    }

    public Trader getTraderByEmail(String email) {
        return traderRepository.findByEmail(email);
    }

    @Transactional
    public Trader updateTraderName(String email, String newName) {
        Trader trader = traderRepository.findByEmail(email);
        trader.setName(newName);
        trader.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        traderRepository.save(trader);
        return trader;
    }

    @Transactional
    public Trader addMoney(String email, double amount) {
        Trader trader = traderRepository.findByEmail(email);
        trader.setBalance(trader.getBalance() + amount);
        trader.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        traderRepository.save(trader);
        return trader;
    }
}

package com.example.blockchaininfo.services;

import com.example.blockchaininfo.model.NetworkHashrate;
import com.example.blockchaininfo.model.PoolHashrate;
import com.example.blockchaininfo.repositories.NetworkHashrateRepository;
import com.example.blockchaininfo.repositories.PoolHashrateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class NetworkHashrateService {

    private final NetworkHashrateRepository networkHashrateRepository;
    private final PoolHashrateRepository poolHashrateRepository;



    public List<NetworkHashrate> getAllNetworks(){

        List<NetworkHashrate> networkHashrateList = new ArrayList<>();

        networkHashrateRepository.findAll().iterator().forEachRemaining(networkHashrateList::add);

        return networkHashrateList;
    }


    public PoolHashrate getPoolHashrate(Long id){

        Optional<PoolHashrate> poolHashrateOptional = poolHashrateRepository.findById(id);

        if(!poolHashrateOptional.isPresent())
            return null;

        return poolHashrateOptional.get();
    }
}
package com.unitedremote.nearshops.controller;

import com.unitedremote.nearshops.model.entity.Shop;
import com.unitedremote.nearshops.model.entity.User;
import com.unitedremote.nearshops.model.repository.ShopRepository;
import com.unitedremote.nearshops.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/shops")
public class ShopController {
    @Autowired
    ShopRepository shopRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/near/{latitude}/{longitude}")
    public ResponseEntity<List<Shop>> nearShops(@PathVariable  double latitude, @PathVariable double longitude, @AuthenticationPrincipal User user){

        List<Shop> nearShops = new ArrayList<>(shopRepository.shopsOrderedByDistance(latitude, longitude));
        user.getDislikedShops().forEach((shopId, date)->{
            Optional<Shop> optionalShop = shopRepository.findById(shopId);
            if(optionalShop.isPresent()){
                if((new Date().getTime()-date.getTime())/3600000 < 2)
                    nearShops.remove(optionalShop.get());
            }
        });

        return ok(nearShops);


    }
    @GetMapping("/preferred")
    ResponseEntity<Iterable<Shop>> preferredShops(@AuthenticationPrincipal User user)
    {

        return ok(shopRepository.findAllById(user.getPreferredShops()));
    }

    @GetMapping("/like/{shopId}")
    ResponseEntity<String> likeShop(@PathVariable String shopId, @AuthenticationPrincipal User user){
       Optional<Shop> optionalShop = shopRepository.findById(shopId);
        if(optionalShop.isPresent()){
            user.getPreferredShops().add(optionalShop.get().getId());
            userRepository.save(user);

        }
       
        return ResponseEntity.accepted().build();

    }

    @GetMapping("/remove-from-preferred/{shopId}")
    ResponseEntity<Shop> removeFromPreferred(@PathVariable String shopId, @AuthenticationPrincipal User user){

        Optional<Shop> optionalShop = shopRepository.findById(shopId);
        if(optionalShop.isPresent()){
            user.getPreferredShops().remove(shopId);
            userRepository.save(user);
        }

        return ResponseEntity.accepted().build();
    }

    @GetMapping("/dislike/{shopId}")
    ResponseEntity<String> dislikeShop(@PathVariable String shopId, @AuthenticationPrincipal User user){

        Optional<Shop> optionalShop = shopRepository.findById(shopId);
        if(optionalShop.isPresent()){
            user.getDislikedShops().put(shopId, new Date());
            userRepository.save(user);
        }

        return ResponseEntity.accepted().build();
    }



}

package com.unitedremote.nearshops.controller;

import com.unitedremote.nearshops.model.entity.Shop;
import com.unitedremote.nearshops.model.entity.User;
import com.unitedremote.nearshops.model.repository.ShopRepository;
import com.unitedremote.nearshops.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/shops")
public class ShopController {
    @Autowired
    ShopRepository shopRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/near/{latitude}/{longitude}")
    public ResponseEntity<List<Shop>> nearShops(@PathVariable  double latitude, @PathVariable double longitude){

        return ok(shopRepository.shopsOrderedByDistance(latitude, longitude));
    }
    @GetMapping("/preferred")
    ResponseEntity<Set<Shop>> preferredShops(@AuthenticationPrincipal User user)
    {

        return ok(user.getPreferredShops());
    }

    @GetMapping("/like/{shopId}")
    ResponseEntity<String> likeShop(@PathVariable String shopId, @AuthenticationPrincipal User user){
       Optional<Shop> optionalShop = shopRepository.findById(shopId);
        if(optionalShop.isPresent()){
            user.getPreferredShops().add(optionalShop.get());
            userRepository.save(user);

        }
       
        return ResponseEntity.accepted().build();

    }

    @PostMapping("/dislike/{shopId}")
    ResponseEntity<String> dislikeShop(@PathVariable String shopId, @AuthenticationPrincipal User user){

        Optional<Shop> optionalShop = shopRepository.findById(shopId);
        if(optionalShop.isPresent()){
            user.getDislikedShops().add(optionalShop.get());
            userRepository.save(user);
        }

        return ResponseEntity.accepted().build();
    }


}

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
    ResponseEntity<List<Shop>> preferredShops(@AuthenticationPrincipal User user)
    {

        return ok(user.getFavoriteShops());
    }

    @GetMapping("/like/{shopId}")
    ResponseEntity<String> likeShop(@PathVariable String shopId, @AuthenticationPrincipal User user){
       
        return ok("ok");

    }

    @PostMapping("/dislike/{email}/{shopId}")
    ResponseEntity<String> dislikeShop(@PathVariable String email, @PathVariable String shopId){
        return ok("ok");

    }


}

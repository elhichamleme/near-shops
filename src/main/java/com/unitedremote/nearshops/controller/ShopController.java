package com.unitedremote.nearshops.controller;

import com.google.common.collect.Lists;
import com.unitedremote.nearshops.model.entity.Shop;
import com.unitedremote.nearshops.model.entity.User;
import com.unitedremote.nearshops.model.repository.ShopRepository;
import com.unitedremote.nearshops.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/shops")
public class ShopController {
    @Autowired
    ShopRepository shopRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/near/{latitude}/{longitude}")
    public ResponseEntity<List<Shop>> nearShops(@PathVariable double latitude, @PathVariable double longitude, @AuthenticationPrincipal User user) {

        List<Shop> nearShops = new ArrayList<>(shopRepository.shopsOrderedByDistance(latitude, longitude));
        // remove disliked shops from nearby shops list
        user.getDislikedShops().forEach((shopId, date) -> {
            Optional<Shop> optionalShop = shopRepository.findById(shopId);
            if (optionalShop.isPresent()) {
                if ((new Date().getTime() - date.getTime()) / 3600000 < 2)
                    System.out.println(nearShops.remove(optionalShop.get()));
            }


        });

        System.out.println(nearShops.size());
        // remove liked shops from nearby shops list
        nearShops.removeAll(Lists.newArrayList(shopRepository.findAllById(user.getPreferredShops())));


        return ok(nearShops);


    }

    @GetMapping("/preferred")
    ResponseEntity<Iterable<Shop>> preferredShops(@AuthenticationPrincipal User user) {

        return ok(shopRepository.findAllById(user.getPreferredShops()));
    }

    @GetMapping("/like/{shopId}")
    ResponseEntity<String> likeShop(@PathVariable String shopId, @AuthenticationPrincipal User user) {
        Optional<Shop> optionalShop = shopRepository.findById(shopId);
        if (optionalShop.isPresent()) {
            user.getPreferredShops().add(optionalShop.get().getId());
            userRepository.save(user);

        }

        return ResponseEntity.accepted().build();

    }

    @GetMapping("/remove-from-preferred/{shopId}")
    ResponseEntity<Shop> removeFromPreferred(@PathVariable String shopId, @AuthenticationPrincipal User user) {

        Optional<Shop> optionalShop = shopRepository.findById(shopId);
        if (optionalShop.isPresent()) {
            user.getPreferredShops().remove(shopId);
            userRepository.save(user);
        }

        return ResponseEntity.accepted().build();
    }

    @GetMapping("/dislike/{shopId}")
    ResponseEntity<String> dislikeShop(@PathVariable String shopId, @AuthenticationPrincipal User user) {

        Optional<Shop> optionalShop = shopRepository.findById(shopId);
        if (optionalShop.isPresent()) {
            user.getDislikedShops().put(shopId, new Date());
            userRepository.save(user);
        }

        return ResponseEntity.accepted().build();
    }


}

package com.serkanguner.controller;

import com.serkanguner.constant.EndPoints;
import com.serkanguner.entity.Address;
import com.serkanguner.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(EndPoints.ADDRESS)
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;

    @PostMapping(EndPoints.SAVE)
    public ResponseEntity<String> save(@RequestBody Address address) {
        addressService.save(address);
        return ResponseEntity.ok("Address saved");
    }

    @PostMapping(EndPoints.DELETE)
    public ResponseEntity<String> delete(@RequestBody Address address) {
        addressService.delete(address);
        return ResponseEntity.ok("Address deleted");
    }

    @PutMapping(EndPoints.UPDATE)
    public ResponseEntity<String> update(@RequestBody Address address) {
        addressService.update(address);
        return ResponseEntity.ok("Address updated");
    }

    @GetMapping(EndPoints.FIND_BY_USERID)
    public ResponseEntity<Address> findByUserId(@RequestBody String userid) {
        return ResponseEntity.ok(addressService.findByUserId(userid));
    }

    @GetMapping(EndPoints.FINDBYID)
    public ResponseEntity<Address> findById(@RequestBody String id) {
        return ResponseEntity.ok(addressService.findById(id));
    }
}

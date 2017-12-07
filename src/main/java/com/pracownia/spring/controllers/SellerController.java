package com.pracownia.spring.controllers;

import com.pracownia.spring.entities.Product;
import com.pracownia.spring.entities.Seller;
import com.pracownia.spring.services.ProductService;
import com.pracownia.spring.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @RequestMapping(value = "/sellers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Seller> list(Model model) {
        return sellerService.listAllSellers();
    }

    @RequestMapping(value = "/seller/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Seller getByPublicId(@PathVariable("id") Integer publicId) {
        return sellerService.getSellerById(publicId);
    }

    @RequestMapping(value = "/seller", method = RequestMethod.POST)
    public ResponseEntity<Seller> create(@RequestBody @Valid @NotNull Seller seller) {
        sellerService.saveSeller(seller);
        return ResponseEntity.ok().body(seller);
    }

    @RequestMapping(value = "/seller", method = RequestMethod.PUT)
    public ResponseEntity<Void> edit(@RequestBody @Valid @NotNull Seller seller) {
            Seller sellerFromData = sellerService.getSellerById(seller.getId());
            if(Objects.nonNull(sellerFromData)) {
                sellerService.saveSeller(seller);
                return new ResponseEntity<>(HttpStatus.CREATED);
            } else
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/seller/{id}", method = RequestMethod.DELETE)
    public RedirectView delete(@PathVariable Integer id) {
        sellerService.deleteSeller(id);
        return new RedirectView("/api/products", true);
    }


}

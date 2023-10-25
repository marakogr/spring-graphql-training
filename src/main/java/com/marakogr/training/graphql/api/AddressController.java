package com.marakogr.training.graphql.api;

import com.marakogr.training.graphql.model.Address;
import com.marakogr.training.graphql.repository.AddressRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Collection;
import java.util.UUID;

@Controller
public class AddressController {
    private final AddressRepository addressRepository;

    public AddressController(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @MutationMapping
    public Address addAddress(final @Argument String fullAddress) {
        return this.addressRepository.save(new Address(UUID.randomUUID().toString(), fullAddress));
    }

    @QueryMapping
    public Collection<Address> allAddresses(){
        return this.addressRepository.findAll();
    }
}

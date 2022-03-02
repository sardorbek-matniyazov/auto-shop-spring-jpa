package com.example.gm.service;

import com.example.gm.entity.Address;
import com.example.gm.entity.AutoShop;
import com.example.gm.repository.AddressRepository;
import com.example.gm.repository.GMRepository;
import com.example.gm.repository.ShopRepository;
import com.example.gm.request.ShopRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ShopService {
    private final ShopRepository repo;
    private final AddressRepository addressRepo;
    private final GMRepository gmRepo;

    public List<AutoShop> getAll() {
        return repo.findAll();
    }

    public AutoShop get(Long id) {
        Optional<AutoShop> shop = repo.findById(id);
        return shop.orElseGet(() -> AutoShop.builder()
                .title("shop didn't find")
                .build());
    }

    public String add(ShopRequest request) {
        if (request.getTitle() == null ||
                request.getAddress() == null ||
                request.getGmId() == null) return "Oops, something went wrong";

        if (request.getTitle().equals(""))
            return "company name can't be empty";


        if (addressRepo.existsByTitleAndCityAndNumber(
                request.getAddress().getTitle(),
                request.getAddress().getCity(),
                request.getAddress().getNumber())
        ) return "address already exist";

        if (!gmRepo.existsById(request.getGmId()))
            return "th gm not found";
        repo.save(
                AutoShop.builder()
                        .title(request.getTitle())
                        .address(
                                addressRepo.save(
                                        Address.builder()
                                                .title(request.getAddress().getTitle())
                                                .city(request.getAddress().getCity())
                                                .number(request.getAddress().getNumber())
                                                .build()
                                )
                        )
                        .gm(gmRepo.getById(request.getGmId()))
                        .build()
        );
        return "successfully created";
    }

    public String edit(Long id, ShopRequest request) {
        if (request.getTitle() == null ||
                request.getAddress() == null ||
                request.getGmId() == null) return "Oops, something went wrong";
        if (!repo.existsById(id))
            return "shop not found";
        if (!gmRepo.existsById(request.getGmId()))
            return "gm not found";

        AutoShop shop = repo.getById(id);
        Address address = Address.builder()
                .id(shop.getAddress().getId())
                .title(request.getAddress().getTitle())
                .city(request.getAddress().getCity())
                .number(request.getAddress().getNumber())
                .build();

        if (!shop.getAddress().equals(address) &&
                addressRepo.existsByTitleAndCityAndNumber(
                        request.getAddress().getTitle(),
                        request.getAddress().getCity(),
                        request.getAddress().getNumber()

                )
        ) return "the address already used";

        shop.setGm(gmRepo.getById(request.getGmId()));
        shop.setAddress(addressRepo.save(address));
        shop.setTitle(request.getTitle());
        repo.save(shop);

        return "successfully edited";
    }

    public String delete(Long id) {
        if (!repo.existsById(id))
            return "shop not found";
        repo.deleteById(id);
        return "successfully deleted";
    }

    public List<AutoShop> getByGMId(Long id) {
        return repo.findAllByGmId(id);
    }
}

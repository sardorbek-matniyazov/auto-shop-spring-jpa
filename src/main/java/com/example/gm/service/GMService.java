package com.example.gm.service;

import com.example.gm.entity.Address;
import com.example.gm.entity.Director;
import com.example.gm.entity.GM;
import com.example.gm.repository.AddressRepository;
import com.example.gm.repository.DirectorRepository;
import com.example.gm.repository.GMRepository;
import com.example.gm.request.GMRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GMService {

    private final GMRepository repo;
    private final AddressRepository addressRepo;
    private final DirectorRepository directorRepo;

    public List<GM> getAll() {
        return repo.findAll();
    }

    public GM get(Long id) {
        Optional<GM> byId = repo.findById(id);
        return byId.orElseGet(() -> GM.builder()
                .compName("shop didn't find")
                .build());
    }

    public String add(GMRequest request) {
        if (request.getCompName() == null ||
                request.getAddress() == null ||
                request.getDirector() == null) return "Oops, something went wrong";

        if (request.getCompName().equals(""))
            return "company name can't be empty";

        if (addressRepo.existsByTitleAndCityAndNumber(
                request.getAddress().getTitle(),
                request.getAddress().getCity(),
                request.getAddress().getNumber())
        ) return "address already used";

        if (request.getDirector().getFirstname().equals("") ||
                request.getDirector().getLastname().equals("")) return "the director's name can't be empty";

        if (!directorRepo.existsByFirstnameAndLastname(
                request.getDirector().getFirstname(),
                request.getDirector().getLastname())
        ) directorRepo.save(
                Director.builder()
                        .firstname(request.getDirector().getFirstname())
                        .lastname(request.getDirector().getLastname())
                        .build()
        );

        repo.save(
                GM.builder()
                        .compName(request.getCompName())
                        .director(
                                directorRepo.getByFirstnameAndLastname(
                                        request.getDirector().getFirstname(),
                                        request.getDirector().getLastname()
                                ))
                        .address(
                                addressRepo.save(
                                        Address.builder()
                                                .title(request.getAddress().getTitle())
                                                .city(request.getAddress().getCity())
                                                .number(request.getAddress().getNumber())
                                                .build()
                                ))
                        .build()
        );
        return "successfully added";
    }

    public String edit(Long id, GMRequest request) {
        if (request.getCompName() == null ||
                request.getAddress() == null ||
                request.getDirector() == null) return "Oops, something went wrong";
        if (!repo.existsById(id))
            return "gm not found";

        GM gm = repo.getById(id);
        Address address = Address.builder()
                .id(gm.getAddress().getId())
                .title(request.getAddress().getTitle())
                .city(request.getAddress().getCity())
                .number(request.getAddress().getNumber())
                .build();


        if (!gm.getAddress().equals(address) &&
                addressRepo.existsByTitleAndCityAndNumber(
                        request.getAddress().getTitle(),
                        request.getAddress().getCity(),
                        request.getAddress().getNumber()

                )
        ) return "the address already used";

        Director director = directorRepo.existsByFirstnameAndLastname(
                request.getDirector().getFirstname(),
                request.getDirector().getLastname()
        ) ? directorRepo.getByFirstnameAndLastname(
                request.getDirector().getFirstname(),
                request.getDirector().getLastname()
        ) : directorRepo.save(
                Director.builder()
                        .firstname(request.getDirector().getFirstname())
                        .lastname(request.getDirector().getLastname())
                        .build()
        );

        gm.setDirector(director);
        gm.setAddress(addressRepo.save(address));
        gm.setCompName(request.getCompName());
        repo.save(gm);

        return "successfully edited";
    }

    public String delete(Long id) {
        if (!repo.existsById(id))
            return "gm not found";
        repo.deleteById(id);
        return "successfully deleted";
    }
}
// 23:04 02.03.2022 )
package com.webavance.exam.resource;

import com.webavance.exam.model.Mock;
import com.webavance.exam.repository.MockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/rest/mock")
public class MockResource {

    @Autowired
    MockRepository mockRepository;

    @PostMapping("/add")
    public Optional<Mock> create(@RequestBody final Mock mock) {
        mockRepository.save(mock);
        return mockRepository.findByEmail(mock.getEmail());
    }
}
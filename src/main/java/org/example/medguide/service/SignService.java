package org.example.medguide.service;

import lombok.RequiredArgsConstructor;
import org.example.medguide.model.Sign;
import org.example.medguide.repository.SignRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SignService {
    private final SignRepository signRepository;

    public List<Sign> getSigns() {
        return signRepository.findAll();
    }
}

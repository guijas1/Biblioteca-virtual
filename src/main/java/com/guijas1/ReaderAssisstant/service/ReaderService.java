package com.guijas1.ReaderAssisstant.service;


import com.guijas1.ReaderAssisstant.repository.ReadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReaderService {

    private final ReadRepository repository;



}

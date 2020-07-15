package com.penchev.perfume.service.impl;

import com.penchev.perfume.repository.BrandRepository;
import com.penchev.perfume.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;
}

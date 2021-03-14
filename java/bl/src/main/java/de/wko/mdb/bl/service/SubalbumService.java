package de.wko.mdb.bl.service;

import de.wko.mdb.data.repository.SubalbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubalbumService {

    @Autowired
    SubalbumRepository subalbumRepository;


}

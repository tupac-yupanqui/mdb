package de.wko.mdb.bl.service;

import de.wko.mdb.data.entity.ExtraEntity;
import de.wko.mdb.data.entity.FileEntity;
import de.wko.mdb.data.repository.ExtraRepository;
import de.wko.mdb.types.Extra;
import de.wko.mdb.types.FileObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExtraService {

    @Autowired
    ExtraRepository extraRepository;

    public Extra save(Extra extra) {
        Optional<ExtraEntity> eo = extraRepository.findById(extra.getId());
        if (eo.isPresent()) {
            ExtraEntity ee = eo.get();
            ee.fromType(extra);
            return extraRepository.save(ee).getType();
        } else if (extra.getId()==0){
            ExtraEntity ee = new ExtraEntity();
            ee.fromType(extra);
            return extraRepository.save(ee).getType();
        }

        return null;
    }

}

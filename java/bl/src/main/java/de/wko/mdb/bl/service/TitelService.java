package de.wko.mdb.bl.service;

import de.wko.mdb.data.entity.TitelEntity;
import de.wko.mdb.data.repository.TitelRepository;
import de.wko.mdb.types.Titel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TitelService {

    @Autowired
    TitelRepository titelRepository;

    public Titel getById(Long id) {
        Titel t = titelRepository.findById(id).get().getType();
        return t;
    }

    public Titel save(Titel titel) {
        Optional<TitelEntity> to = titelRepository.findById(titel.getId());
        if (to.isPresent()) {
            TitelEntity te = to.get();
            te.fromType(titel);
            return titelRepository.save(te).getType();
        } else if (titel.getId()==0){
            TitelEntity te = new TitelEntity();
            te.fromType(titel);
            return titelRepository.save(te).getType();
        }

        return null;
    }

}

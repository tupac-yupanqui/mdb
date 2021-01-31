package de.wko.mdb.bl.service;

import de.wko.mdb.data.entity.ArtistEntity;
import de.wko.mdb.data.entity.HostEntity;
import de.wko.mdb.data.repository.ArtistRepository;
import de.wko.mdb.data.repository.HostRepository;
import de.wko.mdb.types.Artist;
import de.wko.mdb.types.Host;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HostService {
    @Autowired
    HostRepository repo;

    public Host getHostById(Long id) {
        Optional<HostEntity> ho = repo.findById(id);
        return ho.isPresent() ? ho.get().getType() : null;
    }
    public Host getHostByName(String name) {
        HostEntity he = repo.findByName(name);
        return he==null ? null : he.getType();
    }
    public List<Host> getAllHosts() {
        Iterable<HostEntity> all = repo.findAll();
        List<Host> result = new ArrayList<>();
        for (HostEntity h : all) {
            result.add(h.getType());
        }
        return result;
    }
}

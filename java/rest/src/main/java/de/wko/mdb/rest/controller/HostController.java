package de.wko.mdb.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.wko.mdb.bl.service.HostService;
import de.wko.mdb.types.Host;
import de.wko.mdb.types.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HostController {
    @Autowired
    HostService hostservice;
    @Autowired
    private ObjectMapper jacksonObjectMapper;

    @GetMapping("/host/{id}")
    @ResponseBody
    public Host getById(@PathVariable Long id) {
        return hostservice.getHostById(id);
    }


    @GetMapping("/host")
    @ResponseBody
    public Host getByName(@RequestParam String name) {
        return hostservice.getHostByName(name);
    }

    @GetMapping("/hosts")
    @ResponseBody
    public List<Host> getAllHosts() {
        return hostservice.getAllHosts();
    }

    @PostMapping("/host/save")
    public ResponseEntity<?> save(@RequestBody Host host) throws AuthenticationException {
        Host result = hostservice.save(host);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/host/delete/{id}")
    public void delete(@PathVariable Long id) {
        hostservice.delete(id);
        return;
    }
}
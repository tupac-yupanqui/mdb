package de.wko.mdb.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.wko.mdb.bl.service.HostService;
import de.wko.mdb.types.Host;
import org.springframework.beans.factory.annotation.Autowired;
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
        System.out.println("Request ID "+id);
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


}

package project.controllers;

import project.pojo.request.DotRequest;
import project.entities.EntryEntity;
import project.services.entries.EntriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@RestController
@RequestMapping("/web-lab4/api/entries")
@CrossOrigin(origins = "*", maxAge = 3600)
public class EntriesController {

    @Autowired
    private EntriesService entriesService;

    @PostMapping("/check")
    public ResponseEntity<?> checkDot(@RequestBody DotRequest DotRequest, Principal principal){
        return ResponseEntity.ok(entriesService.addDot(DotRequest, principal.getName()));
    }

    @GetMapping("/getEntries")
    public ResponseEntity<?> getEntries( Principal principal){
        return ResponseEntity.ok(entriesService.getDots(principal.getName()));
    }

    @GetMapping("/getEntriesForGraph")
    public ResponseEntity<?> getEntries(@RequestParam("r") float r, Principal principal){
        return ResponseEntity.ok(entriesService.getUpdateRDots(r,principal.getName()));
    }

    @DeleteMapping("/clearEntries")
    public ResponseEntity<?> clearEntries( Principal principal){
        return ResponseEntity.ok(entriesService.deleteDots(principal.getName()));
    }
}

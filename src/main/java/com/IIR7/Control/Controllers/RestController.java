package com.IIR7.Control.Controllers;

import com.IIR7.Control.Models.Commande;
import com.IIR7.Control.Services.CommandeIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.web.bind.annotation.RestController

public class RestController {

    private final CommandeIMPL commandeIMPL;
    @Autowired
    public RestController(CommandeIMPL commandeIMPL) {
        this.commandeIMPL = commandeIMPL;
    }
    @GetMapping("/all")
    public ResponseEntity<List<Commande>> getAllCommandes() {
        List<Commande> commandes = commandeIMPL.listerCommandes();
        if (!commandes.isEmpty()) {
            return new ResponseEntity<>(commandes, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/creer")
    public ResponseEntity<Commande> creerCommande(@RequestBody Commande comm) {
        Commande createdCommande = commandeIMPL.AddCommande(comm);
        if (createdCommande != null) {
            return new ResponseEntity<>(createdCommande, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Commande> mettreAJourCommande(@PathVariable Long id, @RequestBody Commande updatedcmd) {
        Commande updated = commandeIMPL.update(id, updatedcmd);
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Or handle differently for not found scenario
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        commandeIMPL.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

package com.IIR7.Control.Controllers;

import com.IIR7.Control.Models.Commande;
import com.IIR7.Control.Services.CommandeIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
    public ResponseEntity<Commande> creerCommande(@RequestBody Commande nouvelleCommande) {
        Commande createdCommande = commandeIMPL.AddCommande(nouvelleCommande);
        if (createdCommande != null) {
            return new ResponseEntity<>(createdCommande, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

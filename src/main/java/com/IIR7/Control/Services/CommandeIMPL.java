package com.IIR7.Control.Services;

import com.IIR7.Control.Models.Commande;
import com.IIR7.Control.Repository.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommandeIMPL implements CommandeService{
    private final CommandeRepository commandeRepository;
    @Autowired
    public CommandeIMPL(CommandeRepository cmd){
        this.commandeRepository=cmd;
    }

    @Override
    public Commande AddCommande(Commande commande) {
        return commandeRepository.save(commande);


    }

    public List<Commande> listerCommandes() {
        return commandeRepository.findAll();
    }
}

package com.IIR7.Control.Services;

import com.IIR7.Control.Exceptions.ExceptionNullpasNull;
import com.IIR7.Control.Models.Commande;
import com.IIR7.Control.Repository.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommandeIMPL implements CommandeService{
    private final CommandeRepository commandeRepository;
    @Autowired
    public CommandeIMPL(CommandeRepository cmd){
        this.commandeRepository=cmd;
    }

    public Commande AddCommande(Commande commande)  {


        return commandeRepository.save(commande);
    }

    @Override
    public Commande update(Long id, Commande commande) {
        Optional<Commande> optionalCommande = commandeRepository.findById(id);
        if (optionalCommande.isPresent()) {
            Commande existingCommande = optionalCommande.get();
            existingCommande.setDescription(commande.getDescription());
            existingCommande.setQuantite(commande.getQuantite());
            existingCommande.setDate(commande.getDate());
            existingCommande.setMontant(commande.getMontant());
            return commandeRepository.save(existingCommande);
        } else {
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        Optional<Commande> optionalCommande = commandeRepository.findById(id);
        if (optionalCommande.isPresent()) {
            commandeRepository.delete(optionalCommande.get());
        } else {
        }
    }


    public List<Commande> listerCommandes() {
        return commandeRepository.findAll();
    }
}

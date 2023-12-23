package com.IIR7.Control.Services;

import com.IIR7.Control.Exceptions.ExceptionNullpasNull;
import com.IIR7.Control.Models.Commande;
import com.IIR7.Control.Repository.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CommandeIMPL implements CommandeService{
    private final CommandeRepository commandeRepository;
    private final int lastDaysToShow;
    @Autowired
    public CommandeIMPL(CommandeRepository cmd, @Value("${mes-config-ms.commandes-last}")int c){
        this.commandeRepository=cmd;
        this.lastDaysToShow=c;
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
    public List<Commande> getLatestCommands() {

        LocalDate startDate = LocalDate.now().minusDays(lastDaysToShow);
        Date startDateAsDate = Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        // Récupérer les commandes depuis startDateAsDate jusqu'à aujourd'hui
        return commandeRepository.findByDateGreaterThan(startDateAsDate);
    }
}

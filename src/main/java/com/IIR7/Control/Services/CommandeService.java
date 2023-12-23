package com.IIR7.Control.Services;

import com.IIR7.Control.Models.Commande;

import java.util.List;

public interface CommandeService {
     Commande AddCommande(Commande commande);
     List<Commande> listerCommandes();
}

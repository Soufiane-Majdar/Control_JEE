package com.IIR7.Control.Services;

import com.IIR7.Control.Exceptions.ExceptionNullpasNull;
import com.IIR7.Control.Models.Commande;

import java.util.List;

public interface CommandeService {
     Commande AddCommande(Commande commande) ;
     Commande update(Long id, Commande commande);
     void delete(Long id);
     List<Commande> listerCommandes();
}

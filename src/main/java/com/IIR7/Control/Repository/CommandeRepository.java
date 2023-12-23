package com.IIR7.Control.Repository;

import com.IIR7.Control.Models.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface CommandeRepository extends JpaRepository<Commande,Long> {
    @Override
    Optional<Commande> findById(Long aLong);
    List<Commande> findByDateGreaterThan(Date date);
}

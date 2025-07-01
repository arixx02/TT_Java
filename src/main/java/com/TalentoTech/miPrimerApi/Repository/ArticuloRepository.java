package com.TalentoTech.miPrimerApi.Repository;

import com.TalentoTech.miPrimerApi.Model.Articulo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticuloRepository extends JpaRepository<Articulo, Long> {
}
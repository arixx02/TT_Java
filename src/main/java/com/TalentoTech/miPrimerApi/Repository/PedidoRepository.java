package com.TalentoTech.miPrimerApi.Repository;

import com.TalentoTech.miPrimerApi.Model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}

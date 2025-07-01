package com.TalentoTech.miPrimerApi.Controller;
import com.TalentoTech.miPrimerApi.Model.Pedido;
import com.TalentoTech.miPrimerApi.Services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Pedido> crearPedido(@RequestBody Pedido pedido) {
        Pedido creado = pedidoService.crearPedido(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @GetMapping
    public List<Pedido> listarPedidos() {
        return pedidoService.listarPedidos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> obtenerPedido(@PathVariable Long id) {
        Pedido pedido = pedidoService.obtenerPedido(id);
        return ResponseEntity.ok(pedido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> actualizarPedido(@PathVariable Long id, @RequestBody Pedido datos) {
        Pedido actualizado = pedidoService.actualizarPedido(id, datos);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPedido(@PathVariable Long id) {
        pedidoService.eliminarPedido(id);
        return ResponseEntity.noContent().build();
    }
}


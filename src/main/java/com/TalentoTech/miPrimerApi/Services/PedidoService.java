package com.TalentoTech.miPrimerApi.Services;
import java.util.List;
import java.util.ArrayList;
import com.TalentoTech.miPrimerApi.Model.Pedido;
import com.TalentoTech.miPrimerApi.Model.Articulo;
import com.TalentoTech.miPrimerApi.Repository.PedidoRepository;
import com.TalentoTech.miPrimerApi.Repository.ArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepo;

    @Autowired
    private ArticuloRepository articuloRepo;

    public Pedido crearPedido(Pedido pedido) {
        double total = 0;

        // Validar que los artículos existan y calcular total
        List<Articulo> articulosValidos = new ArrayList<>();
        for (Articulo art : pedido.getArticulos()) {
            Articulo encontrado = articuloRepo.findById(art.getId())
                .orElseThrow(() -> new RuntimeException("Articulo no existe: " + art.getId()));
            articulosValidos.add(encontrado);
            total += encontrado.getPrecio();
        }

        pedido.setArticulos(articulosValidos);
        pedido.setTotal(total);

        return pedidoRepo.save(pedido);
    }

    public Pedido actualizarPedido(Long id, Pedido datos) {
        Pedido pedidoExistente = pedidoRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Pedido no encontrado con id " + id));

        pedidoExistente.setCliente(datos.getCliente());
        pedidoExistente.setFecha(datos.getFecha());

        double total = 0;
        List<Articulo> articulosValidos = new ArrayList<>();
        for (Articulo art : datos.getArticulos()) {
            Articulo encontrado = articuloRepo.findById(art.getId())
                .orElseThrow(() -> new RuntimeException("Articulo no existe: " + art.getId()));
            articulosValidos.add(encontrado);
            total += encontrado.getPrecio();
        }

        pedidoExistente.setArticulos(articulosValidos);
        pedidoExistente.setTotal(total);

        return pedidoRepo.save(pedidoExistente);
    }

    public void eliminarPedido(Long id) {
        if (!pedidoRepo.existsById(id)) {
            throw new RuntimeException("Pedido no existe con id " + id);
        }
        pedidoRepo.deleteById(id);
    }

    public List<Pedido> listarPedidos() {
        return pedidoRepo.findAll();
    }

    public Pedido obtenerPedido(Long id) {
        return pedidoRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Pedido no encontrado con id " + id));
    }
}

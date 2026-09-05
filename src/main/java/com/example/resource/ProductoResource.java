package com.example.resource;

import com.example.model.Producto;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Path("/api/productos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductoResource {

    private static final Logger LOG = Logger.getLogger(ProductoResource.class);

    // Almacenamiento en memoria (no se requiere base de datos)
    private final ConcurrentHashMap<Long, Producto> productos = new ConcurrentHashMap<>();
    private final AtomicLong secuencia = new AtomicLong(1);

    public ProductoResource() {
        // Datos iniciales de ejemplo
        Long id1 = secuencia.getAndIncrement();
        productos.put(id1, new Producto(id1, "Teclado mecánico", 45.99));
        Long id2 = secuencia.getAndIncrement();
        productos.put(id2, new Producto(id2, "Mouse inalámbrico", 19.50));
    }

    @GET
    public Response listar() {
        LOG.infof("GET /api/productos -> devolviendo %d productos", productos.size());
        List<Producto> lista = List.copyOf(productos.values());
        return Response.ok(lista).build();
    }

    @POST
    public Response crear(Producto nuevo) {
        Long id = secuencia.getAndIncrement();
        nuevo.id = id;
        productos.put(id, nuevo);
        LOG.infof("POST /api/productos -> producto creado: id=%d nombre=%s precio=%s",
                nuevo.id, nuevo.nombre, nuevo.precio);
        return Response.status(Response.Status.CREATED).entity(nuevo).build();
    }
}

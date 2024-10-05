package vn.edu.iuh.fit.backend.api;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import org.jboss.weld.context.ejb.Ejb;
import vn.edu.iuh.fit.backend.business.ProductBean;
import vn.edu.iuh.fit.backend.repositories.entities.Product;

@Path("/products")
public class ProductResourse {
    @Ejb
    private ProductBean productBean;

    @GET
    public Response getProducts() {
        return Response.ok(productBean.getProducts()).build();
    }

    @GET
    @Path("/{id}")
    public Response getProduct(@PathParam("id") int id) {
        return Response.ok(productBean.getProduct(id)).build();
    }

    @POST
    public Response createProduct(Product product) {
        productBean.createProduct(product);
        return Response.ok(product).build();
    }
}

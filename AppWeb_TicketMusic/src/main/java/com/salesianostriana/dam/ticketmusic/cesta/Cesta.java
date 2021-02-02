package com.salesianostriana.dam.ticketmusic.cesta;

import java.util.Map;

import com.salesianostriana.dam.ticketmusic.model.Entrada;

public interface Cesta {
	
	void addToCesta(Entrada e);
	
	void addToCesta(Long id);
	
	/**
	 *  Eliminar el producto del carrito
	 *  @param id
	 */
	void removeFromCesta(Long id);
	void removeFromCarrito(Entrada e);
	
	
	/**
	 * Vaciar carrito de productos
	 */
	void clear();

	/**
	 * Devuelve los productos del carrito en un Map que
	 * no se puede modificar
	 * NO QUEREMOS QUE NADIE CAMBIE ESTA COLECCIÓN
	 * DESDE FUERA DE LA CLASE; QUEREMOS QUE LO HAGA
	 * A TRAVÉS DE LOS MÉTODOS addToCarrito o removeFromCarrito
	 * @return
	 */
	Map<Entrada, Integer> getCesta();
	

	/**
	 * Número de productos diferentes en el carrito
	 * @return
	 */
	int numeroTotalProductosDiferentes();

	/**
	 * Número de unidades de productos en el carrito
	 * Si del producto P1 solicito una unidad, y de P2 solicito dos unidades
	 * este método debe devolver 3
	 * @return
	 */
	int numeroTotalDeUnidades();
	
	double subTotal();

	/**
	 * Importe total de lo que se incluye en el carrito.
	 * 
	 * @return
	 */
	double importeTotal(double gastosGestion);

}

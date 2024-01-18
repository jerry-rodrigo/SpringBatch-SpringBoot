package com.batch.SpringBatchApplication.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "detalles_ventas")
public class DetalleVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_venta")
    private Venta venta;
    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;
    private int cantidad;
}

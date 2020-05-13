package com.example.cesa;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Factura {
    private int num;
    private String cif;
    private String razon;
    private String descripcion;
    private float base;
    private float iva;
    private float total;
    private Date fechaFac;
    private Date fechaVen;

    //Constructores
    public Factura() {
        this.num = 0;
        this.cif = "";
        this.razon = "";
        this.descripcion = "";
        this.base = 0.0f;
        this.iva = 0.0f;
        this.total = 0.0f;
        this.fechaFac = new Date();
        this.fechaVen = new Date();
    }

    public Factura(int num, String cif, String razon, String descripcion, float base, float iva, float total, Date fechaFac, Date fechaVen) {
        this.num = num;
        this.cif = cif;
        this.razon = razon;
        this.descripcion = descripcion;
        this.base = base;
        this.iva = iva;
        this.total = total;
        this.fechaFac = fechaFac;
        this.fechaVen = fechaVen;
    }

    //Setters y Getters
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getRazon() {
        return razon;
    }

    public void setRazon(String razon) {
        this.razon = razon;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getBase() {
        return base;
    }

    public void setBase(float base) {
        this.base = base;
    }

    public float getIva() {
        return iva;
    }

    public void setIva(float iva) {
        this.iva = iva;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Date getFechaFac() {
        return fechaFac;
    }

    public void setFechaFac(Date fechaFac) {
        this.fechaFac = fechaFac;
    }

    public Date getFechaVen() {
        return fechaVen;
    }

    public void setFechaVen(Date fechaVen) {
        this.fechaVen = fechaVen;
    }

    //Métodos propios
    public File toFile() throws IOException {
        String contenido = "num: " + this.num +
                            "\ncif: " + this.cif +
                            "\nraz: " + this.razon +
                            "\ndes: " + this.descripcion +
                            "\nbas: " + this.base +
                            "\niva: " + this.iva +
                            "\ntot: " + this.total +
                            "\nfec: " + this.fechaFac +
                            "\nven: " + this.fechaVen;

        File file = new File("factura.txt");
        FileWriter fic = new FileWriter(file);
        char[] cad = contenido.toCharArray();

        for (int i = 0; i < cad.length; i++)
            fic.write(cad[i]);

        return file;
    }
}

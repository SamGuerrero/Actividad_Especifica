CREATE DATABASE CESA_FACTURACION;
USE CESA_FACTURACION;

CREATE TABLE FACT_PROV(
cif_proveedor VARCHAR(9) NOT NULL,
raz_proveedor VARCHAR(50),
num_factura INTEGER(6) NOT NULL,
des_factura VARCHAR(50),
bas_imponible DOUBLE NOT NULL,
iva_importe DOUBLE NOT NULL,
tot_importe DOUBLE NOT NULL,
fec_factura DATETIME NOT NULL,
fec_vencimiento DATETIME NOT NULL,
PRIMARY KEY (cif_proveedor, num_factura)
);

CREATE TABLE PROV_COMP(
cif_proveedor VARCHAR(9) NOT NULL,
raz_proveedor VARCHAR(50),
reg_notarial INTEGER(7) NOT NULL,
seg_responsabilidad INTEGER(7) NOT NULL,
seg_importe DOUBLE NOT NULL,
fec_homologacio DATETIME NOT NULL,
PRIMARY KEY(cif_proveedor)
);

ALTER TABLE FACT_PROV ADD CONSTRAINT cif_proveedor FOREIGN KEY (cif_proveedor)
REFERENCES PROV_COMP(cif_proveedor);
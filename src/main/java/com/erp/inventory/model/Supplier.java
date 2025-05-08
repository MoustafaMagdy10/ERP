package com.erp.inventory.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "suppliers")
public class Supplier extends Customer {

}

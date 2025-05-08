package com.erp.inventory.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Client")
public class Client extends Customer {
}

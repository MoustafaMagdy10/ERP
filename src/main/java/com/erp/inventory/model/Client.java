package com.erp.inventory.model;

import com.erp.inventory.model.Customer;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "client")
public class Client extends Customer {
}

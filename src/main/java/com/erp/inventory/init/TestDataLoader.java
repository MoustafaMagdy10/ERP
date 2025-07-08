package com.erp.inventory.init;

import com.erp.inventory.model.Client;
import com.erp.inventory.model.Invoice;
import com.erp.inventory.model.InvoiceItem;
import com.erp.inventory.model.Product;
import com.erp.inventory.model.enums.InvoiceType;
import com.erp.inventory.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

////package com.erp.init;
//package com.erp.inventory.init;
//
//import com.erp.model.enums.InvoiceType;
//import com.erp.repository.InvoiceRepository;
//import com.erp.inventory.repository.ProductRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//import org.springframework.beans.factory.annotation.Autowired;
//import java.math.BigDecimal;
//import java.time.LocalDate;
//import com.erp.inventory.repository.InventoryItemRepository;
//import java.util.List;
//
@Component
public class TestDataLoader implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private InvoiceItemRepository invoiceItemRepository;
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public void run(String... args) throws Exception {
//        System.out.println("Loading test data...");
//
//        // Creating a test Product
//
//        Client client = new Client();
//        client.setName("Magdy");
//        client.setEmail("magdy@gmail.com");
//        client.setPhone("123456789");
//        client.setCreatedAt(LocalDate.now().atStartOfDay());
//        clientRepository.save(client);
//
//
//        Product product = new Product();
//        product.setName("Laptop");
//        productRepository.save(product);
//
//        // Creating an Invoice
//        Invoice invoice = new Invoice();
//        invoice.setInvoiceType(InvoiceType.SELL);
//        invoice.setTotalAmount(new BigDecimal("1200.00"));
//        invoice.setClient(client);
//
//        // Creating an InvoiceItem
//        InvoiceItem item = new InvoiceItem();
//        item.setInvoice(invoice);
//        item.setProduct(product);
//        item.setQuantity(1);
//        item.setPrice(BigDecimal.valueOf(500));
//
//
//        clientRepository.save(client);
//        invoiceRepository.save(invoice);
//        invoiceItemRepository.save(item);
//
//        clientRepository.findAll().forEach(System.out::println);
//        invoiceItemRepository.findAll().forEach(System.out::println);
//
//        System.out.println("✅ Test invoice and product saved!");
    }
}

package com.erp.inventory.service;

import com.erp.inventory.dto.InvoiceDTO;
import com.erp.inventory.dto.InvoiceItemDTO;
import com.erp.inventory.model.Client;
import com.erp.inventory.model.Invoice;
import com.erp.inventory.model.Product;
import com.erp.inventory.model.Supplier;
import com.erp.inventory.repository.ClientRepository;
import com.erp.inventory.repository.InvoiceRepository;
import com.erp.inventory.repository.ProductRepository;
import com.erp.inventory.repository.SupplierRepository;

import com.erp.inventory.model.enums.*;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Transactional
class InvoiceServiceTest {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Test
    void testCreateSaleInvoice_success() {
        // Arrange: add client and product
        System.out.println("Loading test data...");

        Client client = new Client();
        client.setName("Moustafa");
        client.setEmail("moustafa@123");
        client = clientRepository.save(client);

        Product product = new Product();
        product.setName("Laptop");
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());
        product.setPrice(BigDecimal.valueOf(1500));
        product = productRepository.save(product);

        InvoiceItemDTO item = new InvoiceItemDTO();
        item.setProductId(product.getId());
        item.setQuantity(2);
        item.setPrice(product.getPrice());

        InvoiceDTO dto = new InvoiceDTO();
        dto.setInvoiceType(InvoiceType.SELL);
        dto.setClientId(client.getId());
        dto.setItems(List.of(item));
        dto.setDueDate(LocalDate.now().plusDays(5));

        // Act
        Invoice invoice = invoiceService.createInvoice(dto);

        // Assert
        Assertions.assertNotNull(invoice.getId());
        Assertions.assertEquals(BigDecimal.valueOf(3000), invoice.getTotalAmount());
        Assertions.assertEquals(1, invoice.getItems().size());
        Assertions.assertEquals(client.getId(), invoice.getClient().getId());

        System.out.println("✅ Test invoice and product saved!");

    }

    @Test
    void testCreatePurchaseInvoice_success() {
        Supplier supplier = new Supplier();
        supplier.setName("HP Supplier");
        supplier.setEmail("hp@supplier.com");
        supplier = supplierRepository.save(supplier);

        Product product = new Product();
        product.setName("Printer");
        product.setPrice(BigDecimal.valueOf(500));
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());
        product = productRepository.save(product);

        InvoiceItemDTO item = new InvoiceItemDTO();
        item.setProductId(product.getId());
        item.setQuantity(1);
        item.setPrice(product.getPrice());

        InvoiceDTO dto = new InvoiceDTO();
        dto.setInvoiceType(InvoiceType.PURCHASE);
        dto.setSupplierId(supplier.getId());
        dto.setItems(List.of(item));
        dto.setDueDate(LocalDate.now().plusDays(3));

        Invoice invoice = invoiceService.createInvoice(dto);

        Assertions.assertNotNull(invoice.getId());
        Assertions.assertEquals(supplier.getId(), invoice.getSupplier().getId());
        Assertions.assertEquals(BigDecimal.valueOf(500), invoice.getTotalAmount());
    }


    @Test
    void testCreateInvoice_multipleItems() {
        Client client = new Client();
        client.setName("Sara");
        client = clientRepository.save(client);

        Product p1 = new Product();
        p1.setName("Mouse");
        p1.setPrice(BigDecimal.valueOf(100));
        p1.setCreatedAt(LocalDateTime.now());
        p1.setUpdatedAt(LocalDateTime.now());
        p1 = productRepository.save(p1);

        Product p2 = new Product();
        p2.setName("Keyboard");
        p2.setPrice(BigDecimal.valueOf(200));
        p2.setCreatedAt(LocalDateTime.now());
        p2.setUpdatedAt(LocalDateTime.now());
        p2 = productRepository.save(p2);

        InvoiceItemDTO item1 = new InvoiceItemDTO();
        item1.setProductId(p1.getId());
        item1.setQuantity(1);
        item1.setPrice(p1.getPrice());

        InvoiceItemDTO item2 = new InvoiceItemDTO();
        item2.setProductId(p2.getId());
        item2.setQuantity(2);
        item2.setPrice(p2.getPrice());

        InvoiceDTO dto = new InvoiceDTO();
        dto.setInvoiceType(InvoiceType.SELL);
        dto.setClientId(client.getId());
        dto.setItems(List.of(item1, item2));
        dto.setDueDate(LocalDate.now().plusDays(7));

        Invoice invoice = invoiceService.createInvoice(dto);

        Assertions.assertEquals(BigDecimal.valueOf(500), invoice.getTotalAmount());
        Assertions.assertEquals(2, invoice.getItems().size());
    }


}

package com.erp.inventory.service;

import com.erp.inventory.dto.InvoiceDTO;
import com.erp.inventory.dto.InvoiceItemDTO;
import com.erp.inventory.model.InventoryItem;
import com.erp.inventory.model.Invoice;
import com.erp.inventory.model.InvoiceItem;
import com.erp.inventory.model.Product;
import com.erp.inventory.model.enums.InvoiceStatus;
import com.erp.inventory.model.enums.InvoiceType;
import com.erp.inventory.repository.ClientRepository;
import com.erp.inventory.repository.InvoiceRepository;
import com.erp.inventory.repository.ProductRepository;
import com.erp.inventory.repository.SupplierRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;
    private final SupplierRepository ClientRepository;
    private final ClientRepository clientRepository;


    @Transactional
    public Invoice createInvoice(InvoiceDTO dto){
        Invoice invoice = new Invoice();

        invoice.setInvoiceType(dto.getInvoiceType());
        invoice.setCreatedAt(LocalDateTime.now());
        invoice.setDueDate(dto.getDueDate());
        invoice.setStatus(InvoiceStatus.PENDING);


        if(dto.getInvoiceType()== InvoiceType.SELL){
            if(dto.getClientId()==null){
                throw new IllegalArgumentException("client is required for Sale Invoice");
            }

            invoice.setClient(clientRepository.findById(dto.getClientId()).orElseThrow(()->new IllegalArgumentException("client not found")));
        }
        else if(dto.getInvoiceType()==InvoiceType.PURCHASE){
            if(dto.getSupplierId()==null){
                throw new IllegalArgumentException("supplier is required for Sale Invoice");
            }

            invoice.setSupplier(supplierRepository.findById(dto.getSupplierId()).orElseThrow(()->new IllegalArgumentException("Supplier not found")));

        }
        List<InvoiceItem> items = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (InvoiceItemDTO itemDTO : dto.getItems()) {
            Product product = productRepository.findById(itemDTO.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            InvoiceItem item = new InvoiceItem();
            item.setInvoice(invoice);
            item.setProduct(product);
            item.setQuantity(itemDTO.getQuantity());
            item.setPrice(itemDTO.getPrice());

            BigDecimal itemTotal = itemDTO.getPrice().multiply(BigDecimal.valueOf(itemDTO.getQuantity()));
            total = total.add(itemTotal);

            items.add(item);
        }

        invoice.setItems(items);
        invoice.setTotalAmount(total);

        return invoiceRepository.save(invoice);
    }
}

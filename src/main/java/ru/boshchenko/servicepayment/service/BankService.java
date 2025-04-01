package ru.boshchenko.servicepayment.service;

import org.springframework.stereotype.Service;
import ru.boshchenko.servicepayment.dto.BankResponse;

@Service
public interface BankService {

    BankResponse paymentProcessing(String cardUser);
}

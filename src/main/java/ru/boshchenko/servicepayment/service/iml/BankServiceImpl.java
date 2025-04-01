package ru.boshchenko.servicepayment.service.iml;

import org.springframework.stereotype.Service;
import ru.boshchenko.servicepayment.dto.BankResponse;
import ru.boshchenko.servicepayment.service.BankService;

@Service
public class BankServiceImpl implements BankService {

    @Override
    public BankResponse paymentProcessing(String cardUser) {
        return new BankResponse(
                Boolean.TRUE,
                "txn_18eK7xL7Kb9qk2"
        );
    }
}

package com.example.nobsv2.transaction;

import com.example.nobsv2.Command;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class TransferService implements Command<TransferDTO, String> {

    private final BankAccountRepository bankAccountRepository;

    public TransferService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }


    @Override
    public ResponseEntity<String> execute(TransferDTO transfer) {
        System.out.println("USERS: \n" + transfer.getFramUser() + "\n" + transfer.getTaUser() + "\n");
        Optional<BankAccount> fromAccount = bankAccountRepository.findById(transfer.getFramUser());
        Optional<BankAccount> toAccount = bankAccountRepository.findById(transfer.getTaUser());;
        System.out.println("testing pass");
        if (fromAccount.isEmpty() || toAccount.isEmpty()) {
            throw new RuntimeException("User not found"); //can be custom exception
        }

        BankAccount from = fromAccount.get();
        BankAccount to = toAccount.get();

        //add & deduct
        add(to, transfer.getAmount());

        //At this point, have added money but not checked if enough to transfer
        System.out.println("after adding, before deducting");
        System.out.println(bankAccountRepository.findById(to.getName())); //logging statement maybe?
        deduct(from, transfer.getAmount());

        return ResponseEntity.ok("Success");
    }

    private void deduct(BankAccount bankAccount, double amount) {
        if (bankAccount.getBalance() < amount) {
            throw new RuntimeException("Not enough money");
        }
        bankAccount.setBalance(bankAccount.getBalance() - amount);
    }

    private void add(BankAccount bankAccount, double amount) {
        bankAccount.setBalance(bankAccount.getBalance() + amount);
    }
}

package com.nicordesigns.finance.model;

import org.springframework.data.repository.CrudRepository;

public interface BankingTransactionRepository extends CrudRepository<BankingTransaction, Long> {

}
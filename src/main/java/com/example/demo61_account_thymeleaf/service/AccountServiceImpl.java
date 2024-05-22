package com.example.demo61_account_thymeleaf.service;

import com.example.demo61_account_thymeleaf.entity.Account;
import com.example.demo61_account_thymeleaf.repository.AccountRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// implement
@Component
public class AccountServiceImpl implements AccountService {
    // c1: @Autowired
    private AccountRepository accountRepo;

    // c2: constructor
    public AccountServiceImpl(AccountRepository accountRepo) {
        this.accountRepo = accountRepo;
    }

    @Override
    public List<Account> getAll() {
        List<Account> accounts = accountRepo.findAll();
        return accounts;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepo.getDataByUsername(username);

        if (account == null) {
            throw new UsernameNotFoundException("Account not found");
        }
        List<GrantedAuthority> listRole = new ArrayList<>();
        listRole.add(new SimpleGrantedAuthority(account.getRole()));
        // phân quyền: role = ADMIN hoặc role = USER
        return new User(username, account.getPassword(), listRole);
    }
}

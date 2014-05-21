package app.service.impl;

import app.domain.Account;
import app.repository.AccountRepository;
import app.service.impl.AccountServiceImpl;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;

import javax.persistence.UniqueConstraint;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;

import static java.lang.System.*;
import static java.lang.System.out;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceImplTest {

    private static String ACCOUNT_NUMBER = "12345678901234";
    private static String ACCOUNT_NAME = "NAME";
    private static BigDecimal ACCOUNT_MONEY_AMOUNT = new BigDecimal("100.00");
    private static Account ACCOUNT = new Account(){{
        setName(ACCOUNT_NAME);
        setNumber(ACCOUNT_NUMBER);
        setMoneyAmount(ACCOUNT_MONEY_AMOUNT);
    }};

    @InjectMocks
    private AccountServiceImpl accountService;

    @Mock
    private AccountRepository accountRepository;

    @Rule
    public ExpectedException exception = ExpectedException.none();


    @Test
    public void testFindByNumberSuccess() throws Exception {
        when(accountRepository.findByNumber(ACCOUNT_NUMBER)).thenReturn(Arrays.asList(ACCOUNT));

        out.println(" testFindByNumberSuccess");
//        ACCOUNT.setName("AAAA");

        Account actualAccount = accountService.findByNumber(ACCOUNT_NUMBER);

        assertThat(actualAccount, is(ACCOUNT));
        verify(accountRepository, times(1)).findByNumber(ACCOUNT_NUMBER);
    }


    @Test
    public void testFindByNumberFail() throws Exception {
        out.println(" testFindByNumberFail");
        exception.expect(EmptyResultDataAccessException.class);
        exception.expectMessage("No account found for number " + ACCOUNT_NUMBER);

        when(accountRepository.findByNumber(ACCOUNT_NUMBER)).thenReturn(Collections.<Account>emptyList());

        assertThat(ACCOUNT.getName() , is(ACCOUNT_NAME));

        accountService.findByNumber(ACCOUNT_NUMBER);
    }



    @Test
    public void testSaveSuccess() throws Exception {
        out.println(" testSaveSuccess");
        when(accountRepository.save(ACCOUNT)).thenReturn(ACCOUNT);

        accountService.save(ACCOUNT);

        assertThat(ACCOUNT.getName() , is(ACCOUNT_NAME));

        verify(accountRepository, times(1)).save(ACCOUNT);
    }


    @Test
    public void testSaveFail() throws Exception {
        out.println(" testSaveFail");
        exception.expect(RuntimeException.class);
        when(accountRepository.save(ACCOUNT)).thenThrow(RuntimeException.class);

        accountService.save(ACCOUNT);

        assertThat(ACCOUNT.getName() , is(ACCOUNT_NAME));

        verify(accountRepository, times(1)).save(ACCOUNT);
    }



    @Test
    public void testGetAmountSuccess() throws Exception{
        out.println(" testGetAmountSuccess");

        when(accountRepository.findByNumber(ACCOUNT_NUMBER)).thenReturn(Arrays.asList(ACCOUNT));

        BigDecimal amount = accountService.getAmount(ACCOUNT_NUMBER);

        assertThat(amount , is(ACCOUNT.getMoneyAmount()));

        verify(accountRepository, times(1)).findByNumber(ACCOUNT_NUMBER);
    }


    @Test
    public void testGetAmountNoAccount() throws Exception{
        out.println(" testGetAmountNoAccount");

        exception.expect(EmptyResultDataAccessException.class);
        exception.expectMessage("No account found for number " + ACCOUNT_NUMBER);

        when(accountRepository.findByNumber(ACCOUNT_NUMBER)).thenReturn(Collections.<Account>emptyList());

        BigDecimal amount = accountService.getAmount(ACCOUNT_NUMBER);

        assertThat(amount , is(ACCOUNT.getMoneyAmount()));

        verify(accountRepository, times(1)).findByNumber(ACCOUNT_NUMBER);

    }
}
package dev.patika.creditscoreserver.business.services;

import dev.patika.creditscoreserver.api.exceptionhandler.PersonCreditScoreAlreadyExistsException;
import dev.patika.creditscoreserver.api.exceptionhandler.PersonCreditScoreNotFoundException;
import dev.patika.creditscoreserver.entities.CreditScore;
import dev.patika.creditscoreserver.repositories.CreditScoreRepository;
import org.apache.commons.lang.NotImplementedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreditScoreServiceTest {

    @Mock
    CreditScoreRepository creditScoreRepository;
    @InjectMocks
    CreditScoreService creditScoreService;

    @BeforeEach
    void setUp() {
        lenient().when(creditScoreRepository.save(any(CreditScore.class))).thenAnswer(new Answer<CreditScore>() {
            @Override
            public CreditScore answer(InvocationOnMock invocationOnMock) throws Throwable {
                return (CreditScore) invocationOnMock.getArguments()[0];
            }
        });
    }

    @Test
    void findAll() {
        List<CreditScore> expected = new ArrayList<>();
        when(creditScoreRepository.findAll()).thenReturn(expected);

        List<CreditScore> actual = creditScoreService.findAll();

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected.size(), actual.size()),
                () -> assertEquals(expected, actual)
        );
    }

    @Test
    void findById() {
        CreditScore expected=new CreditScore();
        when(creditScoreRepository.findById(anyLong())).thenReturn(Optional.of(expected));

        CreditScore actual=creditScoreService.findById(anyLong());

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );
    }

    @Test
    void findByTcNumber_if_exist_already() {
        CreditScore expected=new CreditScore();
        when(creditScoreRepository.isExistByTcNumber(anyLong())).thenReturn(true);
        when(creditScoreRepository.findByTcNumber(anyLong())).thenReturn(expected);

        CreditScore actual=creditScoreService.findByTcNumber(anyLong());

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );
    }

    @ParameterizedTest
    @ValueSource(longs = {
            87331802800L,
            81069019486L,
            78252644952L,
            65998125746L,
            32700570408L,
            66232895876L,
            37486464552L})
    void findByTcNumber_if_does_not_exist(long tcNumber) {
        when(creditScoreRepository.isExistByTcNumber(anyLong())).thenReturn(false);
        when(creditScoreRepository.findByTcNumber(anyLong())).thenAnswer(new Answer<CreditScore>() {
            @Override
            public CreditScore answer(InvocationOnMock invocationOnMock) throws Throwable {
                long creditScore;
                switch ((int) (((Long)invocationOnMock.getArguments()[0])%10)) {
                    case 2:
                        creditScore=550;
                        break;
                    case 4:
                        creditScore=1000;
                        break;
                    case 6:
                        creditScore=400;
                        break;
                    case 8:
                        creditScore=900;
                        break;
                    case 0:
                        creditScore=2000;
                        break;
                    default:
                        throw new NotImplementedException();
                }
                return new CreditScore((Long)invocationOnMock.getArguments()[0],creditScore);
            }
        });
        CreditScore actual=creditScoreService.findByTcNumber(tcNumber);
        double expectedCreditScore;
        switch ((int) (tcNumber%10)){
            case 2:
                expectedCreditScore=550;
                break;
            case 4:
                expectedCreditScore=1000;
                break;
            case 6:
                expectedCreditScore=400;
                break;
            case 8:
                expectedCreditScore=900;
                break;
            case 0:
                expectedCreditScore=2000;
                break;
            default:
                throw new NotImplementedException();
        }
        System.out.println(actual);
        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expectedCreditScore, actual.getCreditScore())
        );
    }

    @Test
    void save() {
        CreditScore expected=new CreditScore();
        when(creditScoreRepository.isExistByTcNumber(anyLong())).thenReturn(false);

        CreditScore actual=creditScoreService.save(expected);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );
    }

    @Test
    void save_throw_credit_score_already_exists(){
        CreditScore expected=new CreditScore();
        when(creditScoreRepository.isExistByTcNumber(anyLong())).thenReturn(true);

        assertThrows(PersonCreditScoreAlreadyExistsException.class,()->creditScoreService.save(expected));
    }

    @Test
    void update() {
        CreditScore expected=new CreditScore();
        when(creditScoreRepository.isExistByTcNumber(anyLong())).thenReturn(true);

        CreditScore actual=creditScoreService.update(expected);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual)
        );
    }

    @Test
    void update_throw_credit_score_not_found(){
        CreditScore expected=new CreditScore();
        when(creditScoreRepository.isExistByTcNumber(anyLong())).thenReturn(false);

        assertThrows(PersonCreditScoreNotFoundException.class,()->creditScoreService.update(expected));
    }

}
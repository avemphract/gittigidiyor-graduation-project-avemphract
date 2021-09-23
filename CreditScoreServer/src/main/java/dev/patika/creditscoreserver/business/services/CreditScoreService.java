package dev.patika.creditscoreserver.business.services;

import dev.patika.creditscoreserver.api.exceptionhandler.PersonCreditScoreAlreadyExistsException;
import dev.patika.creditscoreserver.api.exceptionhandler.PersonCreditScoreNotFoundException;
import dev.patika.creditscoreserver.business.dto.CreditScoreDto;
import dev.patika.creditscoreserver.business.mappers.CreditScoreMapper;
import dev.patika.creditscoreserver.entities.CreditScore;
import dev.patika.creditscoreserver.repositories.CreditScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CreditScoreService implements BaseService<CreditScoreDto,CreditScore> {
    private final CreditScoreRepository creditScoreRepository;
    private final CreditScoreMapper mapper;

    @Autowired
    public CreditScoreService(CreditScoreRepository creditScoreRepository, CreditScoreMapper creditScoreMapper) {
        this.creditScoreRepository = creditScoreRepository;
        this.mapper = creditScoreMapper;
    }

    @Override
    public List<CreditScore> findAll() {
        List<CreditScore> list = new ArrayList<>();
        creditScoreRepository.findAll().forEach(list::add);
        return list;
    }

    @Override
    public CreditScore findById(long id) {
        Optional<CreditScore> optional=creditScoreRepository.findById(id);
        return optional.orElse(null);
    }

    public CreditScore findByTcNumber(long tcNumber){
        if (!creditScoreRepository.isExistByTcNumber(tcNumber)){
            long creditScore;
            switch ((int) (tcNumber%10)) {
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
                    throw new RuntimeException();

            }
            save(new CreditScore(tcNumber,creditScore));
        }
        return creditScoreRepository.findByTcNumber(tcNumber);
    }

    @Override
    public CreditScore save(CreditScore object) {
        if (creditScoreRepository.isExistByTcNumber(object.getTcNumber()))
            throw new PersonCreditScoreAlreadyExistsException();
        return creditScoreRepository.save(object);
    }

    @Override
    public CreditScore update(CreditScore object) {
        if (!creditScoreRepository.isExistByTcNumber(object.getTcNumber()))
            throw new PersonCreditScoreNotFoundException();
        return creditScoreRepository.save(object);
    }

    @Override
    public CreditScore delete(CreditScore object) {
        CreditScore creditScore=creditScoreRepository.findByTcNumber(object.getTcNumber());
        if (creditScore==null)
            throw new PersonCreditScoreNotFoundException();
        creditScoreRepository.delete(creditScore);
        return object;
    }

    @Override
    public CreditScoreDto toDto(CreditScore creditScore) {
        return mapper.toDto(creditScore);
    }

    @Override
    public CreditScore toEntity(CreditScoreDto creditScoreDto) {
        return mapper.toEntity(creditScoreDto);
    }
}
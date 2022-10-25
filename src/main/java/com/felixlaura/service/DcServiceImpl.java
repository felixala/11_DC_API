package com.felixlaura.service;

import com.felixlaura.binding.*;
import com.felixlaura.entity.*;
import com.felixlaura.repository.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DcServiceImpl implements DcService{

    @Autowired
    private DcCaseRepo dcCaseRepo;

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private DcIncomeRepo incomeRepo;

    @Autowired
    private DcEducationRepo educationRepo;

    @Autowired
    private DcChildrenRepo childrenRepo;

    @Autowired
    private CitizenAppRepository citizenAppRepository;

    @Override
    public Long loadCaseNum(Integer appId) {
        Optional<CitizenAppEntity> app = citizenAppRepository.findById(appId);

        if(app.isPresent()){
            DcCaseEntity entity = new DcCaseEntity();
            entity.setAppId(appId);
            dcCaseRepo.save(entity);

            return entity.getCaseNum();
        }
        return 0L;
    }

    @Override
    public Map<Integer,String> getPlanNames() {

        List<PlanEntity> findAll = planRepository.findAll();
        Map<Integer, String> planMap = new HashMap<>();

        for(PlanEntity entity : findAll){
            planMap.put(entity.getPlanId(), entity.getPlanName());
        }
        return planMap;
    }

    @Override
    public Long savePlanSelection(PlanSelection planSelection) {
        Optional<DcCaseEntity> findById = dcCaseRepo.findById(planSelection.getCaseNum());

        if(findById.isPresent()){
            DcCaseEntity dcCaseEntity = findById.get();
            dcCaseEntity.setPlanId(planSelection.getPlanId());
            dcCaseRepo.save(dcCaseEntity);

            return dcCaseEntity.getCaseNum();
        }
        return null;
    }

    @Override
    public Long saveIncomeData(Income income) {
        DcIncomeEntity incomeEntity = new DcIncomeEntity();
        BeanUtils.copyProperties(income, incomeEntity);
        incomeRepo.save(incomeEntity);

        return incomeEntity.getCaseNum();
    }

    @Override
    public Long saveEducation(Education education) {
        DcEducationEntity educationEntity = new DcEducationEntity();
        BeanUtils.copyProperties(education, educationEntity);
        educationRepo.save(educationEntity);

        return educationEntity.getCaseNum();
    }

    @Override
    public Long saveChildren(ChildRequest request) {

        List<Child> childs = request.getChilds();
        Long caseNum = request.getCaseNum();

        for(Child child : childs){
            DcChildrenEntity childrenEntity = new DcChildrenEntity();
            BeanUtils.copyProperties(child, childrenEntity);
            childrenEntity.setCaseNum(caseNum);
            childrenRepo.save(childrenEntity);
        }
        return request.getCaseNum();
    }

    @Override
    public DcSummary getSummary(Long caseNumber) {

        String planName = "";
        DcIncomeEntity incomeEntity = incomeRepo.findByCaseNum(caseNumber);
        DcEducationEntity educationEntity = educationRepo.findByCaseNum(caseNumber);
        List<DcChildrenEntity> childrenEntities = childrenRepo.findByCaseNum(caseNumber);

        Optional<DcCaseEntity> dcCase = dcCaseRepo.findById(caseNumber);
        if(dcCase.isPresent()){
            Integer planId = dcCase.get().getPlanId();
            Optional<PlanEntity> plan = planRepository.findById(planId);
            if(plan.isPresent()){
                planName = plan.get().getPlanName();
            }
        }

        //set the data to summary obj

        DcSummary summary = new DcSummary();
        summary.setPlanName(planName);

        Income income = new Income();
        BeanUtils.copyProperties(incomeEntity, income);
        summary.setIncome(income);

        Education education = new Education();
        BeanUtils.copyProperties(educationEntity, education);
        summary.setEducation(education);

        List<Child> listChild = new ArrayList<>();
        for(DcChildrenEntity entity: childrenEntities){
            Child ch = new Child();
            BeanUtils.copyProperties(entity, ch);
            listChild.add(ch);
        }
        summary.setChildren(listChild);
        return summary;
    }
}

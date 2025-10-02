package com.motaamneh.patientservice.service;


import com.motaamneh.patientservice.dto.PatientRequestDTO;
import com.motaamneh.patientservice.dto.PatientResponseDTO;
import com.motaamneh.patientservice.mapper.PatientMapper;
import com.motaamneh.patientservice.model.Patient;
import com.motaamneh.patientservice.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDTO>  getPatients(){
        List<Patient> patients = patientRepository.findAll();
        List<PatientResponseDTO> patientResponseDTOs = patients.stream()
                .map(PatientMapper::toDTO
                ).toList();
        return patientResponseDTOs;
    }
    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
        Patient newPatient = patientRepository.save(
                PatientMapper.toModel(patientRequestDTO)
        );
        return PatientMapper.toDTO(newPatient);

    }



}

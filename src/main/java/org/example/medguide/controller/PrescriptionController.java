package org.example.medguide.controller;

import lombok.RequiredArgsConstructor;
import org.example.medguide.dto.PrescriptionCreationDto;
import org.example.medguide.model.Disease;
import org.example.medguide.model.Medication;
import org.example.medguide.model.PrescribedMedication;
import org.example.medguide.model.Prescription;
import org.example.medguide.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PrescriptionController {
    private final PrescriptionService prescriptionService;
    private final DiseaseService diseaseService;

    @GetMapping("/prescriptions")
    public String showPrescriptions(Model model) {
        List<Prescription> allPrescriptions = prescriptionService.getPrescriptions();
        List<Prescription> prescriptionsWithNonDispensedPrescribedMedications = new ArrayList<>();
        for (Prescription prescription : allPrescriptions) {
            if (prescriptionService.getNumberOfPrescribedMedicationsNonDispensed(prescription) > 0) {
                prescriptionsWithNonDispensedPrescribedMedications.add(prescription);
            }
        }
        model.addAttribute("prescriptionsWithNonDispensedMedications", prescriptionsWithNonDispensedPrescribedMedications);
        return "prescriptions/prescriptions";
    }

    @GetMapping("/prescribeTreatment")
    public String showPrescriptionForm(@RequestParam(name = "diseaseId") Long id, Model model) {
        Disease disease = diseaseService.getDiseaseById(id);
        model.addAttribute("disease", disease);
        Prescription prescription = new Prescription();
        prescription.setDiagnosis(disease.getName());
        List<PrescribedMedication> prescribedMedications = new ArrayList<>();
        for (Medication medication : disease.getMedications()) {
            PrescribedMedication prescribedMedication = new PrescribedMedication();
            prescribedMedication.setName(medication.getName());
            prescribedMedication.setActiveIngredient(medication.getActiveIngredient());
            prescribedMedications.add(prescribedMedication);
        }
        PrescriptionCreationDto prescribedMedicationsForm = getPrescriptionCreationDto(disease, prescribedMedications);
        model.addAttribute("form", prescribedMedicationsForm);
        model.addAttribute("prescribedMedications", prescribedMedications);
        model.addAttribute("prescription", prescription);
        return "prescriptions/prescription-form";
    }

    private static PrescriptionCreationDto getPrescriptionCreationDto(Disease disease, List<PrescribedMedication> prescribedMedications) {
        PrescriptionCreationDto prescribedMedicationsForm = new PrescriptionCreationDto();
        prescribedMedicationsForm.setDiagnosis(disease.getName());
        for (PrescribedMedication prescribedMedication : prescribedMedications) {
            PrescribedMedication medication = new PrescribedMedication();
            medication.setName(prescribedMedication.getName());
            medication.setActiveIngredient(prescribedMedication.getActiveIngredient());
            prescribedMedicationsForm.addPrescribedMedication(medication);
        }
        return prescribedMedicationsForm;
    }

    @PostMapping("addPrescription")
    public String addPrescription(Prescription prescription, @ModelAttribute PrescriptionCreationDto form, Model model) {
        prescription.setPrescribedMedications(form.getMedications());
        prescriptionService.addPrescription(prescription);
        model.addAttribute("prescriptions", prescriptionService.getPrescriptions());
        return "redirect:/diseases";
    }

    @GetMapping("/checkPrescription")
    public String dispenseMedicationsForPrescription(@RequestParam(name = "prescriptionId") Long id, Model model) {
        Prescription prescription = prescriptionService.findPrescriptionById(id);
        model.addAttribute("prescription", prescription);
        List<PrescribedMedication> medicationsToDispense = prescription.getPrescribedMedications();
        model.addAttribute("medicationsToDispense", medicationsToDispense);
        int numberOfNonDispensedMedications = prescriptionService.getNumberOfPrescribedButNotDispensedMedicationsForPrescription(prescription);
        model.addAttribute("numberOfNonDispensed", numberOfNonDispensedMedications);
        return "prescriptions/check-page";
    }

}

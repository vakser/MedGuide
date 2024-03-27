package org.example.medguide.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.medguide.model.DispensedMedication;
import org.example.medguide.model.MedicationInStorage;
import org.example.medguide.model.PrescribedMedication;
import org.example.medguide.model.Prescription;
import org.example.medguide.service.DispensedMedicationService;
import org.example.medguide.service.MedicationInStorageService;
import org.example.medguide.service.PrescribedMedicationService;
import org.example.medguide.service.PrescriptionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class StorageController {
    private final MedicationInStorageService medicationInStorageService;
    private final PrescribedMedicationService prescribedMedicationService;
    private final DispensedMedicationService dispensedMedicationService;
    private final PrescriptionService prescriptionService;

    @GetMapping(path = "/medicationsInStock")
    public String showMedicationsInStorage(Model model) {
        List<MedicationInStorage> medicationsInStorage = medicationInStorageService.getMedicationsInStorage();
        model.addAttribute("medicationsInStock", medicationsInStorage);
        return "medications/medications-in-stock";
    }

    @GetMapping("/createNewMedication")
    public String showAddForm(Model model) {
        model.addAttribute("medicationInStock", new MedicationInStorage());
        return "medications/medication-in-stock-form";
    }

    @PostMapping("/saveMedication")
    public String putNewMedicationInStorage(@Valid MedicationInStorage medication, RedirectAttributes redirectAttributes) {
        if (!medicationInStorageService.areNameAndActiveIngredientUnique(medication.getName(), medication.getActiveIngredient())) {
            redirectAttributes.addFlashAttribute("messageNameAndActiveIngredientNotUnique", "Medication with name " +
                    medication.getName() + " and active ingredient " + medication.getActiveIngredient() + " already exists!");
            return "redirect:/medicationsInStock";
        }
        redirectAttributes.addFlashAttribute("successMessage", "New medication " + medication.getName() + " (active ingredient - " +
                medication.getActiveIngredient() + ") has been successfully added.");
        medicationInStorageService.createOrUpdateMedicationInStorage(medication);
        return "redirect:/medicationsInStock";
    }

    @PostMapping("/increaseAmountOfMedication")
    public String increaseAmountOfMedicationInStorage(@Valid MedicationInStorage medication, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("successMessage", "Amount of medication " + medication.getName() + " (active ingredient - " +
                medication.getActiveIngredient() + ") has been successfully increased.");
        medicationInStorageService.createOrUpdateMedicationInStorage(medication);
        return "redirect:/medicationsInStock";
    }

    @GetMapping("/updateAmountForm")
    public String showUpdateAmountForm(@RequestParam(name = "medicationInStorageId") Long id, Model model) {
        MedicationInStorage medicationInStorage = medicationInStorageService.findMedicationInStorageById(id);
        model.addAttribute("medicationInStorage", medicationInStorage);
        return "medications/update-medication-amount";
    }

    @GetMapping("/dispenseForm")
    public String showDispensationForm(@RequestParam(name = "prescribedMedicationId") Long id, Model model) {
        PrescribedMedication prescribedMedication = prescribedMedicationService.findPrescribedMedicationById(id);
        model.addAttribute("prescribedMedication", prescribedMedication);
        String activeIngredient = prescribedMedication.getActiveIngredient();
        List<MedicationInStorage> medicationInStorageList = medicationInStorageService.getMedicationsInStorage();
        List<MedicationInStorage> equivalents = medicationInStorageList.stream().filter(medication -> medication.getActiveIngredient().equals(activeIngredient)).toList();
        List<MedicationInStorage> equivalentsWithSufficientAmount = equivalents.stream().filter(medication -> medication.getAmount() > prescribedMedication.getAmount()).toList();
        model.addAttribute("equivalentsWithSufficientAmount", equivalentsWithSufficientAmount);
        DispensedMedication dispensedMedication = new DispensedMedication();
        dispensedMedication.setPrescribedMedication(prescribedMedication);
        model.addAttribute("dispensedMedication", dispensedMedication);
        return "medications/dispensation-form";
    }

    @PostMapping("/dispenseMedication")
    public String dispenseMedication(DispensedMedication medication) {
        dispensedMedicationService.createDispensedMedication(medication);
        MedicationInStorage medicationInStorage = medicationInStorageService.findMedicationInStorageByName(medication.getName());
        medicationInStorage.setAmount(medicationInStorage.getAmount() - medication.getPrescribedMedication().getAmount());
        medicationInStorageService.createOrUpdateMedicationInStorage(medicationInStorage);
        PrescribedMedication prescribedMedication = prescribedMedicationService.findPrescribedMedicationById(medication.getPrescribedMedication().getId());
        prescribedMedication.setDispensed(true);
        prescribedMedicationService.createOrUpdatePrescribedMedication(prescribedMedication);
        Prescription prescription = prescriptionService.findByPrescribedMedicationId(prescribedMedication.getId());
        return "redirect:/checkPrescription?prescriptionId=" + prescription.getId();
    }

    @GetMapping("/deleteMedication")
    public String deleteMedication(@RequestParam(name = "medicationId") Long id, RedirectAttributes redirectAttributes) {
        MedicationInStorage medicationInStorage = medicationInStorageService.findMedicationInStorageById(id);
        medicationInStorageService.deleteMedicationFromStorage(id);
        redirectAttributes.addFlashAttribute("message", "Medication " + medicationInStorage.getName() + " (active ingredient - "
                + medicationInStorage.getActiveIngredient() + ") has been deleted successfully.");
        return "redirect:/medicationsInStock";
    }

}

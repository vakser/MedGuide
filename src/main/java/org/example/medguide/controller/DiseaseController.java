package org.example.medguide.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.medguide.model.Disease;
import org.example.medguide.service.DiagnosticProcedureService;
import org.example.medguide.service.DiseaseService;
import org.example.medguide.service.MedicationService;
import org.example.medguide.service.SignService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class DiseaseController {
    private final DiseaseService diseaseService;
    private final SignService signService;
    private final DiagnosticProcedureService diagnosticProcedureService;
    private final MedicationService medicationService;

    @GetMapping(path = "/diseases")
    public String showDiseases(Model model) {
        List<Disease> diseases = diseaseService.getDiseases();
        model.addAttribute("diseases", diseases);
        return "diseases/diseases";
    }

    @GetMapping("/createDisease")
    public String showDiseaseForm(Model model) {
        model.addAttribute("disease", new Disease());
        model.addAttribute("allSigns", signService.getSigns());
        model.addAttribute("allDiagnosticProcedures", diagnosticProcedureService.getDiagnosticProcedures());
        model.addAttribute("allMedications", medicationService.getMedications());
        return "diseases/disease-form";
    }

    @PostMapping("/addDisease")
    public String addDisease(@Valid Disease disease, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("allSigns", signService.getSigns());
            model.addAttribute("allDiagnosticProcedures", diagnosticProcedureService.getDiagnosticProcedures());
            model.addAttribute("allMedications", medicationService.getMedications());
            return "diseases/disease-form";
        }
        if (!diseaseService.isNameUnique(disease.getName())) {
            redirectAttributes.addFlashAttribute("messageNameNotUnique", "Disease with name " + disease.getName() + " already exists!");
            return "redirect:/diseases";
        }
        diseaseService.addDisease(disease);
        redirectAttributes.addFlashAttribute("successMsgAdd", "New disease " + disease.getName() +
                " has been successfully added.");
        return "redirect:/diseases";
    }

    @GetMapping("/deleteDisease")
    public String deleteDisease(@RequestParam("diseaseId") Long id, RedirectAttributes redirectAttributes) {
        Disease disease = diseaseService.getDiseaseById(id);
        diseaseService.deleteDisease(id);
        redirectAttributes.addFlashAttribute("message", "Disease " + disease.getName() + " has been deleted successfully.");
        return "redirect:/diseases";
    }

}

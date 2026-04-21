package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Prenda;
import com.example.demo.repository.PrendaRepository;

@Controller
public class RopaController {

    @Autowired
    private PrendaRepository repository;

    /**
     * Calcula las estadísticas evitando el "Unboxing possibly null value"
     * asignando los valores a variables locales dentro del stream.
     */
    private void cargarEstadisticas(Model model, List<Prenda> lista) {
        // Cálculo de unidades totales
        int totalUnidades = lista.stream()
                .mapToInt(p -> {
                    Integer cant = p.getCantidad();
                    return (cant != null) ? cant : 0;
                })
                .sum();

        // Cálculo del valor económico total
        double valorTotal = lista.stream()
                .mapToDouble(p -> {
                    Double precio = p.getPrecio();
                    Integer cant = p.getCantidad();
                    double pEfectivo = (precio != null) ? precio : 0.0;
                    int cEfectiva = (cant != null) ? cant : 0;
                    return pEfectivo * cEfectiva;
                })
                .sum();

        model.addAttribute("totalUnidades", totalUnidades);
        model.addAttribute("valorTotal", valorTotal);
        model.addAttribute("totalModelos", lista.size());
    }

    @GetMapping("/")
    public String home(Model model) {
        cargarEstadisticas(model, repository.findAll());
        return "index";
    }

    @GetMapping("/ropa")
    public String listar(Model model) {
        List<Prenda> lista = repository.findAll();
        model.addAttribute("prendas", lista);
        model.addAttribute("prenda", new Prenda());
        cargarEstadisticas(model, lista);
        return "inventario";
    }

    @PostMapping("/ropa/guardar")
    public String guardar(@ModelAttribute Prenda prenda) {
        repository.save(prenda);
        return "redirect:/ropa";
    }

    @GetMapping("/ropa/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/ropa";
    }
}
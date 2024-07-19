package it.elijah.pizzeria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.elijah.pizzeria.model.Pizza;
import it.elijah.pizzeria.projections.PizzaApi;
import it.elijah.pizzeria.repository.PizzaRepository;

@Service
public class PizzaServiceImpl implements PizzaService {

    @Autowired
    PizzaRepository pizzaRepository;

    @Override
    public List<PizzaApi> getMultiplePizze() {
        List<PizzaApi> pizze = pizzaRepository.findAllProjectedBy();
        return pizze;
    }

    @Override
    public PizzaApi getPizza(Integer id) {
        Optional<PizzaApi> pizzaCercata = pizzaRepository.findPizzaById(id);
        if (pizzaCercata.isPresent()) {
            return pizzaCercata.get();
        } else {
            throw new IllegalArgumentException("La pizza non è stata trovata");
        }
    }

    @Override
    public PizzaApi editPizza(Integer id, Pizza bodyPizza) {
        Optional<Pizza> pizzaCercata = pizzaRepository.findById(id);
        if (pizzaCercata.isPresent()) {
            pizzaCercata.get().setNome(bodyPizza.getNome());
            pizzaCercata.get().setDescrizione(bodyPizza.getDescrizione());
            pizzaCercata.get().setFotoUrl(bodyPizza.getFotoUrl());
            pizzaCercata.get().setPrezzo(bodyPizza.getPrezzo());
            pizzaRepository.save(pizzaCercata.get());
            Optional<PizzaApi> pizzaModificata = pizzaRepository.findPizzaById(id);
            return pizzaModificata.get();
        } else {
            throw new IllegalArgumentException("La pizza da modificare non è stata trovata");
        }
    }

    @Override
    public String deletePizza(Integer id) {
        Optional<Pizza> pizzaCercata = pizzaRepository.findById(id);
        if (pizzaCercata.isPresent()) {
            pizzaRepository.delete(pizzaCercata.get());
            return String.format("La pizza %d è stata eliminata con successo.", pizzaCercata.get().getId());
        } else {
            throw new IllegalArgumentException("La pizza da modificare non è stata trovata");
        }
    }

    @Override
    public PizzaApi createPizza(Pizza pizza) {
        Pizza pizzaCreata = new Pizza();
        pizzaCreata.setNome(pizza.getNome());
        pizzaCreata.setDescrizione(pizza.getDescrizione());
        pizzaCreata.setFotoUrl(pizza.getFotoUrl());
        pizzaCreata.setPrezzo(pizza.getPrezzo());
        pizzaRepository.save(pizzaCreata);
        Optional<PizzaApi> pizzaModificata = pizzaRepository.findPizzaById(pizzaCreata.getId());
        return pizzaModificata.get();
    }

}